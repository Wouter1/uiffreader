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

	// first bytes in the element.
	private List<Long> content = new ArrayList<Long>();

	@Override
	public String getHeader() {
		return "udih";
	}

	@Override
	void readContents(UiffStream stream, long subsize) throws IOException {
		for (int n = 0; n < subsize / 4; n++) {
			content.add(stream.getInt());
		}
	}

	@Override
	public Object getContent() {
		List<Object> cont = new ArrayList<Object>();
		cont.addAll(content);
		cont.add(next().getContent());
		return cont;
	}

}
