package prueba;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url("https://hummingbirdv1.p.rapidapi.com/anime/steins-gate").get()
				.addHeader("X-RapidAPI-Key", "4e1a5bb75cmshb3c2c39e63786b2p106186jsnec5dac4fdc31")
				.addHeader("X-RapidAPI-Host", "hummingbirdv1.p.rapidapi.com").build();

		try {
			Response response = client.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
