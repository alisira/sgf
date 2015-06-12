/*DROP TABLE compromisoinicialdetalle;
DROP TABLE compromisoinicial;
select * from compromisoinicial
select * from compromisoinicialdetalle
*/

CREATE TABLE compromisoinicial
(
  id_compromiso_inicial  serial NOT NULL,  
  id_organismo integer NOT NULL, -- id sigefirrhh del organismo
  ano integer NOT NULL, -- ejercicio presupuestario
  tarea integer NOT NULL,
  CONSTRAINT "$5" PRIMARY KEY (id_compromiso_inicial)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE compromisoinicial
  OWNER TO postgres;

  CREATE TABLE compromisoinicialdetalle
(
  id_compromiso_inicial_detalle serial NOT NULL,  
  id_compromiso_inicial integer NOT NULL, 
  monto double precision NOT NULL DEFAULT 0,
  CONSTRAINT "$3" PRIMARY KEY (id_compromiso_inicial_detalle),
  CONSTRAINT "$4" FOREIGN KEY (id_compromiso_inicial)
      REFERENCES compromisoinicial (id_compromiso_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE compromisoinicialdetalle
  OWNER TO postgres;