package utils;

import java.util.ArrayList;
import java.util.List;

import dto.Producto;

public class ProductoManagement {

	public static List<Producto> listaProductos = new ArrayList<Producto>();

	public static void addProducto(Producto p) {

		if (p != null) {
			listaProductos.add(p);
		}

	}

	public static void eliminaProducto(String nombreEliminar) {
		for (int i = 0; i < listaProductos.size(); i++) {
			if (listaProductos.get(i).getNombre().equalsIgnoreCase(nombreEliminar)) {
				listaProductos.remove(i);
			}
		}
	}
}
