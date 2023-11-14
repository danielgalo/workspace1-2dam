package utils;

import java.util.HashMap;
import java.util.Map;

import dao.Administrador;
import dao.Cliente;

public class PersonaManagement {

	public static Map<Cliente, String> clienteMap = new HashMap<>();
	public static Map<Administrador, String> adminMap = new HashMap<>();

	public static void addCliente(Cliente c, String password) {
		clienteMap.put(c, password);
	}

	public static void addAdmin(Administrador a, String password) {
		adminMap.put(a, password);
	}
}
