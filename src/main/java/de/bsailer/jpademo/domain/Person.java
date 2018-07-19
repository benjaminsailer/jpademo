package de.bsailer.jpademo.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity, deren Ids in der Datenbank via Feld vom Typ "identity" befüllt werden.
 *
 * Der Generator liest die Id nur nachträglich.
 *
 * @author bsailer
 */
@Entity
@Table(name = "personen")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nachname;

  private String vorname;

  private LocalDate geburtsdatum;

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getNachname() {
    return nachname;
  }

  public void setNachname(final String nachname) {
    this.nachname = nachname;
  }

  public String getVorname() {
    return vorname;
  }

  public void setVorname(final String vorname) {
    this.vorname = vorname;
  }

  public LocalDate getGeburtsdatum() {
    return geburtsdatum;
  }

  public void setGeburtsdatum(final LocalDate geburtsdatum) {
    this.geburtsdatum = geburtsdatum;
  }

  @Override
  public String toString() {
    return getClass().getName() + " [" + id + ", " + nachname + ", " + vorname + ", " + geburtsdatum
        + "]";
  }
}
