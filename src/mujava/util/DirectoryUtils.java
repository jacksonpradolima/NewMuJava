/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mujava.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jackson Lima
 */
public class DirectoryUtils {

    public static void prepareOutputDirectory(String dir) {
        if (experimentDirectoryDoesNotExist(dir)) {
            createExperimentDirectory(dir);
        }
    }

    public static boolean experimentDirectoryDoesNotExist(String dir) {
        boolean result;
        File experimentDirectory;

        experimentDirectory = new File(dir);
        if (experimentDirectory.exists() && experimentDirectory.isDirectory()) {
            result = false;
        } else {
            result = true;
        }

        return result;
    }

    public static void createExperimentDirectory(String dir) {
        File experimentDirectory;
        experimentDirectory = new File(dir);

        if (experimentDirectory.exists()) {
            experimentDirectory.delete();
        }

        experimentDirectory.mkdirs();
    }

    public static void createSessionDirectory(String muJavaHomePath, String sessionName) {
        String mutant_path = muJavaHomePath + "/" + sessionName;

        createExperimentDirectory(mutant_path);
        createExperimentDirectory(mutant_path + "/src");
        createExperimentDirectory(mutant_path + "/classes");
        createExperimentDirectory(mutant_path + "/result");
        createExperimentDirectory(mutant_path + "/testset");
    }

    public static List<File> getFromLevel(String startDir, Integer levels) {
        File[] dirsObj = new File(startDir).listFiles(File::isDirectory);
        List<File> files = Arrays.asList(dirsObj);

        for (int i = 1; i < levels; i++) {
            files = getFilesFromDir(files);
        }

        return files;
    }

    public static List<File> getFilesFromDir(List<File> files) {
        List<File> result = new ArrayList();

        for (File file : files) {
            File[] dirsObj = new File(file.getPath()).listFiles(File::isDirectory);
            result.addAll(Arrays.asList(dirsObj));
        }

        return result;
    }

    public static void deleteDirectory(String directory) throws IOException {
        deleteDirectory(Paths.get(directory));
    }

    public static void deleteDirectory(Path directory) throws IOException {
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }

        });
    }
}
