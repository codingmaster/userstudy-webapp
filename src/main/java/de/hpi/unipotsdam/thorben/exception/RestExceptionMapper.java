package de.hpi.unipotsdam.thorben.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import de.hpi.unipotsdam.thorben.dto.ExceptionDto;

@Provider
public class RestExceptionMapper implements ExceptionMapper<RestException> {
  
  @Override
  public Response toResponse(RestException e) {
    ExceptionDto dto = ExceptionDto.fromException(e);
    
    // setting the media type here is a work around in cases when a sub-resource locator throws an exception
    // resteasy is then not able to determine the media type itself
    return Response.status(e.getStatus()).entity(dto).type(MediaType.APPLICATION_JSON).build();
  }
}
