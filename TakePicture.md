

# Introduction #

When we take a picture with Selenium, there are basically two ways to do this.
This will influence how the image will be presented in the evidence report.
This library uses the capture screenshot by Selenium to return a string as [Base64](http://www.docjar.com/docs/api/sun/misc/BASE64Decoder.html)
In the descriptions above, I'll use `selenium` as a new Selenium instance


## Capture the screen ##
Using the following code with Selenium 1:
```
selenium.captureScreenshotToString();
```

Using the following code with Selenium 2:
```
((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)
```

The image will appears as:
![http://lh5.ggpht.com/_E8k4Yrs3PtA/TIl6GC4PTXI/AAAAAAAAApo/4TDPGZ50ZUA/s800/selenium-screen-desktop.png](http://lh5.ggpht.com/_E8k4Yrs3PtA/TIl6GC4PTXI/AAAAAAAAApo/4TDPGZ50ZUA/s800/selenium-screen-desktop.png)

This is a good strategy if you want get desktop image, but the browser may not appear, and this will depend on the browser state (minimized or resided)
Note that image refers to the entire desktop, and the window in the selenium application not complete appears because the browser were not maximized.

See the report example: http://selenium-java-evidence.googlecode.com/files/ExampleEvidence-desktop.pdf

## Capture the entire page ##
Using the following code with Selenium 1:
```
selenium.captureEntirePageScreenShotToString();
```

Using the following code with Selenium 2:
```
((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)
```

The image will appears as:

![http://lh4.ggpht.com/_E8k4Yrs3PtA/TIl6GlBYajI/AAAAAAAAAps/IqNg2ALSclo/s640/selenium-screen-entire-page.png](http://lh4.ggpht.com/_E8k4Yrs3PtA/TIl6GlBYajI/AAAAAAAAAps/IqNg2ALSclo/s640/selenium-screen-entire-page.png)


This command displays the image content even if the browser is minimized, different of the previous strategy, that displays the desktop image.

In this case, the entire page appears even if the browser is maximized, minimized or any state

See the report example: http://selenium-java-evidence.googlecode.com/files/ExampleEvidence-entire.pdf

### Note ###
If you use Internet Explorer, you must install [SnapsIE](http://snapsie.sourceforge.net/) to capture entire page screenshot.
To learn how to install this COM object, see the webpage http://snapsie.sourceforge.net/
