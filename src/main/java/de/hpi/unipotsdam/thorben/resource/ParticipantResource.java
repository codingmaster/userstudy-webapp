package de.hpi.unipotsdam.thorben.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import de.hpi.unipotsdam.thorben.entity.Participant;
import de.hpi.unipotsdam.thorben.entity.Rating;
import de.hpi.unipotsdam.thorben.entity.SessionHelper;
import de.hpi.unipotsdam.thorben.entity.ThreadItem;
import de.hpi.unipotsdam.thorben.exception.RestException;

public class ParticipantResource extends AbstractResource {

  private String participantId;
  
  public ParticipantResource(String participantId, SessionHelper sessionHelper) {
    this.participantId = participantId;
    this.sessionHelper = sessionHelper;
  }
  
  @Path("ratings")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public RatingDto createRating(RatingDto ratingDto) {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    Rating rating = new Rating();
    rating.setRating(ratingDto.getRating());
    
    ThreadItem item = (ThreadItem) session.load(ThreadItem.class, ratingDto.getItemId());
    rating.setThreadItem(item);
    
    Participant participant = (Participant) session.load(Participant.class, participantId);
    rating.setParticipant(participant);
    
    session.save(rating);
    ratingDto.updateFrom(rating);
    
    tx.commit();
    
    return ratingDto;
  }
  
  @Path("ratings/{ratingId}")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void updateRating(@PathParam("ratingId") Long ratingId, RatingDto ratingDto) {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    Rating rating = (Rating) session.get(Rating.class, ratingId);
    rating.setRating(ratingDto.getRating());
    
    if (!rating.getParticipant().getId().equals(participantId)) {
      throw new RestException("Mismatching user id to update rating " + ratingId, Status.FORBIDDEN);
    }
    
    session.update(rating);
    
    tx.commit();
  }
  
  @Path("ratings")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public RatingDto getRating(@QueryParam("itemId") Long itemId) {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    Rating rating = (Rating) session.createCriteria(Rating.class)
        .add(Restrictions.eq("participant.id", participantId))
        .add(Restrictions.eq("threadItem.id", itemId))
        .uniqueResult();
    
    if (rating == null) {
      tx.rollback();
      throw new RestException(Status.NOT_FOUND);
    }
    
    RatingDto dto = RatingDto.fromRating(rating);
    
    tx.commit();
    
    return dto;
  }
  
}
