package utils;

import java.util.ArrayList;
import java.util.List;

import dao.Reserva;

public class ReservasManagement {

	public static List<Reserva> reservas = new ArrayList<>();

	public static void addReserva(Reserva r) {

		if (r != null) {

			reservas.add(r);

		}

	}

}
