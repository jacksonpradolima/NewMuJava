/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mujava.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * Files Util
 *
 * @author Thiago Nascimento
 * @since 2015-09-14
 * @version 1.0
 */
public class FilesUtils {

    public static List<String> getFiles(final File folder, String startWith) {

        List<String> files = new ArrayList<String>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                files.addAll(getFiles(fileEntry, startWith));
            } else if (fileEntry.getName().startsWith(startWith)) {
                files.add(fileEntry.getAbsolutePath());
            }
        }

        return files;
    }

    public static List<String> getFiles(final File folder, String startWith, String endWith) {

        List<String> files = new ArrayList<String>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                files.addAll(getFiles(fileEntry, startWith, endWith));
            } else if (fileEntry.getName().startsWith(startWith) && fileEntry.getName().endsWith(endWith)) {
                files.add(fileEntry.getAbsolutePath());
            }
        }

        return files;
    }

    public static List<String> getFilesIgnore(final File folder, String startWith, String ignoreEndWith) {

        List<String> files = new ArrayList<String>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                files.addAll(getFilesIgnore(fileEntry, startWith, ignoreEndWith));
            } else if (fileEntry.getName().startsWith(startWith) && !fileEntry.getName().endsWith(ignoreEndWith)) {
                files.add(fileEntry.getAbsolutePath());
            }
        }

        return files;
    }

    public static List<String> getFilesNames(final File folder, String startWith) {

        List<String> files = new ArrayList<String>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                files.addAll(getFiles(fileEntry, startWith));
            } else if (fileEntry.getName().startsWith(startWith)) {
                files.add(fileEntry.getName());
            }
        }

        return files;
    }
    
    public static List<String> getFilesNames(final File folder, String startWith, String endWith) {

        List<String> files = new ArrayList<String>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                files.addAll(getFiles(fileEntry, startWith));
            } else if (fileEntry.getName().startsWith(startWith) && fileEntry.getName().endsWith(endWith)) {
                files.add(fileEntry.getName());
            }
        }

        return files;
    }

    public static String getShortPath(String path, String filename) {
        String absolutePath = new File(path).getAbsolutePath();
        return filename.replace(absolutePath, "");
    }

    public static void printToFile(String filename, double value) throws IOException {
        printToFile(new File(filename), String.valueOf(value));
    }

    public static void printToFile(String filename, String content) throws IOException {
        printToFile(new File(filename), content);
    }

    public static void printToFile(File file, String content) throws IOException {
        FileUtils.writeStringToFile(file, content);
    }

    /*
     * Compile a java file and put it into a destination folder
     */
    public static boolean compileJavaFile(String destinationPath, String filePath) {
        com.sun.tools.javac.Main javac = new com.sun.tools.javac.Main();

        String[] args = new String[]{"-d", destinationPath, filePath};
        int status = javac.compile(args);

        if (status != 0) {
            System.err.println("Can't compile src file, please compile manually.");
            return false;
        } else {
            System.out.println("Source file is compiled successfully.");
        }

        return true;
    }

    /**
     * Generate class name from file name
     *
     * @param file_name
     * @return Class Name
     */
    public static String generateClassNameFromFileName(String file_name) {
        String temp = file_name.substring(0, file_name.length() - ".java".length());
        String class_name = "";

        for (int j = 0; j < temp.length(); j++) {
            if ((temp.charAt(j) == '\\') || (temp.charAt(j) == '/')) {
                class_name = class_name + ".";
            } else {
                class_name = class_name + temp.charAt(j);
            }
        }

        return class_name;
    }
}
