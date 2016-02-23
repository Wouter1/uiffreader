import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import uiffreader.UiffElements.videoInfo;

/**
 * Writes a sequence of images, using a basename and a counter.
 * 
 * @author W.Pasman
 *
 */
public class ImagesWriter {

	private File dir;
	int n = 1;

	public ImagesWriter(File directory) {
		this.dir = directory;
	}

	public void write(byte[] image, videoInfo info) throws IOException {
		if (image.length != info.size()) {
			throw new IllegalArgumentException(
					"actual size of image does not match image info");
		}

		BufferedImage bufferedImage = intensityArrayToBufferedImage(image,
				info.width(), info.height());
		File outputfile = new File(dir, "saved" + String.format("%04d", n++)
				+ ".png");
		ImageIO.write(bufferedImage, "png", outputfile);
	}

	/**
	 * http://www.programcreek.com/java-api-examples/java.awt.image.Raster
	 * 
	 * @param array
	 * @param w
	 * @param h
	 * @return image from the array.
	 */
	public static BufferedImage intensityArrayToBufferedImage(byte[] array,
			int w, int h) {
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		int[] nBits = { 8 };
		ColorModel cm = new ComponentColorModel(cs, nBits, false, true,
				Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
		SampleModel sm = cm.createCompatibleSampleModel(w, h);
		DataBufferByte db = new DataBufferByte(array, w * h);
		WritableRaster raster = Raster.createWritableRaster(sm, db, null);
		BufferedImage bm = new BufferedImage(cm, raster, false, null);
		return bm;
	}

}
