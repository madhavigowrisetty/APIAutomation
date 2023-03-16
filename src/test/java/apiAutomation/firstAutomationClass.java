package apiAutomation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import AllRequests.AllRequests;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class firstAutomationClass {

	public static void main(String[] args) {

		// given when then

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(AllRequests.addPlacePostRequest()).when().post("/maps/api/place/add/json").then().log().all()
				.assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)")
				.extract().response().asString();

		System.out.println("Response is: " + response);
		JsonPath jp = new JsonPath(response);
		String placeId = jp.getString("place_id");
		System.out.println(placeId);

		// API call for PUT method updating address for specific place id
		String newAddress = "2465 battleford rd, CA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "  \"place_id\": \"" + placeId + "\",\r\n" + "  \"address\": \"" + newAddress
						+ "\",\r\n" + "  \"key\": \"qaclick123\"\r\n" + "}")
				.when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated")).header("Server", "Apache/2.4.41 (Ubuntu)");

		// API call for GET method to retrieve the place details
		String responseOfGETcall = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println("Response is: " + responseOfGETcall);
		JsonPath jp1 = new JsonPath(responseOfGETcall);
		String actualAddress = jp1.getString("address");
		Assert.assertEquals(actualAddress, newAddress);

	}

}
