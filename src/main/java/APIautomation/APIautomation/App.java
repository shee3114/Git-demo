package APIautomation.APIautomation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import files.Resources;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class App {
	
	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws Exception {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Maddy\\workspace\\APIautomation\\src\\main\\java\\files\\evn.properties");
		prop.load(fis);
	}

	@Test
	public void testrun() {
		// given base url
		RestAssured.baseURI = prop.getProperty("Host");

		given().param("location", "-33.8670522,151.1957362").
		param("radius", "500").
		param("key", prop.getProperty("Key")).

				// Resource
				when().
				get(Resources.getPlaceListResource()).
				then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).
				// and().body("results[0].geometry.location.lat",equalTo
				// ("-33.8688197")).
				and().body("results[0].name", equalTo("Sydney")).and()
				.body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"));
	}
}
