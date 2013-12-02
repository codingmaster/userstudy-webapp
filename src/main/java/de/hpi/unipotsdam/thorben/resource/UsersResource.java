package de.hpi.unipotsdam.thorben.resource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import de.hpi.unipotsdam.thorben.dto.UserDto;
import de.hpi.unipotsdam.thorben.entity.Role;
import de.hpi.unipotsdam.thorben.entity.UserEntity;

@Path("users")
public class UsersResource extends AbstractResource {
  
  private static final String USER_ROLE_NAME = "ROLE_USER";

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void createUser(UserDto userDto) {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    UserEntity user = new UserEntity();
    user.setName(userDto.getUsername());
    user.setPassword(userDto.getPassword());
    
    Set<Role> roles = new HashSet<Role>();
    Role userRole = (Role) session.createCriteria(Role.class).add(Restrictions.eq("name", USER_ROLE_NAME)).uniqueResult();
    roles.add(userRole);
    user.setRoles(roles);
    session.save(user);
    
    tx.commit();
  }
  
  @Path("{id}")
  public UserResource getUser(@PathParam("id") Long userId) {
    // TODO: authorization check here
    return new UserResource(userId, sessionHelper);
  }
}
