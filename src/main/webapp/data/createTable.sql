USE [midtern]
GO

/****** Object:  Table [dbo].[post_atm]    Script Date: 2024/7/15 ¤U¤È 04:33:18 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[post_atm](
	[key] [int] IDENTITY(1,1) NOT NULL,
	[city] [nvarchar](3) NULL,
	[district] [nvarchar](4) NULL,
	[postNo] [nchar](10) NULL,
	[postName] [nvarchar](30) NULL,
	[phone] [nvarchar](20) NULL,
	[address] [nvarchar](50) NULL,
	[longitude] [decimal](9, 6) NULL,
	[latitude] [decimal](9, 6) NULL,
	[hasAtm] [bit] NULL,
	[hasDeposit] [bit] NULL,
	[hasPassbookUpdate] [bit] NULL,
	[hasPassbookUpdateMachine] [bit] NULL,
	[has200cash] [bit] NULL,
	[hasVoiceAssistant] [bit] NULL,
	[isOutside] [bit] NULL,
	[cityNo] [int] NULL,
	[insertTime] [datetime] NULL,
 CONSTRAINT [PK_post_atm] PRIMARY KEY CLUSTERED 
(
	[key] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


