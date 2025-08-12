package steps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 通用API测试基础类
 * 提供常用的API测试方法和断言
 */
public abstract class BaseAPISteps {
    
    protected static final Logger logger = LoggerFactory.getLogger(BaseAPISteps.class);
    
    protected RequestSpecification httpRequest;
    protected Response response;
    protected JSONObject requestParams;
    protected String responseId;
    protected String baseUrl;
    
    /**
     * 初始化基础URL
     * @param url 基础URL
     */
    protected void initializeBaseUrl(String url) {
        this.baseUrl = url;
        RestAssured.baseURI = url;
    }
    
    /**
     * 执行GET请求
     * @param endpoint 端点路径
     */
    protected void executeGetRequest(String endpoint) {
        httpRequest = RestAssured.given();
        response = httpRequest.get(endpoint);
    }
    
    /**
     * 执行POST请求
     * @param endpoint 端点路径
     * @param requestBody 请求体
     */
    protected void executePostRequest(String endpoint, JSONObject requestBody) {
        httpRequest = given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestBody.toString());
        response = httpRequest.post(endpoint);
    }
    
    /**
     * 执行PUT请求
     * @param endpoint 端点路径
     * @param requestBody 请求体
     */
    protected void executePutRequest(String endpoint, JSONObject requestBody) {
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestBody.toString());
        response = httpRequest.put(endpoint);
    }
    
    /**
     * 执行DELETE请求
     * @param endpoint 端点路径
     */
    protected void executeDeleteRequest(String endpoint) {
        httpRequest = RestAssured.given();
        response = httpRequest.delete(endpoint);
    }
    
    /**
     * 验证响应状态码
     * @param expectedCode 期望的状态码
     */
    protected void verifyResponseCode(Integer expectedCode) {
        int actualCode = response.getStatusCode();
        assertEquals((int) expectedCode, actualCode);
    }
    
    /**
     * 从响应中提取ID
     */
    protected void extractResponseId() {
        JsonPath jsonPath = response.jsonPath();
        responseId = jsonPath.getJsonObject("id").toString();
        logger.info("Response Status: {}", response.getStatusLine());
        logger.debug("Response Body: {}", response.getBody().asString());
    }
    
    /**
     * 验证响应体中的字段值
     * @param fieldPath JSON路径
     * @param expectedValue 期望值
     */
    protected void verifyResponseField(String fieldPath, String expectedValue) {
        JsonPath jsonPath = response.jsonPath();
        String actualValue = jsonPath.getJsonObject(fieldPath).toString();
        assertEquals(expectedValue, actualValue);
    }
    
    /**
     * 打印响应信息（用于调试）
     */
    protected void printResponse() {
        logger.info("Response Status Code: {}", response.getStatusCode());
        logger.debug("Response Body: {}", response.getBody().asString());
    }
}