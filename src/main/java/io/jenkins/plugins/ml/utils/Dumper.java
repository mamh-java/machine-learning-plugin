/*
 * The MIT License
 *
 * Copyright 2020 Loghi Perinpanayagam.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.jenkins.plugins.ml.utils;

import hudson.FilePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Dumper- A helping tool for save html or image files in the workspace
 */
public final class Dumper {

    private static final Logger LOGGER = LoggerFactory.getLogger(Dumper.class);
    private static final DateTimeFormatter FORMAT_OBJ = DateTimeFormatter.ofPattern("dd-MM-yyyy-hh-mm-ss");

    private Dumper() {
    }

    /**
     * Dump html output from Zeppelin API as html files under the @param foldername .
     *
     * @param data       the data
     * @param foldername the folder name
     * @param ws         the workspace
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
    public static void dumpHtml(String data, String foldername, FilePath ws)
            throws IOException, InterruptedException {
        LocalDateTime dateObj = LocalDateTime.now();
        String filename = File.separator + FORMAT_OBJ.format(dateObj) + ".html";
        FilePath dumpPath = new FilePath(ws, foldername + filename);
        dumpPath.write(data, "UTF-8");
        // enable javascript to run
        System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "");
        LOGGER.info("Success");
    }

    /**
     * Dump image output from Zeppelin API as html files under the @param foldername .
     *
     * @param data       the data for the Image
     * @param foldername the folder name
     * @param ws         the workspace
     * @throws IOException raise when write fails
     */
    public static void dumpImage(BufferedImage data, String foldername, FilePath ws) throws IOException {
        LocalDateTime dateObj = LocalDateTime.now();
        String filename = File.separator + FORMAT_OBJ.format(dateObj) + ".jpg";
        FilePath dumpPath = new FilePath(ws, foldername + filename);
        ImageIO.write(data, "jpg", new File(dumpPath.getRemote()));
        LOGGER.info("Success");
  }
}