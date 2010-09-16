package com.googlecode.seleniumjavaevidence.selenium;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;

/**
 * Bean to store evidence message and image in a BASE64Decoder
 *
 * @author Elias Nogueira <elias.nogueira@gmai.com>
 * @author Selenium Java Evidence <http://code.google.com/p/selenium-java-evidence>
 */
public final class SeleniumEvidence {

    /**
     * Evidence message
     */
    private String sel_message;

    /**
     * String returned by Selenium
     */
    private String sel_image_string;

    /**
     * Image to use in evidence report
     */
    private BufferedImage sel_image;

    /**
     * Constructor to create a new instance of the evidence data
     * @param sel_message
     * @param sel_image_string
     * @throws Exception
     */
    public SeleniumEvidence(String sel_message, String sel_image_string) throws Exception {
        setSel_message(sel_message);
        setSel_image_string(sel_image_string);
    }

    /**
     * Get the evidence message
     * @return the sel_message
     */
    public String getSel_message() {
        return sel_message;
    }

    /**
     * Set the evidence message
     * @param sel_message the sel_message to set
     */
    public void setSel_message(String sel_message) {
        this.sel_message = sel_message;
    }

    /**
     * Get the image
     * @return the sel_image
     */
    public BufferedImage getSel_image() {
        return sel_image;
    }

    /**
     * Set the image
     * @param sel_image the sel_image to set
     */
    public void setSel_image(BufferedImage sel_image) {
        this.sel_image = sel_image;
    }

    /**
     * Get the image in BASE64Decoder
     * @return the sel_image_string
     */
    public String getSel_image_string() {
        return sel_image_string;
    }

    /**
     * Creates a ImageIO based on string coming to Selenium
     * @param sel_image_string the sel_image_string to set
     * @throws IOException if occurs any problem with the directory
     * @throws Exception if occurs any other problem with the code
     */
    public void setSel_image_string(String sel_image_string) throws IOException, Exception {
        this.sel_image_string = sel_image_string;
        setSel_image(ImageIO.read(new ByteArrayInputStream(toImage(sel_image_string))));
    }

    /**
     * Transform the string returned in BASE64Decoder
     * @param string string returned by Selenium
     * @return an instance of BASE64Decoder
     * @throws Exception if the string is malformed or null
     */
    public static byte[] toImage(String string) throws Exception {
        return new BASE64Decoder().decodeBuffer(string);
    }
}
