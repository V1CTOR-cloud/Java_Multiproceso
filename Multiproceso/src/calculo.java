import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;

public class calculo {
	
	public static void calculoNEO(String n1, String n2, String n3) throws IOException, InterruptedException{
		double posicionNEO = Double.parseDouble(n2);
		double velocidadNEO = Double.parseDouble(n3);		
		double posicionTierra = 1;
		double velocidadTierra = 100;
		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
		posicionNEO = posicionNEO + velocidadNEO * i;
		posicionTierra = posicionTierra + velocidadTierra * i;
		}
		double resultado = 100 * Math.random() *
		Math.pow( ((posicionNEO-posicionTierra)/(posicionNEO+posicionTierra)), 2);
		guardarNEO(n1, resultado);
		resultado = Math.round(resultado * 100.0) / 100.0;
		Thread.sleep(500);		
		
		if(resultado > 10) {
			System.err.println("\n\n-------------¡ALERTA MUNDIAL!--------------");
			System.err.println("Nombre\t" + n1);
			System.err.println("Percentatje de perill\t" + resultado + "%");
			System.err.println("-------------------------------------------");
		}else {
			System.out.println("\n\n-------------  TOT ESTÁ BÉ  --------------");
			System.out.println("Nombre\t" + n1);
			System.out.println("Percentatje de perill\t" + resultado + "%");
			System.out.println("-------------------------------------------");
		}		
	}

	public static void main(String[] args) throws IOException, InterruptedException{
		calculoNEO(args[0], args[1], args[2]);
	}
	
	public static void guardarNEO(String nombre, double porciento) throws IOException {
		File directorio = new File(System.getProperty("user.dir") + File.separator + "ProbabilidadesNEO");
		directorio.mkdirs();
		File f = new File(System.getProperty("user.dir") + File.separator +
				"ProbabilidadesNEO" + File.separator + nombre + ".txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));		
		bw.write("Probabilidad de colisión:\t" + String.valueOf(porciento) + "%");
		bw.close();		
	}
}
