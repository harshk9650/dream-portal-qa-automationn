# Dream Portal QA Automation

## Project Overview
This project contains automated functional tests for the Dream Portal website. It uses Java, Selenium WebDriver, TestNG, and Maven, following the Page Object Model (POM) design pattern.

## Objective
To validate the behavior of the Home page, Dreams Diary page, and Dreams Total page. The tests verify UI components, animations, tab handling, and data summarization.

## Tech Stack
- **Java** (v11+)
- **Selenium WebDriver** (v4.16)
- **TestNG**
- **Maven**
- **ExtentReports**
- **Eclipse IDE** (compatible)

## Prerequisites
- Java JDK 11+
- Maven
- Google Chrome browser
- Eclipse IDE (optional, for local development)

## Project Structure
```text
dream-portal-qa-automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/          # Page Object classes
│   │       └── utils/          # Utilities (DriverFactory, Reporting, AIValidator, etc.)
│   └── test/
│       └── java/
│           ├── base/           # BaseTest for setup and teardown
│           └── tests/          # TestNG test classes
├── screenshots/                # Screenshots on test failures
├── reports/                    # Extent HTML report
├── pom.xml                     # Maven configuration
└── testng.xml                  # TestNG suite configuration
```

## Installation
1. Clone the repository to your local machine.
2. Import the project into Eclipse as a "Maven Project" (File -> Import -> Maven -> Existing Maven Projects).
3. Let Maven download all dependencies.
4. (Optional) Create a `.env` file from `.env.example` to set your `OPENAI_API_KEY` if you want to run the AI Validation test.

## Running Tests
To run the complete test suite from the terminal via Maven:
```bash
mvn clean test
```

## Running from Eclipse
1. Right-click on `testng.xml`.
2. Select **Run As** > **TestNG Suite**.

## Reporting
After executing tests, an HTML report is generated at `reports/ExtentReport.html`. Open this file in any web browser to view detailed test execution results.

## Screenshots
Screenshots for failed tests are automatically captured and saved in the `screenshots/` directory. These are also attached to the Extent report.

## AI Validation
An optional test `AIValidationTest` uses the OpenAI API to compare the dream type (Good/Bad) determined by the UI against an AI classification.
To run this test, you must configure your API key by setting an environment variable:
- Windows (CMD): `set OPENAI_API_KEY=your_key_here`
- Mac/Linux: `export OPENAI_API_KEY=your_key_here`

If the environment variable is not present, the test will gracefully skip.

## Test Coverage
| Requirement | Implementation | Class/Test | Status |
|-------------|----------------|------------|--------|
| Loading animation | Wait & Validate | HomePageTest.testLoadingAnimationAndContentVisibility | PASS |
| Main content | Wait & Validate | HomePageTest.testLoadingAnimationAndContentVisibility | PASS |
| My Dreams button | Wait & Validate | HomePageTest.testLoadingAnimationAndContentVisibility | PASS |
| New windows/tabs | Window handles | HomePageTest.testClickMyDreamsOpensNewTabs | PASS |
| 10 dream entries | Table row count | DreamsDiaryTest.testDiaryEntries | PASS |
| Good/Bad types | Cell parsing | DreamsDiaryTest.testDiaryEntries | PASS |
| Complete rows | Cell parsing | DreamsDiaryTest.testDiaryEntries | PASS |
| Good Dreams = 6 | Value extraction | DreamsTotalTest.testSummaryValues | PASS |
| Bad Dreams = 4 | Value extraction | DreamsTotalTest.testSummaryValues | PASS |
| Total Dreams = 10 | Value extraction | DreamsTotalTest.testSummaryValues | PASS |
| Recurring Dreams = 2 | Value extraction | DreamsTotalTest.testSummaryValues | PASS |
| Recurring dream names | HashMap aggregation | CrossPageValidationTest.testDiaryToSummaryValidation | PASS |
| POM | Structured classes | `pages/` | PASS |
| TestNG assertions | Hard assertions | `tests/` | PASS |
| Reporting | ExtentReports | `utils/ExtentReportManager` | PASS |
| Logging | Extent Test Logs | `utils/TestListener` | PASS |
| Screenshots | OutputType.FILE | `utils/ScreenshotUtil` | PASS |
| AI validation | HTTP Client + Gson | AIValidationTest | PASS / SKIP |

## Known Limitations
- The website is static, so data cannot be added or deleted.
- AI test skips by default unless the `OPENAI_API_KEY` environment variable is defined.
