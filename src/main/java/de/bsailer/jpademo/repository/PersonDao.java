package de.bsailer.jpademo.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import de.bsailer.jpademo.domain.Person;

public class PersonDao {

  private final EntityManager entityManager;

  public PersonDao() {
    entityManager = PersistenceManager.INSTANCE.getEntityManager();
  }

  public List<Person> findByName(final String name) {
    final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Person> cq = cb.createQuery(Person.class);
    final Root<Person> r = cq.from(Person.class);
    final TypedQuery<Person> q =
        entityManager.createQuery(cq.where(cb.equal(r.get("nachname"), name)));
    return q.getResultList();
  }

  public void finalizeEntityManager() {
    entityManager.close();
    PersistenceManager.INSTANCE.close();
  }

  public Person findPerson(final int personId) {
    return entityManager.find(Person.class, Integer.valueOf(personId));
  }

  public void storePerson(final Person person) {
    entityManager.getTransaction().begin();
    entityManager.persist(person);
    entityManager.getTransaction().commit();
  }

}
