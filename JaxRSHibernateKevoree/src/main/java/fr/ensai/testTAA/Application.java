package fr.ensai.testTAA;

import java.lang.reflect.Field;

import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.hk2.internal.ServiceLocatorFactoryImpl;
import org.glassfish.jersey.servlet.ServletContainer;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletInfo;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class Application {  

    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        new Application();
    }

    public Application() throws Exception{
    	ServletInfo s = Servlets.servlet("Jersey", ServletContainer.class)
                .addInitParam("javax.ws.rs.Application", "fr.ensai.testTAA.JerseyApp")
                .addMapping("/*");
    
    	
    	System.err.println(Application.class.getClassLoader());
        DeploymentInfo servletBuilder = Servlets.deployment()
                .setClassLoader((Application.class.getClassLoader()))
                .setContextPath("/")
                .setDeploymentName("Undertow Throwdown")
                .addServlet(s
                );

        
        Field hack;
		try {
			hack = ServiceLocatorFactoryImpl.class.getDeclaredField("defaultGenerator");
	        hack.setAccessible(true);
	        System.err.println(hack.get( ServiceLocatorFactory.getInstance()));

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
		}
		
        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        manager.deploy();
        PathHandler path = Handlers.path(Handlers.redirect("/"))
                .addPrefixPath("/", manager.start());

        Undertow server = Undertow.builder()
                .addHttpListener(PORT, "localhost")
                .setHandler(path)
                .build();

        System.out.println("Started Undertow On Port " + PORT);
        server.start();

    }
}