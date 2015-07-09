-- Table: compromisoinicial

/*  	
	DROP TABLE compromisoinicialdetalle;
	DROP TABLE compromisoinicial;
  
*/
select * from compromisoinicial 

CREATE TABLE compromisoinicial
(
  id_compromiso_inicial serial NOT NULL, -- id del compromiso inicial
  id_organismo integer NOT NULL, -- id sigefirrhh del organismo
  ano integer NOT NULL, -- ejercicio presupuestario
  tarea integer NOT NULL,
  estatus integer NOT NULL,
  id_unidad_administradora integer NOT NULL, -- id de la UA en sigefirrhh
  id_cuentadante integer,
  id_tipo_pago integer, -- identificador del tipo de pago, el cual ya tiene asociado el tipo de fondo.
  id_tipo_documento integer, -- identificador del documento
  documento character varying(10), -- numero del documento
  observacion character varying(300), -- observacion del compromiso
  origen_presupuestario character varying(1), -- Origen presupuestario del compromiso. Puede ser:...
  gaceta_cred_adicional character varying(10), -- numero de la gaceta del credito adicional
  decreto_cred_adicional character varying(10), -- decreto del credito adicional
  fecha_cred_adicional date, -- fecha del creditod adicional
  gaceta_rectificacion character varying(10), -- nro de la gaceta de rectificacion
  decreto_rectificacion character varying(10), -- numero de decreto de la rectificacion
  fecha_rectificacion date, -- fecha de la rectificacion
  expediente bigint, -- numero del expediente que es enviado por sigecof
  compromiso bigint, -- numero del compromiso que es enviado por sigecof
  fecha_registro date, -- fecha de registro del compromiso en sigecof
  CONSTRAINT id_compromiso_inicial_sigecof PRIMARY KEY (id_compromiso_inicial),
  CONSTRAINT origen_presupuestario CHECK (origen_presupuestario::text = '1'::text OR origen_presupuestario::text = '2'::text OR origen_presupuestario::text = '3'::text)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE compromisoinicial
  OWNER TO postgres;
-- Table: compromisoinicialdetalle


CREATE TABLE compromisoinicialdetalle
(
  id_compromiso_inicial_detalle serial NOT NULL, -- id del registro
  id_compromiso_inicial integer NOT NULL, -- id del compromiso inicial al cual pertenece
  id_partida_uel_especifica integer NOT NULL, -- referencia para obtener: uel, accion especifica, categoria presupuestaria, objeto de gasto y fuente de financiamiento
  monto double precision NOT NULL DEFAULT 0,
  guardado boolean NOT NULL DEFAULT false, -- indica si la imputacion fue guardada o no
  mensajerta character varying, -- Mensaje que indica si la imputacion fue guardada exitosamente, de lo contrario indicara el motivo por el cual no pudo ser guardado
  ff integer NOT NULL,
  CONSTRAINT id_compromiso_inicial_detalle PRIMARY KEY (id_compromiso_inicial_detalle),
  CONSTRAINT id_compromiso_inicial FOREIGN KEY (id_compromiso_inicial)
      REFERENCES compromisoinicial (id_compromiso_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
  
)
WITH (
  OIDS=FALSE
);
ALTER TABLE compromisoinicialdetalle
  OWNER TO postgres;