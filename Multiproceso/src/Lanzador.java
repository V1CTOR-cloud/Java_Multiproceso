import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Lanzador {
	static int tope = 0;
	static int cores = Runtime.getRuntime().availableProcessors();
	static int maxcores = 1;
	private static String ficheroLectura = "C:\\Users\\víctor\\Desktop\\DAM 2\\Servicios\\Multiproceso_Evaluable\\Multiproceso\\NEOs.txt";

	public void lanzarSumador(String n1, String n2, String n3) {
		String clase = "calculo";
		try {
			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			String className = clase;

			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(n1);
			command.add(n2);
			command.add(n3);
			ProcessBuilder builder = new ProcessBuilder(command);
			Process process = builder.inheritIO().start();

			if (cores == maxcores) {
				process.waitFor();
			} else {
				maxcores++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Lanzador l = new Lanzador();
		long fin, time;
		long init = System.currentTimeMillis();
		try {
			File f = new File(ficheroLectura);
			FileReader fr = new FileReader(f);
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				if (cores >= maxcores) {
					String linea = sc.nextLine();
					String[] elementos = linea.split(",");
					l.lanzarSumador(elementos[0], elementos[1], elementos[2]);
					tope++;
				}
				maxcores = 1;
			}
			fr.close();
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		fin = System.currentTimeMillis();
		try {
			Thread.sleep(500);
			time = fin - init;
			System.out.println("Temps de ejecucio total:\t" + time + " Segundos");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
