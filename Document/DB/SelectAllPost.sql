CREATE PROCEDURE SelectAllPost
AS
BEGIN
    SET NOCOUNT ON;

    SELECT [post_id]
          ,[user_id]
          ,[content]
          ,[image]
          ,[created_at]
    FROM [dbo].[Post]

END;