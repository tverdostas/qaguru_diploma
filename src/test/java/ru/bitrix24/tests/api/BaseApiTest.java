package ru.bitrix24.tests.api;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bitrix24.BaseUiTest;
import ru.bitrix24.config.AppConfig;

public abstract class BaseApiTest {
    protected static AppConfig config;
    public static final Logger log = LoggerFactory.getLogger(BaseUiTest.class);

    @BeforeAll
    static void setUpConfig() {
        config = ConfigFactory.create(AppConfig.class, System.getProperties());
    }
}
