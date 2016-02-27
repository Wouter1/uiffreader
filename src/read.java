import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import uiffreader.UiffStream;
import uiffreader.UiffElements.ContentModifier;
import uiffreader.UiffElements.E00bb;
import uiffreader.UiffElements.Element;
import uiffreader.UiffElements.ElementFactory;
import uiffreader.UiffElements.ImageData;
import uiffreader.UiffElements.videoInfo;
import uiffreader.UiffElements.vids;

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

	public read(File source, File targetdir) throws IOException {

		ContentModifier contentModifier = new MyContentModifier(targetdir);

		ElementFactory elementFactory = new ElementFactory(contentModifier);

		UiffStream stream = new UiffStream(new FileInputStream(source),
				source.length(), "root", elementFactory) {
			@Override
			public void close() {
				System.out.println("reading complete");
			}

		};
		System.out.println(stream.getElement());

	}

	/**
	 * Tracks the parsing, interceppts images, dumps them to file and then
	 * erases the image to save memory.
	 * 
	 * @author W.Pasman
	 *
	 */
	class MyContentModifier implements ContentModifier {

		private videoInfo info;
		private ImagesWriter imagesWriter;

		public MyContentModifier(File targetdir) {
			if (targetdir.exists()) {
				throw new IllegalArgumentException("target dir " + targetdir
						+ " already exists");
			}
			targetdir.mkdir();

			imagesWriter = new ImagesWriter(targetdir);
		}

		@Override
		public Object modify(Element elt, Object contents) {
			try {
				return modify1(elt, contents);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return contents;
		}

		public Object modify1(Element elt, Object contents) throws IOException {
			if (elt instanceof vids) {
				info = (videoInfo) contents;
			} else if (elt instanceof E00bb) {
				ImageData data = (ImageData) contents;
				System.out.println("found image!" + data.getSize());
				imagesWriter.write(data.getImage(), info);
				// remove actual image data as modification (saves memory)
				// data.setImage(null);
			}
			return contents;
		}

	}

	public static void main(String[] args) throws IOException,
			InstantiationException, IllegalAccessException {
		if (args.length != 1) {
			System.out.println("usage: split <filename>");
			return;
		}
		if (args[0].equals("-info")) {
			info();
			return;
		}
		File destination = new File("images");
		File file = new File(args[0]);
		new read(file, destination);
	}

	private static void info() {
		System.out.println("info for file :...");
	}
}