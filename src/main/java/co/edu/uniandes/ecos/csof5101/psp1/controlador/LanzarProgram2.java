/**
 * LanzarProgram2.java
 * PSP Program 2
 * Copyright (c) Universidad de los Andes.
 */
package co.edu.uniandes.ecos.csof5101.psp1.controlador;

import co.edu.uniandes.ecos.csof5101.psp1.vista.InterfazConsola;

/**
 * LanzarProgram2.
 * 
 * @author Javier Mesa
 * @version 1.0
 * @since 18/02/2017 04:58:29 PM 2017
 */
public class LanzarProgram2 {

	/**
	 * Inicia la aplicación.
	 * @param args argumentos de entrada de la aplicación
	 */
	public static void main(String[] args) {
		InterfazConsola face = new InterfazConsola();
		String ruta = "";
		for (String string : args) {
			ruta += string;
		}
		try {
			face.leerDirectorio(ruta);
			face.imprimirConteo();
		} catch (NullPointerException e) {
			System.out.println("No existe el directorio: " + ruta);
			System.out.println("Cree el directorio de codigo fuente en C:\\directorioJava");
		}
	}
}
