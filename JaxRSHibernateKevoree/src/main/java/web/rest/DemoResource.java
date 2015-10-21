package web.rest;


import java.util.Set;
import java.util.TreeSet;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import domain.DemoBean;

@Path("/bar")
@Singleton
@Produces("application/json")
public class DemoResource   {

        private static final Integer NumberOfNodes = 1000000;
        private Set<DemoBean> dataSource;

        public DemoResource() {
            dataSource = new TreeSet<DemoBean>();

            for (int i = 0 ; i < NumberOfNodes ; i++) {
                dataSource.add(new DemoBean(String.valueOf(i)));
            }
        }

        @GET
        public String hello(){
        	return "bar";
        }


    @GET
    @Path("/foo")
    @Produces("application/json")
    public DemoBean hello1() {

        return new DemoBean("foo");
    }

}
