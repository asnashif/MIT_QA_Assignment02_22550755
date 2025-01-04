# **MIT_QA_Assignment03_22550755 - Selenium Test Automation Framework**

This project is a **Selenium WebDriver** automation framework built using **TestNG** and **Maven**. It follows the **Page Object Model (POM)** design pattern and is capable of generating detailed reports using **Extent Reports**.

---

## **Description of the Framework**
This framework automates test cases for the website **("https://www.saucedemo.com/")**. It is designed to execute tests across multiple browsers, ensuring maintainability and scalability for future enhancements.

### **Key Features**
- **Page Object Model (POM):** Modular and reusable code structure.
- **TestNG:** Test execution and reporting.
- **Maven:** Dependency and build management.
- **Extent Reports:** Comprehensive HTML test reports.

---

## **Prerequisites**
Ensure the following are installed on your system before running the framework:

1. **Java Development Kit (JDK) 21.0.3+**  
   Download: [https://www.oracle.com/java/technologies/downloads/#java21](https://www.oracle.com/java/technologies/downloads/#java21)

2. **Apache Maven 3.9+**  
   Download: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

3. **Git**  
   Download: [https://git-scm.com/downloads](https://git-scm.com/downloads)

4. **Web Drivers**
    - **ChromeDriver** for Chrome
    - **EdgeDriver** for Edge  
      Download links:
    - Chrome: [https://chromedriver.chromium.org/downloads](https://chromedriver.chromium.org/downloads)
    - Edge: [https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)

---

## **Setup Instructions**

1. **Clone the Repository**  
   Clone the project repository:
   ```bash
   git clone https://github.com/asnashif/MIT_QA_Assignment03_22550755.git
   ```  

2. **Navigate to the Project Directory**
   ```bash
   cd MIT_QA_Assignment03_22550755
   ```  

3. **Install Dependencies**  
   Use Maven to install the required dependencies:
   ```bash
   mvn clean install
   ```  

---

## **Test Execution Instructions**

### **Via IntelliJ IDEA**
1. Open the project in **IntelliJ IDEA**.
2. Install Maven dependencies (if not already installed).
3. Navigate to the `src/test/java/testCases/test.xml` file.
4. Right-click on the **test.xml** file and select **Run**.
5. Test results will be displayed in the **TestNG Console**.
6. Reports will be available in the `src/test/resources/reports` folder.
7. Screenshots will be saved in the `src/test/resources/screenshots` folder.

### **Via Command Line**
1. Open a terminal and navigate to the project directory.
2. Execute the tests using Maven:
   ```bash
   mvn test
   ```  

### **Via Jenkins**
1. Open Jenkins and configure a new job.
2. **General Configuration**:
    - **GitHub** selected.
    - **Repository URL:**
      ```text
      https://github.com/asnashif/MIT_QA_Assignment03_22550755.git
      ```  

3. **Source Code Management**:
    - **Git** selected.
    - **Branch Specifier:** `*/main`
    - **Credentials:** None required.

4. **Build Triggers**:
    - Select **Build whenever a SNAPSHOT dependency is built**.

5. **Build Steps**:
    - **Root POM:** `pom.xml`
    - **Goals and Options:** `clean test`

6. **Advanced Settings**:
    - Enable **triggering of downstream projects**.
    - Select **Block downstream trigger when building**.

7. **Post-build Actions**:
    - Select **Run regardless of build result**.

8. Save the job configuration.
9. Trigger the build.
10. View the execution report in **Console Output**.

---

## **Project Structure**
```text
MIT_QA_Assignment03_22550755/
├── src/
│   ├── main/
│   └── test/
│       ├── java/
│       │    ├── pageObjects/     # Page classes (POM)
│       │    ├── testBase/        # Test Base Page
│       │    ├── testCases/       # Test scripts
│       │    └── utilities/       # Utility functions
│       │ 
│       └── resources/
│             ├──  logs/
│             ├──  reports/         # Test execution reports
│             ├──  screenshots/     # Captured screenshots
│             ├──  config.properties/
│             └──  log4j2.xml/
│             
├── testData/                    # Input test data
├── pom.xml                      # Maven configuration
├── README.md                    # Project documentation
├── .gitignore                   # Ignored files
```  

---

## **Outputs**
- **Test Reports:** Available in `src/test/resources/reports/`.
- **Screenshots:** Saved in `src/test/resources/screenshots/`.

---

## **Contributors**
**Asna Shif**  
[GitHub Profile](https://github.com/asnashif)

--- 

This README provides all essential details about the project, including setup, execution, and additional information for effective usage.