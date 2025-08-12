# API Test Cucumber Project

这是一个使用 Cucumber 和 REST Assured 进行 API 自动化测试的项目。

## 项目结构

```
api-test-cucumber/
├── src/test/java/steps/
│   └── Products.java          # 测试步骤定义
├── src/test/resources/features/
│   └── ProductsAPI.feature    # 统一的测试场景文件
├── TestRunner.java            # 测试运行器
├── pom.xml                    # Maven 依赖配置
└── README.md                  # 项目说明
```

## 主要特性

- **统一的 Feature 文件**: 所有 CRUD 操作测试场景合并在一个文件中
- **优化的代码结构**: 提取公共方法，减少代码重复
- **版本管理**: 使用 Maven 属性统一管理依赖版本
- **HTML 报告**: 自动生成带 .html 扩展名的测试报告
- **现代化依赖**: 使用 JUnit 5 断言和最新的 Cucumber 版本

## 运行测试

```bash
mvn test
```

## 查看报告

测试完成后，HTML 报告将生成在 `target/cucumber-reports.html`，可直接在浏览器中打开查看。

## 测试覆盖

- GET /products - 获取产品列表
- POST /products - 创建新产品
- PUT /products/{id} - 更新产品
- DELETE /products/{id} - 删除产品

## 技术栈

- Java 17
- Cucumber 7.18.0
- REST Assured 5.5.5
- JUnit Platform 1.9.3
- Maven 3.x