package uiffreader.UiffElements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uiffreader.UiffStream;

public class vids extends AbstractElement {

	private List<Integer> content = new ArrayList<Integer>();
	private List<Integer> palette = new ArrayList<Integer>();

	@Override
	public String getHeader() {
		return "vids";
	}

	@Override
	public Object getContent() {
		return content;
	}

	@Override
	public Element read(UiffStream stream) throws IOException {
		for (int n = 0; n < 36; n++) {
			content.add((int) stream.getInt());
		}
		long palettesize = stream.getInt();
		for (int n = 0; n < palettesize; n++) {
			palette.add((int) stream.getInt());
		}
		return this;
	}
}
