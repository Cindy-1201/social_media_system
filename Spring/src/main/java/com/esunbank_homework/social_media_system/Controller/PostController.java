package com.esunbank_homework.social_media_system.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esunbank_homework.social_media_system.Model.PostModel;
import com.esunbank_homework.social_media_system.Service.PostService;

@RestController
@RequestMapping("/")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
	
    //add
    @PostMapping("AddPost")
	public String addPost(@RequestBody PostModel request) {
		return postService.addPost(request);
	}
	
    //select
    @GetMapping("SelectPost")
	public String selectPost(@RequestParam Long user_id) {
		return postService.selectPost(user_id);
	}
	
    //delete
    @PostMapping("DeletePost")
	public String deletePost(@RequestBody PostModel request) {
		return postService.deletePost(request);
	}
	
    //updata
    @PostMapping("UpdataPost")
	public String updataPost(@RequestBody PostModel request) {
		return postService.updataPost(request);
	}
}
