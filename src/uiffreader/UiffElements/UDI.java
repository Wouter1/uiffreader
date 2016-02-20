package uiffreader.UiffElements;

import java.io.IOException;

import uiffreader.UiffStream;

/**
 * UDI seems top level wrapper that does nothing
 * 
 * @author W.Pasman
 *
 */
public class UDI extends AbstractElement {

	private Element content;

	@Override
	public String getHeader() {
		return "UDI ";
	}

	@Override
	public Element read(UiffStream stream) throws IOException {
		content = stream.getElement(); // should be LIST.
		return this;
	}

	@Override
	public Object getContent() {
		return content;
	}
}
