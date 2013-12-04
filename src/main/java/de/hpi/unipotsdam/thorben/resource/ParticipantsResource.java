package de.hpi.unipotsdam.thorben.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.hpi.unipotsdam.thorben.dto.ParticipantDto;
import de.hpi.unipotsdam.thorben.entity.Participant;
import de.hpi.unipotsdam.thorben.exception.RestException;

@Path("participants")
public class ParticipantsResource extends AbstractResource {
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public ParticipantDto createParticipant(ParticipantDto participantDto) {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    Participant participant = new Participant();
    participant.setName(participantDto.getName());
    
    session.save(participant);
    
    tx.commit();
    
    participantDto.updateFrom(participant);
    
    return participantDto;
  }
  
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public ParticipantResource getUser(@PathParam("id") String participantId) {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    Object participant = session.get(Participant.class, participantId);
    if (participant == null) {
      throw new RestException("Participant " + participantId + " does not exist", Status.BAD_REQUEST);
    }
    
    tx.commit();
    
    return new ParticipantResource(participantId, sessionHelper);
  }
}
