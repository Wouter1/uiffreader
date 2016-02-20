package uiffreader.UiffElements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uiffreader.UiffStream;

/**
 * An {@link AbstractLinkedElement} that contains an {@link Element}.
 * 
 * @author W.Pasman
 *
 */
public abstract class AbstractLinkedElementWithElement extends
		AbstractLinkedElement {
	private Element content;

	@Override
	void readContents(UiffStream stream, long subsize) throws IOException {
		content = stream.getSubElement(subsize);
	}

	@Override
	public Object getContent() {
		List<Object> objects = new ArrayList<Object>();
		objects.add(content);
		if (next() != null) {
			objects.add(next().getContent());
		}
		return objects;
	}
}
