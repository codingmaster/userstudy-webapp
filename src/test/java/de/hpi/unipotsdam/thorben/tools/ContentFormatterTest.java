package de.hpi.unipotsdam.thorben.tools;

import org.junit.Assert;
import org.junit.Test;

public class ContentFormatterTest {

  @Test
  public void testSimpleAbstractFormatting() {
    String abstractContent = "a bullet point; another bullet point";
    
    ContentFormatter formatter = new ContentFormatter();
    String formattedAbstractContent = formatter.formatArticleAbstract(abstractContent);
    
    Assert.assertEquals("<ul><li>a bullet point</li><li>another bullet point</li></ul>", formattedAbstractContent);
  }
}
