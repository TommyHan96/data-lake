package com.realtime.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RealtimeProperties {
    public Properties getProperties() throws IOException {
        InputStream resource = this.getClass().getResourceAsStream("app.properties");
        Properties properties = new Properties();
        properties.load(resource);
        return properties;
    }
}
