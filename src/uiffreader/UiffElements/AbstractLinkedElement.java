package uiffreader.UiffElements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uiffreader.UiffStream;

/**
 * An AbstractLinkedElement is an abstract class that provides a basic read
 * mechanism for linkedlists in the data. It reads the head and size of the
 * current object. It then calls {@link #readContents(UiffStream, long)} to
 * process the current object. Then it repeats (recursively) the processes with
 * the next element (in a linked list, the next element is also a linkedlist
 * type).
 * 
 * <p>
 * 
 * In the actual file we find subsizes that do not align with a word (4-byte)
 * boundary. Particularly at the end of the file, it seems alignment is off.
 * 
 * @author W.Pasman
 *
 */
public abstract class AbstractLinkedElement extends AbstractElement implements
		LinkedElement {

	private Element next;

	@Override
	public Element next() {
		return next;
	}

	@Override
	public Element read(UiffStream stream) throws IOException {
		long subsize = stream.getLong();
		readContents(stream, subsize);
		if (stream.remaining() > 0) {
			next = stream.getElement();
			if (!(next instanceof AbstractLinkedElement)) {
				System.out.println("WARNING linked elements " + this
						+ "did not terminate with linked element:" + next);
			}
		}
		return this;
	}

	/**
	 * Read the actual element contents of this linked element. You must
	 * implement this to handle the sub-contents. stream is the original stream,
	 * so you may need to create a substream of subsize. We do not make a
	 * substream yet because we don't know the correct header for a substream.
	 * 
	 * @param stream
	 * @param subsize
	 *            the size
	 * @return
	 * @throws IOException
	 */
	abstract void readContents(UiffStream stream, long subsize)
			throws IOException;

	@Override
	public Object getContent() {
		List<Object> objects = new ArrayList<Object>();
		objects.add(super.getContent());
		if (next() != null) {
			objects.add(next().getContent());
		}
		return objects;
	}

}
