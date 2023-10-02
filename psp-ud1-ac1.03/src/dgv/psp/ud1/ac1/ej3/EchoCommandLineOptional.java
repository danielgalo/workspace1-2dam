package dgv.psp.ud1.ac1.ej3;

import java.util.ArrayList;
import java.util.List;

public class EchoCommandLineOptional {

	public static void main(String[] args) {
		
		List<String> argList = new ArrayList<>();
		
		if (args.length == 0) {
			
			argList.add("parametro1");
			argList.add("parametro2");
			
		} else if (args.length == 1) {
			
			argList.add("parametro1");
			
		} else {
			
			for (String arg: args) {
				argList.add(arg);
			}
			
		}
		
		for (int i = 0; i < argList.size(); i++) {
			System.out.println("Parámetro " + (i + 1) + ": " + argList.get(i));
		}
		
	}

}
