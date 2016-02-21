package uiffreader.UiffElements;

import java.io.IOException;

import uiffreader.UiffStream;

/**
 * UIFF is ambiguous header. The first one occurs in the head and there it has
 * an UDI inside. However it occurs further in the file and there it has no
 * proper header string anymore but only zeros.
 * 
 * @author W.Pasman
 *
 */
public class UIFF extends AbstractLinkedElement {

	@Override
	public String getHeader() {
		return "UIFF";
	}

	@Override
	void readContents(UiffStream stream, long subsize) throws IOException {
		if (stream.remaining() < 10) {
			setContent("");
			return;
		}
		String header = stream.getString();
		if (header.equals("UDI ")) {
			UiffStream substream = stream.substream(subsize - 4, "UDI");
			setContent(new UDI().read(substream));
			substream.close();
		} else {
			UiffStream substream = stream.substream(subsize - 4,
					"unknown block");
			setContent("unparsed, size=" + substream.remaining());
			substream.close();
		}
	}

}
