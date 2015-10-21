package org.kevoree.taa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.hsqldb.DatabaseURL;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.KevoreeInject;
import org.kevoree.annotation.Param;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;

@ComponentType
public class HSQLDBServer {

	org.hsqldb.Server server = new org.hsqldb.Server();;

	@Param(defaultValue = "9001")
	Integer port;

	@Param(defaultValue = "/tmp")
	String path;

	@Param(defaultValue = "KevDataBase")
	String databasename;

	@Param(defaultValue = "SA")
	String user;

	@Param(defaultValue = "")
	String pass;

	@KevoreeInject
	org.kevoree.api.Context context;

	/*
	 * public static void main(String[] args) { org.hsqldb.Server server = new
	 * org.hsqldb.Server(); server.setDatabaseName(0, "KevDataBase");
	 * server.setDatabasePath(0, "/tmp/testKevoree");
	 * 
	 * server.start(); }
	 */

	@Start
	public void start() {
		// server.setPort(port);
		HsqlProperties props = DatabaseURL.parseURL("jdbc:hsqldb:hsql://localhost" + ":" + port + "/" + databasename,
				true, false);
		Properties prop = new Properties();
		if (user != null)
			prop.put("user", user);
		if (pass != null)
			prop.put("password", pass);
		props.addProperties(prop);

		try {
			server.setProperties(props);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AclFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.setDatabaseName(0, databasename);
		server.setDatabasePath(0, "/tmp/" + context.getInstanceName());
		server.start();

	}

	@Stop
	public void stop() {
		server.stop();

	}

	@Update
	public void update() {
		System.out.println("Param updated!");
	}

}
