package uiffreader.UiffElements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uiffreader.UiffStream;

/**
 * The 2Dus element.
 * 
 * @author W.Pasman
 *
 */
public class E2Dus extends AbstractElement {

	@Override
	public String getHeader() {
		return "2Dus";
	}

	@Override
	public Element read(UiffStream stream) throws IOException {
		List<Integer> data = new ArrayList<Integer>();
		while (stream.remaining() > 0) {
			data.add((int) stream.getInt());
		}
		setContent(data);
		return this;
	}

}
