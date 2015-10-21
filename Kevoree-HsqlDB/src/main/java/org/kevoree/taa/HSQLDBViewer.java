package org.kevoree.taa;



import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;

import org.hsqldb.ClientConnection;
import org.hsqldb.DatabaseURL;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.persist.HsqlProperties;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.KevoreeInject;
import org.kevoree.annotation.Param;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;


@ComponentType
public class HSQLDBViewer {

	org.hsqldb.util.DatabaseManager manager = new org.hsqldb.util.DatabaseManager ();
	
    @Param(defaultValue = "9001")
    Integer port;

    @Param(defaultValue = "localhost")
    String host;

    @Param(defaultValue = "SA")
    String user;

    @Param(defaultValue = "")
    String pass;

    @Param(defaultValue = "kevdatabase")
    String databasename;

    
    @KevoreeInject
    org.kevoree.api.Context context;


    
    @Start
    public void start() {
    	
    	
    	try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	org.hsqldb.util.DatabaseManager manager = new org.hsqldb.util.DatabaseManager ();
		HsqlProperties props = DatabaseURL.parseURL("jdbc:hsqldb:hsql://"+host+":"+port+"/"+ databasename,true, false);
		Properties prop = new Properties();
		if (user != null)
			prop.put("user", user);
		if (pass != null)
			prop.put("password", pass);
		props.addProperties(prop);

		JDBCConnection connection = null;
		try {
			connection = new JDBCConnection(props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		manager.main();

		manager.connect(connection);

    	
    }

    @Stop
    public void stop() {
    	manager.hide();
    	manager.stop();

    	
    }

    @Update
    public void update() {
    	
    }

}

