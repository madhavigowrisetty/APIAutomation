package apiAutomation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AllRequests.LibraryAPIRequests;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LibraryApiAutomation {

	@DataProvider(name = "TestData")
	public Object[][] testData() {
		return new Object[][] { { "JAVA4", "lfhd" }, { "JAVA5", "jfsfhf" }, { "JAVA6", "jradgjre" } };
	}

	@Test(dataProvider = "TestData")
	public void addLibraryPOST(String bookname, String Id) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json")
				.body(LibraryAPIRequests.requestOfPost(bookname, Id)).when().post("/Library/Addbook.php").then()
				.assertThat().statusCode(200).body("Msg", equalTo("successfully added")).header("Server", "Apache")
				.extract().response().asString();

		System.out.println(response);

		JsonPath jp = new JsonPath(response);
		String responseID = jp.get("ID");
		deleteBook(responseID);

	}

	@Test
	public void deleteBook(String responseID) {
		RestAssured.baseURI = "http://216.10.245.166";

		given().log().all().header("Content-Type", "application/json")
				.body("{\n" + "  \"ID\": \"" + responseID + "\"\n" + "}").when().delete("/Library/DeleteBook.php")
				.then().log().all().statusCode(200).body("msg", equalTo("book is successfully deleted"));
	}

}
