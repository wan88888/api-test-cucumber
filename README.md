# 通用API自动化测试框架

这是一个基于 Cucumber 和 REST Assured 的通用API自动化测试框架，支持多种API接口的CRUD操作测试。

## 项目结构

```
api-test-cucumber/
├── src/test/java/steps/
│   ├── BaseAPISteps.java           # 通用API测试基础类
│   ├── Products.java               # 产品API测试步骤
│   └── JSONPlaceholderSteps.java   # JSONPlaceholder API测试步骤
├── src/test/resources/
│   ├── features/
│   │   ├── ProductsAPI.feature     # 产品API测试场景
│   │   └── JSONPlaceholderAPI.feature # JSONPlaceholder API测试场景
│   └── simplelogger.properties     # 日志配置文件
├── TestRunner.java                 # 测试运行器
├── pom.xml                         # Maven 依赖配置
├── .gitignore                      # Git忽略文件
└── README.md                       # 项目说明
```

## 主要特性

### 🏗️ 通用架构设计
- **BaseAPISteps**: 通用API测试基础类，提供所有HTTP方法的通用实现
- **继承机制**: 各API测试类继承基础类，实现代码复用
- **模块化设计**: 支持快速扩展新的API接口测试

### 🔧 技术优化
- **专业日志系统**: 使用SLF4J替代System.out.println，支持日志级别控制
- **统一的Feature文件**: 所有CRUD操作测试场景合并优化
- **版本管理**: 使用Maven属性统一管理依赖版本
- **现代化依赖**: JUnit 5断言和最新Cucumber版本

### 📊 测试报告
- **HTML报告**: 自动生成详细的测试报告
- **日志配置**: 可控的日志输出，便于调试和生产环境使用

## 运行测试

### 运行所有测试
```bash
mvn test
```

### 运行特定API测试
```bash
# 只运行产品API测试
mvn test -Dcucumber.filter.tags="@products"

# 只运行JSONPlaceholder API测试
mvn test -Dcucumber.filter.tags="@jsonplaceholder"
```

## 查看报告

测试完成后，HTML 报告将生成在 `target/cucumber-reports.html`，可直接在浏览器中打开查看。

## 测试覆盖

### 产品API (FakeStoreAPI)
- GET /products - 获取产品列表
- POST /products - 创建新产品
- PUT /products/{id} - 更新产品
- DELETE /products/{id} - 删除产品

### JSONPlaceholder API
- **Posts**: 文章的CRUD操作
- **Users**: 用户的CRUD操作
- **Comments**: 评论的CRUD操作

### 验证内容
- ✅ HTTP状态码验证
- ✅ 响应内容验证
- ✅ 数据完整性检查
- ✅ 错误处理验证

## 技术栈

- **Java 17** - 编程语言
- **Cucumber 7.18.0** - BDD测试框架
- **REST Assured 5.5.5** - API测试库
- **JUnit Platform 1.9.3** - 测试运行平台
- **SLF4J 2.0.9** - 日志框架
- **Maven 3.x** - 构建工具

## 扩展新API

要添加新的API测试，只需：

1. **创建步骤定义类**
```java
public class NewAPISteps extends BaseAPISteps {
    // 定义特定的常量和方法
}
```

2. **创建Feature文件**
```gherkin
Feature: New API Testing
  Scenario Outline: Test New API
    # 定义测试场景
```

3. **自动集成**: TestRunner会自动发现并运行新的测试

## 日志配置

项目使用SLF4J进行日志管理，可通过 `simplelogger.properties` 配置：
- 默认INFO级别，减少冗余输出
- BaseAPISteps设置为DEBUG级别，便于查看详细响应
- 支持生产环境的日志级别调整

## CI/CD集成

项目包含GitHub Actions工作流 (`.github/workflows/api-tests.yml`)，提供：

### 🚀 自动化测试
- **触发条件**: 推送到main/develop分支、Pull Request、定时任务（每日10:00）
- **手动触发**: 支持workflow_dispatch手动运行
- **环境**: Ubuntu最新版 + Java 17

### 📊 测试报告
- **自动上传**: 测试报告和Surefire报告
- **保留期**: 30天
- **失败处理**: 即使测试失败也会上传报告

### ⚡ 性能优化
- **Maven缓存**: 加速依赖下载
- **并行执行**: 充分利用CI资源

### 使用方式
```bash
# 查看工作流状态
# 访问GitHub仓库的Actions标签页

# 手动触发测试
# 在Actions页面点击"Run workflow"按钮
```