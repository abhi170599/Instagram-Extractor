package com.absoft.DataExtractor;

public final class IGUrl {
	
	//url to get posts with hash tags
		private static String TAG = "https://www.instagram.com/explore/tags/{tags}/?__a=1";
		
		//url to get the user name from user id
		private static String USERNAME = "https://i.instagram.com/api/v1/users/{id}/info/";
		
		private static String USERINFO = "https://instagram.com/{username}/?__a=1";
		
		//user agent for get user name headers
		private static String USERAGENT   = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 Instagram 123.1.0.26.115 (iPhone11,8; iOS 13_3; en_US; en-US; scale=2.00; 828x1792; 190542906)";

		public static String getTAG() {
			return TAG;
		}

		public static void setTAG(String tAG) {
			TAG = tAG;
		}

		public static String getUSERNAME() {
			return USERNAME;
		}

		public static void setUSERNAME(String uSERNAME) {
			USERNAME = uSERNAME;
		}

		public static String getUSERINFO() {
			return USERINFO;
		}

		public static void setUSERINFO(String uSERINFO) {
			USERINFO = uSERINFO;
		}

		public static String getUSERAGENT() {
			return USERAGENT;
		}

		public static void setUSERAGENT(String uSERAGENT) {
			USERAGENT = uSERAGENT;
		}


}
