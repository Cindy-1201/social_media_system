CREATE PROCEDURE CreateUser
    @user_name NVARCHAR(20),
    @email VARCHAR(max),
    @phone VARCHAR(10),
    @password VARCHAR(max),
    @cover_image VARCHAR(500),
    @biography NVARCHAR(max)
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO [dbo].[User]
        ([user_name]
        ,[email]
        ,[phone]
        ,[password]
        ,[cover_image]
        ,[biography]
        )
    VALUES (
        @user_name,
        @email,
        @phone,
        @password,
        @cover_image,
        @biography
    );

    SELECT SCOPE_IDENTITY() AS user_id;
END;