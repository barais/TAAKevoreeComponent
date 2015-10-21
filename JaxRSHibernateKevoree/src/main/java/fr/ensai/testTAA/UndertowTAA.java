package fr.ensai.testTAA;
 


import org.glassfish.jersey.servlet.ServletContainer;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Input;
import org.kevoree.annotation.KevoreeInject;
import org.kevoree.annotation.Output;
import org.kevoree.annotation.Param;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletInfo;


@ComponentType 
public class UndertowTAA { 

    @Param(defaultValue = "Default Content")
    String message;

    @Param(defaultValue = "8080")
    Integer port;

    @KevoreeInject
    org.kevoree.api.Context context;

    @Output
    org.kevoree.api.Port out;

    @Input
    public void in(Object i) {
    	
    }

    @Start
    public void start() {
    	
    	ServletInfo s = Servlets.servlet("Jersey", ServletContainer.class)
                .addInitParam("javax.ws.rs.Application", "fr.ensai.testTAA.JerseyApp")
                .addMapping("/*");
    
    	
        DeploymentInfo servletBuilder = Servlets.deployment()
                .setClassLoader(Application.class.getClassLoader())
                .setContextPath("/")
                .setDeploymentName("Undertow Throwdown")

                .addServlet(s
                );
        
        
        //ServiceLoader.initialize();
   	    
     /*   
        Field hack;
		try {
			hack = ServiceLocatorFactoryImpl.class.getDeclaredField("defaultGenerator");
	        hack.setAccessible(true);
	        hack.set( ServiceLocatorFactory.getInstance(), new MyServiceLocatorGenerator());

		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
               
        //ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        
        
        //servletBuilder.addServletContextAttribute(ServletProperties.SERVICE_LOCATOR, locator);
        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        manager.deploy();
        
        PathHandler path = null;

        try{
        	path= Handlers.path(Handlers.redirect("/"))
                .addPrefixPath("/", manager.start());
        }catch (Exception e){
        	e.printStackTrace();
        }

        Undertow server = Undertow.builder()
                .addHttpListener(port, "localhost")
                .setHandler(path)
                .build();

        server.start();


    /*   BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("0.0.0-alpha");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage("web.rest");
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);*/


    	
    	
    	
    }

    @Stop
    public void stop() {}

    @Update
    public void update() {
    	
    	
    }

}

