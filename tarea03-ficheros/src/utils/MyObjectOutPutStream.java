package utils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutPutStream extends ObjectOutputStream {

	public MyObjectOutPutStream() throws IOException, SecurityException {
		super();
	}

	public MyObjectOutPutStream(OutputStream out) throws IOException {
		super(out);
	}

	@Override
	public void writeStreamHeader() throws IOException {
		// No hacer nada, para que no escriba la cabecera.
	}

}
