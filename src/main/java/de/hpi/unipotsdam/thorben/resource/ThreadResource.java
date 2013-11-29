package de.hpi.unipotsdam.thorben.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.hpi.unipotsdam.thorben.dto.NewsThreadDto;
import de.hpi.unipotsdam.thorben.entity.NewsThread;
import de.hpi.unipotsdam.thorben.exception.RestException;

@Produces(MediaType.APPLICATION_JSON)
public class ThreadResource extends AbstractResource {

  private Long threadId;
  
  public ThreadResource(Long threadId) {
    this.threadId = threadId;
  }
  
  @GET
  public NewsThreadDto getThread() {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    NewsThread thread = (NewsThread) session.get(NewsThread.class, threadId);
    tx.commit();
    
    if (thread == null) {
      throw new RestException("News thread with id " + threadId + " does not exist", Status.NOT_FOUND);
    }
    
    return NewsThreadDto.fromNewsThread(thread);
  }
}
