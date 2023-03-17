package sephora.cartcheckout.config;

import org.aeonbits.owner.ConfigFactory;

public class ApplicationConfig {

    public static final AppConfig config = ConfigFactory.create(AppConfig.class, System.getProperties());
}