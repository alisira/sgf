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
  id_compromiso_inicial NOT NULL, -- id de la regularizacion del compromiso inicial  
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
  
  INSERT INTO public.dependencia(id_dependencia, cod_dependencia, dependencia_staff, vigente, localidad, nivel_estructura, sede_diplomatica, id_tipo_dependencia, id_administradora_uel, id_unidad_funcional, id_grupo_organismo, id_organismo, nombre, aprobacion_mpd, id_sede, id_region, id_estructura) VALUES ('42'::integer, '01000400001'::text, 'N'::text, 'S'::text, 'C'::text, '1'::integer, 'N'::text, '11'::integer, '22'::integer, '16'::integer, '11'::integer, '11'::integer, 'DIRECCION DE PRUEBA DE CREDITO PUBLICO'::text, 'S'::text, '12'::integer, '11'::integer, '12'::integer)
UPDATE public.accionespecifica SET denominacion='EJEMPLO DE ACCION CENTRALIZADA'::text WHERE id_accion_especifica = '51'::integer
UPDATE public.accionespecifica SET denominacion='EJEMPLO CON PROYECTOS'::text WHERE id_accion_especifica = '61'::integer



SELECT  
ctapre.cod_presupuesto, ctapre.descripcion,  uel.id_unidad_ejecutora, uel.cod_unidad_ejecutora, uel.nombre,       
uelesp.categoria_presupuesto,       pue.id_fuente_financiamiento, 
pue.id_partida_uel_especifica, 
dep.id_organismo, ctapre.id_cuenta_presupuesto   
from frecuenciapago f,  frecuenciatipopersonal ftp, unidadadministradora uad, administradorauel au, dependencia dep, categoriapersonal catper,    
clasificacionpersonal cper, trabajador t, conceptofijo cf, concepto c, tipopersonal tp,    conceptotipopersonal ctp, conceptocuenta ccta, cuentapresupuesto ctapre,
 partidauelespecifica pue,    unidadejecutora uel, uelespecifica uelesp, 
 accioncentralizada acc right join accionespecifica ace on  ace.id_accion_centralizada = acc.id_accion_centralizada    left join proyecto pro on ace.id_proyecto = pro.id_proyecto    
 where     uelesp.id_accion_especifica = ace.id_accion_especifica    and uel.id_unidad_ejecutora = uelesp.id_unidad_ejecutora    
 and pue.id_uel_especifica = uelesp.id_uel_especifica    and ctapre.id_cuenta_presupuesto = pue.id_cuenta_presupuesto    
 and ccta.id_cuenta_presupuesto = ctapre.id_cuenta_presupuesto    and ccta.id_concepto_tipo_personal = ctp.id_concepto_tipo_personal         
 and c.id_concepto = ctp.id_concepto    and cf.id_concepto_tipo_personal = ctp.id_concepto_tipo_personal    AND cf.id_trabajador = t.id_trabajador     
 and cper.id_clasificacion_personal = tp.id_clasificacion_personal    and catper.id_categoria_personal =  cper.id_categoria_personal    
 and t.id_tipo_personal = tp.id_tipo_personal     AND t.id_dependencia = dep.id_dependencia    AND dep.id_administradora_uel = au.id_administradora_uel     
 AND uel.id_unidad_ejecutora = au.id_unidad_ejecutora    AND uad.id_unidad_administradora = au.id_unidad_administradora    
 AND tp.id_tipo_personal = ctp.id_tipo_personal    AND ftp.id_tipo_personal = tp.id_tipo_personal    
 AND ftp.id_frecuencia_tipo_personal = cf.id_frecuencia_tipo_personal    and f.id_frecuencia_pago = ftp.id_frecuencia_pago                       
 and ace.anio IN  ( 2014 )  
 and f.cod_frecuencia_pago IN  ( 1,2,3  )         
 group by ctapre.id_cuenta_presupuesto, ctapre.cod_presupuesto, ctapre.descripcion,  uel.id_unidad_ejecutora, uel.cod_unidad_ejecutora, uel.nombre,   
 uelesp.categoria_presupuesto, pue.id_fuente_financiamiento, pue.id_partida_uel_especifica, dep.id_organismo   
 order by  uelesp.categoria_presupuesto, ctapre.cod_presupuesto  
