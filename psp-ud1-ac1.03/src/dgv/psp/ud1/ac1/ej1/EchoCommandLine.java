package dgv.psp.ud1.ac1.ej1;

public class EchoCommandLine {

	public static void main(String[] args) {

		int paramCount = 0;
		
		for (String param: args) {
			paramCount++;
			System.out.println("Parameter " + paramCount + " = " + param);
		}
		
	}

}
