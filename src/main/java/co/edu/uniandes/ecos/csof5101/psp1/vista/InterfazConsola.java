/**
 * InterfazConsola.java
 * PSP Program 1
 * Copyright (c) Universidad de los Andes.
 */

package co.edu.uniandes.ecos.csof5101.psp1.vista;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.ecos.csof5101.psp1.modelo.Archivo;

/**
 * InterfazConsola.
 * 
 * @author Javier Mesa
 * @version 1.0
 * @since 04/02/2017 05:34:29 PM 2017
 */

public class InterfazConsola {
	
	//Variables de instancia
	private List<Archivo> archivoList = new ArrayList<Archivo>();
	private String directorio;
	
	/**
	 * Método que lee el directorio ingresado desde la consola.
	 * @param ruta ubicación del directorio
	 */
	public void leerDirectorio(String ruta) {
		if(null == ruta || ruta.equalsIgnoreCase("")){
			ruta = "C:\\directorioJava"; //Directorio por defecto
		}
		this.directorio = ruta;
		File directorio = new File(ruta);
		obtenerArchivos(directorio);
	}

	/**
	 * Método que obtiene los archivos que se van a contar.
	 * @param directorio ubicación de los archivos
	 */
	public void obtenerArchivos(File directorio) {
		for (File archivoIteracion : directorio.listFiles()) {
			if (archivoIteracion.isDirectory()) {
				obtenerArchivos(archivoIteracion);
			} else {
				if (archivoIteracion.getName().contains(".java")){
					Archivo archivo = new Archivo(archivoIteracion);
					this.archivoList.add(archivo);
				}
			}
		}
	}
	
	/**
	 * Método que imprime los datos obtenidos.
	 */
	public void imprimirConteo(){
		System.out.println("----------------------------------------");
		System.out.println("Directorio leido: " +this.directorio);
		System.out.println("Número Programa: " + 1);
		
		for (Archivo archivo : this.archivoList) {
			System.out.println("----------------------------------------");
			System.out.println("Nombre Parte: " + archivo.getNombreClase());
			System.out.println("Número de Items: " + archivo.getNumeroMetodos());
			System.out.println("Tamaño Parte: " +  archivo.getNumeroLineas());
		}
		System.out.println("----------------------------------------");
		System.out.println("Tamaño Total: " + obtenerTamanoTotal());
		System.out.println("----------------------------------------");
	}

	/**
	 * Método que obtiene la suma total de todas las lineas de código del directorio.
	 * @return suma valor que contiene la sumatorio de todas las lineas
	 */
	public int obtenerTamanoTotal(){
		int suma = 0;
		for (Archivo archivo : archivoList) {
			suma+= archivo.getNumeroLineas();
		}
		return suma;
	}
}
