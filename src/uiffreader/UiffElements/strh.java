package uiffreader.UiffElements;

import java.io.IOException;

import uiffreader.UiffStream;

/**
 * strh is tricky element, as it contains an "strh" header... This object is the
 * top level 'strh'.
 * 
 * @author W.Pasman
 *
 */
public class strh extends AbstractElement {

	private Element content;

	@Override
	public String getHeader() {
		return "strh";
	}

	@Override
	public Element read(UiffStream stream) throws IOException {
		stream.isNextString("strh");
		content = new strhInner().read(stream);
		return this;
	}

	@Override
	public Object getContent() {
		return content;
	}
}

/**
 * Inner first element of a strh which also has the header "strh". This element
 * should not trigger automatically as that might override the top level strh.
 * Therefore it's only an inner class at this point and not part of the
 * ElementFactory.
 * 
 * @author W.Pasman
 *
 */
class strhInner extends AbstractLinkedElementWithElement {

	@Override
	public String getHeader() {
		return "strh";
	}

}
