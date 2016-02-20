import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import uiffreader.UiffStream;

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
			System.out.println(stream.getElement());
		}
	}

	private static void info() {
		System.out.println("info for file :...");
	}
}