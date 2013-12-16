package de.hpi.unipotsdam.thorben.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import de.hpi.unipotsdam.thorben.dto.NewsThreadDto;
import de.hpi.unipotsdam.thorben.dto.ThreadItemDto;
import de.hpi.unipotsdam.thorben.entity.NewsThread;
import de.hpi.unipotsdam.thorben.entity.ThreadItem;
import de.hpi.unipotsdam.thorben.exception.RestException;

@Produces(MediaType.APPLICATION_JSON)
public class ThreadResource extends AbstractResource {


  @GET
  @Transactional(readOnly = true)
  public NewsThreadDto getThread(@PathParam("id") Long threadId) {
    Session session = sessionHelper.getCurrentSession();
    NewsThread thread = (NewsThread) session.get(NewsThread.class, threadId);
    
    if (thread == null) {
      throw new RestException("News thread with id " + threadId + " does not exist", Status.NOT_FOUND);
    }
    
    NewsThreadDto dto = NewsThreadDto.fromNewsThread(thread);
    return dto;
  }
  
  @GET
  @Path("items")
  @Transactional(readOnly = true)
  public List<ThreadItemDto> getItems(@PathParam("id") Long threadId) {
    Session session = sessionHelper.getCurrentSession();
    
    Query itemQuery = session.getNamedQuery("ThreadItem.selectArticlesForThread");
    itemQuery.setParameter("threadId", threadId);
    List<ThreadItem> items = itemQuery.list();
    
    List<ThreadItemDto> itemDtos = new ArrayList<ThreadItemDto>();
    for (ThreadItem item : items) {
      itemDtos.add(ThreadItemDto.fromThreadItem(item));
    }
    
    return itemDtos;
  }
}
