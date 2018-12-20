package de.bsailer.jpademo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import de.bsailer.jpademo.domain.Adresse;
import de.bsailer.jpademo.domain.Person;
import de.bsailer.jpademo.repository.AdresseDao;
import de.bsailer.jpademo.repository.PersonDao;

/**
 * Write Person to database
 *
 */
public class JpaDemo {

  private final AdresseDao adresseDao = new AdresseDao();

  private final PersonDao personDao = new PersonDao();

  public static void main(final String[] args) {
    final JpaDemo demo = new JpaDemo();
    demo.analyzeAnnotationsLikeHibernate();
    demo.run();
  }

  private void analyzeAnnotationsLikeHibernate() {
    for (final Annotation a : Person.class.getAnnotations()) {
      System.out.println(a.toString());
    }
    for (final Field f : Person.class.getDeclaredFields()) {
      System.out.println(f.getName());
      for (final Annotation a : f.getAnnotations()) {
        System.out.println(a.toString());
      }
    }
  }

  private void run() {
    final Integer petersId = storePeopleAndGetPetersAddress();
    addAddressToPeter(petersId);
    outputPeterWithAddress();
    updateWatsonsName();
  }

  private void outputPeterWithAddress() {
    initDaos();
    final List<Person> persons = personDao.findByName("Parker");
    for (final Person person : persons) {
      System.out.println("Person: " + person);
      System.out.println("Adresse: " + person.getAdresse());
    }
    finalizeDaos();
  }

  private void addAddressToPeter(final Integer petersId) {
    initDaos();
    final Person peter = personDao.findPerson(petersId);
    final Adresse adresse = storeNewAdresse("Steinheilstr. 6", "", "Ismaning");
    System.out.println("person found from peters id: " + peter);
    peter.setVorname("Peter Ben");
    peter.setAdresse(adresse);
    personDao.storePerson(peter);
    finalizeDaos();
  }

  private Integer storePeopleAndGetPetersAddress() {
    initDaos();
    final Integer petersId = storeNewPerson("Parker", "Peter", "1983-01-11").getId();
    storeNewPerson("Watson", "Mary", "1986-02-15");
    storeNewPerson("Lee", "Stan", "1945-08-10");
    finalizeDaos();
    return petersId;
  }

  private void updateWatsonsName() {
    initDaos();
    final List<Person> watsons = personDao.findByName("Watson");
    for (final Person watson : watsons) {
      System.out.println("watson found from name: " + watson);
      watson.setVorname("Mary Jane");
      personDao.storePerson(watson);
      System.out.println(watson);
    }
    finalizeDaos();
  }

  private void finalizeDaos() {
    adresseDao.finalizeEntityManager();
    personDao.finalizeEntityManager();
  }

  private void initDaos() {
    adresseDao.initializeEntityManager();
    personDao.initializeEntityManager();
  }

  private Adresse storeNewAdresse(final String strasseHausnummer, final String plz,
      final String ort) {
    final Adresse adresse = createAdresse(strasseHausnummer, plz, ort);
    System.out.println("new adresse to store: " + adresse);
    adresseDao.storeAdresse(adresse);
    System.out.println("new adresse stored: " + adresse);
    return adresse;
  }

  private Adresse createAdresse(final String strasseHausnummer, final String plz,
      final String ort) {
    final Adresse adresse = new Adresse();
    adresse.setStrasseHausnummer(strasseHausnummer);
    adresse.setPlz(plz);
    adresse.setOrt(ort);
    return adresse;
  }

  private Person storeNewPerson(final String nachname, final String vorname,
      final String geburtsdatum) {
    final Person person = createPerson(nachname, vorname, geburtsdatum);
    System.out.println("new person to store: " + person);
    personDao.storePerson(person);
    System.out.println("new person stored: " + person);
    return person;
  }

  private Person createPerson(final String nachname, final String vorname,
      final String geburtsdatum) {
    final Person person = new Person();
    person.setNachname(nachname);
    person.setVorname(vorname);
    person.setGeburtsdatum(LocalDate.from(DateTimeFormatter.ISO_DATE.parse(geburtsdatum)));
    return person;
  }

}
