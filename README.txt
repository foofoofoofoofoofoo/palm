***MAIN APPLICATION***

****GENERAL INFORMATION***
This is an application built using Spring with a Hexagonal architecture approach.

****COMPILE AND RUN****
Please ensure you have Java 17+ installed.
Run `./gradlew bootRun` to compile and run the server.
It will be serving at localhost:8080.
Please see "RestAccountAdapter.java" and "RestUserAdapter" for the REST API paths of the application.


***TESTS***
In the "integration_tests" directory, there is an "integration_test.py" file containing integration tests for all endpoints.
Please ensure you have Python3.6+ installed.
Run `python3 -m pip install -r requirements.txt` if you do not have the "requests" package already installed.
The server must be started up before running the integration tests.
Run `python3 integration_test.py` to run the integration tests.
The server must be restarted before re-running the integration tests, because the tests will modify the data in the database.
