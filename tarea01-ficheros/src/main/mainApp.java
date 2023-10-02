package main;

import java.util.List;

import models.Empleado;
import utils.UtilsFicheroArray;

public class mainApp {

	public static void main(String[] args) {

		List<Empleado> lista = UtilsFicheroArray.leerEmpleados(args[0]);
		// UtilsFicheroArray.toArchivoTexto(lista);
		UtilsFicheroArray.toArchivoBinarioInverso(lista);

	}
}