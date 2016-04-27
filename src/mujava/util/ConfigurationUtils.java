/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mujava.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import mujava.MutationSystem;

/**
 *
 * @author Jackson Lima
 */
public class ConfigurationUtils {
  
    public static boolean debug = false;
    public static String muJavaHomePath = "";
    
    public static Properties getFromFile(String filename) throws FileNotFoundException, IOException {
        Properties props = new Properties();

        FileInputStream file = new FileInputStream(filename);
        props.load(file);

        file.close();

        return props;
    }

     /*
     * load config file
     */
    public static void loadCLIConfig() throws IOException {
        Properties properties = getFromFile(System.getProperty("user.dir") + "/configuration/mujavaCLI.properties");
        
        ConfigurationUtils.muJavaHomePath = properties.getProperty("MuJava_HOME");
        String debug = properties.getProperty("Debug_mode");

        if (debug != null && debug.equals("true")) {
            ConfigurationUtils.debug = true;
        } else {
            ConfigurationUtils.debug = false;
        }
    }
    
    public static void loadConfig() throws IOException {
        Properties properties = getFromFile(System.getProperty("user.dir") + "/configuration/mujava.properties");
        ConfigurationUtils.muJavaHomePath = properties.getProperty("MuJava_HOME");
    }    
}
