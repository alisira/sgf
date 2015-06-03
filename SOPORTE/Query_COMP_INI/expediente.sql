CREATE TABLE expediente
(
  expediente integer NOT NULL,
  ano integer NOT NULL,
  id_usuario integer NOT NULL,
  estatus integer NOT NULL,
  fecha_reg date NOT NULL,
  id_proceso integer NOT NULL,
  observacion character varying(300) NOT NULL,
  id_organismo integer NOT NULL,
  CONSTRAINT clave_principal_pkey PRIMARY KEY (expediente, ano),
  CONSTRAINT expediente_id_usuario_fkey FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE expediente
  OWNER TO postgres;
COMMENT ON TABLE expediente
  IS 'columna Expediente: 0=proceso, 1=aprobado, 2=anulado';

