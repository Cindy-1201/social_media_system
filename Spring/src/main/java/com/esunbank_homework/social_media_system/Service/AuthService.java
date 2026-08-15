package com.esunbank_homework.social_media_system.Service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esunbank_homework.social_media_system.Common.PasswordUtil;
import com.esunbank_homework.social_media_system.Model.AuthModel;
import com.esunbank_homework.social_media_system.Repository.AuthRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    /**
     * 登入
     *
     * @param request AuthModel
     * @param session HttpSession
     * @return 使用者資訊
     */
	public String login(AuthModel request, HttpSession session) {
		JSONObject resobj = new JSONObject();
		JSONObject userDataObj = new JSONObject();

	    String sInsertPhone = request.getPhone();
	    String sInsertPW = request.getPassword();
	    
	    if(sInsertPhone.equals("") || sInsertPW.equals("")) {
	    	resobj.put("UserData", userDataObj);
	    	resobj.put("STATE", "ERROR");
	    	resobj.put("MSG", "資料填寫不完整");
	    }else {
	    	Map<String, Object> userData = new HashMap();
		    try{
		    	userData = authRepository.userLogin(sInsertPhone);
		    	log.info("userData=>{}", userData.toString());
	
		    } catch (Exception e) {
	
		        log.error("發生資料庫錯誤", e);	
		        
				resobj.put("UserData", userDataObj);
		        resobj.put("STATE", "ERROR");
		        resobj.put("MSG", "系統發生錯誤");
	
		        return resobj.toString();
		    }
	    	
	    	if(userData.isEmpty()) {
				resobj.put("UserData", userDataObj);
		    	resobj.put("STATE", "NOK");
		    	resobj.put("MSG", "此電話號碼未註冊");
	    	}else {
		    	String hashPW = userData.get("password").toString();
				
		    	boolean isCorrect = PasswordUtil.verifyPassword(sInsertPW,hashPW);
		        log.info("密碼是否正確=>{}", isCorrect);
		        
		        if(isCorrect) {
			    	String sUserId = userData.get("user_id").toString();
			    	String sUserName = userData.get("user_name").toString();
			    	String sPhone = userData.get("phone").toString();
			    	String sEmail = userData.get("email").toString();
			    	String sCoverImage = (userData.get("cover_image")==null)?"":userData.get("cover_image").toString();
			    	String sBiography = (userData.get("biography")==null)?"":userData.get("biography").toString();
			    				    	
			    	userDataObj.put("user_id", sUserId);
			    	userDataObj.put("user_name", sUserName);
			    	userDataObj.put("phone", sPhone);
			    	userDataObj.put("email", sEmail);
			    	userDataObj.put("cover_image", sCoverImage);
			    	userDataObj.put("biography", sBiography);

			    	session.setAttribute("user_id", sUserId);
			    	session.setAttribute("user_name", sUserName);
			    	session.setAttribute("phone", sPhone);
			    	session.setAttribute("email", sEmail);
			    	session.setAttribute("cover_image", sCoverImage);
			    	session.setAttribute("biography", sBiography);
			    	
					resobj.put("UserData", userDataObj);
			    	resobj.put("STATE", "OK");
			    	resobj.put("MSG", "");
		        }else {
					resobj.put("UserData", userDataObj);
			    	resobj.put("STATE", "NOK");
			    	resobj.put("MSG", "密碼錯誤");
		        }
	    	}	
	    }	    	    		
		return resobj.toString();
	}

    /**
     * 登出
     *
     * @param session HttpSession
     * @return 執行結果
     */
	public String logout(HttpSession session) {
		JSONObject resobj = new JSONObject();		

		String sUserId = session.getAttribute("user_id").toString();
		String sUserName = session.getAttribute("user_name").toString();
		String sPhone = session.getAttribute("phone").toString();
		String sEmail = session.getAttribute("email").toString();
		String sCoverImage = session.getAttribute("cover_image").toString();
		String sBiography = session.getAttribute("biography").toString();
				
		log.info("使用者ID：{}",sUserId.toString());
		log.info("使用者名稱：{}",sUserName.toString());
		log.info("使用者電話：{}",sPhone.toString());
		log.info("使用者信箱：{}",sEmail.toString());
		log.info("使用者圖片：{}",sCoverImage.toString());
		log.info("使用者自我介紹：{}",sBiography.toString());
				
	    try {
	        session.invalidate();

	        resobj.put("STATE", "OK");
	        resobj.put("MSG", "登出成功");

	    } catch (Exception e) {

	        log.error("Logout 發生錯誤", e);

	        resobj.put("STATE", "ERROR");
	        resobj.put("MSG", "系統發生錯誤");
	    }
		return resobj.toString();
	}

    /**
     * 註冊
     *
     * @param request AuthModel
     * @return 執行結果
     */
	public String register(AuthModel request) {
		JSONObject resobj = new JSONObject();
		String userId = "";

	    String sUserName = request.getUserName();
	    String sEmail = request.getEmail();
	    String sPhone = request.getPhone();
	    String sPassword = request.getPassword();
	    String sCoverImage = request.getCoverImage();
	    String sBiography = request.getBiography();
	    
	    if(sUserName.equals("") || sEmail.equals("") || sPhone.equals("") || sPassword.equals("")) {
	    	resobj.put("UserId", userId);
	    	resobj.put("STATE", "ERROR");
	    	resobj.put("MSG", "資料填寫不完整");
	    }else {
	    	String hashPW = PasswordUtil.hashPassword(sPassword);
	    	try {
	    		userId = authRepository.createUser(sUserName,sEmail,sPhone,hashPW,sCoverImage,sBiography);

		    } catch (Exception e) {
	
		        log.error("發生資料庫錯誤", e);	
		        
				resobj.put("UserId", userId);
		        resobj.put("STATE", "ERROR");
		        resobj.put("MSG", "系統發生錯誤");
	
		        return resobj.toString();
		    }
	    	
			resobj.put("UserId", userId);
	    	resobj.put("STATE", "OK");
	    	resobj.put("MSG", "");
	    }	    	    		
		return resobj.toString();
		
	}	

    /**
     * 使用者資更新
     *
     * @param request AuthModel
     * @param session HttpSession
     * @return 執行結果和更新後使用者資料
     */
	public String updataUser(AuthModel request, HttpSession session) {
		JSONObject resobj = new JSONObject();
		JSONObject userDataObj = new JSONObject();

	    String sInsertUserId = request.getUserId();
	    String sInsertUserName = request.getUserName();
	    String sInsertPhone = request.getPhone();
	    String sInsertEmail = request.getEmail();
	    String sInsertCoverImage = request.getCoverImage();
	    String sInsertBiography = request.getBiography();
	    
	    if(sInsertUserId.equals("") || sInsertUserName.equals("") || sInsertPhone.equals("") 
	    		|| sInsertEmail.equals("")) {
	    	resobj.put("UserData", userDataObj);
	    	resobj.put("STATE", "ERROR");
	    	resobj.put("MSG", "資料填寫不完整");
	    }else {
	    	int updateSucces = 0;
		    try{
		    	updateSucces = authRepository.userUpdata(sInsertUserId, sInsertUserName, sInsertPhone, sInsertEmail, sInsertCoverImage, sInsertBiography);
		    	log.info("updateSucces=>{}", updateSucces);	
		    } catch (Exception e) {
	
		        log.error("發生資料庫錯誤", e);	
		        
				resobj.put("UserData", userDataObj);
		        resobj.put("STATE", "ERROR");
		        resobj.put("MSG", "系統發生錯誤");
	
		        return resobj.toString();
		    }
	    	
	    	if(updateSucces<=0) {
				resobj.put("UserData", userDataObj);
		    	resobj.put("STATE", "NOK");
		    	resobj.put("MSG", "資料更新失敗");
	    	}else {		        
	    			    		
		    	userDataObj.put("user_id", sInsertUserId);
		    	userDataObj.put("user_name", sInsertUserName);
		    	userDataObj.put("phone", sInsertPhone);
		    	userDataObj.put("email", sInsertEmail);
		    	userDataObj.put("cover_image", sInsertCoverImage);
		    	userDataObj.put("biography", sInsertBiography);

		    	session.setAttribute("user_id", sInsertUserId);
		    	session.setAttribute("user_name", sInsertUserName);
		    	session.setAttribute("phone", sInsertPhone);
		    	session.setAttribute("email", sInsertEmail);
		    	session.setAttribute("cover_image", sInsertCoverImage);
		    	session.setAttribute("biography", sInsertBiography);
		    	
				resobj.put("UserData", userDataObj);
		    	resobj.put("STATE", "OK");
		    	resobj.put("MSG", "");
	    	}	
	    }	    	    		
		return resobj.toString();
	}
	
}
