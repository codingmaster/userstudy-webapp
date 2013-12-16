package de.unipotsdam.hpi.thorben;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.mockito.Mockito;

import de.hpi.unipotsdam.thorben.exception.RestExceptionMapper;
import de.hpi.unipotsdam.thorben.resource.ThreadsResource;

@ApplicationPath("/")
public class MockUserStudyApplication extends Application {

  private Set<Object> singletons = new HashSet<Object>();
  
  public MockUserStudyApplication() {
    singletons = getDefaultResources();
  }
  
  private Set<Object> getDefaultResources() {
    Set<Object> resources = new HashSet<Object>();
    resources.add(Mockito.mock(ThreadsResource.class));
    resources.add(new RestExceptionMapper());
    return resources;
  }

  public MockUserStudyApplication(Set<Object> resources) {
    singletons = resources;
  }
  
  @Override
  public Set<Object> getSingletons() {
    return singletons;
  }
}
