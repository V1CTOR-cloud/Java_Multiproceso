import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Lanzador {
	
	private static int cores = Runtime.getRuntime().availableProcessors();
	private static String ficheroLectura = "C:\\Users\\víctor\\Desktop\\DAM 2\\Servicios\\Multiproceso_Evaluable\\Multiproceso\\NEOs.txt";
	
	public void lanzarSumador(String n1,String n2, String n3){
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		Lanzador l = new Lanzador(); 
		try {
			File f = new File(ficheroLectura);
			FileReader fr = new FileReader(f);
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {	
				String linea = sc.nextLine();
				String[] elementos = linea.split(",");
				l.lanzarSumador(elementos[0], elementos[1],elementos[2]);
			}
			fr.close();
			sc.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
