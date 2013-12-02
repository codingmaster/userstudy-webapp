package de.hpi.unipotsdam.thorben.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUserDetails extends User {

  private Long userId;
  
  public MyUserDetails(Long userId, String username, String password,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.userId = userId;
  }

  public Long getUserId() {
    return userId;
  }
}
