package rest_sample;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;

import Pojo.pojoclass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class day1 {
	
	@Test(enabled=false)
	public void testcase()
	{
		Response response = RestAssured.get("http://localhost:3000/ibmHaldia");
		
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		System.out.println(response.body());
		System.out.println(response.getHeaders());		
	}
	
	@Test(enabled = true, dependsOnMethods = "postexample1")
	public void testcase2(ITestContext val)
	{
		RestAssured.baseURI="http://localhost:3000";
		
		String id = val.getAttribute("id").toString();
		
		given().queryParam("username", "amit").queryParam("password", "abc").get("/ibmHaldia/" + id).then().statusCode(200).log().all();
		
		/*String city = resp.jsonPath().getString("city");
		
		System.out.println("this is my value "+city);
		
		String id = resp.jsonPath().getString("id");
		
		System.out.println("this is my id "+id);
		
		Assert.assertEquals("", "");
		*/
		
		
		
//		given().delete("/ibmHaldia/25").then().statusCode(200).log().body();
	}
	
	@Test(enabled = true)
	public void postexample1(ITestContext val)
	{
		RestAssured.baseURI="http://localhost:3000";
		
		String reqbody = "{\"studentname\":\"abc\",\"batch\":\"DL\",\"city\":\"delhi\"}";
		
		
		
		Response resp = given()
			.contentType(ContentType.JSON)
			.body(reqbody).
		when()
			.post("/ibmHaldia").
		then()
			.statusCode(201)
			.log()
			.all().extract().response();
		
		String id = resp.jsonPath().getString("id");
		
		val.setAttribute("id", id);
		
	}
	
	@Test(enabled = false)
	public void postexample2(ITestContext val)
	{
		RestAssured.baseURI="http://localhost:3000";
		
		String id = "1";
		
		val.setAttribute("id", id);
		
		
		
		JSONObject parentbody = new JSONObject();
		parentbody.put("id", 0);
		parentbody.put("name", "abc");
		parentbody.put("status", "available");
		
		
		JSONObject categoryobj = new JSONObject();
		categoryobj.put("id", 0);
		categoryobj.put("name","pet");
		
		JSONObject tagobj = new JSONObject();
		tagobj.put("id", 0);
		tagobj.put("name","pet");		
		
		parentbody.put("category",categoryobj );
		parentbody.put("tags", tagobj);
				
		JsonArray obj = new JsonArray();
		obj.add("amit");
		obj.add("abhishek");
		obj.add("anuj");
		
		
		parentbody.put("photoUrls", obj);
		
		System.out.println(parentbody.toJSONString());
		
		
		
		//System.out.println(obj.toJSONString());
		
		
		
		
		
	/*	given()
			.contentType(ContentType.JSON)
			.body(obj.toJSONString()).
		when()
			.post("/ibmHaldia").
		then()
			.statusCode(201)
			.log()
			.all();*/
	}
	
	@Test(enabled = false)
	public void postexample3(ITestContext val) throws JsonProcessingException
	{
		pojoclass obj = new pojoclass();
		obj.setBatch("BCA");
		obj.setCity("delhi");
		obj.setStudentname("amit");
		
		System.out.println(obj.getBatch());
		System.out.println(obj.getCity());
		System.out.println(obj.getStudentname());
		
		ObjectMapper objmapper = new ObjectMapper();
		
		System.out.println(val.getAttribute("id").toString());
		
		
		System.out.println(objmapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
		
		
		
	}
		
		
	
	
	

}
