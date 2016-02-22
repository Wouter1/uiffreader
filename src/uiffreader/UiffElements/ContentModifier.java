package uiffreader.UiffElements;

import uiffreader.UiffStream;

/**
 * A content modifier is capable of inspecting and modifying the contents of an
 * element, before it actually is stored in the element. It can be attached to
 * an {@link UiffStream}, which will then attach it to all objects being
 * created.
 * 
 */
public interface ContentModifier {

	/**
	 * modify the contents , before it actually is stored in the element.
	 * 
	 * @param elt
	 *            the element that is receiving the content
	 * @param contents
	 *            the contents that intended to use as contents for elt
	 * @return the (possibly modified) object that will actually be placed in
	 *         the element.
	 */
	public Object modify(Element elt, Object contents);

}
