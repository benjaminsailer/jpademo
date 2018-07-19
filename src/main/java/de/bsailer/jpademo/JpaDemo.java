package de.bsailer.jpademo;

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
    demo.run();
  }

  private void run() {
    final Integer petersId = storeNewPerson("Parker", "Peter", "1983-01-11").getId();
    storeNewPerson("Watson", "Mary", "1986-02-15");
    storeNewPerson("Lee", "Stan", "1945-08-10");
    storeNewAdresse("Steinheilstr. 6", "", "Ismaning");
    final Person peter = personDao.findPerson(petersId);
    System.out.println("person found from peters id: " + peter);
    peter.setVorname("Peter Ben");
    personDao.storePerson(peter);
    final List<Person> watsons = personDao.findByName("Watson");
    for (final Person watson : watsons) {
      System.out.println("watson found from name: " + watson);
      watson.setVorname("Mary Jane");
      personDao.storePerson(watson);
      System.out.println(watson);
    }
    adresseDao.finalizeEntityManager();
    personDao.finalizeEntityManager();
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
