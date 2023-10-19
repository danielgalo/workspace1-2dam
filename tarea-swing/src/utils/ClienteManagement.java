package utils;

import java.util.ArrayList;
import java.util.List;

import dto.Cliente;

public class ClienteManagement {

	public static List<Cliente> listaClientes = new ArrayList<Cliente>();

	public static void addCliente(Cliente c) {

		if (c != null) {
			listaClientes.add(c);
		}

	}

	public static void eliminaClientes(String nombreEliminar, String apellidosEliminar) {
		for (int i = 0; i < listaClientes.size(); i++) {
			if (listaClientes.get(i).getNombre().equalsIgnoreCase(nombreEliminar)
					&& listaClientes.get(i).getApellidos().equalsIgnoreCase(apellidosEliminar)) {
				listaClientes.remove(i);

			}
		}

	}

}
