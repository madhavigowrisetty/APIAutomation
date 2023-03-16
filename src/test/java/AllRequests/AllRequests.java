package AllRequests;

public class AllRequests {

	public static String addPlacePostRequest() {
		return "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
				+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"my place 1\",\r\n"
				+ "  \"phone_number\": \"(+91) 984 833 3414\",\r\n"
				+ "  \"address\": \"50, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n" + "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n" + "  ],\r\n" + "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"English\"\r\n" + "}";
	}

}
