package com.realtime.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RealtimeProperties {
    public Properties getProperties() throws IOException {
        InputStream resource = this.getClass().getResourceAsStream("app.properties");
        java.util.Properties properties = new java.util.Properties();
        properties.load(resource);
        return properties;
    }
}
