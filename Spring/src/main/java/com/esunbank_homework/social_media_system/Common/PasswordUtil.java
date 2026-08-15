package com.esunbank_homework.social_media_system.Common;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtil {
	
    private PasswordUtil() {}

    /**
     * 密碼加密
     *
     * @param password 使用者密碼
     * @return 加密後密碼
     */
    public static String hashPassword(String password)
	{
		String salt = BCrypt.gensalt(12); // 12代表重複運算2^12次嵾數
	    String hash = BCrypt.hashpw(password, salt);
	    return hash;
	}
	
    /**
     * 密碼驗證
     *
     * @param sInsertPW 使用者密碼
     * @param hashPW 加密密碼
     * @return 驗證結果
     */
    public static boolean verifyPassword(String sInsertPW, String hashPW)
	{
        boolean isCorrect = BCrypt.checkpw(sInsertPW, hashPW);
	    return isCorrect;
	}
}
