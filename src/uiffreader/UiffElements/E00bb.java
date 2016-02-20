package uiffreader.UiffElements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uiffreader.UiffStream;

/**
 * image header. Maybe used for other things as well?
 * 
 * @author W.Pasman
 *
 */
public class E00bb extends AbstractLinkedElement {
	List<Integer> headercontent = new ArrayList<Integer>();

	int[] image = new int[0];// takes less memory than ArrayList... Already
								// takes ~4 times the expected size...

	@Override
	public String getHeader() {
		return "00bb";
	}

	@Override
	void readContents(UiffStream stream, long subsize) throws IOException {
		UiffStream substream = stream.substream(subsize, getHeader());
		for (int n = 0; n < 12; n++) {
			headercontent.add((int) substream.getInt());
		}
		int size = getSize();
		image = new int[size];
		for (int n = 0; n < size / 4; n++) {
			image[n] = (int) substream.getInt();
		}
		substream.close();
	}

	private int getSize() {
		return headercontent.get(3);
	}

	@Override
	public Object getContent() {
		String content = "image[" + getSize() + "b]";
		if (next() != null) {
			content += "," + next().getContent();
		}
		return content;
	}

}
