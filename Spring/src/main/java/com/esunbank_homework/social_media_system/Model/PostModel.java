package com.esunbank_homework.social_media_system.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostModel {
	
	@JsonProperty("post_id")
    private String sPostId;
	
	@JsonProperty("user_id")
    private String sUserId;
	
	@JsonProperty("content")
    private String sContent;
	
	@JsonProperty("image")
    private String sImage;
	
	@JsonProperty("created_at")
    private String sCreatedAt;
	
    public String getPostId() {
        return sPostId;
    }

    public void setPostId(String sPostId) {
        this.sPostId = sPostId;
    }
    
    public String getUserId() {
        return sUserId;
    }

    public void setUserId(String sUserId) {
        this.sUserId = sUserId;
    }

    public void setContent(String sContent) {
        this.sContent = sContent;
    }
    public String getContent() {
        return sContent;
    }

    public void setImage(String sImage) {
        this.sImage = sImage;
    }
    public String getImage() {
        return sImage;
    }

    public String getCreatedAt() {
        return sCreatedAt;
    }

    public void setCreatedAt(String sCreatedAt) {
        this.sCreatedAt = sCreatedAt;
    }
}
