Responses to questions:
1. Designing and implementing a CI+CD pipeline (including automated Selenium tests) with a small team. It included having to talk with the program manager and lead, talking with the company's INFOSEC team, and organizing a small team of interns. Our program was the first to implement such a pipeline in our office.
2. https://www.testcontainers.org/. It's a library that lets you spin up anything that can be contained in a Docker image at test time and gets automatically torn down at test completion. You can use it to write integration tests for DBs, message queues, etc. 
3. They create apps that allow patients, doctors and insurance companies to conduct business easier.
4. The solution to this is contained in LispChecker. Tests for it are contained in LispCheckerTests.
5. The solution to this is mostly contained in App.jsx. However, the entire project can be built and run as a standalone server for the app. Build by running gradlew build. The JAR will be at build/libs/test-0.0.1-SNAPSHOT.jar. URL is localhost:8080.
6. The solution to this is contained in the com.availity.test.csv package. The tests are in CsvParserTests.