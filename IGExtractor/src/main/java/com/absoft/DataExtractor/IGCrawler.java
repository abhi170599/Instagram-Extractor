package com.absoft.DataExtractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;

import com.absoft.IGExtractor.User;

public class IGCrawler {
	
	
	
	private  IGClient client;
	
	
	public IGCrawler() {
		   
		        
		    this.client = new IGClient();
		    
	}
	
	public JSONObject crawl(String tags,int limit) {
		
		//Spawn multiple threads
		//create custom thread pool
		List<JSONObject> user_json_array = Collections.synchronizedList(new ArrayList<JSONObject>());
		
		SortedSet<User> users = Collections.synchronizedSortedSet(new TreeSet<User>(new UserComparators().getInfComparator()));
		
		
		List<String> user_ids = this.client.getUsers(tags);
		System.out.println(user_ids);
		
		user_ids.parallelStream().forEach(s->{
			System.out.println(s);
			try {
			users.add(client.getInfo(s));
			}catch(Exception e) {
				System.out.println("User not found");
			}
		});		
		 
		System.out.println(users.toArray()); 
		
		/*
		IntStream.range(0,limit).parallel().forEach(i->{
		      JSONObject obj = new JSONObject();
		      obj.put("user_id", user_Arr[i].getId());
		      obj.put("username", user_Arr[i].getUsername());
		      obj.put("Bio", user_Arr[i].getBiography());
		      obj.put("influence", user_Arr[i].getInfluence());
		      
		      user_json_array.add(obj);
		      
		});
		*/
		/*users.parallelStream().forEach(u->{
			  JSONObject obj = new JSONObject();
		      obj.put("user_id", u.getId());
		      obj.put("username", u.getUsername());
		      obj.put("Bio", u.getBiography());
		      obj.put("influence", u.getInfluence());
		      
		      user_json_array.add(obj);
		});*/
		
		 //user_json_array.toArray();
		
		JSONObject response = new JSONObject();
		response.put("users", users);
		
		return response;
	    
		
		
	}
	
	
    
	
	

}
