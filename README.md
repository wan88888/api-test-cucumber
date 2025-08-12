# API自动化测试框架

基于Cucumber和REST Assured的通用API自动化测试框架，支持BDD风格的API测试用例编写和执行。

## 项目特性

- 🥒 **Cucumber BDD框架** - 使用Gherkin语法编写可读性强的测试用例
- 🔧 **REST Assured** - 强大的API测试库，支持各种HTTP请求
- ☕ **Java 17** - 基于最新的Java LTS版本
- 📊 **Maven构建** - 标准的Maven项目结构和依赖管理
- 📝 **详细日志** - 集成SLF4J日志框架
- 🎯 **标签化执行** - 支持通过标签选择性执行测试用例

## 技术栈

- **Java**: 17
- **测试框架**: Cucumber 7.18.0
- **API测试**: REST Assured 5.5.5
- **断言**: JUnit Jupiter 5.9.3
- **构建工具**: Maven
- **日志**: SLF4J 2.0.9
- **JSON处理**: org.json 20231013

## 项目结构

```
api-test-cucumber/
├── src/test/
│   ├── java/
│   │   ├── TestRunner.java              # 测试运行器
│   │   └── steps/
│   │       ├── BaseAPISteps.java        # 基础API步骤定义
│   │       ├── JSONPlaceholderSteps.java # JSONPlaceholder API步骤
│   │       └── Products.java            # 产品API步骤
│   └── resources/
│       ├── features/
│       │   ├── JSONPlaceholderAPI.feature # JSONPlaceholder测试用例
│       │   └── ProductsAPI.feature       # 产品API测试用例
│       └── simplelogger.properties       # 日志配置
├── pom.xml                               # Maven配置文件
└── .gitignore                           # Git忽略文件配置
```

## 快速开始

### 环境要求

- Java 17 或更高版本
- Maven 3.6 或更高版本

### 安装依赖

```bash
mvn clean install
```

### 运行测试

#### 运行所有测试
```bash
mvn test
```

#### 运行特定标签的测试
```bash
# 运行posts相关的测试
mvn test -Dcucumber.filter.tags="@posts"

# 运行users相关的测试
mvn test -Dcucumber.filter.tags="@users"
```

#### 运行特定feature文件
```bash
mvn test -Dcucumber.features="src/test/resources/features/JSONPlaceholderAPI.feature"
```

## 编写测试用例

### Feature文件示例

在`src/test/resources/features/`目录下创建`.feature`文件：

```gherkin
Feature: API测试示例
  作为一个测试人员
  我想要测试API接口
  以确保API功能正常

  @api @smoke
  Scenario Outline: 验证GET请求
    Given 我访问API端点 "<endpoint>"
    When 我发送GET请求
    Then 我应该收到状态码 <statusCode>
    And 响应应该包含 "<field>" 字段
    
    Examples:
      | endpoint | statusCode | field |
      | /posts   | 200        | id    |
      | /users   | 200        | name  |
```

### 步骤定义示例

在`src/test/java/steps/`目录下创建步骤定义类：

```java
public class APISteps {
    
    @Given("我访问API端点 {string}")
    public void 我访问API端点(String endpoint) {
        // 设置API端点
    }
    
    @When("我发送GET请求")
    public void 我发送GET请求() {
        // 发送GET请求
    }
    
    @Then("我应该收到状态码 {int}")
    public void 我应该收到状态码(int statusCode) {
        // 验证状态码
    }
}
```

## 配置说明

### Maven配置

项目使用Maven Surefire插件执行测试，配置包含以下测试文件模式：
- `**/*Test*.java`
- `**/Test*.java`
- `**/*Tests.java`
- `**/*TestCase.java`
- `**/TestRunner.java`

### 日志配置

日志配置文件位于`src/test/resources/simplelogger.properties`，可以根据需要调整日志级别。

## 最佳实践

1. **功能分组**: 使用标签对测试用例进行分组，如`@smoke`、`@regression`
2. **数据驱动**: 使用Scenario Outline和Examples实现数据驱动测试
3. **步骤复用**: 将通用的API操作封装为可复用的步骤定义
4. **环境配置**: 通过配置文件管理不同环境的API端点
5. **断言清晰**: 编写清晰明确的断言，便于问题定位

## 扩展功能

### 添加新的API测试

1. 在`features/`目录下创建新的`.feature`文件
2. 在`steps/`目录下创建对应的步骤定义类
3. 使用REST Assured编写API请求和响应验证逻辑

### 集成报告

可以集成Allure或Cucumber自带的HTML报告：

```bash
# 生成Cucumber HTML报告
mvn test -Dcucumber.plugin="html:target/cucumber-reports"
```

## 贡献指南

1. Fork项目
2. 创建功能分支
3. 提交更改
4. 推送到分支
5. 创建Pull Request

## 许可证

本项目采用MIT许可证 - 查看LICENSE文件了解详情。