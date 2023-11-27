package psp.ud02.actividad203;

import psp.ud02.actividad203.utils.PropertiesProcesser;

public class ImageProcessingSerevice {

	public static void main(String[] args) {

		PropertiesProcesser pp = new PropertiesProcesser("confg.properties");
		System.out.println(pp.getInputFolder());
	}

}
