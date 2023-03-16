package AllRequests;

public class LibraryAPIRequests {

	public static String requestOfPost(String name, String id) {
		String request = "{\r\n" + "  \"name\": \"" + name + "\",\r\n" + "  \"isbn\": \"" + id + "\",\r\n"
				+ "  \"aisle\": \"227\",\r\n" + "  \"author\": \"John foe\"\r\n" + "}";
		return request;
	}

}
