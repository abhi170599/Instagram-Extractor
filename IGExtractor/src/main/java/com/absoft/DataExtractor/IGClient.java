package com.absoft.DataExtractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.absoft.DataExtractor.IGUrl;
import com.absoft.IGExtractor.User;

public class IGClient {
	
	 
	private final CloseableHttpClient client = HttpClients.createDefault();
	
	/*
	 * Function to get the users related to a hashtag
	 * @params:
	 * hashtag : the hashtag to be searched
	 * 
	 */
	 public List<String> getUsers(String hashtag) {
	      
	      String url = IGUrl.getTAG().replace("{tags}", hashtag);
	      
	      HttpGet request = new HttpGet(url);
	      
	      List<String> users_base = new  ArrayList<String>();
	      List<String> users = Collections.synchronizedList(users_base);
	      
	      
	      String result = "";
	      //extract owner id from the posts
	      try(CloseableHttpResponse response = this.client.execute(request)){
	    	  
	    	  HttpEntity entity = response.getEntity();
	    	  
	    	  if(entity!=null) {
	    		  result = EntityUtils.toString(entity);
	    		  //System.out.println(result);
	    	  }
	    	  
	    	  JSONObject res = new JSONObject(result);
	    	  JSONArray obj =  res.getJSONObject("graphql").getJSONObject("hashtag").getJSONObject("edge_hashtag_to_media").getJSONArray("edges");
	    	  
	    	  /*
	    	  IntStream.range(0,obj.length()).forEach(i->{
	    		  
	    		  JSONObject user = (JSONObject)obj.get(i);
	    		  String user_id  = user.getJSONObject("node").getJSONObject("owner").getString("id");
	    		  users.add(user_id);
	    		  
	    	  });
	    	  */
	    	  
	    	  //Custom thread pool
	    	  ForkJoinPool customPool = new ForkJoinPool(20);
	    	  customPool.submit(()->IntStream.range(0, obj.length()).parallel().forEach(i->{
	    		  JSONObject user = (JSONObject)obj.get(i);
	    		  String user_id  = user.getJSONObject("node").getJSONObject("owner").getString("id");
	    		  users.add(user_id);
	    		  
	    	  })).get();
	    	  
	    	  //this loop should be made parallel
	    	  /*
	    	  for(int i=0;i<obj.length();i++) {
	    		  
	    		  JSONObject user = (JSONObject)obj.get(i);
	    		  String user_id  = user.getJSONObject("node").getJSONObject("owner").getString("id");
	    		  users.add(user_id);
	    	  }
	    	  */
	        	      
	      }catch(Exception e) {
	    	  e.printStackTrace();
	      }
	          
	      return  users;
	     	   
      }
	 
	 
	 /*
	  * Function to get the user_info from user_id
	  * @params : String userid, the user id to be searched
	  * 
	  * return type: a user object
	  * 	  *  
	  */
	 
	  public User getInfo(String userId) {
		  
		  String url = IGUrl.getUSERNAME().replace("{id}", userId);
		  
		  HttpGet request = new HttpGet(url);
		  request.addHeader(HttpHeaders.USER_AGENT, IGUrl.getUSERAGENT());
		  
		  User user = new User();
		  String result="";
		  
		  try(CloseableHttpResponse response = this.client.execute(request)){
			  
              HttpEntity entity = response.getEntity();
	    	  
	    	  if(entity!=null) {
	    		  result = EntityUtils.toString(entity);
	    		  //System.out.println(result);
	    	  }
	    	  
	    	  JSONObject res = new JSONObject(result);
	    	  //System.out.println(res);
	    	  String username = res.getJSONObject("user").getString("username");
	          
	    	  
	    	  /** get user information**/
	    	  String url_user = IGUrl.getUSERINFO().replace("{username}", username);
	    	  HttpGet request_user = new HttpGet(url_user);
	    	  
	    	  try(CloseableHttpResponse response_user = this.client.execute(request_user)){
	    		 
	    		  String result_user="";
	    		  
	    		  HttpEntity entity_user = response_user.getEntity();
		    	  
		    	  if(entity!=null) {
		    		  result_user = EntityUtils.toString(entity_user);
		    		  //System.out.println(result);
		    	  }
		    	  
		    	  JSONObject res_obj = new JSONObject(result_user);
		    	   //System.out.println(res_obj);
		    	  JSONObject user_obj=res_obj.getJSONObject("graphql").getJSONObject("user");
		    	  String id = userId;
		    	  String user_name = username;
		    	  
		    	  int Followers = user_obj.getJSONObject("edge_followed_by").getInt("count");
		    	  int Following = user_obj.getJSONObject("edge_follow").getInt("count");
		    	  
		    	  String bio = user_obj.getString("biography");
		    	  
		    	  double inf = Followers/Following;
		    	  
		    	  user.setId(id);
		    	  user.setUsername(username);
		    	  user.setFollowers(Followers);

		    	  user.setFollowing(Following);
		    	  user.setInfluence(inf);
		    	  user.setBiography(bio);
	          
	    	  }catch(Exception e) {
	    		  e.printStackTrace();
	    	  }
	    	  
			 
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		  
		  return user;
				  
	  }

}
