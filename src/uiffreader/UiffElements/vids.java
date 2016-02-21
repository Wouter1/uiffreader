package uiffreader.UiffElements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uiffreader.UiffStream;

public class vids extends AbstractElement {

	@Override
	public String getHeader() {
		return "vids";
	}

	@Override
	public Element read(UiffStream stream) throws IOException {
		// create locally, and pass to setContent.
		// This allows us to decide not to store the content.
		List<Integer> settings = new ArrayList<Integer>();
		List<Integer> palette = new ArrayList<Integer>();
		List<List<Integer>> data = new ArrayList<List<Integer>>();
		data.add(settings);
		data.add(palette);

		for (int n = 0; n < 36; n++) {
			settings.add((int) stream.getInt());
		}
		long palettesize = stream.getInt();
		for (int n = 0; n < palettesize; n++) {
			palette.add((int) stream.getInt());
		}

		setContent(data);
		return this;
	}
}
