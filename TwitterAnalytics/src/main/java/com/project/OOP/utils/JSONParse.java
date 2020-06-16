package com.project.OOP.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.project.OOP.model.Friend;
import com.project.OOP.model.FriendsCollection;


/**
 * 
 * Classe utilizzata per parsare il file JSON
 *@author Nicolò Rossini
 *@author Mcdonald Gregory James
 */
public class JSONParse {
	
	/**
	 * 
	 * metodo per parsare il file json
	 * @return FriendsCollection(list) oggetto con all'interno la lista contenente i dati parsati 
	 * @throws FileNotFoundException Segnala che il tentativo di aprire il file indicato da un percorso specificato non è riuscito.
	 * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo.
	 * 		   è la classe generale di eccezioni prodotta da operazioni I/O non riuscite o interrotte. 
	 * @throws ParseException eccezione per il parsing dati 
	 */
	
	  public static FriendsCollection parsing() throws FileNotFoundException, IOException, ParseException{
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("data.json"));
			ArrayList<Friend> list = new ArrayList<>();
			int i = 0;
			JSONObject jsonObject = objectToJSONObject(obj);			
			JSONArray users = (JSONArray) jsonObject.get("users");
			JSONObject jobj;
			
			
			for (i = 0; i < users.length(); i++) {
				jobj = (JSONObject) users.get(i);
				list.add(new Friend(jobj.getString("name"), jobj.getInt("followers_count")));
			}
			return new FriendsCollection(list);
	}
	  
	  /**
	   * 
	   * @param object di tipo Object
	   * metodo che fa un casting da Object a JSONObject
	   */
	  public static JSONObject objectToJSONObject(Object object){
		    Object json = null;
		    JSONObject jsonObject = null;
		    try {
		        json = new JSONTokener(object.toString()).nextValue();
		    } catch (JSONException e) {
		        e.printStackTrace();
		    }
		    if (json instanceof JSONObject) {
		        jsonObject = (JSONObject) json;
		    }
		    return jsonObject;
		}
}
