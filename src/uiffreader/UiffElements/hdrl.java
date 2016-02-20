package uiffreader.UiffElements;

import java.io.IOException;

import uiffreader.UiffStream;

public class hdrl extends AbstractElement {

	private Element content;

	@Override
	public Element read(UiffStream stream) throws IOException {
		// we just start reading the next element right away. SHould be udih.
		content = stream.getElement();
		return this;
	}

	@Override
	public String getHeader() {
		return "hdrl";
	}

	@Override
	public Object getContent() {
		return content;
	}

}
