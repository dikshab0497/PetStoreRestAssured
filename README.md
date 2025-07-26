\# 🧪 PetStore API Automation Project



This project automates API testing of the \[Swagger PetStore](https://petstore.swagger.io/) using \*\*Rest Assured\*\*, \*\*Java\*\*, \*\*Maven\*\*, and \*\*TestNG\*\*. It includes structured reporting via \*\*Extent Reports\*\* and follows modular, maintainable best practices.



---



\## 📌 Key Features



\- ✅ REST API automation using \*\*Rest Assured\*\*

\- 🧱 Build and dependency management via \*\*Maven\*\*

\- 🧪 Test execution and assertions using \*\*TestNG\*\*# 🧪 PetStore API Automation Project



This project automates API testing of the \[Swagger PetStore](https://petstore.swagger.io/) using \*\*Rest Assured\*\*, \*\*Java\*\*, \*\*Maven\*\*, and \*\*TestNG\*\*. It includes structured reporting via \*\*Extent Reports\*\* and follows modular, maintainable best practices.



---



\## 📌 Key Features



\- ✅ REST API automation using \*\*Rest Assured\*\*

\- 🧱 Build and dependency management via \*\*Maven\*\*

\- 🧪 Test execution and assertions using \*\*TestNG\*\*

\- 📊 \*\*Extent Reports\*\* for rich HTML reporting

\- 🔁 Request \& response validation (status code, headers, body)

\- 📂 Organized by modules with clear naming conventions

\- 🔐 Token-based authentication support (if applicable)



---



\## 🛠️ Tech Stack



| Tool           | Purpose                         |

|----------------|---------------------------------|

| Java           | Programming Language            |

| Rest Assured   | API Testing Framework           |

| TestNG         | Test Execution \& Assertions     |

| Maven          | Dependency \& Project Management |

| Extent Reports | Test Reporting Framework        |

| Git \& GitHub   | Version Control                 |



---



\## 🗂️ Project Structure



PetStoreRestAssured/

├── src/

│ ├── main/java/

│ │ └── utilities/ # Common utilities (e.g., config, report setup)

│ └── test/java/

│ ├── tests/ # API Test classes

│ ├── pojo/ # Request/Response POJOs

│ ├── base/ # Base test class (setup \& teardown)

│ └── data/ # Test data (JSON or static data)

├── testng.xml # TestNG Suite Configuration

├── pom.xml # Maven Dependencies

├── test-output/ # Extent HTML Report Output

└── README.md # Project Documentation



yaml

Copy

Edit



---



\## 🚀 How to Run the Tests



1\. \*\*Clone the Repository\*\*

&nbsp;  ```bash

&nbsp;  git clone https://github.com/dikshab0497/PetStoreRestAssured.git

&nbsp;  cd PetStoreRestAssured

Import as a Maven Project into IntelliJ or Eclipse



Run using Maven



bash

Copy

Edit

mvn clean test

Or directly from testng.xml in your IDE.



📊 Reporting – Extent Reports

The project uses Extent Reports for professional-grade HTML reporting.



📁 Reports are generated automatically after each run and stored in:



bash

Copy

Edit

/test-output/ExtentReport.html

🔍 Reports include:



Pass/Fail status per test



Timestamps



Category or class-level grouping



Exception trace (if any)



(Optional) Screenshot support



🧪 Sample API Test Scenarios

🐶 Add a new pet to the store



🔍 Retrieve pet details by ID



✏️ Update pet information



❌ Verify error handling for invalid IDs



📦 Get pet by status (available/sold/pending)



🧑‍💻 Login user and get session (if enabled)



🗑️ Delete pet and verify deletion



📌 Future Enhancements

&nbsp;Add JSON Schema Validation for response body



&nbsp;Implement Data-Driven Testing using Excel/JSON



&nbsp;Add retry logic for flaky APIs



&nbsp;Jenkins integration for CI/CD automation



&nbsp;Trigger reports via email after test execution



🙋‍♀️ Author

Diksha Bandagale

📧 dikshab0497@gmail.com

🔗 LinkedIn



📜 License

This project is licensed under the MIT License.



yaml

Copy

Edit



\- 📊 \*\*Extent Reports\*\* for rich HTML reporting

\- 🔁 Request \& response validation (status code, headers, body)

\- 📂 Organized by modules with clear naming conventions

\- 🔐 Token-based authentication support (if applicable)



---



\## 🛠️ Tech Stack



| Tool           | Purpose                         |

|----------------|---------------------------------|

| Java           | Programming Language            |

| Rest Assured   | API Testing Framework           |

| TestNG         | Test Execution \& Assertions     |

| Maven          | Dependency \& Project Management |

| Extent Reports | Test Reporting Framework        |

| Git \& GitHub   | Version Control                 |



---



\## 🗂️ Project Structure



PetStoreRestAssured/

├── src/

│ ├── main/java/

│ │ └── utilities/ # Common utilities (e.g., config, report setup)

│ └── test/java/

│ ├── tests/ # API Test classes

│ ├── pojo/ # Request/Response POJOs

│ ├── base/ # Base test class (setup \& teardown)

│ └── data/ # Test data (JSON or static data)

├── testng.xml # TestNG Suite Configuration

├── pom.xml # Maven Dependencies

├── test-output/ # Extent HTML Report Output

└── README.md # Project Documentation



yaml

Copy

Edit



---



\## 🚀 How to Run the Tests



1\. \*\*Clone the Repository\*\*

&nbsp;  ```bash

&nbsp;  git clone https://github.com/dikshab0497/PetStoreRestAssured.git

&nbsp;  cd PetStoreRestAssured

Import as a Maven Project into IntelliJ or Eclipse



Run using Maven



bash

Copy

Edit

mvn clean test

Or directly from testng.xml in your IDE.



📊 Reporting – Extent Reports

The project uses Extent Reports for professional-grade HTML reporting.



📁 Reports are generated automatically after each run and stored in:



bash

Copy

Edit

/test-output/ExtentReport.html

🔍 Reports include:



Pass/Fail status per test



Timestamps



Category or class-level grouping



Exception trace (if any)



(Optional) Screenshot support



🧪 Sample API Test Scenarios

🐶 Add a new pet to the store



🔍 Retrieve pet details by ID



✏️ Update pet information



❌ Verify error handling for invalid IDs



📦 Get pet by status (available/sold/pending)



🧑‍💻 Login user and get session (if enabled)



🗑️ Delete pet and verify deletion



📌 Future Enhancements

&nbsp;Add JSON Schema Validation for response body



&nbsp;Implement Data-Driven Testing using Excel/JSON



&nbsp;Add retry logic for flaky APIs



&nbsp;Jenkins integration for CI/CD automation



&nbsp;Trigger reports via email after test execution



🙋‍♀️ Author

Diksha Bandagale

📧 dikshab0497@gmail.com

🔗 LinkedIn



📜 License

This project is licensed under the MIT License.



yaml

Copy

Edit



