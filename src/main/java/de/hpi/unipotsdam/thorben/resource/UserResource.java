package de.hpi.unipotsdam.thorben.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import de.hpi.unipotsdam.thorben.dto.RatingDto;
import de.hpi.unipotsdam.thorben.entity.Rating;
import de.hpi.unipotsdam.thorben.entity.SessionHelper;
import de.hpi.unipotsdam.thorben.entity.ThreadItem;
import de.hpi.unipotsdam.thorben.entity.UserEntity;
import de.hpi.unipotsdam.thorben.exception.RestException;

public class UserResource extends AbstractResource {

  private Long userId;
  
  public UserResource(Long userId, SessionHelper sessionHelper) {
    this.userId = userId;
    this.sessionHelper = sessionHelper;
  }
  
  @Path("ratings")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void createRating(RatingDto ratingDto) {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    Rating rating = new Rating();
    rating.setRating(ratingDto.getRating());
    
    ThreadItem item = (ThreadItem) session.load(ThreadItem.class, ratingDto.getItemId());
    rating.setThreadItem(item);
    
    UserEntity user = (UserEntity) session.load(UserEntity.class, userId);
    rating.setUser(user);
    
    session.save(rating);
    
    tx.commit();
  }
  
  @Path("ratings/{ratingId}")
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public void updateRating(@PathParam("ratingId") Long ratingId, RatingDto ratingDto) {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    Rating rating = (Rating) session.get(Rating.class, ratingId);
    rating.setRating(ratingDto.getRating());
    session.update(rating);
    
    tx.commit();
  }
  
  @Path("ratings")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public RatingDto getRating(@QueryParam("itemId") Long itemId) {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    Rating rating = (Rating) session.createCriteria(Rating.class).add(Restrictions.eq("item_id", itemId)).uniqueResult();
    
    if (rating == null) {
      throw new RestException(Status.NOT_FOUND);
    }
    
    RatingDto dto = RatingDto.fromRating(rating);
    
    tx.commit();
    
    return dto;
  }
  
}
