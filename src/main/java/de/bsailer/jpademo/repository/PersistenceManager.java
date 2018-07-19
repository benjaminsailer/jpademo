package de.bsailer.jpademo.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum PersistenceManager {

  INSTANCE;

  private EntityManagerFactory entityManagerFactory;

  private PersistenceManager() {
    this.entityManagerFactory = Persistence.createEntityManagerFactory("jpademo");
  }

  public EntityManager getEntityManager() {
    return entityManagerFactory.createEntityManager();
  }

  public void close() {
    entityManagerFactory.close();
  }

}
