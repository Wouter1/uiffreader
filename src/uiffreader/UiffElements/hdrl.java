package uiffreader.UiffElements;

import java.io.IOException;

import uiffreader.UiffStream;

public class hdrl extends AbstractElement {

	@Override
	public Element read(UiffStream stream) throws IOException {
		// we just start reading the next element right away. SHould be udih.
		setContent(stream.getElement());
		return this;
	}

	@Override
	public String getHeader() {
		return "hdrl";
	}

}
