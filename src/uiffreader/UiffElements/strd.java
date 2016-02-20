package uiffreader.UiffElements;

import java.io.IOException;

import uiffreader.UiffStream;

public class strd extends AbstractElement {
	private Element content;

	@Override
	public Element read(UiffStream stream) throws IOException {
		// we just start reading the next element right away. Should be 00ft.
		content = stream.getElement();
		return this;
	}

	@Override
	public String getHeader() {
		return "strd";
	}

	@Override
	public Object getContent() {
		return content;
	}
}
