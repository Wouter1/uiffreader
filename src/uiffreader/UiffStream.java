package uiffreader;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import uiffreader.UiffElements.Element;
import uiffreader.UiffElements.ElementFactory;

/**
 * Handles an UIFF stream.
 * 
 * @author W.Pasman 19feb16
 *
 */
public abstract class UiffStream {
	private InputStream stream;
	private byte[] data = new byte[4];
	private UiffStream substream;
	private long remaining;
	private String header;

	/**
	 * constructor. Use only at top level. Use {@link #substream(int)} for
	 * sub-element parsers.
	 * 
	 * @param stream
	 *            providing a UIFF-structured file.
	 * @param length
	 *            the number of bytes to read from the stream. All bytes must be
	 *            read.
	 * @param header
	 *            the header of the object we're reading. For error mesages.
	 */
	public UiffStream(InputStream stream, long length, String header) {
		this.stream = stream;
		this.remaining = length;
		this.header = header;
	}

	public UiffStream substream(final long length, final String newheader) {
		if (substream != null) {
			throw new IllegalStateException(
					header
							+ " tried to open new substream while substream is already open for "
							+ substream.getHeader());
		}
		if (length > remaining) {
			throw new IllegalArgumentException("sub-element " + newheader
					+ " requests " + length
					+ " bytes data which is more than remaining in the "
					+ header);
		}
		substream = new UiffStream(stream, length, newheader) {
			@Override
			public void close() {
				closeSubstream(length);
			}
		};
		return substream;
	}

	/**
	 * 
	 * @param length
	 *            the number of bytes consumed in the substream. Needs to be
	 *            subtracted from the main stream as well.
	 */
	private void closeSubstream(long length) {
		if (substream.remaining() != 0) {
			System.out.println("warning: sub-element " + substream.getHeader()
					+ " did not read all data: " + substream.remaining()
					+ " remaining");
			substream.empty();
		}
		substream = null;
		remaining -= length;

	}

	/**
	 * reads all remaining bytes.
	 * 
	 * @throws IOException
	 */
	private void empty() {
		try {
			for (int i = 0; i < remaining; i++) {
				stream.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		remaining = 0;
	}

	private byte[] read() throws IOException {
		if (substream != null) {
			throw new IllegalStateException("sub-element "
					+ substream.getHeader() + " is not yet finished reading.");
		}
		if (remaining < 4) {
			throw new IOException("Element " + header
					+ " attempts to read beyond its length");
		}
		if (stream.read(data) != 4) {
			throw new IOException("Element " + header
					+ " tried to read past end of its length");
		}
		remaining -= 4;
		return data;

	}

	private String getHeader() {
		return header;
	}

	/**
	 * This should be called when the element finished reading, so that the
	 * parent can continue reading.
	 */
	public abstract void close();

	/**
	 * @return string read from the stream
	 * @throws IOException
	 */
	public String getString() throws IOException {
		return new String(read());
	}

	/**
	 * Read a 32bit value from the stream.
	 * 
	 * @return long containing unsigned int.
	 * @throws IOException
	 * @throws EOFException
	 */
	public long getInt() throws IOException {
		read();
		return (data[0] & 0xff) | ((data[1] & 0xff) << 8)
				| ((data[2] & 0xff) << 16) | ((data[3] & 0xff) << 24);
	}

	public long getLong() throws IOException {
		return getInt() | getInt() << 32;
	}

	public long remaining() {
		return remaining;
	}

	/**
	 * Check that next string in stream is given string.
	 * 
	 * @param string
	 *            the expected string.
	 * @throws IOException
	 */
	public void isNextString(String string) throws IOException {
		String header = getString();
		if (!header.equals(string)) {
			throw new IllegalArgumentException(
					"hdrl element does not contain a " + string + ", found "
							+ header);

		}

	}

	/**
	 * Get a full sub-element from the stream and consume the entire given
	 * length. The sub-element gets a separate stream that restricts reading to
	 * the given length. The type is determined by the header string that must
	 * be next in the stream. This must be a known header type.
	 * 
	 * @param size
	 *            the size of the next element. This is known from the parent's
	 *            'next' pointer.
	 * @return the next sub-element from the stream. .
	 * @throws IOException
	 */
	public Element getSubElement(long size) throws IOException {
		if (size < 4) {
			throw new IllegalArgumentException("size for element is at least 4");
		}
		// FIXME can we use geteElement here?
		String newheader = getString();
		UiffStream substr = substream(size - 4, newheader);
		Element element = ElementFactory.get(newheader).read(substr);
		substr.close();
		return element;
	}

	/**
	 * Get an element off the current stream. Not all bytes need to be consumed.
	 * The element type is determined from the header string that must be next
	 * in the stream. This must be a known header type.
	 * 
	 * @return next element on the stream.
	 * @throws IOException
	 */
	public Element getElement() throws IOException {
		String newheader = getString();
		return ElementFactory.get(newheader).read(this);

	}

}
