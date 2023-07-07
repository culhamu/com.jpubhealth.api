package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class jphStepdefinitions {

        static String endpoint="";
        Response response;
        JsonPath responseJP;

        @Given("Kullanici {string} base URL'ini kullanir")
        public void kullanici_base_url_ini_kullanir(String url) {
            endpoint= ConfigReader.getProperty(url);
        }
        @Then("Path parametreleri icin {string} kullanir")
        public void path_parametreleri_icin_kullanir(String pathParams) {

            endpoint=endpoint+"/"+pathParams;
        }
        @Then("jPH server a GET request gonderir ve testleri yapmak icin response degerini kaydeder")
        public void j_ph_server_a_get_request_gonderir_ve_testleri_yapmak_icin_response_degerini_kaydeder() {

            response=given().when().get(endpoint);
            response.prettyPrint();
        }
        @Then("jPH respons'da status degerinin {int}")
        public void j_ph_respons_da_status_degerinin(Integer statusCode) {

            Assert.assertEquals(statusCode,(Integer) response.getStatusCode());
        }
        @Then("jPH respons'da content type degerinin {string}")
        public void j_ph_respons_da_content_type_degerinin(String contentType) {

            Assert.assertEquals(contentType,response.contentType());
        }
        @Then("jPH GET respons body'sinde {string} degerinin Integer {int}")
        public void j_ph_get_respons_body_sinde_degerinin_ınteger(String attribute, Integer expectedValue) {

            responseJP=response.jsonPath();
            Assert.assertEquals(expectedValue,responseJP.get(attribute));
        }
        @Then("jPH GET respons body'sinde {string} degerinin String {string}")
        public void j_ph_get_respons_body_sinde_degerinin_string(String attribute, String expectedValue) {

            responseJP=response.jsonPath();
            Assert.assertEquals(expectedValue,responseJP.get(attribute));
        }


    }
