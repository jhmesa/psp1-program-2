/**
 * Archivo.java
 * PSP Program 2
 * Copyright (c) Universidad de los Andes.
 */
package co.edu.uniandes.ecos.csof5101.psp1.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Archivo.
 * 
 * @author Javier Mesa
 * @version 1.0
 * @since 18/02/2017 04:58:29 PM 2017
 */

public class Archivo {

	//Variables de instancia
	private int numeroMetodos;
	private int numeroLineas;
	private String nombreClase;
	
	/**
	 * Constructor público.
	 * @param archivo objeto de tipo File que contiene el archivo para contar
	 */
	public Archivo(File archivo){
		calcularNumeroLineasYMetodos(archivo);
	}
	
	/**
	 * Método que calcula las numeros de lineas y metodos del archivo.
	 * @param archivo objeto de tipo File que contiene el archivo para contar
	 */
	public void calcularNumeroLineasYMetodos(File archivo){
		String ruta = archivo.getAbsolutePath();
		int numeroLineas=0;
		int numeroMetodos=0;
		try {
			BufferedReader br =  new BufferedReader(new FileReader(ruta));
			String linea = br.readLine();
			while (null != linea) {
				if(esLineaValida(linea)){
					setNombreClase(archivo, linea);
					++numeroLineas;
					if(esLineaDeMetodo(linea)){
						++numeroMetodos;
					}
				}
				linea = br.readLine();
			}
			br.close();
		}catch(IOException e){
			System.out.println("Hay un error obteniendo el archivo:" + ruta);
		}
		this.numeroLineas = numeroLineas;
		this.numeroMetodos = numeroMetodos;
	}

	/**
	 * Método que obtiene el nombre completo de la clase.
	 * @param archivo objeto que se esta leyendo
	 * @param linea cadena que corresponde a la linea del archivo que se está leyendo
	 */
	public void setNombreClase(File archivo, String linea){
		if(linea.matches("^package.*")){
			String nombreClase = archivo.getName().substring(0, archivo.getName().indexOf('.'));
			String paquete = linea.substring(8, linea.length()-1);
			this.nombreClase = paquete.concat("." + nombreClase);
		}
	}
	
	/**
	 * Método que determina si la linea leida es valida para ser contada.
	 * @param linea cadena que corresponde a la linea del archivo que se está leyendo
	 * @return esValida valor true si es valida
	 */
	public boolean esLineaValida(String linea){
		boolean esValida = false;
		if(!linea.isEmpty()){
			if (linea.indexOf('/') == 0){
				esValida = false;
			}else if(linea.indexOf('*') == 0){
				esValida = false;
			}else if (linea.lastIndexOf(';') == linea.length()-1){
				esValida = true;
			}else if (linea.lastIndexOf('{') == linea.length()-1){
				esValida = true;
			}else if (linea.lastIndexOf('}') == linea.length()-1){
				esValida = true;
			}else{
				esValida = false;
			}
		}
		return esValida;
	}
	
	/**
	 * Método que valida si la linea leida es un metodo.
	 * @param linea cadena que corresponde a la linea del archivo que se está leyendo
	 * @return esMetodo valor true si es un método
	 */
	public boolean esLineaDeMetodo(String linea){
		boolean esMetodo = false;
		if(linea.contains("for ") || linea.contains("while ")
				|| linea.contains("if ") || linea.contains("catch")
				|| linea.contains("catch(") || linea.contains("if(")){
			esMetodo = false;
		}else if (linea.contains("(") && linea.contains(")") 
				&& linea.indexOf('{') == linea.length()-1){
			esMetodo = true;
		}else{
			esMetodo = false;
		}
		return esMetodo;
	}

	/**
	 * Método que obtiene el atributo numeroMetodos
	 * @return el numeroMetodos
	 */
	public int getNumeroMetodos() {
		return numeroMetodos;
	}

	/**
	 * Método que obtiene el atributo numeroLineas
	 * @return numeroLineas
	 */
	public int getNumeroLineas() {
		return numeroLineas;
	}

	/**
	 * Método que obtiene el atributo nombreClase
	 * @return nombreClase
	 */
	public String getNombreClase() {
		return nombreClase;
	}
}
