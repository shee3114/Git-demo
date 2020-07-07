package files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class commonFunction {

	public static JsonPath rawToJsonConvertor(Response res){
		String responseString = res.asString();
		System.out.println("Response: " + responseString);
		JsonPath js = new JsonPath(responseString);
		return js;
	}
}
