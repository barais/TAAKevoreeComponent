package fr.ensai.testTAA;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import web.rest.DemoResource;

@ApplicationPath("")
public class JerseyApp extends ResourceConfig {  
    public JerseyApp() {
    	register(DemoResource.class);
        register(new MyApplicationBinder());
        //       packages(true, "web.rest");

    }
}