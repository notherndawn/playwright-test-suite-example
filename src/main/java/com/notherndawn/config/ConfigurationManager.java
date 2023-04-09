package com.notherndawn.config;

import org.aeonbits.owner.ConfigCache;

public class ConfigurationManager {
    private ConfigurationManager() {
    }

    public static Configuration getConfiguration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
