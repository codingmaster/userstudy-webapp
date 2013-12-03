package de.hpi.unipotsdam.thorben.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.hpi.unipotsdam.thorben.dto.CreateNewsThreadDto;
import de.hpi.unipotsdam.thorben.dto.NewsThreadDto;
import de.hpi.unipotsdam.thorben.entity.Article;
import de.hpi.unipotsdam.thorben.entity.NewsThread;
import de.hpi.unipotsdam.thorben.entity.ThreadItem;

@Path("threads")
public class ThreadsResource extends AbstractResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<NewsThreadDto> getAllThreads() {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    List threads = session.createCriteria(NewsThread.class).list();
    List<NewsThreadDto> result = new ArrayList<NewsThreadDto>();
    for (Object thread : threads) {
      NewsThreadDto dto = NewsThreadDto.fromNewsThread((NewsThread) thread);
      result.add(dto);
    }
    
    tx.commit();
    
    return result;
  }
  
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
