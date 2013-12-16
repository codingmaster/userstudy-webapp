package de.hpi.unipotsdam.thorben.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import de.hpi.unipotsdam.thorben.dto.ParticipantDto;
import de.hpi.unipotsdam.thorben.entity.Participant;

@Path("participants")
public class ParticipantsResource extends AbstractResource {
  
  private ParticipantResource participantResource;
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Transactional
  public ParticipantDto createParticipant(ParticipantDto participantDto) {
    Session session = sessionHelper.getCurrentSession();
    
    Participant participant = new Participant();
    participant.setName(participantDto.getName());
    
    session.save(participant);
    
    participantDto.updateFrom(participant);
    
    return participantDto;
  }
  
  @Path("{id}")
  public ParticipantResource getParticipant() {
    return participantResource;
  }

  public ParticipantResource getParticipantResource() {
    return participantResource;
  }

  public void setParticipantResource(ParticipantResource participantResource) {
    this.participantResource = participantResource;
  }
}
