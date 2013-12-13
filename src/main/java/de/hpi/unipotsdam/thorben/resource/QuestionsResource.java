package de.hpi.unipotsdam.thorben.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.hpi.unipotsdam.thorben.dto.QuestionDto;
import de.hpi.unipotsdam.thorben.entity.QuestionItem;

@Path("questions")
public class QuestionsResource extends AbstractResource {

  @GET
  public List<QuestionDto> getQuestions() {
    List<QuestionDto> result = new ArrayList<QuestionDto>();
    
    Session session = sessionHelper.getCurrentSession();
    
    Transaction tx = session.beginTransaction();
    
    List<QuestionItem> questionItems = session.createCriteria(QuestionItem.class).list();
    
    for (QuestionItem questionItem : questionItems) {
      result.add(QuestionDto.fromQuestion(questionItem));
    }
    
    tx.commit();
    
    return result;
    
  }
}
