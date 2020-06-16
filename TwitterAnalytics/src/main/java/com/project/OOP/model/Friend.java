package com.project.OOP.model;

/**
 * 
 * classe che identifica l'oggetto friend con i relativi attributi
 * @param name Stringa contenente il nome del follower
 * @param friends_count contiente il numero di amici che ha quel determinato follower 
 */

public class Friend {
	
	private String name;
	private int followers_count;
	
	/**
     * Costruttore della classe che inizializza l'oggetto.
     */
	public Friend(String name, int followers_count) {
		super();
		this.name = name;
		this.followers_count = followers_count;
	}
	
	public Friend() {
		this.name = null;
		this.followers_count = 0;
	}
	
	/**
	 * @return metodo che restituisce il nome di un friend
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * metodo che permette di impostare un nome
	 * @param name per impostare il nome di un friend
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return metodo che restituisce i followers di un friend
	 */
	public int getFollowers_count() {
		return followers_count;
	}

	/**
	 * metodo che permette di impostare i follower
	 * @param name per impostare i follower di un friend
	 */
	public void setFollowers_count(int followers_count) {
		this.followers_count = followers_count;
	}

	
	
	
	

}
