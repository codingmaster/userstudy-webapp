package de.hpi.unipotsdam.thorben;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import de.hpi.unipotsdam.thorben.exception.RestExceptionMapper;
import de.hpi.unipotsdam.thorben.resource.ThreadsResource;

@ApplicationPath("/")
public class UserStudyApplication extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();
    
    // resources
    classes.add(ThreadsResource.class);
    
    // providers
    classes.add(RestExceptionMapper.class);
    
    return classes;
  }
}
