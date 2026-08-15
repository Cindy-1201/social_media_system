CREATE PROCEDURE UserLogin
    @phone VARCHAR(10)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT [user_id]
      ,[user_name]
      ,[email]
      ,[phone]
      ,[password]
      ,[cover_image]
      ,[biography]
  FROM [dbo].[User]
  WHERE [phone] = @phone
END;