package com.act;

import java.io.IOException;

public class CopiaFichero {

	public static void main(String[] args) {

		ProcessBuilder builder = new ProcessBuilder("xcopy", "C:\\Windows\\explorer.exe");

		try {
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
