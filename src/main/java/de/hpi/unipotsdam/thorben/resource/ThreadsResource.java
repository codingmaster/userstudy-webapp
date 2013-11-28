package de.hpi.unipotsdam.thorben.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.hpi.unipotsdam.thorben.entity.NewsThread;
import de.hpi.unipotsdam.thorben.entity.SessionFactoryUtil;

@Path("threads")
public class ThreadsResource {

  @POST
  public void createThread() {
    Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();
    session.save(new NewsThread());
    tx.commit();
  }
  
  @Path("{id}")
  public ThreadResource getThread(@PathParam("id") Long id) {
    return new ThreadResource(id);
  }
  
  
}
