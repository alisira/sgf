
/*  	
	DROP TABLE compromisoinicialdetalle;
	DROP TABLE compromisoinicial;
  
*/

CREATE TABLE compromisoinicial
(
  id serial NOT NULL,
  id_organismo integer NOT NULL,
  ano integer NOT NULL,
  tarea integer NOT NULL,
  CONSTRAINT id_compromiso_inicial_sigecof PRIMARY KEY (id)
);

CREATE TABLE compromisoinicialdetalle
(
  id_compromiso_inicial_detalle serial NOT NULL,
  id_compromiso_inicial integer NOT NULL,
  monto double precision NOT NULL DEFAULT 0,
  CONSTRAINT id_compromiso_inicial_detalle PRIMARY KEY (id_compromiso_inicial_detalle),
  CONSTRAINT id_compromiso_inicial_fk FOREIGN KEY (id_compromiso_inicial)
      REFERENCES compromisoinicial (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
