package psp.ud02.actividad203;

import psp.ud02.actividad203.utils.PropertiesProcesser;

/**
 * 
 */
public class ImageProcessingSerevice {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		PropertiesProcesser pp = new PropertiesProcesser("config.properties");
		System.out.println("Ancho: " + pp.getMaxWidth() + "\nAlto: " + pp.getMaxHeigth() + "\nSalida: "
				+ pp.getOutputFolder() + "\nEntrada: " + pp.getInputFolder());

		System.out.println(pp.getMaxHeigth());
	}

}
