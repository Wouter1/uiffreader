package uiffreader.UiffElements;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the details of an 00bb image
 * 
 * @author W.Pasman
 *
 */
public class ImageData {
	List<Integer> headercontent = new ArrayList<Integer>();

	public int getSize() {
		return headercontent.get(3);
	}

	/*
	 * The actual image. takes less memory than ArrayList... Already takes ~4
	 * times the expected size...
	 */
	int[] image = new int[0];

	public void setHeader(List<Integer> header) {
		headercontent = header;

	}

	public void setImage(int[] newImage) {
		image = newImage;
	}

	/**
	 * Get the image. Convert to byte[]
	 * 
	 * @return
	 */
	public byte[] getImage() {
		byte[] b = new byte[image.length * 4];
		int j = 0;
		for (int i = 0; i < image.length; i++) {
			b[j++] = (byte) (image[i] >> 24);
			b[j++] = (byte) (image[i] >> 16);
			b[j++] = (byte) (image[i] >> 8);
			b[j++] = (byte) (image[i]);
		}

		return b;
	}

	@Override
	public String toString() {
		return "image[" + getSize() + "bytes]";
	}
}
