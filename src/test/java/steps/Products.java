package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;

/**
 * FakeStore API测试步骤定义
 * 继承BaseAPISteps获得通用API测试功能
 */
public class Products extends BaseAPISteps {
    private static final String BASE_URL = "https://fakestoreapi.com/";
    private static final String PRODUCTS_ENDPOINT = "products";
    
    // 创建产品请求体
    private JSONObject createProductRequestBody(String title) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("price", 13.5);
        requestBody.put("description", "lorem ipsum set");
        requestBody.put("image", "https://i.pravatar.cc");
        requestBody.put("category", "electronic");
        return requestBody;
    }

    @Given("I hit the url of get product api endpoint")
    public void i_hit_the_url_of_get_product_api_endpoint() {
        initializeBaseUrl(BASE_URL);
    }

    @When("I pass the url of products in the request")
    public void i_pass_the_url_of_products_in_the_request() {
        executeGetRequest(PRODUCTS_ENDPOINT);
    }

    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer expectedCode) {
        verifyResponseCode(expectedCode);
    }

    @Then("I verify that the rate of the first product is {}")
    public void i_verify_that_the_rate_of_the_first_product_is(String expectedRate) {
        verifyResponseField("rating[0].rate", expectedRate);
    }

    @Then("I receive the response body with price {} at index {}")
    public void i_receive_the_response_body_with_price_at_index(String expectedPrice, int index) {
        verifyResponseField("price[" + index + "]", expectedPrice);
    }

    @Given("I hit the url of post product api endpoint")
    public void iHitTheUrlOfPostProductApiEndpoint() {
        initializeBaseUrl(BASE_URL);
    }

    @And("I pass the request body of product title {}")
    public void iPassTheRequestBodyOfProductTitle(String title) {
        JSONObject requestBody = createProductRequestBody(title);
        executePostRequest(PRODUCTS_ENDPOINT, requestBody);
        extractResponseId();
    }

    @Then("I receive the response body with id as {}")
    public void iReceiveTheResponseBodyWithIdAs(String expectedId) {
        verifyResponseField("id", expectedId);
    }

    @Given("I hit the url of put product api endpoint")
    public void iHitTheUrlOfPutProductApiEndpoint() {
        initializeBaseUrl(BASE_URL);
    }

    @When("I pass the url of products in the request with {}")
    public void iPassTheUrlOfProductsInTheRequestWith(String productNumber) {
        JSONObject requestBody = createProductRequestBody("test product");
        executePutRequest(PRODUCTS_ENDPOINT + "/" + productNumber, requestBody);
        extractResponseId();
    }

    @Given("I hit the url of delete product api endpoint")
    public void iHitTheUrlOfDeleteProductApiEndpoint() {
        initializeBaseUrl(BASE_URL);
    }

    @When("I pass the url of delete products in the request with {}")
    public void iPassTheUrlOfDeleteProductsInTheRequestWith(String productNumber) {
        executeDeleteRequest(PRODUCTS_ENDPOINT + "/" + productNumber);
    }
}
