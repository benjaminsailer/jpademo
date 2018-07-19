package de.bsailer.jpademo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity, deren Ids in durch den Generator erzeugt und dann von ihm explizit in die Datenbank
 * eingefügt werden.
 *
 * Der Generator nutzt hierzu die Sequence, deren Namen in der SequenceGenerator-Annotation vermerkt
 * ist.
 *
 * @author bsailer
 */
@Entity
@Table(name = "adressen")
public class Adresse {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adress_id_generator")
  @SequenceGenerator(name = "adress_id_generator", sequenceName = "adressen_id_seq")
  private Integer id;

  private String strasseHausnummer;

  private String plz;

  private String ort;

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getStrasseHausnummer() {
    return strasseHausnummer;
  }

  public void setStrasseHausnummer(final String strasseHausnummer) {
    this.strasseHausnummer = strasseHausnummer;
  }

  public String getPlz() {
    return plz;
  }

  public void setPlz(final String plz) {
    this.plz = plz;
  }

  public String getOrt() {
    return ort;
  }

  public void setOrt(final String ort) {
    this.ort = ort;
  }

  @Override
  public String toString() {
    return getClass().getName() + " [" + id + ", " + strasseHausnummer + ", " + plz + ", " + ort
        + "]";
  }

}
