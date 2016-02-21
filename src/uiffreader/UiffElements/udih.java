package uiffreader.UiffElements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uiffreader.UiffStream;

/**
 * Contains #images in the file. Maybe contains an UDI too?
 * 
 * @author W.Pasman
 *
 */
public class udih extends AbstractLinkedElement {

	@Override
	public String getHeader() {
		return "udih";
	}

	@Override
	void readContents(UiffStream stream, long subsize) throws IOException {
		List<Long> data = new ArrayList<Long>();
		for (int n = 0; n < subsize / 4; n++) {
			data.add(stream.getInt());
		}
		setContent(data);
	}

}
