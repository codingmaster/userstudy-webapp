package de.hpi.unipotsdam.thorben.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.hpi.unipotsdam.thorben.dto.CreateNewsThreadDto;
import de.hpi.unipotsdam.thorben.entity.Article;
import de.hpi.unipotsdam.thorben.entity.NewsThread;
import de.hpi.unipotsdam.thorben.entity.ThreadItem;

@Path("threads")
public class ThreadsResource extends AbstractResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void createThread(CreateNewsThreadDto newsThreadDto) {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    NewsThread thread = new NewsThread();
    thread.setDescription(newsThreadDto.getDescription());
    thread.setTitle(newsThreadDto.getTitle());
    session.save(thread);
    
    for (Long id : newsThreadDto.getArticleIds()) {
      ThreadItem item = new ThreadItem();
      item.setThread(thread);
      
      Article article = (Article) session.get(Article.class, id);
      item.setArticle(article);
      session.save(item);
    }
    
    tx.commit();
  }
  
  @Path("{id}")
  public ThreadResource getThread(@PathParam("id") Long id) {
    ThreadResource resource = new ThreadResource(id);
    resource.setSessionHelper(sessionHelper);
    return resource;
  }
  
  
}
