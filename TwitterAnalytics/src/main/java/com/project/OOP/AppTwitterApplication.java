package com.project.OOP;

import java.io.IOException;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.project.OOP.utils.JsonDownload;

/**
 * 
 *@author Nicolò Rossini
 *@author Mcdonald Gregory James
 */

@SpringBootApplication
public class AppTwitterApplication {

	public static void main(String[] args) throws JSONException, IOException, ParseException {
		
		JsonDownload.downloadingJson("https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/friends/list.json?cursor=-1&screen_name=LodiCitta&skip_status=true&include_user_entities=false&count=100");
		SpringApplication.run(AppTwitterApplication.class, args);
	}

}
