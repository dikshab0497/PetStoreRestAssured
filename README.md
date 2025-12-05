# 🧪 PetStore API Automation Project

This project automates API testing of the [Swagger PetStore](https://petstore.swagger.io/) using **Rest Assured**, **Java**, **Maven**, and **TestNG**. It includes structured reporting via **Extent Reports** and follows modular, maintainable best practices.

## 📌 Key Features
- ✅ REST API automation using **Rest Assured**
- 🧱 Build and dependency management via **Maven**
- 🧪 Test execution and assertions using **TestNG**
- 📊 **Extent Reports** for rich HTML reporting
- 🔁 Request & response validation (status code, headers, body)
- 📂 Organized by modules with clear naming conventions
- 🔐 Token-based authentication support (if applicable)

## 🛠️ Tech Stack

| Tool | Purpose |
|------|---------|
| Java | Programming Language |
| Rest Assured | API Testing Framework |
| TestNG | Test Execution & Assertions |
| Maven | Dependency & Project Management |
| Extent Reports | Test Reporting Framework |
| Git & GitHub | Version Control |

## 🗂️ Project Structure

PetStoreRestAssured/
├── src/
│ ├── main/java/
│ │ └── utilities/ # Common utilities (config, report setup)
│ └── test/java/
│ ├── tests/ # API Test classes
│ ├── pojo/ # Request/Response POJOs
│ ├── base/ # Base test class (setup & teardown)
│ └── data/ # Test data (JSON/static data)
├── testng.xml # TestNG Suite Configuration
├── pom.xml # Maven Dependencies
├── test-output/ # Extent HTML Report Output
└── README.md # Project Documentation

bash
Copy code

## 🚀 How to Run the Tests

1. **Clone the Repository**
```bash
git clone https://github.com/dikshab0497/PetStoreRestAssured.git
cd PetStoreRestAssured
Import as a Maven Project into IntelliJ or Eclipse

Run tests using Maven

bash
Copy code
mvn clean test
Or directly from testng.xml in your IDE.

📊 Reporting – Extent Reports
Reports are generated automatically after each run in /test-output/ExtentReport.html

Includes:

Pass/Fail status per test

Timestamps

Class-level grouping

Exception trace (if any)

Optional: screenshots

🧪 Sample API Test Scenarios
🐶 Add a new pet to the store

🔍 Retrieve pet details by ID

✏️ Update pet information

❌ Verify error handling for invalid IDs

📦 Get pet by status (available/sold/pending)

🧑‍💻 Login user and get session (if enabled)

🗑️ Delete pet and verify deletion

📌📌 Future Enhancements

Parallel execution for faster regression runs

Add retry logic for flaky APIs

Jenkins integration for CI/CD automation

Trigger reports via email after test execution

🙋‍♀️ Author
Diksha Bandagale
📧 dikshabandagale0497@gmail.com
🔗 https://www.linkedin.com/in/diksha-bandagale-44bbb81ab/?lipi=urn%3Ali%3Apage%3Ad_flagship3_profile_view_base_contact_details%3BzUVxnPD2QGGH0dTyq1fZXw%3D%3D

📜 License
This project is licensed under the MIT License.

pgsql
Copy code

---

This version is **clean, professional, and ready to push to GitHub**. It will also look great when you **feature it on LinkedIn**.  

If you want, I can also **suggest a few extra badges and visuals** to make your GitHub repo **stand out even more**.  

Do you want me to do that?