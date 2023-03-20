package apiAutomation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LibraryAPIwithRequestFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json").body(new String(Files.readAllBytes(Paths
				.get("C:\\Users\\madha\\Desktop\\Eclipse projects\\apiAutomation\\src\\test\\java\\AllRequests\\libraryPostRequest.json"))))
				.when().post("/Library/Addbook.php").then().assertThat().statusCode(200)
				.body("Msg", equalTo("successfully added")).header("Server", "Apache").extract().response().asString();

		System.out.println(response);

		JsonPath jp = new JsonPath(response);
		String responseID = jp.get("ID");
		System.out.println("adding some code");

	}

}
