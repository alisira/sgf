--Tabla opcion 06/05/15
SELECT id_opcion, codigo_opcion, descripcion, ruta, tipo, uri, jerarquia, estatus FROM opcion;

begin transaction;

alter table rolopcion drop constraint "$1";
alter table rolopcion add constraint "$1" FOREIGN KEY (id_opcion) REFERENCES opcion (id_opcion) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
update opcion set estatus = 'I' where estatus is null or estatus = '';
alter table opcion add CONSTRAINT estatus_opcion CHECK (estatus::text = 'A'::text OR estatus::text = 'I'::text);
update opcion set jerarquia = substr(md5(trunc(random() * 99999999 + 1000000)::text), 1,9) where jerarquia is null or jerarquia = '';
ALTER TABLE opcion ALTER COLUMN jerarquia SET NOT NULL;
ALTER TABLE opcion ALTER COLUMN ruta SET NOT NULL;
update opcion set uri = null where uri is null or uri = '' or uri = 'N/A';
alter table opcion add CONSTRAINT opcion_uri unique(uri);

--rollback;
commit;


--Temporal de la regularizacion del compromiso
CREATE TABLE regularizacioncompromiso
(
  id_regularizacion_compromiso_inicial serial NOT NULL, -- id de la regularizacion del compromiso inicial
  id_compromiso_inicial serial NOT NULL, -- id de la regularizacion del compromiso inicial  
  expediente bigint, -- numero de la regularizacion, expediente que es enviado por sigecof
  compromiso bigint, -- numero del compromiso que es enviado por sigecof
  ano integer NOT NULL, -- ejercicio presupuestario
  tarea integer NOT NULL,
  estatus integer NOT NULL,
  id_tipo_documento integer, -- identificador del documento
  documento character varying(10), -- numero del documento
  observacion character varying(300), -- observacion del compromiso  
  gaceta_cred_adicional character varying(10), -- numero de la gaceta del credito adicional
  decreto_cred_adicional character varying(10), -- decreto del credito adicional
  fecha_cred_adicional date, -- fecha del creditod adicional
  gaceta_rectificacion character varying(10), -- nro de la gaceta de rectificacion
  decreto_rectificacion character varying(10), -- numero de decreto de la rectificacion
  fecha_rectificacion date, -- fecha de la rectificacion
  fecha_registro date, -- fecha de registro del compromiso en sigecof 
  origen_presupuestario character varying(1), -- Origen presupuestario del compromiso. Puede ser:... 
  CONSTRAINT id_regularizacion_compromiso_sigecof PRIMARY KEY (id_compromiso_inicial),    
  CONSTRAINT id_tipo_documento FOREIGN KEY (id_tipo_documento)
      REFERENCES tipodocumento (id_tipo_documento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT origen_presupuestario CHECK (origen_presupuestario::text = '1'::text OR origen_presupuestario::text = '2'::text OR origen_presupuestario::text = '3'::text)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE regularizacioncompromiso
  OWNER TO postgres;
