package fr.ensai.testTAA;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import web.rest.DemoResource;

public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
//    	System.err.println("MyApplicationBinder");
        bind(DemoResource.class).to(DemoResource.class);
    }
}