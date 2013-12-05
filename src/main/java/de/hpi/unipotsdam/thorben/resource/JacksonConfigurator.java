package de.hpi.unipotsdam.thorben.resource;

import java.text.SimpleDateFormat;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonConfigurator implements ContextResolver<ObjectMapper> {

  ObjectMapper mapper;
  
  public JacksonConfigurator() {
    mapper = new ObjectMapper();

    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }
  
  @Override
  public ObjectMapper getContext(Class<?> clazz) {
    return mapper;
  }

}

