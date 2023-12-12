package main;

import java.util.Scanner;

import api.utils.TMDBApiUtils;
import models.Pelicula;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		int posicionPeli = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el titulo de la pelicula a buscar: ");
		String titulo = sc.nextLine();

		Pelicula[] pelis = TMDBApiUtils.getPeliculas(titulo);

		System.out.println("PRIMER RESULTADO:");
		showPelicula(pelis, posicionPeli);
		int opcion = 0;
		do {
			System.out.println("1) Siguiente\n2) Anterior\n3) Salir");
			opcion = Integer.parseInt(sc.nextLine());
			if (opcion == 1) {
				if (posicionPeli < pelis.length - 1) {
					posicionPeli++;
					showPelicula(pelis, posicionPeli);
				} else {
					System.err.println("No hay más peliculas.");
				}
			} else if (opcion == 2) {
				if (posicionPeli > 0) {
					posicionPeli--;
					showPelicula(pelis, posicionPeli);
				} else {
					System.err.println("No hay pelicula anterior");
				}
			}
		} while (opcion != 3);

	}

	/**
	 * @param pelis
	 */
	private static void showPelicula(Pelicula[] pelis, int pos) {
		System.out.println("ID:\n" + pelis[pos].getId());
		System.out.println("Titulo original:\n" + pelis[pos].getOriginal_title());
		System.out.println("Overview:\n" + pelis[pos].getOverview());
		System.out.println("Release date:\n" + pelis[pos].getRelease_date());
		System.out.println("Popularity\n" + pelis[pos].getPopularity());
		System.out.println("Poster:\n" + pelis[pos].getPoster_path());
		System.out.println("--------------------------");
	}
}
