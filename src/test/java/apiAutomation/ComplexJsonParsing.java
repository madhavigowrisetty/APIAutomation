package apiAutomation;

import org.testng.Assert;

import Allresponse.AllResponses;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParsing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath jp = new JsonPath(AllResponses.mocResponse());
		System.out.println(jp.getInt("courses.size()"));
		System.out.println(jp.getInt("dashboard.purchaseAmount"));
		System.out.println(jp.get("courses[0].title"));
		System.out.println("Printing all the course titles and prices: ");
		System.out.println("------------------------------------------");
		for (int i = 0; i < jp.getInt("courses.size()"); i++) {
			System.out.println(jp.getString("courses[" + i + "].title"));
			System.out.println(jp.getString("courses[" + i + "].price"));

		}

		System.out.println("Printing no of copies sold by RPA Course is :");
		System.out.println("-----------------------------------------------");
		for (int i = 0; i < jp.getInt("courses.size()"); i++) {
			if (jp.getString("courses[" + i + "].title").equals("RPA")) {
				System.out.println(jp.getInt("courses[" + i + "].copies"));
			}
		}

		System.out.println("Verify if Sum of all Course prices matches with Purchase Amount");
		System.out.println("----------------------------------------------------------------");
		int expectedvalue = jp.getInt("dashboard.purchaseAmount");
		int actualvalue = 0;
		for (int i = 0; i < jp.getInt("courses.size()"); i++) {
			actualvalue = actualvalue
					+ (jp.getInt("courses[" + i + "].copies") * jp.getInt("courses[" + i + "].price"));
		}

		Assert.assertEquals(actualvalue, expectedvalue);
		System.out.println(actualvalue);
	}

}
