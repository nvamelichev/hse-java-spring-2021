package ru.hse.java.scraper.dagger;

import dagger.Binds;
import dagger.Module;
import ru.hse.java.scraper.BindsDemoInterface;
import ru.hse.java.scraper.impl.BindsDemo;

@Module
public abstract class BindsDemoModule {
    @Binds
    public abstract BindsDemoInterface someDemo(BindsDemo __);
}
