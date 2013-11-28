package de.hpi.unipotsdam.thorben.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import de.hpi.unipotsdam.thorben.dto.ExceptionDto;

@Provider
public class RestExceptionMapper implements ExceptionMapper<RestException> {

  @Override
  public Response toResponse(RestException e) {
    ExceptionDto dto = ExceptionDto.fromException(e);
    
    return Response.status(e.getStatus()).entity(dto).build();
  }
}
