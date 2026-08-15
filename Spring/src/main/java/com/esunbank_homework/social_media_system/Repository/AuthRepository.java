package com.esunbank_homework.social_media_system.Repository;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class AuthRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
    private static final Logger log = LoggerFactory.getLogger(AuthRepository.class);

    public AuthRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    /**
     * 新增用戶
     *
     * @param sUserName 使用者名稱
     * @param sEmail 使用者信箱
     * @param sPhone 使用者電話
     * @param sPassword 使用者密碼
     * @param sCoverImage 使用者封面圖
     * @param sBiography 使用者文章
     * @return 使用者代號
     */
    public String createUser(String sUserName, String sEmail, String sPhone, String sPassword, String sCoverImage, String sBiography) {

		log.info("sUserName==>{}", sUserName);
		log.info("sEmail==>{}", sEmail);
		log.info("sPhone==>{}", sPhone);
		log.info("sPassword==>{}", sPassword);
		log.info("sCoverImage==>{}", sCoverImage);
		log.info("sBiography==>{}", sBiography);
    	
    	String sql = "EXEC CreateUser "
        		+ "@user_name = ?,"
        		+ "@email = ?,"
        		+ "@phone = ?,"
        		+ "@password = ?,"
        		+ "@cover_image = ?,"
        		+ "@biography = ?";

	    try {
	        return jdbcTemplate.queryForObject(
	        		sql,
	        		String.class,
	        		sUserName,
	        		sEmail,
	        		sPhone,
	        		sPassword,
	        		sCoverImage,
	        		sBiography
	        	);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	log.error("Exception：{}", e);
	    	throw e;
	    }
    }

	/**
	 * 登出
	 *
	 * @param sInsertPhone 使用者電話
	 * @return 使用者代號
	 */
	public Map<String, Object> userLogin(String sInsertPhone) {
    	log.info("sInsertPhone==>{}", sInsertPhone);
    	
	    String sql = "EXEC UserLogin @phone = ?";   
	    	    
	    try {
		    return jdbcTemplate.queryForMap(sql, sInsertPhone);
	    }catch (EmptyResultDataAccessException e) {
	        // 查不到資料
	        log.info("查無此電話號碼: {}", sInsertPhone);
	        return Map.of();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	log.error("Exception：{}", e);
	    	throw e;
	    }
	}

    /**
     * 使用者資料更新
     *
     * @param sInsertUserId 使用者代號
     * @param sInsertUserName 使用者名稱
     * @param sInsertPhone 使用者電話
     * @param sInsertPhone 使用者信箱
     * @param sInsertCoverImage 使用者封面圖
     * @param sInsertBiography 使用者文章
     * @return 更新狀態結果
     */
	public int userUpdata(String sInsertUserId, String sInsertUserName, String sInsertPhone, String sInsertEmail,
			String sInsertCoverImage, String sInsertBiography) {

		log.info("sInsertUserId==>{}", sInsertUserId);
		log.info("sInsertUserName==>{}", sInsertUserName);
		log.info("sInsertEmail==>{}", sInsertEmail);
		log.info("sInsertPhone==>{}", sInsertPhone);
		log.info("sInsertCoverImage==>{}", sInsertCoverImage);
		log.info("sInsertBiography==>{}", sInsertBiography);
    	
    	String sql = "EXEC UpdataUser "
        		+ "@user_id = ?,"
        		+ "@user_name = ?,"
        		+ "@email = ?,"
        		+ "@phone = ?,"
        		+ "@cover_image = ?,"
        		+ "@biography = ?";

	    try {
	        return jdbcTemplate.update(
	        		sql,
	        		sInsertUserId,
	        		sInsertUserName,
	        		sInsertEmail,
	        		sInsertPhone,
	        		sInsertCoverImage,
	        		sInsertBiography
	        	);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	log.error("Exception：{}", e);
	    	throw e;
	    }
	}
}
