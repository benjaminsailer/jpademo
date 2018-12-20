 
create table personen (
  id serial not null,
  geburtsdatum date,
  nachname varchar(255),
  vorname varchar(255),
  adresse_id int4,
  primary key (id)
);

create table adressen (
  id int4 not null,
  ort varchar(255),
  plz varchar(255),
  strasseHausnummer varchar(255),
  primary key (id)
);

create sequence adressen_id_seq start 1;

alter table personen
  add constraint p_a_fk
  foreign key (adresse_id) references adressen (id);
