package landLordExample.Rest.test;


import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import landLordExample.Rest.main.Landlord;

public class LandLordTest {

	@BeforeClass
	public static void init(){

		RestAssured.baseURI = "http://localhost:8080";
	}
	
	@Test
	public void failTest(){
		Assert.assertTrue(false);
		
	}
	
	/*@Test(priority=0,dependsOnMethods="failTest")
	public void getLandlords1(){


		when()
		.get("/landlords")
		.then()
		.statusCode(200)
		.body("", is(empty()));
	}*/
	
	@DataProvider(name="validLandlords")
	public Object[][]createData(){
		return new Object[][]{
			{new Landlord("Alex","Fruz")},
			{new Landlord("Alex1","Fruz1",true)},
			};
		}
	
	
	/*@Test(priority=0)
	public void getLandlords(){

		//RestAssured.baseURI = "http://localhost:8080";

		when()
		.get("/landlords")
		.then()
		.statusCode(200)
		.body("", is(empty()));
	}*/
	
	


	@Test(priority=1,dataProvider="validLandlords",groups="critical")
	public void postLanlord01(Landlord landlord){
		
		
		//Landlord landlord = new Landlord("Jhon","Mathew");


		
		String id = given()
				.contentType(ContentType.JSON)
				.body(landlord)
				.when()
				.post("/landlords")
				.then()
				.statusCode(201)
				.body("firstName",is(landlord.getFirstName()))
				.body("lastName",is(landlord.getLastName()))
				.body("trusted",is(landlord.getTrusted()))
				.body("apartments", is(empty()))
				.extract()
				.path("id");
		given()
		.pathParam("id", id)
		.when()
		.get("/landlords/{id}")
		.then()
		.statusCode(200)
		.body("id", is(id))
		.body("firstName",is(landlord.getFirstName()))
		.body("lastName",is(landlord.getLastName()))
		.body("trusted",is(landlord.getTrusted()))
		.body("apartments", is(empty()));

	}
	@Test(priority=1)
	public void postLanlord02(){


		Landlord landlord = new Landlord("J1","Ma1",true);
		String id = given()
				.contentType(ContentType.JSON)
				.body(landlord)
				.when()
				.post("/landlords")
				.then()
				.statusCode(201)
				.body("firstName",is(landlord.getFirstName()))
				.body("lastName",is(landlord.getLastName()))
				.body("trusted",is(true))
				.body("apartments", is(empty()))
				.extract()
				.path("id");
		given()
		.pathParam("id", id)
		.when()
		.get("/landlords/{id}")
		.then()
		.statusCode(200)
		.body("id", is(id))
		.body("firstName",is(landlord.getFirstName()))
		.body("lastName",is(landlord.getLastName()))
		.body("trusted",is(true))
		.body("apartments", is(empty()));

	}
	
	@Test(priority=1)
	public void postLanlordNegative01(){

		
		Landlord landlord = new Landlord("","");
		given()
			.contentType(ContentType.JSON)
			.body(landlord)
		.when()
			.post("/landlords")
		.then()
			.statusCode(400)
			.body("message",is("Fields are with validation errors"))
			//DTO -Data Transfer Object
			.body("fieldErrorDTOs[0].fieldName",is("firstName"))
			.body("fieldErrorDTOs[0].fieldError",is("First name can not be empty"))
			.body("fieldErrorDTOs[1].fieldName",is("lastName"))
			.body("fieldErrorDTOs[1].fieldError",is("Last name can not be empty"));
		
	}
	
	@Test(priority=2)
	public void putLandlord(){
		Landlord landlord = new Landlord("Aleee1","Fruz22",true);
		String id = given()
					.contentType(ContentType.JSON)
					.body(landlord)
				.when()
					.post("/landlords")
				.then()
					.statusCode(201)
				
				.extract()
					.path("id");
		
		Landlord landlordForUpdate = new Landlord("allet","bott");
			given()
				.contentType(ContentType.JSON)
				.body(landlordForUpdate)
				.pathParam("id", id)
			.when()
				.put("/landlords/{id}")
			.then()
				.statusCode(200)
				.body("message", is("LandLord with id: "+id+" successfully updated"));
						
						
			given()
				.pathParam("id", id)
			.when()
				.get("/landlords/{id}")
			.then()
				.statusCode(200)
				.body("firstName",is(landlordForUpdate.getFirstName()))
				.body("lastName",is(landlordForUpdate.getLastName()))
				.body("trusted",is(false));
		
	}
	
	
	@Test(priority=3)
	public void deleteLandlord(){
		Landlord landlord = new Landlord("Jath1","Ma1");
		String id = given()
			.contentType(ContentType.JSON)
			.body(landlord)
		.when()
			.post("/landlords")
		.then()
			.statusCode(201)		
		.extract()
				.path("id");
		
		given()
			.pathParam("id", id)
		.when()
			.delete("/landlords/{id}")
		.then()
			.statusCode(200)
			.body("message", is("LandLord with id: "+id+" successfully deleted"));
		
		given()
			.pathParam("id", id)
		.when()
			.get("/landlords/{id}")
		.then()
			.statusCode(404)
			.body("message",is("There is no LandLord with id: "+id));
			
	}


}
