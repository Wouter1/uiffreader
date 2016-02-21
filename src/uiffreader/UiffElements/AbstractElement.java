package uiffreader.UiffElements;

import java.io.IOException;

import uiffreader.UiffStream;

/**
 * Provides some basic implementation for Element, as well as a default read
 * function that just eats all the data.
 * 
 * @author W.Pasman
 *
 */
public abstract class AbstractElement implements Element {

	private Object content;

	@Override
	public Element read(UiffStream stream) throws IOException {
		System.out.println("warning: read not implemented for " + getHeader()
				+ ". Reading any element");

		while (stream.remaining() > 0) {
			stream.getInt();
		}
		return this;
	}

	@Override
	public String toString() {
		return getHeader() + "[" + getContent() + "]";
	}

	@Override
	public Object getContent() {
		return content;
	}

	@Override
	public void setContent(Object c) {
		content = c;
	}
}
