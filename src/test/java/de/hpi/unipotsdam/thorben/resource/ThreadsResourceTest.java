package de.hpi.unipotsdam.thorben.resource;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import com.jayway.restassured.http.ContentType;

import de.hpi.unipotsdam.thorben.entity.NewsThread;
import de.hpi.unipotsdam.thorben.entity.SessionFactoryUtil;

public class ThreadsResourceTest extends AbstractRestTest {

  protected String THREAD_RESOURCE_PATH = ROOT_RESOURCE_PATH + "/threads";
  
  @Test
  public void testThreadCreation() {
    Map<String, Object> threadJson = new HashMap<String, Object>();
    threadJson.put("title", "a thread title");
    threadJson.put("description", "a description");
    
    given().contentType(ContentType.JSON).body(threadJson)
      .then().expect().statusCode(Status.NO_CONTENT.getStatusCode())
      .when().post(THREAD_RESOURCE_PATH);
    
    Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();
    List threads = session.createCriteria(NewsThread.class).list();
    
    Assert.assertEquals(1, threads.size());
    
    NewsThread thread = (NewsThread) threads.get(0);
    Assert.assertEquals("a thread title", thread.getTitle());
    Assert.assertEquals("a description", thread.getDescription());
    Assert.assertEquals(0, thread.getArticles().size());
    
    tx.commit();
  }
}
