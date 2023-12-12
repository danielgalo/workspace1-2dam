package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.Pelicula;

/**
 * 
 */
public class TMDBApi {

	static JsonArray results;

	/**
	 * Obtiene la primera pelicula por su titulo
	 * 
	 * @param movieTitle
	 * @return
	 */
	public static Pelicula getPelicula(String movieTitle, int posicionPelicula) {
		Pelicula pelicula = null;

		try {
			String apiKey = "1e5c1817e84de2c0de8d34bc91651a00";

			// Codificar el título para incluirlo en la URL de la solicitud
			String encodedTitle = URLEncoder.encode(movieTitle, "UTF-8");

			URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + encodedTitle);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				response.append(line);
			}

			reader.close();
			connection.disconnect();

			// Utilizar GSON para parsear el JSON
			JsonObject searchResult = JsonParser.parseString(response.toString()).getAsJsonObject();
			results = searchResult.getAsJsonArray("results");

			if (results.size() > 0) {
				// Tomar el primer resultado
				JsonObject firstResult = results.get(posicionPelicula).getAsJsonObject();

				// Obtengo datos
				String title = firstResult.get("original_title").getAsString();
				String overview = firstResult.get("overview").getAsString();
				String releaseDate = firstResult.get("release_date").getAsString();
				String posterPath = firstResult.get("poster_path").getAsString();
				String urlImagen = "https://image.tmdb.org/t/p/w500" + posterPath;

				pelicula = new Pelicula(title, overview, releaseDate, urlImagen);

			} else {
				System.out.println("No se encontraron resultados para la búsqueda.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pelicula;
	}

	public static int getResultsLength() {
		return results.size();
	}

}
