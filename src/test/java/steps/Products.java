package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Products {
    private static final String BASE_URL = "https://fakestoreapi.com/";
    private static final String PRODUCTS_ENDPOINT = "products";
    
    private RequestSpecification httpRequest;
    private Response response;
    private JSONObject requestParams;
    private String responseId;


    // 公共方法：初始化基础URL
    private void initializeBaseUrl() {
        RestAssured.baseURI = BASE_URL;
    }
    
    // 公共方法：创建产品请求体
    private JSONObject createProductRequestBody(String title) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("price", 13.5);
        requestBody.put("description", "lorem ipsum set");
        requestBody.put("image", "https://i.pravatar.cc");
        requestBody.put("category", "electronic");
        return requestBody;
    }
    
    // 公共方法：执行请求并提取ID
    private void executeRequestAndExtractId(Response response) {
        JsonPath jsonPath = response.jsonPath();
        responseId = jsonPath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(response.getBody().asString());
    }

    @Given("I hit the url of get product api endpoint")
    public void i_hit_the_url_of_get_product_api_endpoint() {
        initializeBaseUrl();
    }

    @When("I pass the url of products in the request")
    public void i_pass_the_url_of_products_in_the_request() {
        httpRequest = RestAssured.given();
        response = httpRequest.get(PRODUCTS_ENDPOINT);
    }

    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer expectedCode) {
        int actualCode = response.getStatusCode();
        assertEquals((int) expectedCode, actualCode);
    }

    @Then("I verify that the rate of the first product is {}")
    public void i_verify_that_the_rate_of_the_first_product_is(String expectedRate) {
        JsonPath jsonPath = response.jsonPath();
        String actualRate = jsonPath.getJsonObject("rating[0].rate").toString();
        assertEquals(expectedRate, actualRate);
    }

    @Then("I receive the response body with price {} at index {}")
    public void i_receive_the_response_body_with_price_at_index(String expectedPrice, int index) {
        JsonPath jsonPath = response.jsonPath();
        String actualPrice = jsonPath.getJsonObject("price[" + index + "]").toString();
        assertEquals(expectedPrice, actualPrice);
    }

    @Given("I hit the url of post product api endpoint")
    public void iHitTheUrlOfPostProductApiEndpoint() {
        initializeBaseUrl();
        httpRequest = given();
    }

    @And("I pass the request body of product title {}")
    public void iPassTheRequestBodyOfProductTitle(String title) {
        requestParams = createProductRequestBody(title);
        httpRequest.body(requestParams.toString());
        response = httpRequest.post(PRODUCTS_ENDPOINT);
        executeRequestAndExtractId(response);
    }

    @Then("I receive the response body with id as {}")
    public void iReceiveTheResponseBodyWithIdAs(String expectedId) {
        assertEquals(expectedId, responseId);
    }

    @Given("I hit the url of put product api endpoint")
    public void iHitTheUrlOfPutProductApiEndpoint() {
        initializeBaseUrl();
    }

    @When("I pass the url of products in the request with {}")
    public void iPassTheUrlOfProductsInTheRequestWith(String productNumber) {
        httpRequest = RestAssured.given();
        requestParams = createProductRequestBody("test product");
        httpRequest.body(requestParams.toString());
        response = httpRequest.put(PRODUCTS_ENDPOINT + "/" + productNumber);
        executeRequestAndExtractId(response);
    }

    @Given("I hit the url of delete product api endpoint")
    public void iHitTheUrlOfDeleteProductApiEndpoint() {
        initializeBaseUrl();
    }

    @When("I pass the url of delete products in the request with {}")
    public void iPassTheUrlOfDeleteProductsInTheRequestWith(String productNumber) {
        httpRequest = RestAssured.given();
        requestParams = createProductRequestBody("test product");
        httpRequest.body(requestParams.toString());
        response = httpRequest.delete(PRODUCTS_ENDPOINT + "/" + productNumber);
        executeRequestAndExtractId(response);
    }
}
