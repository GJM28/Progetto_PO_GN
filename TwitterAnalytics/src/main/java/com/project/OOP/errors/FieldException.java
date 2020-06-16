package com.project.OOP.errors;


/**
 * 
 * @author Nicolò Rossini
 * @author Mcdonald Gregory James
 * classe per gestire le eccezioni del parametro field
 *
 */
public class FieldException extends Exception{

	/**
	 * Costruttore della classe 
	 * @param message messaggio che specifica l'errore che si è verificato
	 */
	public FieldException(String message) {
		super(message);
	}


	
}
