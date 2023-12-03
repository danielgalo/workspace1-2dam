package psp.ud02.actividad203;

import psp.ud02.actividad203.utils.PropertiesProcesser;
import psp.ud02.actividad203.workers.Dispatcher;

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
<<<<<<< HEAD
		System.out.println("Ancho: " + pp.getMaxWidth() + "\nAlto: " + pp.getMaxHeigth() + "\nSalida: "
				+ pp.getOutputFolder() + "\nEntrada: " + pp.getInputFolder());

		Dispatcher d = new Dispatcher(pp.getInputFolder(), pp.getMaxHeigth(), pp.getMaxWidth());
		d.start();
=======
		System.out.println(pp.getMaxHeigth());
>>>>>>> 201fef0c670f7d87f7c32796e4b38137ae982d68
	}

}
