package de.hpi.unipotsdam.thorben.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.hpi.unipotsdam.thorben.dto.ParticipantDto;
import de.hpi.unipotsdam.thorben.entity.Participant;

@Path("participants")
public class ParticipantsResource extends AbstractResource {
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void createParticipant(ParticipantDto participantDto) {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    Participant participant = new Participant();
    participant.setName(participantDto.getUsername());
    
    session.save(participant);
    
    tx.commit();
  }
  
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public ParticipantResource getUser(@PathParam("id") String participantId) {
    return new ParticipantResource(participantId, sessionHelper);
  }
}