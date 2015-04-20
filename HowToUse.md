

# Introduction #
Hello! Welcome to selenium-java-evidence. Fell free to give us new functionality and report the bugs that you find.

The use of this API is peace of cake! Follow the steps bellow:

## Step 1 - Add the jar file to you Selenium project ##

At this moment you can choose the jar file to put in classpath, we have two:
  * [selenium-java-evidence-onejar.zip](http://selenium-java-evidence.googlecode.com/files/selenium-java-evidence-onejar.zip)
  * [selenium-java-evidence-with-libs.zip](http://selenium-java-evidence.googlecode.com/files/selenium-java-evidence-with-libs.zip)

The .zip with _onejar_ carries embedded libraries used to generate a report and you not needed other jar file to put in your classpath.

The .zip with _with-libs_ carries the libraries used on a lib folder and you'll need put these libraries in you classpath

Booth .zip files carries the following structure:
  * report
  * selenium-java-evidence.jar
  * init.properties

#### Report folder ####
The report folder contains two example report's to use with **selenium-java-evidence**.
Weather you want to create you own report, please see the CreateYourOwnReport.

The report that will be used is on the properties file, explained lather.

#### selenium-java-evidence.jar ####
This is the mini-framework. You must place it in you classpath, independent if you are using the _onejar_ or _with-libs_ .zip

#### init.properties ####
This file contains the properties that will be used in report, usually the report labels.
Pay attention of the image properties, that must be contain the relative image path.

<font color='red'>Don't forget: </font>**If you downloaded the _with-libs_, you need to put all libraries in _lib_ folder in your classpath.**

## Step 2 - Using in your source code ##
Always you need put these codes in your source code:
  1. import `com.googlecode.seleniumjavaevidence.report.GenerateEvidenceReport;` package
  1. import `com.googlecode.seleniumjavaevidence.report.SeleniumEvidence;` package
  1. create `private List<SeleniumEvidence> evidence;` as global attribute (the name of this attribute is at your own)
  1. create a global variable to store the exception, if there, by the following code: `String exString;`
  1. create the following instance in you **`setup()`** method: `evidence = new ArrayList<SeleniumEvidence>();`
  1. for each evidence that you want to show, put the following code: `evidence.add(new SeleniumEvidence("message", selenium.captureScreenshotToString()));`
  1. you **must** put your test code under a `try-catch` to get the exception, if there...
    * Inside any catch, put the following code: `evidence.add(new SeleniumEvidence("fail message", selenium.captureScreenshotToString()));` and the following code: `exString = e.fillInStackTrace().getMessage();`
    * inside the `finally`, put the following code: `GenerateEvidenceReport evidenceReport.generatePDFEvidence(evidence, exString);`

**Bellow a template class using the selenium-java-evidence:**

```
import com.googlecode.seleniumjavaevidence.report.GenerateEvidenceReport;
import com.googlecode.seleniumjavaevidence.selenium.SeleniumEvidence;
import com.thoughtworks.selenium.*;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.server.SeleniumServer;

public class TestExample extends junit.framework.TestCase {

    private Selenium selenium;
    private SeleniumServer seleniumServer;
    private List<SeleniumEvidence> evidence;
    String exString;

    @Override
    public void setUp() throws Exception {
        evidence = new ArrayList<SeleniumEvidence>();
        seleniumServer = new SeleniumServer();
        selenium = new DefaultSelenium("localhost", 4444, "*firefox3", "http://localhost/");
        seleniumServer.start();
        selenium.start();
    }

    @Override
    public void tearDown() throws Exception {
        selenium.stop();
        seleniumServer.stop();
    }

    public void testLink() throws Exception {
        try {
            //your selenium test here
            evidence.add(new SeleniumEvidence("get evidence from here", selenium.captureScreenshotToString()));
        } catch (Exception e) {
            evidence.add(new SeleniumEvidence("Unexpected error", selenium.captureScreenshotToString()));
            exString = e.fillInStackTrace().getMessage();
        } finally {
            GenerateEvidenceReport.generatePDFEvidence(evidence, exString);
        }

    }
}
```

## Note about Selenium 2 ##
If you're using Selenium 2 (WebDriver), do it:
  * find the line  `evidence.add(new SeleniumEvidence("get evidence from here", selenium.captureScreenshotToString()));`
  * replace `selenium.captureScreenshotToString()}} to {{{((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)`


### Pay attention ###
  * You must have the selenium libraries in in your classpath
  * You must have the _report_ folder in the root folder of the application
  * You must have the _init.properties_ file in the root folder of the application
  * If you'll not use images, put null value in the image properties