CREATE TABLE [dbo].[User](
    [user_id] [bigint] IDENTITY(1,1) NOT NULL PRIMARY KEY,
    [user_name] NVARCHAR(20) NOT NULL,
    [email] VARCHAR(max) NOT NULL,
    [phone] VARCHAR(10) NOT NULL,
    [password] VARCHAR(max) NOT NULL,
    [cover_image] NVARCHAR(max) NULL,
    [biography] NVARCHAR(max) NULL
);

CREATE TABLE [dbo].[Post](
	[post_id] [bigint] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[user_id] [bigint] NOT NULL,
	[content] [nvarchar](max) NOT NULL,
	[image] [nvarchar](max) NULL,
	[created_at] [datetime2](3) NOT NULL DEFAULT SYSDATETIME(),

    CONSTRAINT FK_Post_User
        FOREIGN KEY (user_id) 
        REFERENCES [dbo].[User](user_id)
);


CREATE TABLE [dbo].[Comment](
	[comment_id] [bigint] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[user_id] [bigint] NOT NULL,
	[post_id] [bigint] NOT NULL,
	[content] [nvarchar](max) NOT NULL,
	[created_at] [datetime2](3) NOT NULL DEFAULT SYSDATETIME(),

    CONSTRAINT FK_Comment_User 
        FOREIGN KEY (user_id) 
        REFERENCES [dbo].[User](user_id),
    CONSTRAINT FK_Comment_Post
        FOREIGN KEY (post_id) 
        REFERENCES [dbo].[Post](post_id)
);