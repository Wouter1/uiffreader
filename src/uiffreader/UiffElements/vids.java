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

	private final static int PALETTE_SIZE = 254;

	@Override
	public Element read(UiffStream stream) throws IOException {
		List<Integer> settings = new ArrayList<Integer>();
		List<Integer> palette = new ArrayList<Integer>();

		for (int n = 0; n < 37; n++) {
			settings.add((int) stream.getInt());
		}
		for (int n = 0; n < 5 * PALETTE_SIZE; n++) {
			palette.add((int) stream.getInt());
		}

		setContent(new videoInfo(settings, palette));
		return this;
	}
}

class videoInfo {

	private List<Integer> settings;
	private List<Integer> palette;

	public videoInfo(List<Integer> settings, List<Integer> palette) {
		this.settings = settings;
		this.palette = palette;
	}

	@Override
	public String toString() {
		return "videoInfo[" + settings + "]";
	}

}
