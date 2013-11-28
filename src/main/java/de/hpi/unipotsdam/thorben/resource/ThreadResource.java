package de.hpi.unipotsdam.thorben.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.hpi.unipotsdam.thorben.dto.NewsThreadDto;
import de.hpi.unipotsdam.thorben.entity.NewsThread;
import de.hpi.unipotsdam.thorben.entity.SessionFactoryUtil;

@Produces(MediaType.APPLICATION_JSON)
public class ThreadResource {

  private Long threadId;
  
  public ThreadResource(Long threadId) {
    this.threadId = threadId;
  }
  
  @GET
  public NewsThreadDto getThread() {
    Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();
    NewsThread thread = (NewsThread) session.get(NewsThread.class, threadId);
    tx.commit();
    
    if (thread == null) {
      // TODO do exception handling, throw 404
    }
    
    return NewsThreadDto.fromNewsThread(thread);
  }
}
