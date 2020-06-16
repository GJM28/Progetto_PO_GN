package com.project.OOP.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;




/**
 *@author Nicolò Rossini
 *@author Mcdonald Gregory James
 * @param url1 oggetto URL
 * @param url stringa che contiene l'indirizzo url
 * I metodi transferTo () e transferFrom () sono più efficienti della semplice lettura da un flusso utilizzando un buffer. A seconda del sistema operativo sottostante, 
 * i dati possono essere trasferiti direttamente dalla cache del filesystem al nostro file senza copiare alcun byte nella memoria dell'applicazione .
 *
 */
public class JsonDownload {

	   public static void downloadingJson(String url1) {
		   
		   try {
	            URL url = new URL(url1);
	            URLConnection conn = url.openConnection();

	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            File file = new File("data.json");

	            FileWriter fw = new FileWriter(file);
	            BufferedWriter bw  = new BufferedWriter(fw);
	            String inputLine;
	            while ((inputLine = br.readLine()) != null) {
	                bw.write(inputLine);
	            }

	            br.close();
	            bw.flush();
	            bw.close();
	            fw.close();
	        }catch (IOException ioe){
	            ioe.printStackTrace();
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	    }
	   

	  
}
