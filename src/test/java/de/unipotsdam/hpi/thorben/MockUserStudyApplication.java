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

  @Override
  public Set<Object> getSingletons() {
    Set<Object> resources = new HashSet<Object>();
    resources.add(Mockito.mock(ThreadsResource.class));
    resources.add(new RestExceptionMapper());
    return resources;
  }
}
