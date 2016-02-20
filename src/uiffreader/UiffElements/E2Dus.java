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

	private List<Integer> content = new ArrayList<Integer>();

	@Override
	public String getHeader() {
		return "2Dus";
	}

	@Override
	public Element read(UiffStream stream) throws IOException {
		while (stream.remaining() > 0) {
			content.add((int) stream.getInt());
		}
		return this;
	}

	@Override
	public Object getContent() {
		return content;
	}

}
