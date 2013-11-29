package de.hpi.unipotsdam.thorben.resource;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hpi.unipotsdam.thorben.dto.NewsThreadDto;
import de.hpi.unipotsdam.thorben.entity.Article;
import de.hpi.unipotsdam.thorben.entity.NewsThread;
import de.hpi.unipotsdam.thorben.entity.SessionHelper;

public class ThreadsResourceLogicTest {

  private Article article;
  private static SessionHelper sessionHelper;
  
  @BeforeClass
  public static void createSessionHelper() {
    sessionHelper = new SessionHelper();
    sessionHelper.setSessionFactory(TestUtil.getSessionFactory());
  }
  
  @Before
  public void setup() {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    article = new Article();
    article.setContent("some content");
    article.setTitle("some title");
    session.save(article);
    
    tx.commit();
  }
  
  @Test
  public void testThreadCreation() {
    ThreadsResource threadsResource = new ThreadsResource();
    threadsResource.setSessionHelper(sessionHelper);
    
    NewsThreadDto dto = new NewsThreadDto();
    dto.setTitle("some title");
    dto.setDescription("some description");
    
    List<Long> articleIds = new ArrayList<Long>();
    articleIds.add(article.getId());
    dto.setArticles(articleIds);
    
    threadsResource.createThread(dto);

    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    List threads = session.createCriteria(NewsThread.class).list();
    
    Assert.assertEquals(1, threads.size());
    
    NewsThread thread = (NewsThread) threads.get(0);
    Assert.assertEquals("some title", thread.getTitle());
    Assert.assertEquals("some description", thread.getDescription());
    Assert.assertEquals(1, thread.getArticles().size());
    
    tx.commit();
  }
  
}
