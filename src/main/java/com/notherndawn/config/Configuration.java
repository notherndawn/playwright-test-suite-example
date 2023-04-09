package com.notherndawn.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:test.properties"})
public interface Configuration extends Config {
    @Key("base.url")
    String baseUrl();

    @Key("password")
    String password();

    @Key("username")
    String username();
}