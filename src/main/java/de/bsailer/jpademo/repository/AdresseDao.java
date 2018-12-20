package de.bsailer.jpademo.repository;

import javax.persistence.EntityManager;
import de.bsailer.jpademo.domain.Adresse;

public class AdresseDao {

  private EntityManager entityManager;

  public void initializeEntityManager() {
    entityManager = PersistenceManager.INSTANCE.getEntityManager();
  }

  public void finalizeEntityManager() {
    entityManager.close();
  }

  public void storeAdresse(final Adresse adresse) {
    entityManager.getTransaction().begin();
    entityManager.persist(adresse);
    entityManager.getTransaction().commit();
  }

}
