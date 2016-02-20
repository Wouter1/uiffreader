package uiffreader.UiffElements;

import java.io.IOException;

import uiffreader.UiffStream;

/**
 * this stores a structured block of data in the uiff stream. Implenentors of
 * this are created for known header types, and they know how to decypher the
 * stream data properly.
 * <p>
 * Every element stores content, but this is implementation dependent.
 * 
 * <p>
 * The top level element of a uiff stream is {@link UIFF}.
 * 
 * Given a header string, the {@link ElementFactory} can provide the correct
 * implementation.
 * 
 * @author W.Pasman
 *
 */
public interface Element {
	/**
	 * @return the header that occurs in the file that matches this type of
	 *         object. When Element is created, the header already has been
	 *         matched
	 * 
	 */
	public String getHeader();

	/**
	 * read the contents of the object.
	 * 
	 * @param stream
	 *            input stream that points just after the header. The UiffStream
	 *            that must be read in its entirity. This should be a substream
	 *            with the correct remaining size, set by the caller. The caller
	 *            will close the stream when you're done.
	 * @return the instantiated element (so that you can chain this command).
	 * @throws IOException
	 */
	public Element read(UiffStream stream) throws IOException;

	/**
	 * @return the contents of this element. Usually an Element or
	 *         List<Integer>.
	 */
	public Object getContent();
}
