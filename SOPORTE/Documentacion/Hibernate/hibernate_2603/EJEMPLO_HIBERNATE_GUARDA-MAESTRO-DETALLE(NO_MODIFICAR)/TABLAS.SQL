SELECT id, nombre, ape1, ape2 FROM profesor where nombre = 'Laura'
select * from correoelectronico;


delete from correoelectronico;
delete FROM profesor;


DROP TABLE correoelectronico;
drop table profesor;

CREATE TABLE profesor
(
  id  serial,
  nombre character varying(80) NOT NULL,
  ape1 character varying(80),
  ape2 character varying(80),
  CONSTRAINT profesor_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE profesor
  OWNER TO postgres;


-- Table: correoelectronico

-- DROP TABLE correoelectronico;

CREATE TABLE correoelectronico
(
  idcorreo serial,
  direccioncorreo character varying(80),
  idprofesor integer NOT NULL, 
  CONSTRAINT "$1" PRIMARY KEY (idcorreo),
  CONSTRAINT correoelectronico_idprofesor_fkey FOREIGN KEY (idprofesor)
      REFERENCES profesor (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE correoelectronico
  OWNER TO postgres;
