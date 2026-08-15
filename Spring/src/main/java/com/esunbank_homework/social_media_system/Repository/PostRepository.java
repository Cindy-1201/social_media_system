package com.esunbank_homework.social_media_system.Repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
    private static final Logger log = LoggerFactory.getLogger(PostRepository.class);

    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Map<String, Object>> selectPost(String sUserId) {

		log.info("sUserId==>{}", sUserId);
    	
    	String sql = "EXEC SelectPost "
        		+ "@user_id = ?";
    	
    	List<Map<String, Object>> postData = new ArrayList<>();   
    	
	    try {
	    	postData = jdbcTemplate.queryForList(
	        		sql,
	        		sUserId);	    		    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	log.error("Exception：{}", e);
	    	throw e;
	    }
		return postData;
    }
}
