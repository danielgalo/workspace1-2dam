package main;

import java.time.LocalDate;
import java.util.Map;

import utils.FileUtils;
import utils.Validation;

public class MainApp {

	public static void main(String[] args) {

		LocalDate l;

		FileUtils fu = new FileUtils("files/datos.txt");

		Map<String, String> mapa = fu.mapContent();
//
//		for (String clave : mapa.keySet()) {
//			String valor = mapa.get(clave);
//			System.out.println("Clave: " + clave + " | Valor: " + valor);
//		}

		System.out.println(Validation.checkFecha("31/01/2002"));

	}

}
