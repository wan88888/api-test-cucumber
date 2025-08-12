package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;

/**
 * JSONPlaceholder API测试步骤定义
 * 继承BaseAPISteps获得通用API测试功能
 */
public class JSONPlaceholderSteps extends BaseAPISteps {
    
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String POSTS_ENDPOINT = "posts";
    private static final String USERS_ENDPOINT = "users";
    private static final String COMMENTS_ENDPOINT = "comments";
    
    // Posts API 步骤定义
    @Given("I hit the JSONPlaceholder posts API endpoint")
    public void i_hit_the_jsonplaceholder_posts_api_endpoint() {
        initializeBaseUrl(BASE_URL);
    }
    
    @When("I send a GET request to posts")
    public void i_send_a_get_request_to_posts() {
        executeGetRequest(POSTS_ENDPOINT);
    }
    
    @When("I send a GET request to posts with id {int}")
    public void i_send_a_get_request_to_posts_with_id(Integer postId) {
        executeGetRequest(POSTS_ENDPOINT + "/" + postId);
    }
    
    @When("I send a POST request to posts with title {string} and body {string}")
    public void i_send_a_post_request_to_posts_with_title_and_body(String title, String body) {
        JSONObject requestBody = createPostRequestBody(title, body, 1);
        executePostRequest(POSTS_ENDPOINT, requestBody);
        extractResponseId();
    }
    
    @When("I send a PUT request to posts {int} with title {string} and body {string}")
    public void i_send_a_put_request_to_posts_with_title_and_body(Integer postId, String title, String body) {
        JSONObject requestBody = createPostRequestBody(title, body, 1);
        requestBody.put("id", postId);
        executePutRequest(POSTS_ENDPOINT + "/" + postId, requestBody);
        extractResponseId();
    }
    
    @When("I send a DELETE request to posts {int}")
    public void i_send_a_delete_request_to_posts(Integer postId) {
        executeDeleteRequest(POSTS_ENDPOINT + "/" + postId);
    }
    
    // Users API 步骤定义
    @Given("I hit the JSONPlaceholder users API endpoint")
    public void i_hit_the_jsonplaceholder_users_api_endpoint() {
        initializeBaseUrl(BASE_URL);
    }
    
    @When("I send a GET request to users")
    public void i_send_a_get_request_to_users() {
        executeGetRequest(USERS_ENDPOINT);
    }
    
    @When("I send a GET request to users with id {int}")
    public void i_send_a_get_request_to_users_with_id(Integer userId) {
        executeGetRequest(USERS_ENDPOINT + "/" + userId);
    }
    
    // Comments API 步骤定义
    @Given("I hit the JSONPlaceholder comments API endpoint")
    public void i_hit_the_jsonplaceholder_comments_api_endpoint() {
        initializeBaseUrl(BASE_URL);
    }
    
    @When("I send a GET request to comments")
    public void i_send_a_get_request_to_comments() {
        executeGetRequest(COMMENTS_ENDPOINT);
    }
    
    @When("I send a GET request to comments for post {int}")
    public void i_send_a_get_request_to_comments_for_post(Integer postId) {
        executeGetRequest(POSTS_ENDPOINT + "/" + postId + "/comments");
    }
    
    // 通用验证步骤
    @Then("I should receive status code {int}")
    public void i_should_receive_status_code(Integer expectedCode) {
        verifyResponseCode(expectedCode);
    }
    
    @Then("the response should contain {string} field with value {string}")
    public void the_response_should_contain_field_with_value(String fieldPath, String expectedValue) {
        verifyResponseField(fieldPath, expectedValue);
    }
    
    @Then("the response should contain post id {int}")
    public void the_response_should_contain_post_id(Integer expectedId) {
        verifyResponseField("id", expectedId.toString());
    }
    
    @Then("the response should contain user name {string}")
    public void the_response_should_contain_user_name(String expectedName) {
        verifyResponseField("name", expectedName);
    }
    
    @And("I print the response for debugging")
    public void i_print_the_response_for_debugging() {
        printResponse();
    }
    
    // 私有辅助方法
    private JSONObject createPostRequestBody(String title, String body, Integer userId) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("body", body);
        requestBody.put("userId", userId);
        return requestBody;
    }
}