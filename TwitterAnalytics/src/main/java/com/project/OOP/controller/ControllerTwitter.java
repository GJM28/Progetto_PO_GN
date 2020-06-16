package com.project.OOP.controller;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.project.OOP.errors.CommandNotFoundException;
import com.project.OOP.errors.FieldException;
import com.project.OOP.model.Friend;
import com.project.OOP.model.FriendsCollection;
import com.project.OOP.utils.JSONParse;

/**
 *@author Nicolò Rossini
 *@author Mcdonald Gregory James
 *classe che implementa il controller per effettuare chiamate GET e POST
 */

@RestController
public class ControllerTwitter {
	
	/**
	 * Rotta che mostra i metadati 
	 * @return restituisce i metadati del file JSON parsato
	 */
	 @RequestMapping(value = "/metadata", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	 public String getMetadata(){
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
	            JsonSchema schema = schemaGen.generateSchema(Friend.class);
	            return mapper.writeValueAsString(schema);
	        } catch (Exception e){
	        	return "Si è verificato un errore imprevisto";
	        }
	    }
	 
	 /**
	  * Rotta che permette di visualizzare tutti i dati
	  * @throws FileNotFoundException Segnala che il tentativo di aprire il file indicato da un percorso specificato non è riuscito.
	  * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo.
	  * 		   è la classe generale di eccezioni prodotta da operazioni I/O non riuscite o interrotte. 
	  * @throws ParseException eccezione per il parsing dati
	  * @return restituisce tutti i dati (nome dei friends e numero follower)
	  */
	 @RequestMapping(value = "/data", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	 public String getData() throws FileNotFoundException, IOException, ParseException {
		 
         ObjectMapper mapper = new ObjectMapper();
         FriendsCollection objects= JSONParse.parsing();
		 return mapper.writeValueAsString(objects.getFriends());
	 }
		
	 
	 /**
	  * Rotta POST che permette di visualizzare i dati filtrati (le frequenze per fasce di numeri di follower). 
	  * @param filter indica il filtro che si vuole utilizzare
	  * @return metodo che restituisce il json con i dati filtrati(caso in cui la variabile filter sia diversa da 0).
	  * @throws ParseException eccezione per il parsing
	 * @throws CommandNotFoundException 
	  */
	 	@RequestMapping(value = "/getBracket", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	    public String getBracket(@RequestBody(required = false) String filter) throws ParseException{
		 try {
	            FriendsCollection objects = JSONParse.parsing();
	            JSONObject json = null;
	            ArrayList<Friend> result = null;
	            if(filter != null){
	                try {
	                    json = new JSONObject(filter);
	                    result = parseCommands(objects, json);
	                } catch (ClassCastException e) {
	                    e.printStackTrace();
	                    return "Sono stati inseriti dei valori in un formato errato";
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                    return "Il JSON non sembra essere ben formato";
	                } catch (CommandNotFoundException e) {
	                    return e.getMessage();
	                }
	            }

	            ObjectMapper mapper = new ObjectMapper();
	            if(result != null) {
	                return mapper.writeValueAsString(result);
	            } else {
	            	return "non ci sono dati che rispettano il filtro";
	            }
	        } catch (IOException e){
	        	return "Si è verificato un errore imprevisto";
	        }
	    }
		
		
		/**
	     * Rotta che permette di visualizzare le statistiche in base al numero di followers
	     * @return Restituisce le statistiche
	     */
	    @RequestMapping(value = "/stats", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	    public String getStatsOnObject(){
	        try {
	                FriendsCollection objects = JSONParse.parsing();
	                HashMap<String, Object> result = new HashMap<>();
	                if(objects != null){
	                    result.put("min", objects.getMin());
	                    result.put("max", objects.getMax());
	                    result.put("Somma_tot_follower", objects.getSum());
	                    result.put("Media_follower", objects.getAvg());
	                    result.put("Deviazione_standard", objects.getDevStandard());
	                } else {
	                    return "Non sono stati trovati elementi corrispondenti alla ricerca";
	                }
	                ObjectMapper mapper = new ObjectMapper();
	                return mapper.writeValueAsString(result);
	                
	        } catch (Exception e){
	        	return "Si è verificato un errore imprevisto";
	        }
	    }
	    
	    /**
	     * Rotta che restituisce la classifica in base al numero di followers
	     * @param field campo che permette di decidere quale classifica si vuole andare a visuallizzare (Top10, Last10)
	     * @return restituisce la classifica parametrica
	     * @throws FileNotFoundException Segnala che il tentativo di aprire il file indicato da un percorso specificato non è riuscito.
	     * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo.
	     * 		   è la classe generale di eccezioni prodotta da operazioni I/O non riuscite o interrotte. 
	     * @throws ParseException eccezione per il parsing dati
	     * @throws FieldException eccezione parametro field errato
	     */
	 	
	    @RequestMapping(value = "/ranking", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	    public String rank(@RequestParam String field) throws FileNotFoundException, IOException, ParseException, FieldException{
	    	FriendsCollection objects = JSONParse.parsing();
	    	HashMap<String, Object> result = new HashMap<>();
	    	ObjectMapper mapper = new ObjectMapper();
	    	if(objects != null){
	    		if(field.equals("Top10"))
	    			result.put(field, objects.Topranking(field));
	    		if(field.equals("Last10"))
	    			result.put(field, objects.Lastranking(field));
	    	}
	    	
	    	return mapper.writeValueAsString(result);  	
	    }
	    
	 	
	     /**
	     * Rotta che consente di filtrare i contenuti del dataset in base ai filtri specificati
	     * @param obj Dataset su cui applicare i filtri
	     * @param parsedJson Filtri forniti all'interno di un JSON object
	     * @return Restituisce la collezione di oggetti filtrati
	     * @throws CommandNotFoundException Errore che si ha quando il comando inserito non rientra tra quelli previsti
	     */
	  
	  private ArrayList<Friend> parseCommands(FriendsCollection obj, JSONObject parsedJson) throws CommandNotFoundException{
		  
		  		  String field = parsedJson.keys().next();
				  JSONObject innerObj = parsedJson.getJSONObject(field);
		          String operator = innerObj.keys().next();
		          if(operator.equals("$bt")) { 
			              int min = innerObj.getJSONArray(operator).getInt(0);
			              int max = innerObj.getJSONArray(operator).getInt(1);
			              return obj.filterField(field, operator, min, max);
		          }
		          else if(operator.equals("$gt")) {
		        	  int val = innerObj.getInt(operator);
                      return obj.filterField(field, operator, val);
		          }
		          else if(operator.equals("$lt")) {
		        	  int val = innerObj.getInt(operator);
                      return obj.filterField(field, operator, val);
		          }
		          else {
		        	  throw new CommandNotFoundException("Uno o più dei comandi inseriti non è valido");
		          }
	  }
}
