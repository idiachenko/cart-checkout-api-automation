package sephora.cartcheckout.jupiter.extension;

import org.junit.platform.engine.ConfigurationParameters;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;

import static sephora.cartcheckout.config.ApplicationConfig.config;

public class CustomParallelExecutionStrategy implements ParallelExecutionConfiguration, ParallelExecutionConfigurationStrategy {

    @Override
    public int getParallelism() {
        return config.getNumberOfThreads();
    }

    @Override
    public int getMinimumRunnable() {
        return config.getNumberOfThreads();
    }

    @Override
    public int getMaxPoolSize() {
        return config.getNumberOfThreads();
    }

    @Override
    public int getCorePoolSize() {
        return config.getNumberOfThreads();
    }

    @Override
    public int getKeepAliveSeconds() {
        return config.getNumberOfThreads();
    }

    @Override
    public ParallelExecutionConfiguration createConfiguration(ConfigurationParameters configurationParameters) {
        return this;
    }

}
