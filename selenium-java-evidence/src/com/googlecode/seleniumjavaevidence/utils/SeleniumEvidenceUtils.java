package com.googlecode.seleniumjavaevidence.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class with shared methods and action to Selenium Evidence
 * @author Elias Nogueira <elias.nogueira@gmail.com>
 * @author Selenium Java Evidence <http://code.google.com/p/selenium-java-evidence>
 */
public class SeleniumEvidenceUtils {

    /**
     * Properties that loads report parameterization
     */
    static Properties properties;


    /**
     * Load Selenium Evidence property file
     * @return a property
     * @throws IOException if file do not exists
     */
    public static Properties loadProperties() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("init.properties"));

        return properties;
    }

}
