package de.hpi.unipotsdam.thorben.tools;

import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Ul;

/**
 * Takes articles of the new york times annotated corpus and adds html tags for formatting and display
 * 
 * @author Thorben
 */
public class ContentFormatter {
  
  private static final String BULLET_POINT_DELIMITER = ";";

  public String formatArticleAbstract(String articleAbstract) {
    Ul ulElement = new Ul();
    String[] bulletPoints = articleAbstract.split(BULLET_POINT_DELIMITER);
    
    for (String bulletPoint : bulletPoints) {
      bulletPoint = bulletPoint.trim();
      Li liElement = new Li();
      liElement.appendText(bulletPoint);
      ulElement.appendChild(liElement);
    }
    
    return ulElement.write();
    
  }
}
