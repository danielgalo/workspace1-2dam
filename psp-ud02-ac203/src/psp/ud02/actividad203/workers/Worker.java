package psp.ud02.actividad203.workers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import psp.ud02.actividad203.utils.ImageCompressor;

public class Worker extends Thread {

	private String imagePath;
	private int maxWidth;
	private int maxHeigth;

	/**
	 * @param inputFolder
	 * @param maxWidth
	 * @param maxHeigth
	 */
	public Worker(String imagePath, int maxWidth, int maxHeigth) {
		this.imagePath = imagePath;
		this.maxWidth = maxWidth;
		this.maxHeigth = maxHeigth;
	}

	@Override
	public void run() {

		// Check if the image is JPG
		if (isJPG(imagePath)) {

			try {
				// Create the image
				File inputFile = new File(imagePath);
				BufferedImage originalImage = ImageIO.read(inputFile);
				// Check the size convertion
				ImageCompressor compressor = new ImageCompressor(originalImage, maxHeigth, maxWidth);

				ImageIO.write(compressor.resizeImage(), "jpg", new File(imagePath));

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			System.err.println("Imagen no es JPG");
		}

	}

	/**
	 * 
	 * @param image
	 * @return
	 */
	private static boolean isJPG(String image) {

		boolean isJpg = Boolean.FALSE;

		if (image.endsWith(".jpg") || image.endsWith(".jpeg")) {
			isJpg = Boolean.TRUE;
		}
		return isJpg;
	}

}
