package de.hpi.unipotsdam.thorben.resource;

import static com.jayway.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import com.jayway.restassured.http.ContentType;

public class ThreadsResourceApiTest extends AbstractRestTest {

  protected String THREAD_RESOURCE_PATH = ROOT_RESOURCE_PATH + "/threads";
  
  
  @Test
  public void testThreadCreation() {
    Map<String, Object> threadJson = new HashMap<String, Object>();
    threadJson.put("title", "a thread title");
    threadJson.put("description", "a description");
    
    List<Long> articlesJson = new ArrayList<Long>();
    articlesJson.add(new Long(1));
    
    threadJson.put("articles", articlesJson);
    
    given().contentType(ContentType.JSON).body(threadJson)
      .then().expect().statusCode(Status.NO_CONTENT.getStatusCode())
      .when().post(THREAD_RESOURCE_PATH);
  }
  
}
