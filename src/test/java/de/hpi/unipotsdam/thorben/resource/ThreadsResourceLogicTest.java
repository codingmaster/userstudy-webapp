package de.hpi.unipotsdam.thorben.resource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hpi.unipotsdam.thorben.dto.CreateNewsThreadDto;
import de.hpi.unipotsdam.thorben.dto.ThreadItemDto;
import de.hpi.unipotsdam.thorben.entity.Article;
import de.hpi.unipotsdam.thorben.entity.NewsThread;
import de.hpi.unipotsdam.thorben.entity.SessionHelper;
import de.hpi.unipotsdam.thorben.entity.ThreadItem;

public class ThreadsResourceLogicTest {

  private Article article;
  private Article article2;
  private Article article3;
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
    
    article2 = new Article();
    article2.setContent("some more content");
    article2.setTitle("another title");
    session.save(article2);
    
    article3 = new Article();
    article3.setContent("third content");
    article3.setTitle("a third title");
    session.save(article3);
    
    tx.commit();
  }
  
  @Test
  public void testThreadCreation() {
    ThreadsResource threadsResource = new ThreadsResource();
    threadsResource.setSessionHelper(sessionHelper);
    
    CreateNewsThreadDto dto = new CreateNewsThreadDto();
    dto.setTitle("some title");
    dto.setDescription("some description");
    
    List<Long> articleIds = new ArrayList<Long>();
    articleIds.add(article.getId());
    dto.setArticleIds(articleIds);
    
    Transaction tx = sessionHelper.getCurrentSession().beginTransaction();
    threadsResource.createThread(dto);
    tx.commit();

    Session session = sessionHelper.getCurrentSession();
    tx = session.beginTransaction();
    List threads = session.createCriteria(NewsThread.class).list();
    
    Assert.assertEquals(1, threads.size());
    
    NewsThread thread = (NewsThread) threads.get(0);
    Assert.assertEquals("some title", thread.getTitle());
    Assert.assertEquals("some description", thread.getDescription());
    Assert.assertEquals(1, thread.getItems().size());
    
    ThreadItem item = thread.getItems().toArray(new ThreadItem[]{})[0];
    Assert.assertEquals(article.getId(), item.getArticle().getId());
    Assert.assertEquals(article.getContent(), item.getArticle().getContent());
    Assert.assertEquals(article.getTitle(), item.getArticle().getTitle());
    
    tx.commit();
  }
  
  @Test
  public void testThreadSelectionByOrder() {
    ThreadItem item = new ThreadItem();
    item.setArticle(article);
    item.setLogicalOrder(3);
    
    ThreadItem item2 = new ThreadItem();
    item2.setArticle(article2);
    item2.setLogicalOrder(1);
    
    ThreadItem item3 = new ThreadItem();
    item3.setArticle(article3);
    item3.setLogicalOrder(2);
    
    NewsThread thread = new NewsThread();
    item.setThread(thread);
    item2.setThread(thread);
    item3.setThread(thread);
    
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    session.save(thread);
    session.save(item);
    session.save(item2);
    session.save(item3);
    tx.commit();
    
    ThreadResource resource = new ThreadResource();
    resource.setSessionHelper(sessionHelper);
    
    session = sessionHelper.getCurrentSession();
    tx = session.beginTransaction();
    List<ThreadItemDto> returnedItems = resource.getItems(thread.getId(), "logical_order");
    tx.commit();
    
    Assert.assertEquals(3, returnedItems.size());
    Assert.assertEquals(new Integer(1), returnedItems.get(0).getLogicalOrder());
    Assert.assertEquals(new Integer(2), returnedItems.get(1).getLogicalOrder());
    Assert.assertEquals(new Integer(3), returnedItems.get(2).getLogicalOrder());
    
  }
  
}
