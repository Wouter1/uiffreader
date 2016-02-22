package uiffreader.UiffElements;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory utility class. Produces Uiff objects
 * 
 * @author W.Pasman
 *
 */
public class ElementFactory {
	private static Map<String, Class<? extends Element>> types = new HashMap<String, Class<? extends Element>>();

	private ContentModifier contentModifier = null;

	static {
		init();
	}

	private static void init() {
		try {
			addType(List1.class);
			addType(UIFF.class);
			addType(UDI.class);
			addType(udih.class);
			addType(hdrl.class);
			addType(strh.class);
			addType(strd.class);
			addType(E2Dus.class);
			addType(strf.class);
			addType(vids.class);
			addType(E00ft.class);
			addType(E00bb.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param modifier
	 *            the content modifier to use for all {@link Element}s. This is
	 *            part of the stream (and not of the {@link ElementFactory}) so
	 *            that you can make multiple streams, each with different
	 *            {@link ContentModifier}s.
	 */
	public ElementFactory(ContentModifier modifier) {
		this.contentModifier = modifier;
	}

	/**
	 * Add new type to list of know types.
	 * 
	 * @param type
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private static void addType(Class<? extends Element> type)
			throws InstantiationException, IllegalAccessException {
		types.put(type.newInstance().getHeader(), type);
	}

	/**
	 * Get a new instance of Element that has the given header.
	 * 
	 * @param header
	 *            the header as it occurs in the file.
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public Element get(String header) {
		if (header.length() != 4) {
			throw new IllegalArgumentException("header '" + header
					+ "' is not of length 4");
		}
		Class<? extends Element> clazz = types.get(header);
		if (clazz == null) {
			throw new IllegalArgumentException("unknown header '" + header
					+ "'");
		}
		try {
			Element element = clazz.newInstance();
			if (contentModifier != null) {
				element.setContentModifier(contentModifier);
			}
			return element;

		} catch (IllegalAccessException e) {
			throw new RuntimeException("failed to create " + clazz, e);
		} catch (InstantiationException e) {
			throw new RuntimeException("failed to create " + clazz, e);
		}
	}

}
