package de.hpi.unipotsdam.thorben;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import de.hpi.unipotsdam.thorben.resource.ThreadsResource;

@ApplicationPath("/")
public class UserStudyApplication extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();
    
    classes.add(ThreadsResource.class);
    
    return classes;
  }
}
