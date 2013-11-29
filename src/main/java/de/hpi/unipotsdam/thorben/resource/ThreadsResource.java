package de.hpi.unipotsdam.thorben.resource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.hpi.unipotsdam.thorben.dto.NewsThreadDto;
import de.hpi.unipotsdam.thorben.entity.Article;
import de.hpi.unipotsdam.thorben.entity.NewsThread;

@Path("threads")
public class ThreadsResource extends AbstractResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void createThread(NewsThreadDto newsThreadDto) {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    NewsThread thread = new NewsThread();
    thread.setDescription(newsThreadDto.getDescription());
    thread.setTitle(newsThreadDto.getTitle());
    
    Set<Article> articles = new HashSet<Article>();
    for (Long articleId : newsThreadDto.getArticles()) {
      Article article = (Article) session.load(Article.class, articleId);
      if (article != null) {
        articles.add(article);
      }
    }
    
    thread.setArticles(articles);
    
    session.save(thread);
    tx.commit();
  }
  
  @Path("{id}")
  public ThreadResource getThread(@PathParam("id") Long id) {
    ThreadResource resource = new ThreadResource(id);
    resource.setSessionHelper(sessionHelper);
    return resource;
  }
  
  
}
