package psp.ud02.actividad203.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageCompressor {

	private int maxWidth;
	private int maxHeight;
	private BufferedImage originalImage;

	public ImageCompressor(BufferedImage originalImage, int maxHeigth, int maxWidth) {
		this.originalImage = originalImage;
		this.maxHeight = maxHeigth;
		this.maxWidth = maxWidth;
	}

	/**
	 * 
	 * @return
	 */
	public synchronized BufferedImage resizeImage() {

		int originalWidth = originalImage.getWidth();
		int originalHeight = originalImage.getHeight();

		double ratio = getMaxRatio(originalWidth, originalHeight);

		int newWidth = (int) (originalWidth / ratio);
		int newHeight = (int) (originalHeight / ratio);

		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

		Graphics2D g2d = resizedImage.createGraphics();

		g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
		g2d.dispose();

		return resizedImage;

	}

	/**
	 * 
	 * @param originalW
	 * @param originalH
	 * @return
	 */
	private synchronized double getMaxRatio(double originalW, double originalH) {

		double widthRatio = originalW / maxWidth;
		double heightRatio = originalH / maxHeight;

		return Math.max(widthRatio, heightRatio);

	}

}
