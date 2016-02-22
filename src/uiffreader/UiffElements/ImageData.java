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

	@Override
	public String toString() {
		return "image[" + image.length + "bytes]";
	}
}
