CREATE PROCEDURE UpdataUser
    @user_id BIGINT,
    @user_name NVARCHAR(20),
    @email VARCHAR(max),
    @phone VARCHAR(10),
    @cover_image VARCHAR(500),
    @biography NVARCHAR(max)
AS
BEGIN
    UPDATE [dbo].[User]
    SET [user_name] = @user_name
        ,[email] = @email
        ,[phone] = @phone
        ,[cover_image] = @cover_image
        ,[biography] = @biography
     WHERE [user_id] = @user_id
END;