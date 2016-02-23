import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import uiffreader.UiffStream;
import uiffreader.UiffElements.ContentModifier;
import uiffreader.UiffElements.E00bb;
import uiffreader.UiffElements.Element;
import uiffreader.UiffElements.ElementFactory;
import uiffreader.UiffElements.ImageData;

/**
 * Example that reads uiff from file and dumps structure to stdout and modifies
 * some content. Without the content modifier, reading a 80Mb uiff file consumes
 * 450MB memory, If we remove the image contents (but keep the header) we need
 * hardly any memory (less than 10MB)
 * 
 * @author W.Pasman
 *
 */
class read {

	public static void main(String[] args) throws IOException,
			InstantiationException, IllegalAccessException {

		ContentModifier contentModifier = new ContentModifier() {
			@Override
			public Object modify(Element elt, Object contents) {
				if (elt instanceof E00bb) {
					ImageData data = (ImageData) contents;
					System.out.println("found image!" + data.getSize());
					// remove actual image data as modification (saves memory)
					data.setImage(null);
				}
				return contents;
			}
		};

		ElementFactory elementFactory = new ElementFactory(contentModifier);

		if (args.length != 1) {
			System.out.println("usage: split <filename>");
			return;
		}
		if (args[0].equals("-info")) {
			info();
		} else {
			File file = new File(args[0]);
			UiffStream stream = new UiffStream(new FileInputStream(file),
					file.length(), "root", elementFactory) {
				@Override
				public void close() {
					System.out.println("reading complete");
				}

			};
			System.out.println(stream.getElement());
		}
	}

	private static void info() {
		System.out.println("info for file :...");
	}
}