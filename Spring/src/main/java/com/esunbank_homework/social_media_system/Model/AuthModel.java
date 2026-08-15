package com.esunbank_homework.social_media_system.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthModel {
	
	@JsonProperty("user_id")
    private String sUserId;
	
	@JsonProperty("user_name")
    private String sUserName;
	
	@JsonProperty("email")
    private String sEmail;
	
	@JsonProperty("phone")
    private String sPhone;
	
	@JsonProperty("password")
    private String sPassword;
	
	@JsonProperty("cover_image")
    private String sCoverImage;
	
	@JsonProperty("biography")
    private String sBiography;
    
    public String getUserId() {
        return sUserId;
    }

    public void setUserId(String sUserId) {
        this.sUserId = sUserId;
    }
    
    public String getUserName() {
    	return sUserName;
    }

    public void setUserName(String sUserName) {
        this.sUserName = sUserName;
    }
    
    public String getEmail() {
        return sEmail;
    }

    public void setEmail(String sEmail) {
        this.sEmail = sEmail;
    }
    
    public String getPhone() {
        return sPhone;
    }

    public void setPhone(String sPhone) {
        this.sPhone = sPhone;
    }
    
    public String getPassword() {
        return sPassword;
    }

    public void setPassword(String sPassword) {
        this.sPassword = sPassword;
    }
    
    public String getCoverImage() {
        return sCoverImage;
    }

    public void setCoverImage(String sCoverImage) {
        this.sCoverImage = sCoverImage;
    }
    
    public String getBiography() {
        return sBiography;
    }

    public void setBiography(String sBiography) {
        this.sBiography = sBiography;
    }
}
