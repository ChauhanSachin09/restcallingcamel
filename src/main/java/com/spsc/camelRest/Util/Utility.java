/**
 * 
 */
package com.spsc.camelRest.Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author Sachin Pratap Singh
 *
 */
public class Utility {
	
	
	public void sendRequest(String  jsonString,String url) {
		HttpClient httpClient = new DefaultHttpClient();
	    try {
	        HttpPost postRequest = new HttpPost(url);
	        postRequest.setHeader("Content-type", "application/json");
	        StringEntity entity = new StringEntity(jsonString);

	        postRequest.setEntity(entity);

	        long startTime = System.currentTimeMillis();
	        HttpResponse response = httpClient.execute(postRequest);
	        long elapsedTime = System.currentTimeMillis() - startTime;
	        //System.out.println("Time taken : "+elapsedTime+"ms");

	        InputStream is = response.getEntity().getContent();
	        Reader reader = new InputStreamReader(is);
	        BufferedReader bufferedReader = new BufferedReader(reader);
	        StringBuilder builder = new StringBuilder();
	        while (true) {
	            try {
	                String line = bufferedReader.readLine();
	                if (line != null) {
	                    builder.append(line);
	                } else {
	                    break;
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        //System.out.println(builder.toString());
	        //System.out.println("****************");
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}

}
