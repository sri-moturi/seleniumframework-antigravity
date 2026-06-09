package com.tecskool.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            if (input == null) {
                logger.fatal("Unable to find config.properties in the classpath at config/config.properties");
                throw new RuntimeException("config.properties not found in classpath at config/config.properties");
            }
            properties.load(input);
            logger.info("Loaded config.properties successfully from classpath");
        } catch (IOException ex) {
            logger.fatal("Failed to load config.properties from classpath", ex);
            throw new RuntimeException("Failed to load config.properties", ex);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property key '{}' not found in config.properties", key);
        }
        return value;
    }

    public static String getBaseUrl() {
        return getProperty("baseUrl");
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static int getImplicitWait() {
        String implicitWait = getProperty("implicitWait");
        try {
            return Integer.parseInt(implicitWait);
        } catch (NumberFormatException e) {
            logger.warn("Invalid implicitWait value, defaulting to 10 seconds: {}", implicitWait);
            return 10;
        }
    }

    public static int getExplicitWait() {
        String explicitWait = getProperty("explicitWait");
        try {
            return Integer.parseInt(explicitWait);
        } catch (NumberFormatException e) {
            logger.warn("Invalid explicitWait value, defaulting to 15 seconds: {}", explicitWait);
            return 15;
        }
    }

    public static int getStepDelay() {
        String stepDelay = getProperty("stepDelay");
        try {
            return stepDelay != null ? Integer.parseInt(stepDelay) : 0;
        } catch (NumberFormatException e) {
            logger.warn("Invalid stepDelay value, defaulting to 0 ms: {}", stepDelay);
            return 0;
        }
    }

    public static String getUsername() {
        return getProperty("username");
    }

    public static String getPassword() {
        return getProperty("password");
    }
}
