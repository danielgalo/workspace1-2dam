package main;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import persistence.dao.CompanyDaoImpl;
import persistence.dao.GeneroDaoImpl;
import persistence.dao.GeneroPeliculaDaoImpl;
import persistence.dao.PeliculaDaoImpl;
import persistence.entities.Company;
import persistence.entities.Genero;
import persistence.entities.GeneroPelicula;
import persistence.entities.GeneroPeliculaId;
import persistence.entities.Pelicula;
import utils.HibernateUtil;

/**
 * Clase Principal para probar DAO
 *
 */
public class DaoApp {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		try {

			// Crear e insertar compañia
			Company comp1 = new Company();
			comp1.setName("Compañía 1");

			CompanyDaoImpl compDao = new CompanyDaoImpl(session);
			compDao.insert(comp1);

			// Crear e insertar generos
			Genero gen1 = new Genero();
			gen1.setNombre("Accion");

			Genero gen2 = new Genero();
			gen2.setNombre("Drama");

			GeneroDaoImpl genDao = new GeneroDaoImpl(session);
			genDao.insert(gen1);
			genDao.insert(gen2);

			// Crear pelicula
			Pelicula peli1 = new Pelicula();
			peli1.setTitulo("Pelicula 1");
			peli1.setCompany(comp1);

			PeliculaDaoImpl peliDao = new PeliculaDaoImpl(session);
			peliDao.insert(peli1);

			// Crear relaciones
			// Peli 1 con Genero 1
			GeneroPelicula genPeli1 = new GeneroPelicula();
			genPeli1.setId(new GeneroPeliculaId(peli1, gen1));

			// Peli 1 con Genero 2
			GeneroPelicula genPeli2 = new GeneroPelicula();
			genPeli2.setId(new GeneroPeliculaId(peli1, gen2));

			// Lista de Relaciones
			List<GeneroPelicula> listGeneros = new ArrayList<>();
			listGeneros.add(genPeli2);
			listGeneros.add(genPeli1);

			// Añadir a peli 1
			peli1.setGeneroPelicula(listGeneros);

			// Insertar relaciones
			GeneroPeliculaDaoImpl genPeliDao = new GeneroPeliculaDaoImpl(session);
			genPeliDao.insert(genPeli2);
			genPeliDao.insert(genPeli1);

			HibernateUtil.closeSession();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
