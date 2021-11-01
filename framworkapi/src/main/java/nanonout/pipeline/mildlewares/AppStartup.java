package nanonout.pipeline.mildlewares;

import nanonout.filters.AppFilter;

public abstract class AppStartup extends AppFilter implements IStartup {

    public AppStartup(String basePackage) {
        super(basePackage);
    }
}
