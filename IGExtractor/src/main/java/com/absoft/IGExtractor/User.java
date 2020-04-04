package com.absoft.IGExtractor;

public class User {
	
	private String Id;
	private String Username;
	private String Biography;
	private int Followers;
	private int Following;
	
	private double influence;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getBiography() {
		return Biography;
	}

	public void setBiography(String biography) {
		Biography = biography;
	}

	public int getFollowers() {
		return Followers;
	}

	public void setFollowers(int followers) {
		Followers = followers;
	}

	public int getFollowing() {
		return Following;
	}

	public void setFollowing(int following) {
		Following = following;
	}
	
	public double getInfluence() {
		return this.influence;
	}
	
	public void setInfluence(double d) {
		this.influence = d;
	}
	
	public String toString() {
		
		return this.Username+" : "+this.influence;
	}
	
	

}
