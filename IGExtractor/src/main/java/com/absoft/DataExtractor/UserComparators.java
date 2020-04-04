package com.absoft.DataExtractor;

import java.util.Comparator;

import com.absoft.IGExtractor.User;

public class UserComparators {
	
	//Influence Comparator
	 class InfCompare implements Comparator<User>{
		 
		@Override
		public int compare(User arg0, User arg1) {
			if(arg0.getInfluence()<arg1.getInfluence())
				return 1;
			else 
				return -1;
			
		}
	 }
	 
	 public InfCompare getInfComparator() {
		 return new InfCompare();
	 }

}
