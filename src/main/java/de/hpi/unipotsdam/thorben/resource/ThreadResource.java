package de.hpi.unipotsdam.thorben.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.hpi.unipotsdam.thorben.dto.NewsThreadDto;
import de.hpi.unipotsdam.thorben.dto.ThreadItemDto;
import de.hpi.unipotsdam.thorben.entity.NewsThread;
import de.hpi.unipotsdam.thorben.entity.ThreadItem;
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
    
    if (thread == null) {
      throw new RestException("News thread with id " + threadId + " does not exist", Status.NOT_FOUND);
    }
    
    NewsThreadDto dto = NewsThreadDto.fromNewsThread(thread);
    tx.commit();

    return dto;
  }
  
  @GET
  @Path("items")
  public List<ThreadItemDto> getItems() {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    Query itemQuery = session.getNamedQuery("ThreadItem.selectArticlesForThread");
    itemQuery.setParameter("threadId", threadId);
    List<ThreadItem> items = itemQuery.list();
    
    List<ThreadItemDto> itemDtos = new ArrayList<ThreadItemDto>();
    for (ThreadItem item : items) {
      itemDtos.add(ThreadItemDto.fromThreadItem(item));
    }
    
    tx.commit();
    
    return itemDtos;
    
    
  }
}
