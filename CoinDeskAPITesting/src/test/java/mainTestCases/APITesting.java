package mainTestCases;

import data.DataDriven;
import io.restassured.http.ContentType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APITesting {



    @Test
    public void checkStatusCode(){
        given().get("https://ipinfo.io/161.185.160.93/geo").then().assertThat().statusCode(200);
    }

    @Test
    public void validateDataInResponseBody(){
        given().get("https://ipinfo.io/161.185.160.93/geo").then().assertThat().body("ip",equalTo("161.185.160.93")).and().
        assertThat().body("city",equalTo("New York City")).
        and().assertThat().body("region",equalTo("New York"));
    }

    @DataProvider(name = "sampleData")
    private Object[][] testData()  {
        try {
            return DataDriven.expectedData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(dataProvider = "sampleData")
    public void validateFullResponseBody(String ip,String city,String region,String country,String loc,String org,
                                         String postal,String timezone,String readme){
         String json =given().get("https://ipinfo.io/"+ip+"/geo").asString();
         SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(json.contains(ip));
        softAssert.assertTrue(json.contains(city));
        softAssert.assertTrue(json.contains(region));
        softAssert.assertTrue(json.contains(country));
        softAssert.assertTrue(json.contains(loc));
        softAssert.assertTrue(json.contains(org));
        softAssert.assertTrue(json.contains(postal));
        softAssert.assertTrue(json.contains(timezone));
        softAssert.assertTrue(json.contains(readme));
        softAssert.assertAll();
    }

    @Test
    public void validateNegativeScenarioByInvalidData(){
        String resposeData = given().get("https://ipinfo.io/16o1.1n85.w0.9k0/g").asString();
        Assert.assertTrue(resposeData.contains("Wrong ip"));

        given().get("https://ipinfo.io/16o1.1n85.w0.9k0/g").then().assertThat().body("error.'title'",equalTo("Wrong ip")).
                and().assertThat().body("error.'message'",equalTo("Please provide a valid IP address"));
    }

    @Test
    public void validateContentTypeAsJSONApplication(){
        given().get("https://ipinfo.io/161.185.160.93/geo").then().assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void logRequestAndResponseDetails(){
        given().log().all().when().get("https://ipinfo.io/161.185.160.93/geo").then().log().body();
    }





}
