package de.hpi.unipotsdam.thorben.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.hpi.unipotsdam.thorben.dto.NewsThreadDto;
import de.hpi.unipotsdam.thorben.entity.NewsThread;
import de.hpi.unipotsdam.thorben.entity.SessionFactoryUtil;

@Path("threads")
public class ThreadsResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void createThread(NewsThreadDto newsThreadDto) {
    Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    NewsThread thread = new NewsThread();
    thread.setDescription(newsThreadDto.getDescription());
    thread.setTitle(newsThreadDto.getTitle());
    session.save(thread);
    tx.commit();
  }
  
  @Path("{id}")
  public ThreadResource getThread(@PathParam("id") Long id) {
    return new ThreadResource(id);
  }
  
  
}
