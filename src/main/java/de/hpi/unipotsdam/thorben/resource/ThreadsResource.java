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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import de.hpi.unipotsdam.thorben.dto.CreateNewsThreadDto;
import de.hpi.unipotsdam.thorben.dto.NewsThreadDto;
import de.hpi.unipotsdam.thorben.entity.Article;
import de.hpi.unipotsdam.thorben.entity.NewsThread;
import de.hpi.unipotsdam.thorben.entity.ThreadItem;

@Path("threads")
public class ThreadsResource extends AbstractResource {

  private ThreadResource threadResource;
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional(readOnly = true)
  public List<NewsThreadDto> getAllThreads() {
    Session session = sessionHelper.getCurrentSession();
    
    List threads = session.createCriteria(NewsThread.class).list();
    List<NewsThreadDto> result = new ArrayList<NewsThreadDto>();
    for (Object thread : threads) {
      NewsThreadDto dto = NewsThreadDto.fromNewsThread((NewsThread) thread);
      result.add(dto);
    }
    
    return result;
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Transactional
  public void createThread(CreateNewsThreadDto newsThreadDto) {
    Session session = sessionHelper.getCurrentSession();
    
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
  }
  
  @Path("{id}")
  public ThreadResource getThread() {
    
    return threadResource;
  }

  public ThreadResource getThreadResource() {
    return threadResource;
  }

  public void setThreadResource(ThreadResource threadResource) {
    this.threadResource = threadResource;
  }
  
}
