package com.absoft.IGExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import com.absoft.DataExtractor.IGClient;
import com.absoft.DataExtractor.IGCrawler;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

    	//IGClient client = new IGClient();
    	
    	long start = System.currentTimeMillis();
    	
    	System.out.println("\nStarted Crawling");
    	IGCrawler crawler = new IGCrawler();
    	JSONObject users = crawler.crawl("vadodaramodels", 20);
    	
        System.out.println(users);
        long end = System.currentTimeMillis();
        
        long timeElapsed = end-start;
        
        System.out.println("\nExecution time : "+timeElapsed);
        
       
    }
}
