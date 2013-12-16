package de.hpi.unipotsdam.thorben.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import de.hpi.unipotsdam.thorben.dto.QuestionDto;
import de.hpi.unipotsdam.thorben.entity.QuestionItem;

@Path("questions")
public class QuestionsResource extends AbstractResource {

  @GET
  @Transactional(readOnly = true)
  public List<QuestionDto> getQuestions() {
    List<QuestionDto> result = new ArrayList<QuestionDto>();
    
    Session session = sessionHelper.getCurrentSession();
    
    List<QuestionItem> questionItems = session.createCriteria(QuestionItem.class).list();
    
    for (QuestionItem questionItem : questionItems) {
      result.add(QuestionDto.fromQuestion(questionItem));
    }
    
    return result;
  }
}
