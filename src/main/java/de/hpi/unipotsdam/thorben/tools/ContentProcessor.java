package de.hpi.unipotsdam.thorben.tools;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.hpi.unipotsdam.thorben.entity.Article;
import de.hpi.unipotsdam.thorben.entity.SessionHelper;

public class ContentProcessor {

  private static final String APPLICATION_CONTEXT_FILE = "spring/applicationContext.xml";
  
  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_FILE);
    SessionHelper sessionHelper = applicationContext.getBean(SessionHelper.class);
    
    ContentFormatter formatter = new ContentFormatter();
    
    Session session = sessionHelper.getSessionFactory().openSession();
    
    Transaction tx = session.beginTransaction();
    
    try {
      ScrollableResults articles = session.createCriteria(Article.class).scroll(ScrollMode.FORWARD_ONLY);
      
      while (articles.next()) {
        Article article = (Article) articles.get(0);
        String formattedAbstract = formatter.formatArticleAbstract(article.getAbstractContent());
        article.setHtmlAbstractContent(formattedAbstract);
        session.save(article);
      }
    } catch (Exception e) {
      tx.rollback();
      session.close();
      throw e;
    } finally {
      tx.commit();
      session.close();      
    }
    
    
    
    
    
  }
}
