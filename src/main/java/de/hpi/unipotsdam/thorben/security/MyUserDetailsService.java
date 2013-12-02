package de.hpi.unipotsdam.thorben.security;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import de.hpi.unipotsdam.thorben.entity.Role;
import de.hpi.unipotsdam.thorben.entity.SessionHelper;
import de.hpi.unipotsdam.thorben.entity.UserEntity;

public class MyUserDetailsService implements UserDetailsService {

  private SessionHelper sessionHelper;
  
  
  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    Session session = sessionHelper.getCurrentSession();
    Transaction tx = session.beginTransaction();
    
    UserEntity userEntity = (UserEntity) session.createCriteria(UserEntity.class)
        .add(Restrictions.eq("name", username)).uniqueResult();
    
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    
    for (Role role : userEntity.getRoles()) {
      GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
      authorities.add(authority);
    }
    
    MyUserDetails user = new MyUserDetails(userEntity.getId(), userEntity.getName(), userEntity.getPassword(), authorities);
    
    tx.commit();
    return user;
  }
  
  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }
  public void setSessionHelper(SessionHelper sessionHelper) {
    this.sessionHelper = sessionHelper;
  }
}
