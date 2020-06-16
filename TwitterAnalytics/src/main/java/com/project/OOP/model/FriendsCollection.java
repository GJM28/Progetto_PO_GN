package com.project.OOP.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import com.project.OOP.errors.CommandNotFoundException;
import com.project.OOP.errors.FieldException;
import com.project.OOP.utils.Filter;
import com.project.OOP.utils.FilterUtils;


/**
 *@author Nicolò Rossini
 *@author Mcdonald Gregory James
 * classe che raggruppa tutti gli oggetti friends in una collection in modo da poterli analizzare più facilmente
 * @param friends, è un arraylist di tipo Friend che serve per contenere tutti gli oggetti
 * @param utils, attributo che permette di applicare dei filtri alla collezione dei dati
 */

public class FriendsCollection implements Filter<Friend, Object[]> {
	
	private ArrayList<Friend> friends;
	private FilterUtils<Friend> utils;
	
	/** 
	 * costruttore per inizializzare il l'oggetto
	 */
	public FriendsCollection(ArrayList<Friend> friends, FilterUtils<Friend> utils) {
		super();
		this.friends = friends;
		this.utils = utils;
	}
	
	/** 
	 * costruttore per inizializzare il l'oggetto
	 */
	public FriendsCollection(ArrayList<Friend> friends) {
        this.friends = friends;
        this.utils = new FilterUtils<Friend>();
    }
   
	/**
	 * metodo per collezionare tutti i friends di un utente
	 * @return restituisce la colleizone dei friends
	 */
	public ArrayList<Friend> getFriends() {
		return friends;
	}

	/**
     * Metodo capace di settare una nuova collezione di Friends per la classe
     * @param friends oggetti da inserire
     */
	public void setFriends(ArrayList<Friend> friends) {
		this.friends = friends;
	}

	public FilterUtils<Friend> getUtils() {
		return utils;
	}

	public void setUtils(FilterUtils<Friend> utils) {
		this.utils = utils;
	}

	/**
     * Metodo che calcola il numero medio di follower dei friends
     * @return Restituisce la media
     */
    public float getAvg() {
        int count = 0;
        float sum = 0;
        for (Friend val : friends) {
            sum += val.getFollowers_count();
            count++;
        }
        return sum/count;
    }
    
    /**
     * Metodo che calcola il minimo
     * @return restituisce la persona(friends) che ha meno follower 
     */

    public Map<String, Object> getMin() {
        int Min = friends.get(0).getFollowers_count();
        HashSet<String> str = new HashSet<>();
        str.add(friends.get(0).getName());
        for (int i = 1; i < friends.size(); i++) 
        {
            if(friends.get(i).getFollowers_count() == Min)
            {
                str.add(friends.get(i).getName());
            }
            else if(friends.get(i).getFollowers_count() < Min)
            {
                str.clear();
                Min = friends.get(i).getFollowers_count();
                str.add(friends.get(i).getName());
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("Name", str);
        result.put("Followers", Min);
        return result;
    }
    
    /**
     * Metodo che calcola il massimo
     * @return restituisce la persona(friends) che ha più follower. se ci sono due o più persone che hanno lo stesso numero di follower allora restituisce l'insieme delle persone 
     */

    public Map<String, Object> getMax() {
        int Max = friends.get(0).getFollowers_count();
        HashSet<String> str = new HashSet<>();
        str.add(friends.get(0).getName());
        for (int i = 1; i < friends.size(); i++) 
        {
            if(friends.get(i).getFollowers_count() == Max)
            {
                str.add(friends.get(i).getName());
            }
            else if(friends.get(i).getFollowers_count() > Max) // se l'elemento che si sta considerando è maggiore del massimo allora pulisce l'hashset e inserisce l'elemento
            {
                str.clear();  
                Max = friends.get(i).getFollowers_count();
                str.add(friends.get(i).getName());
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("Name", str);
        result.put("Followers", Max);
        return result;
    }
   
    /**
     * metodo che permette di visualizzare le classifiche dei friends che hanno più followers
     * @param field parametro che permette di scegliere quale classifica si vuole vedere
     * @return restituisce la classifica scelta
     * @throws FieldException eccezzione per parametro field errato
     */
    
    public Map<String, Object> Topranking(String field) throws FieldException{
       
    	TreeMap<Integer, String> arrsort = new TreeMap<>();  
        Map<String, Object> result = new HashMap<>();
        TreeMap<Integer, String> rank1;
        
        for (int i = 0; i < friends.size(); i++) {
		      arrsort.put(friends.get(i).getFollowers_count(), friends.get(i).getName());
	    }
        
        if(field != null) {
        	
		        if(field.equals("Top10")) {
		        	rank1 = arrsort.descendingMap().entrySet().stream().limit(10).collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);  
		        	result.put("Result", rank1.descendingMap());
		        }
		        else {
	        		throw new FieldException("campo field errato");
	        	}
		        
		        return result;
	    }
        else {
        	throw new FieldException("campo field nullo");
        }		
    }
    
    /**
     * metodo che permette di visualizzare le classifiche dei friends che hanno meno followers
     * @param field parametro che permette di scegliere quale classifica si vuole vedere
     * @return restituisce la classifica scelta
     * @throws FieldException eccezzione per parametro field errato
     */    
    public Map<String, Object> Lastranking(String field) throws FieldException{
    	TreeMap<Integer, String> arrsort = new TreeMap<>();  
        Map<String, Object> result = new HashMap<>();
        
        for (int i = 0; i < friends.size(); i++) {
		      arrsort.put(friends.get(i).getFollowers_count(), friends.get(i).getName());
	    }
        
        if(field != null) {
	        if(field.equals("Last10")) {
	         	
			    result.put("Result", arrsort.entrySet().stream()
			    									   .limit(10)
			    									   .collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll));
	        }
	        else {
	    		throw new FieldException("campo field errato");
	    	}
	        
	        return result;
        }
        else {
        	throw new FieldException("campo field nullo");
        }      
    }
    
    /**
     * metodo che permette di fare la somma di tutti i followers dei fried
     * @return restituisce la somma di tutti i followers 
     */
    public int getSum() {
    	int sum = 0;
    	for(Friend val: friends) {
    		sum += val.getFollowers_count();
    	}
    	return sum;
    }

    /**
     * Metodo per il calcolo della deviazione standard
     * @return Restituisce la deviazione standard
     */
    public float getDevStandard() {
        float avg = getAvg();
        int count = 0;
        float sum = 0;
        for (Friend val : friends) {
            sum += (float) Math.pow(val.getFollowers_count() - avg, 2);
            count++;
        }
        return (float) Math.pow(sum/count, 0.5);
    }
  
    
    /**
     * Metodo che permette di applicare i filtri
     * @param fieldName Campo su cui vi vuole specificare la condizione di filtro
     * @param operator specifica il filtro che si vuole applicare
     * @param value Valori che caratterizzano la condizione di filtro
     * @return La collezione di oggetti filtrata
     */
	@Override
	public ArrayList<Friend> filterField(String fieldName, String operator, Object... value) {
		// TODO Auto-generated method stub
		return (ArrayList<Friend>) utils.select(this.getFriends(), fieldName, operator, value);
	}
	   
}
