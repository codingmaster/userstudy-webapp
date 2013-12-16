package de.hpi.unipotsdam.thorben.resource;

import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.jayway.restassured.RestAssured;

import de.unipotsdam.hpi.thorben.MockUserStudyApplication;

public abstract class AbstractRestTest {

  protected static final int TEST_HTTP_PORT = 8080;
  protected static final String ROOT_RESOURCE_PATH = "/rest-test";
  
  protected static NettyJaxrsServer server;
  
  @BeforeClass
  public static void setUp() {
    RestAssured.port = TEST_HTTP_PORT;
    
    server = new NettyJaxrsServer();
    server.setRootResourcePath(ROOT_RESOURCE_PATH);
    server.setPort(TEST_HTTP_PORT);
    
    server.getDeployment().setApplication(new MockUserStudyApplication());
    
    server.start();
  }
  
  @AfterClass
  public static void tearDown() {
    server.stop();
  }
}
