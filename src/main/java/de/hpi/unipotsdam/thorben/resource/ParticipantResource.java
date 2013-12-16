package de.hpi.unipotsdam.thorben.resource;

import java.util.ArrayList;
import java.util.List;

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
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import de.hpi.unipotsdam.thorben.dto.RatingDto;
import de.hpi.unipotsdam.thorben.entity.Participant;
import de.hpi.unipotsdam.thorben.entity.QuestionItem;
import de.hpi.unipotsdam.thorben.entity.Rating;
import de.hpi.unipotsdam.thorben.entity.ThreadItem;
import de.hpi.unipotsdam.thorben.exception.RestException;

public class ParticipantResource extends AbstractResource {
  
  
  @Path("ratings")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public RatingDto createRating(@PathParam("id") String participantId, RatingDto ratingDto) {
    ensureParticipantExists(participantId);
    
    Session session = sessionHelper.getCurrentSession();
    
    Rating rating = new Rating();
    rating.setRating(ratingDto.getRating());
    
    ThreadItem item = (ThreadItem) session.load(ThreadItem.class, ratingDto.getItemId());
    rating.setThreadItem(item);
    
    Participant participant = (Participant) session.load(Participant.class, participantId);
    rating.setParticipant(participant);
    
    QuestionItem question = (QuestionItem) session.load(QuestionItem.class, ratingDto.getQuestionId());
    rating.setQuestionItem(question);
    
    session.save(rating);
    ratingDto.updateFrom(rating);
    
    
    return ratingDto;
  }
  
  @Path("ratings/{ratingId}")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Transactional
  public void updateRating(@PathParam("id") String participantId, @PathParam("ratingId") Long ratingId, RatingDto ratingDto) {
    ensureParticipantExists(participantId);
    
    Session session = sessionHelper.getCurrentSession();
    
    Rating rating = (Rating) session.get(Rating.class, ratingId);
    rating.setRating(ratingDto.getRating());
    
    if (!rating.getParticipant().getId().equals(participantId)) {
      throw new RestException("Mismatching user id to update rating " + ratingId, Status.FORBIDDEN);
    }
    
    session.update(rating);
  }
  
  @Path("ratings")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional(readOnly = true)
  public List<RatingDto> getRatings(@PathParam("id") String participantId, @QueryParam("itemId") Long itemId) {
    ensureParticipantExists(participantId);
    
    Session session = sessionHelper.getCurrentSession();
    
    List<Rating> ratings = session.createCriteria(Rating.class)
        .add(Restrictions.eq("participant.id", participantId))
        .add(Restrictions.eq("threadItem.id", itemId))
        .list();
    
    List<RatingDto> result = new ArrayList<RatingDto>();
    for (Rating rating : ratings) {
      result.add(RatingDto.fromRating(rating));
    }
    
    return result;
  }
  
  private void ensureParticipantExists(String participantId) {
    Session session = sessionHelper.getCurrentSession();
    
    Object participant = session.get(Participant.class, participantId);
    if (participant == null) {
      throw new RestException("Participant " + participantId + " does not exist", Status.BAD_REQUEST);
    }
  }
  
}
