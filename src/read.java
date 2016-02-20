import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import uiffreader.UiffStream;
import uiffreader.UiffElements.UIFF;

/**
 * Example that reads uiff from file and dumps structure to stdout
 * 
 * @author W.Pasman
 *
 */
class read {

	public static void main(String[] args) throws IOException,
			InstantiationException, IllegalAccessException {
		if (args.length != 1) {
			System.out.println("usage: split <filename>");
			return;
		}
		if (args[0].equals("-info")) {
			info();
		} else {
			File file = new File(args[0]);
			UiffStream stream = new UiffStream(new FileInputStream(file),
					file.length(), "root") {

				@Override
				public void close() {
					System.out.println("reading complete");
				}

			};
			if (!stream.getString().equals("UIFF")) {
				throw new IllegalArgumentException(
						"stream does not start with the 'UIFF' header");
			}
			System.out.println(new UIFF().read(stream));
		}
	}

	private static void info() {
		System.out.println("info for file :...");
	}
}