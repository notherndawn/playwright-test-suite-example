# Playwright example project (using Java)

Demonstrates the usage of Playwright in combination with Java, Maven, JUnit5 and Allure

## Prerequisites:

* Java jdk-1.8 or higher
* Apache Maven 3 or higher

## Run tests:

Clone the repo:

```bash
$ git clone git@github.com:notherndawn/playwright-test-suite-example.git
```

Build the project:
```bash
$ mvn clean verify
```

Build Allure report run
```bash
$ mvn allure:report
```

In order to view the report run
```bash
$ mvn allure:serve
```
