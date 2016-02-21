package uiffreader.UiffElements;

import java.io.IOException;

import uiffreader.UiffStream;

/**
 * An {@link AbstractLinkedElement} that contains an {@link Element}.
 * 
 * @author W.Pasman
 *
 */
public abstract class AbstractLinkedElementWithElement extends
		AbstractLinkedElement {

	@Override
	void readContents(UiffStream stream, long subsize) throws IOException {
		setContent(stream.getSubElement(subsize));
	}

}
