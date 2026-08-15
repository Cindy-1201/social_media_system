CREATE PROCEDURE SelectPost
    @user_id BIGINT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT [post_id]
          ,[user_id]
          ,[content]
          ,[image]
          ,[created_at]
    FROM [dbo].[Post]
    WHERE  [user_id]  = @user_id
END;