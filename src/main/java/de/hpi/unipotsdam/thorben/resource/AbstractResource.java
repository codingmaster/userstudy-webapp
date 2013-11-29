package de.hpi.unipotsdam.thorben.resource;

import de.hpi.unipotsdam.thorben.entity.SessionHelper;

public abstract class AbstractResource {

  protected SessionHelper sessionHelper;

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }

  public void setSessionHelper(SessionHelper sessionHelper) {
    this.sessionHelper = sessionHelper;
  }
}
