-- Table: expediente

-- DROP TABLE expediente;
select max(expediente) from expediente WHERE and  id_organismo= 55 
SELECT max (expediente)  FROM expediente WHERE id_organismo= 11 and ano= 2014 group by ano,id_usuario,id_proceso     ,estatus,fecha_reg,id_organismo,observacion
select * from expediente

 insert into public.expediente  (expediente,ano,id_usuario,id_proceso     ,estatus,fecha_reg,id_organismo,observacion)  
 values      ((SELECT COALESCE(MAX (expediente), 0) + 1  FROM expediente WHERE id_organismo= 55),2014,1,55 ,1,'2014-01-01',55,'')  returning expediente   
2015-06-03 12:48:15 VET DETALLE:  parámetros: $1 = '2014', $2 = '11', $3 = '2014', $4 = '1', $5 = '55', $6 = '1', $7 = '2015-06-03', $8 = '11', $9 = 'prueba'

insert into public.expediente      (expediente,ano,id_usuario,id_proceso     ,estatus,fecha_reg,id_organismo,observacion) 
 values      ((SELECT COALESCE(MAX (expediente), 0) + 1  FROM expediente WHERE id_organismo= 55),2014,1,55 ,1,'2014-01-01',55,'')
 result 

CREATE TABLE expediente
(
  id_expediente serial not null,
  expediente integer NOT NULL,
  ano integer NOT NULL,
  id_usuario integer NOT NULL,
  estatus integer NOT NULL,
  fecha_reg date NOT NULL,
  id_proceso integer NOT NULL,
  observacion character varying(300) NOT NULL,
  id_organismo integer NOT NULL,
  CONSTRAINT clave_principal_pkey PRIMARY KEY (id_expediente),
  CONSTRAINT expediente_expediente_ano_ukey UNIQUE (expediente, id_organismo, ano),
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
