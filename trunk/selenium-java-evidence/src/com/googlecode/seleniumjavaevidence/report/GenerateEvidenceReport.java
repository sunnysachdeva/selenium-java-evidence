package com.googlecode.seleniumjavaevidence.report;

import com.googlecode.seleniumjavaevidence.selenium.SeleniumEvidence;
import com.googlecode.seleniumjavaevidence.utils.SeleniumEvidenceUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Generate the test evidence in PDF file
 * @author Elias Nogueira <elias.nogueira@gmail.com>
 * @author Selenium Java Evidence <http://code.google.com/p/selenium-java-evidence>
 */
public class GenerateEvidenceReport {

    /**
     * Generate evidence in PDF file. The evidence will be save on home dir on a folder called by the name of the project
     * @param list list of evidence made of an text and image
     * @param reportName report name
     * @param exception exception text that are throwing by Java
     * @throws IOException if occurs any problem with the directory
     */
    public static void generatePDFEvidence(List<SeleniumEvidence> list, String reportName, String tester, String project, String exception) throws IOException {
        List<SeleniumEvidence> data = list;
        Properties properties = SeleniumEvidenceUtils.loadProperties();
        String evidenceDir = System.getProperty("user.dir") + System.getProperty("file.separator") + properties.getProperty("evidence.dir") + System.getProperty("file.separator");
        
        createDirToEvidence(evidenceDir);

        try {

            String companyImage = properties.getProperty("image.company.path");
            String customerImage = properties.getProperty("image.customer.path");

            BufferedImage imageCompany;
            BufferedImage imageClient;

            if (companyImage.equals("null")) {
                imageCompany = null;
            } else {
                imageCompany = ImageIO.read(new File(companyImage));
            }

            if (customerImage.equals("null")) {
                imageClient = null;
            } else {
                imageClient = ImageIO.read(new File(customerImage));
            }

            //BufferedImage imageCompany = ImageIO.read(new File(properties.getProperty("image.company.path")));
            //BufferedImage imageClient = ImageIO.read(new File(properties.getProperty("image.customer.path")));

            Map parameters = new HashMap();
            if (exception != null) {
                parameters.put("SEL_EXCEPTION", exception);
            }
            parameters.put("SEL_COMPANY_LOGO", imageCompany);
            parameters.put("SEL_CUSTOMER_LOGO", imageClient);
            parameters.put("SEL_PROJECT", project);
            parameters.put("SEL_TESTER", tester);

            parameters.put("SEL_LABEL_EVINDENCE_TITLE", properties.getProperty("label.evidenceReport"));
            parameters.put("SEL_LABEL_PROJECT", properties.getProperty("label.projetc"));
            parameters.put("SEL_LABEL_TESTER", properties.getProperty("label.tester"));
            parameters.put("SEL_LABEL_STATUS", properties.getProperty("label.status"));
            parameters.put("SEL_LABEL_PASS", properties.getProperty("label.status.pass"));
            parameters.put("SEL_LABEL_FAILED", properties.getProperty("label.status.failed"));
            parameters.put("SEL_LABEL_EVIDENCE_REPORT", properties.getProperty("label.evidenceReport"));
            parameters.put("SEL_LABEL_DATE", properties.getProperty("label.date"));
            parameters.put("SEL_LABEL_FOOTER", properties.getProperty("label.footer"));
            parameters.put("SEL_LABEL_ERROR_DETAIL", properties.getProperty("label.errorDetail"));
            parameters.put("SEL_LABEL_PAGE", properties.getProperty("label.page"));
            parameters.put("SEL_LABEL_COMPANY_NAME", properties.getProperty("label.company.name"));

            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(data);
            JasperReport jasperReport = JasperCompileManager.compileReport(properties.getProperty("evidence.file"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, evidenceDir + reportName + ".pdf");

        } catch (SecurityException ex) {
            ex.printStackTrace();
        } catch (JRException jre) {
            jre.printStackTrace();
        }
    }

    /**
     * Create a directory with the project's name
     * @param project project name
     */
    private static boolean createDirToEvidence(String project) {
        boolean dirExists = false;
        
        try {
            File dir = new File(project);
            boolean exists = dir.exists();

            if (!exists) {
                dir.mkdir();
                dirExists = false;
            } else {
                dirExists = true;
            }
        } catch (SecurityException se) {
            se.printStackTrace();
        }
        return dirExists;
    }
}
