package apiAutomation;

import static io.restassured.RestAssured.given;

import AllRequests.AllRequests;
import POJO.ResponseOfGetAPI;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Deserialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(AllRequests.addPlacePostRequest()).when().post("/maps/api/place/add/json").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath jp = new JsonPath(response);
		String placeId = jp.getString("place_id");

		ResponseOfGetAPI responseOfGetAPI = given().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("/maps/api/place/get/json").then().assertThat().statusCode(200).extract().response()
				.as(ResponseOfGetAPI.class);

		System.out.println(responseOfGetAPI.getName());
		System.out.println(responseOfGetAPI.getAddress());
		System.out.println(responseOfGetAPI.getLocation().getLatitude());

	}

}
