package uiffreader.UiffElements;

public interface LinkedElement extends Element {
	/**
	 * @return the next element in the chain, or null if this is the last
	 *         element in the list. Notice that in the uiff file, there is
	 *         always a "next" but it may point to some element that is part of
	 *         a parent list, or to some point way out of the file. These seem
	 *         just the way of uiff to indicate the end of the list.
	 */
	public Element next();
}
