package api.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.google.gson.Gson;

import api.RespuestaAPI;
import models.Pelicula;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TMDBApiUtils {

	public static Pelicula[] getPeliculas(String titulo) {
		// Array de peliculas a guardar
		Pelicula[] peliculas = null;

		OkHttpClient client = new OkHttpClient();
		String encodedTitle = "";
		try {
			// Acondicionar el titulo introducido para poder insertarlo en la url
			encodedTitle = URLEncoder.encode(titulo, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// Hacer request a la api
		Request request = new Request.Builder()
				.url("https://api.themoviedb.org/3/search/movie?query="
						+ encodedTitle + "&include_adult=false&language=en-US&page=1")
				.get().addHeader("accept", "application/json")
				.addHeader("Authorization",
						"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZTVjMTgxN2U4NGRlMmMwZGU4ZDM0YmM5MTY1MWEwMCIsInN1YiI6IjY1NmVlZDEzNjUxN2Q2MDBjYzQzMWQyNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ISS3oHobccfNtISmXqBs_dPb3jOm0s9LDQdbN7t1fEQ")
				.build();
		Response response = null;
		try {
			// Obtener información de la respuesta
			response = client.newCall(request).execute();
			String respuesta = response.body().string();
			// Obtener datos del JSON generado
			Gson gson = new Gson();
			RespuestaAPI apiRes = gson.fromJson(respuesta, RespuestaAPI.class);

			for (Pelicula p : apiRes.getResults()) {
				System.out.println("ID:\n" + p.getId());
				System.out.println("Titulo original:\n" + p.getOriginal_title());
				System.out.println("Overview:\n" + p.getOverview());
				System.out.println("Release date:\n" + p.getRelease_date());
				System.out.println("Popularity\n" + p.getPopularity());
				System.out.println("Poster:\n" + p.getPoster_path());
				System.out.println("--------------------------");
			}

			peliculas = apiRes.getResults();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return peliculas;
	}

}
