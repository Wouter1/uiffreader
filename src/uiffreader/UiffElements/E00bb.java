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

	@Override
	public String getHeader() {
		return "00bb";
	}

	@Override
	void readContents(UiffStream stream, long subsize) throws IOException {
		ImageData data = new ImageData();
		UiffStream substream = stream.substream(subsize, getHeader());

		List<Integer> header = new ArrayList<Integer>();
		for (int n = 0; n < 12; n++) {
			header.add(((int) substream.getInt()));
		}
		data.setHeader(header);
		int size = data.getSize();
		int[] image = new int[size / 4];
		for (int n = 0; n < size / 4; n++) {
			image[n] = (int) substream.getInt();
		}
		data.setImage(image);
		setContent(data);
		substream.close();
	}

}
