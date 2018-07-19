 
create table personen (
  id serial not null,
  geburtsdatum date,
  nachname varchar(255),
  vorname varchar(255),
  primary key (id)
)

create table adressen (
  id int4 not null,
  ort varchar(255),
  plz varchar(255),
  strasseHausnummer varchar(255),
  primary key (id)
)

create sequence adressen_id_seq start 1
