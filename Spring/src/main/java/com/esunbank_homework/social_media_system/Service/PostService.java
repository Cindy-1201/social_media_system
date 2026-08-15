package com.esunbank_homework.social_media_system.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.esunbank_homework.social_media_system.Model.PostModel;
import com.esunbank_homework.social_media_system.Repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;
    private static final Logger log = LoggerFactory.getLogger(PostService.class);

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

	public String addPost(PostModel request) {
		// TODO Auto-generated method stub
		return null;
	}

	public String selectPost(@RequestParam Long sUserId) {
		
    	List<Map<String, Object>> postData = new ArrayList<>();  
		
    	postData = postRepository.selectPost(sUserId.toString());
    	log.info("postData=>{}", postData.toString());
		return postData.toString();
	}

	public String deletePost(PostModel request) {
		// TODO Auto-generated method stub
		return null;
	}

	public String updataPost(PostModel request) {
		// TODO Auto-generated method stub
		return null;
	}

}
