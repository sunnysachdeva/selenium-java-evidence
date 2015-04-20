

# Introduction #

You can be create your own Selenium evidence report through [iReport](http://jasperforge.org/projects/ireport) or [JasperReports](http://jasperforge.org/projects/jasperreports).

# Steps #
Bellow follow the steps that we must follow to create the report

## Step 1 - Preparation ##
Assure that you have installed [iReport](http://jasperforge.org/projects/ireport) <br>
After installation (or weather you have it) open <a href='http://jasperforge.org/projects/ireport'>iReport</a>

<h3>Create report bands</h3>
To create a new report, you need put these report bands:<br>
<ul><li><b>Page Header:</b> to place the images and the title<br>
</li><li><b>Column Header:</b> to place the project name, tester name, date and status. This will appear in each page created<br>
</li><li><b>Detail:</b> to place the evidence message and the image<br>
</li><li><b>Page Footer:</b> to place the footer message and the number of pages<br>
</li><li><b>Summary:</b> to place the exception, if there</li></ul>

<h3>Insert report parameters</h3>
To use the report properly you need to put nine teen (19) parameters on the report:<br>
<ul><li>SEL_PROJECT: name of the project<br>
</li><li>SEL_TESTER: name of the tester<br>
</li><li>SEL_EXCEPTION: the exception if it appears<br>
</li><li>SEL_COMPANY_LOGO: image logo of the company. null to not display<br>
</li><li>SEL_CUSTOMER_LOGO: image logo of the customer. null to not display<br>
</li><li>SEL_LABEL_EVINDENCE_TITLE: title of the evidence<br>
</li><li>SEL_LABEL_PROJECT: name of the project label<br>
</li><li>SEL_LABEL_TESTER: name of the tester label<br>
</li><li>SEL_LABEL_DATE: name of the date label<br>
</li><li>SEL_LABEL_STATUS: name of the status label<br>
</li><li>SEL_LABEL_PASS: name of the pass status<br>
</li><li>SEL_LABEL_FAILED: name of the failed status<br>
</li><li>SEL_LABEL_ERROR_DETAIL: name of label report detail<br>
</li><li>SEL_LABEL_FOOTER: name of the label footer<br>
</li><li>SEL_LABEL_PAGE: name of the label page<br>
</li><li>SEL_LABEL_COMPANY_NAME: name of company</li></ul>

<h2>Step 2 - Report Design</h2>
All of the parameters will be placed directly in the report, except the image and message. Below we'll see where put each parameter.<br>
<br>
<br>
<h3>Which parameter you will place in each band?</h3>

<h3>Page Header</h3>
Put these parameters in this band:<br>
<ul><li>SEL_LABEL_COMPANY_NAME<br>
</li><li>SEL_LABEL_EVINDENCE_TITLE<br>
</li><li>SEL_COMPANY_LOGO<br>
</li><li>SEL_CUSTOMER_LOGO</li></ul>

<b>PS:</b> to use the logo parameters, put the image element and put the name of the parameter in <i>Image Expression</i>. E.g: Image Expression: <code>$P{SEL_COMPANY_LOGO}</code>

<h3>Column Header</h3>
Put these parameters in this band:<br>
<ul><li>SEL_LABEL_PROJECT<br>
</li><li>SEL_LABEL_TESTER<br>
</li><li>SEL_LABEL_DATE<br>
</li><li>SEL_LABEL_STATUS<br>
</li><li>SEL_LABEL_PASS<br>
</li><li>SEL_LABEL_FAILED</li></ul>

To the <code>SEL_LABEL_PASS</code> parameter, is recommended put a font color as <b>green</b>. After insert in  <i>Print When Expression</i> the following code: <code>$P{SEL_EXCEPTION} == null</code>

To the <code>SEL_LABEL_FAILED</code> parameter, is recommended put a font color as <b>red</b>. After insert in  <i>Print When Expression</i> the following code: <code>$P{SEL_EXCEPTION}.length() &gt; 0</code>

Is recommended to overlap these parameters, to appears in the same position.<br>
<br>
<br>
<h3>Detail</h3>
In this band you need put two <i>Text Fileds</i> elements instead of a parameters. After that you put these elements, in the <i>Text Field Expression</i> put the names bellow, one for each text field:<br>
<ul><li>$F{sel_message}<br>
</li><li>$F{sel_image}</li></ul>

This is necessary because these information's are non statical text's.<br>
<br>
<h3>Page Footer</h3>
Put these parameters in this band:<br>
<ul><li>SEL_LABEL_FOOTER<br>
</li><li>SEL_LABEL_PAGE</li></ul>

This band will display the footer message and the number of pages of the report.<br>
To put in the same example of the report example, put a <i>Text Field</i> and the following expression: <code>$P{SEL_LABEL_PAGE}+ " " + $V{PAGE_NUMBER}+" de" + " " + $V{PAGE_NUMBER}</code>
This will displays something like this: <i>Page 1 of 2</i>

<h3>Summary</h3>
Fist of all, you need put one expression on this band. This band will appears the exception message, if there. Click in this band and put the following expression in <i>Print When Expression</i>: <code>$P{SEL_EXCEPTION}.length() &gt; 0</code>

Put these parameters in this band:<br>
<ul><li>SEL_LABEL_ERROR_DETAIL<br>
</li><li>SEL_EXCEPTION</li></ul>

That's all, now you have a custom report. Pay attention on the formats, fonts, color and others things that you wanna put on report.