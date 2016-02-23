package uiffreader.UiffElements;

import java.util.List;

public class videoInfo {

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

	/**
	 * 
	 * @return Image size (bytes)
	 */
	public int size() {
		return settings.get(34);
	}

	public int width() {
		return settings.get(33);
	}

	public int depth() {
		return settings.get(32);
	}

	public int height() {
		return settings.get(31);
	}

}
