
INSERT INTO niveleducativo  VALUES(16,'N','NO POSEE ESTUDIO',16);
ALTER TABLE personal DROP CONSTRAINT personal_nivel_educativo;

ALTER TABLE personal ADD  CONSTRAINT personal_nivel_educativo CHECK (nivel_educativo::text = 'P'::text OR nivel_educativo::text = 'D'::
text OR nivel_educativo::text = 'T'::text OR nivel_educativo::text = 'S'::text OR nivel_educativo::text = 'U'::text OR nivel_educativo::text = 'G'::
text OR nivel_educativo::text = 'C'::text OR nivel_educativo::text = 'O'::text OR nivel_educativo::text = 'I'::text OR nivel_educativo::text = 'H'::
text OR nivel_educativo::text = 'E'::text OR nivel_educativo::text = 'L'::text OR nivel_educativo::text = 'M'::text OR nivel_educativo::text = 'R':: text OR nivel_educativo::text = 'N'::text);

alter table educacion alter column calificacion type varchar(5);

ALTER TABLE prestamo  DROP  CONSTRAINT prestamocod; 


/************************************** INICIO DE FUNCION validar_prestamos_activos() **********************************************/
CREATE OR REPLACE FUNCTION validar_prestamos_activos()

RETURNS trigger AS

$BODY$

-- Por JACKSON PEREZ (PROMAFE)

-- Sistema de Gestión Financiera de Recursos Humanos SIGEFIRRHH (Versión 3.10)

-- 02/01/2010


-- trigger que impide que un trabajador posea dos prestamos activos

DECLARE

my_record record;

_codigo text;

_orga text;

_unad text;

_anho int4;

i int4 :=0;

aux int4 :=0;

contador int4 ;

ceros text;

codigo text;

BEGIN

contador:=(select count(*)

from prestamo where id_trabajador=NEW.id_trabajador and

id_concepto_tipo_personal=NEW.id_concepto_tipo_personal and

id_concepto_tipo_personal =NEW.id_concepto_tipo_personal

and estatus='A');


IF TG_OP='INSERT' and NEW.estatus ='A' AND contador=1 THEN

RAISE EXCEPTION 'No se puede registrar otro prestamo activo para el trabajador';

ELSIF TG_OP='UPDATE' THEN

IF OLD.estatus <>'A' and NEW.estatus ='A' AND contador=1 THEN

RAISE EXCEPTION 'No se puede registrar otro prestamo activo para el trabajador';

END IF;

END IF;


RETURN NEW;

END;

$BODY$

LANGUAGE 'plpgsql' VOLATILE

COST 100;

/************************************** FIN DE FUNCION validar_prestamos_activos() **********************************************/


/************************************** INTERFAZ *******************************************************************************/
ALTER TABLE unidadadministradora ADD COLUMN ano_presupuestario integer;
ALTER TABLE unidadadministradora ADD COLUMN cod_pagadora character varying(10);
ALTER TABLE unidadadministradora ADD CONSTRAINT unidadadministradora_id_organismo_cod_unidad_administ_ano_f_key UNIQUE (id_organismo, cod_unidad_administ, ano_presupuestario);
ALTER TABLE unidadadministradora DROP CONSTRAINT unidadadministradora_tipo;
ALTER TABLE unidadadministradora ADD CONSTRAINT unidadadministradora_tipo CHECK (tipo_unidad::text = 'O'::text OR tipo_unidad::text = 'S'::text OR tipo_unidad::text = 'A'::text OR tipo_unidad::text = '0'::text OR tipo_unidad::text = '1'::text OR tipo_unidad::text = '2'::text);
COMMENT ON COLUMN unidadadministradora.tipo_unidad IS 'El tipo de unidad puede ser:
O: Ordenadora -  denominacion anterior
S: Solicitante -  denominacion anterior
A: Ambas -  denominacion anterior
0: Unidad Administradora Desconcentrada sin delegación de firma
1: Unidad Administradora Desconcentrada con delegación de firma
2: Unidad Administradora Central';
COMMENT ON COLUMN unidadadministradora.ano_presupuestario IS 'ejercicio presupuestario';
ALTER TABLE unidadadministradora ALTER column cod_unidad_administ TYPE character varying(10);

ALTER TABLE unidadejecutora ALTER column cod_unidad_ejecutora TYPE character varying(10);
ALTER TABLE unidadejecutora  ADD COLUMN id_organismo integer;
ALTER TABLE unidadejecutora ADD CONSTRAINT unidadejecutora_id_organismo_fkey FOREIGN KEY (id_organismo)  REFERENCES organismo (id_organismo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE unidadejecutora ADD COLUMN ano_presupuestario integer;
-- OJO: se debe crear un procedimiento que ponga 2013  a el ano presupuestario de las columnas ya existentes
ALTER TABLE unidadejecutora ADD CONSTRAINT unidadejecutora_id_organismo_cod_unidad_ejecutora_ano_fisca_key UNIQUE (id_organismo, cod_unidad_ejecutora, ano_presupuestario);

ALTER TABLE administradorauel ADD COLUMN ano_presupuestario integer;
ALTER TABLE administradorauel ADD COLUMN id_organismo integer;

ALTER TABLE personal ADD CONSTRAINT personal_cedula_key UNIQUE (cedula);

CREATE TABLE cuentadante
(
  id_cuentadante serial NOT NULL, -- id del cuentadante
  id_organismo integer NOT NULL, -- id del organismo
  cedula integer NOT NULL, -- cedula del cuentadante
  cod_unidad_administ character varying(10) NOT NULL, -- codigo de la unidad administradora para la cual es cuentadante
  fecha_designacion date NOT NULL,
  ano_presupuestario integer NOT NULL,
  estatus character varying(1) NOT NULL,
  CONSTRAINT cuentadante_pkey PRIMARY KEY (id_cuentadante),
  CONSTRAINT cuentadante_cedula_fkey FOREIGN KEY (cedula)
      REFERENCES personal (cedula) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT cuentadante_id_organismo_fkey FOREIGN KEY (id_organismo, cod_unidad_administ, ano_presupuestario)
      REFERENCES unidadadministradora (id_organismo, cod_unidad_administ, ano_presupuestario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cuentadante
  OWNER TO postgres;
COMMENT ON COLUMN cuentadante.id_cuentadante IS 'id del cuentadante';
COMMENT ON COLUMN cuentadante.id_organismo IS 'id del organismo';
COMMENT ON COLUMN cuentadante.cedula IS 'cedula del cuentadante';
COMMENT ON COLUMN cuentadante.cod_unidad_administ IS 'codigo de la unidad administradora para la cual es cuentadante';

CREATE TABLE tipofondo
(
  id_tipo_fondo serial NOT NULL, -- identificador unico de la tabla, corresponde con el mismo de SIGECOF
  denominacion character varying NOT NULL,
  CONSTRAINT tipo_fondo_pkey PRIMARY KEY (id_tipo_fondo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tipofondo
  OWNER TO postgres;
COMMENT ON COLUMN tipofondo.id_tipo_fondo IS 'identificador unico de la tabla';

INSERT INTO tipofondo VALUES (3, 'Fondo de remuneraciones al personal');
INSERT INTO tipofondo VALUES (8, 'Fondo de Pensionados y Jubilados');
INSERT INTO tipofondo VALUES (9, 'Fondo de Becados');

CREATE TABLE cuentadantecuentafondo
(
  id_cuentadante_cuenta_fondo serial NOT NULL, -- id de la tabla id_cuentadante_cuenta_fondo
  id_cuentadante integer NOT NULL, -- id del cuentadante
  id_tipo_fondo integer NOT NULL,
  cod_banco character varying(5) NOT NULL, -- codigo de la institucion bancaria
  numero_cuenta_bancaria character varying(30) NOT NULL, -- numero de la cuenta
  ano_presupuestario integer NOT NULL, -- ejercicio presupuestario
  status character varying(1) NOT NULL,
  CONSTRAINT cuentadante_cuenta_fondo_pkey PRIMARY KEY (id_cuentadante_cuenta_fondo),
  CONSTRAINT cuentadante_cuenta_fondo_id_cuentadante_fkey FOREIGN KEY (id_cuentadante)
      REFERENCES cuentadante (id_cuentadante) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT cuentadante_cuenta_fondo_id_tipo_fondo_fkey FOREIGN KEY (id_tipo_fondo)
      REFERENCES tipofondo (id_tipo_fondo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cuentadantecuentafondo
  OWNER TO postgres;
COMMENT ON COLUMN cuentadantecuentafondo.id_cuentadante_cuenta_fondo IS 'id de la tabla id_cuentadante_cuenta_fondo';
COMMENT ON COLUMN cuentadantecuentafondo.id_cuentadante IS 'id del cuentadante';
COMMENT ON COLUMN cuentadantecuentafondo.cod_banco IS 'codigo de la institucion bancaria';
COMMENT ON COLUMN cuentadantecuentafondo.numero_cuenta_bancaria IS 'numero de la cuenta';
COMMENT ON COLUMN cuentadantecuentafondo.ano_presupuestario IS 'ejercicio presupuestario';

--CATEGORIA PRES
ALTER TABLE proyecto ALTER COLUMN enunciado TYPE character varying;
ALTER TABLE proyecto ALTER COLUMN enunciado SET NOT NULL;
ALTER TABLE accioncentralizada ALTER COLUMN denominacion TYPE character varying;
ALTER TABLE accioncentralizada ALTER COLUMN denominacion SET NOT NULL;
ALTER TABLE accionespecifica ALTER COLUMN denominacion TYPE character varying;
ALTER TABLE accionespecifica ADD COLUMN id_organismo integer;
ALTER TABLE accionespecifica ADD CONSTRAINT id_organismo FOREIGN KEY (id_organismo) REFERENCES organismo (id_organismo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE SET NULL;
ALTER TABLE uelespecifica ADD COLUMN id_organismo integer;
ALTER TABLE uelespecifica ADD CONSTRAINT id_organismo FOREIGN KEY (id_organismo) REFERENCES organismo (id_organismo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE SET NULL;
COMMENT ON COLUMN accionespecifica.tipo IS 'Se refiere a si la AE perteneca a un poryecto (P) o una Accion Centralizada (AC)';
ALTER TABLE accionespecifica ADD CONSTRAINT tipo CHECK (tipo::text = 'P'::text OR tipo::text = 'A'::text);
COMMENT ON COLUMN uelespecifica.categoria_presupuesto IS 'la categoria se forma automaticamente';


INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (1,'1','Ingresos Ordinarios');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (2,'2','Credito Interno');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (3,'3','Credito Externo');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (4,'4','Venta de Activos');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (5,'5','Recursos provenientes del FEM');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (6,'6','Reservas del Tesoro no comprometidas');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (7,'7','Otros');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (8,'8','Gestion Fiscal');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (9,'9','Deuda Publica');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (10,'10','Proyectos por Endeudamiento');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (11,'11','Compromisos y Transferencias');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (12,'12','Para Pagos de Compromisos');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (13,'13','PROYECTOS AGRÍCOLAS');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (14,'14','GRAN MISIÓN AGRO-VENEZUELA');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (15,'15','GRAN MISIÓN VIVIENDA VENEZUELA');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (16,'16','GRAN MISIÓN TRABAJO VENEZUELA');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (17,'17','EMERGENCIAS Y DESASTRES NATURALES');
INSERT INTO fuentefinanciamiento(id_fuente_financiamiento, cod_fuente_financiamiento, nombre) VALUES (18,'18','ENDEUDAMIENTO COMPLEMENTARIO');

ALTER TABLE cuentapresupuesto ADD COLUMN ano_presupuestario integer;


CREATE TABLE anopresupuestario
(
  id_ano_presupuestario serial NOT NULL, -- identificador unico
  ano_presupuestario integer NOT NULL, -- ano presupuestario
  estatus character varying(1) NOT NULL, -- indica si esta activo (A) o inactivo (I)
  CONSTRAINT ano_presupuestario_pkey PRIMARY KEY (id_ano_presupuestario),
  CONSTRAINT ano_presupuestario_estatus_check CHECK (estatus::text = 'A'::text OR estatus::text = 'I'::text)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE anopresupuestario
  OWNER TO postgres;
COMMENT ON COLUMN anopresupuestario.id_ano_presupuestario IS 'identificador unico';
COMMENT ON COLUMN anopresupuestario.ano_presupuestario IS 'ano presupuestario';
COMMENT ON COLUMN anopresupuestario.estatus IS 'indica si esta activo (A) o inactivo (I)';


INSERT INTO anopresupuestario(id_ano_presupuestario, ano_presupuestario, estatus) VALUES (1,2015,'A');

CREATE TABLE tipopago
(
  id_tipo_pago serial NOT NULL, -- identificador unico de la tabla, autoincremental
  id_tipo_fondo integer NOT NULL, -- identificador del tipo de fondo
  denominacion character varying NOT NULL, -- denominacion del tipo de pago
  directo character(1) NOT NULL DEFAULT 'N', -- toma los valores S, N
  CONSTRAINT id_tipo_pago PRIMARY KEY (id_tipo_pago),
  CONSTRAINT id_tipo_fondo FOREIGN KEY (id_tipo_fondo)
      REFERENCES tipofondo (id_tipo_fondo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT check_directo CHECK (directo = 'S'::bpchar OR directo = 'N'::bpchar)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tipopago
  OWNER TO postgres;
COMMENT ON COLUMN tipopago.id_tipo_pago IS 'identificador unico de la tabla, autoincremental';
COMMENT ON COLUMN tipopago.id_tipo_fondo IS 'identificador del tipo de fondo';
COMMENT ON COLUMN tipopago.denominacion IS 'denominacion del tipo de pago';
COMMENT ON COLUMN tipopago.directo IS 'toma los valores S, N';

INSERT INTO tipopago(id_tipo_pago, id_tipo_fondo, denominacion, directo) VALUES ('3','3','Avances para el pago de personal activo','N');
INSERT INTO tipopago(id_tipo_pago, id_tipo_fondo, denominacion, directo) VALUES ('4','8','Avances para el pago de personal pensionado y jubilado','N');
INSERT INTO tipopago(id_tipo_pago, id_tipo_fondo, denominacion, directo) VALUES ('12','9','Avances para el pago de personal becado','N');

CREATE TABLE tipomovimiento
(
  id_tipo_movimiento serial NOT NULL, -- identificador del tipo de movimiento presupuestario
  denominacion character varying NOT NULL, -- denominacion del movimiento
  CONSTRAINT pk_tipo_movimiento PRIMARY KEY (id_tipo_movimiento)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tipomovimiento
  OWNER TO postgres;
COMMENT ON COLUMN tipomovimiento.id_tipo_movimiento IS 'identificador del tipo de movimiento presupuestario';
COMMENT ON COLUMN tipomovimiento.denominacion IS 'denominacion del movimiento';


INSERT INTO tipomovimiento(id_tipo_movimiento, denominacion) VALUES ('12','Compromiso');
INSERT INTO tipomovimiento(id_tipo_movimiento, denominacion) VALUES ('13','Causados');

CREATE TABLE tipodocumento
(
  id_tipo_documento serial NOT NULL, -- identificador del documento presupuestario
  denominacion character varying NOT NULL, -- denominacion del documento presupuestario
  CONSTRAINT pk_id_tipo_documento PRIMARY KEY (id_tipo_documento)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tipodocumento
  OWNER TO postgres;
COMMENT ON COLUMN tipodocumento.id_tipo_documento IS 'identificador del documento presupuestario';
COMMENT ON COLUMN tipodocumento.denominacion IS 'denominacion del documento presupuestario';

INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (1, 'Punto de Cuenta');
INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (7, 'Contrato de Obra');
INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (10, 'Orden de Servicio');
INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (11, 'Resumen de Nomina');
INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (46, 'Compromiso de Remuneracion de al Personal');
INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (47, 'Compromiso de Remuneracion Pensionados y Jubilados');
INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (48, 'Planilla de Liquidacion');
INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (50, 'Resumen de Nomina Empleados');
INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (51, 'Resumen de Nomina Obreros');
INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (53, 'Resumen de Nomina Pensionados');
INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (54, 'Resumen de Nomina Jubilados');
INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (55, 'Resumen de Nomina Contratados');
INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (58, 'Memorandum');
INSERT INTO tipodocumento(id_tipo_documento, denominacion) VALUES (60, 'Resumen de Nomina Personal Becado');

CREATE TABLE tipopagodocmov
(
  id_tipo_pago_doc_mov serial NOT NULL, -- identificador unico de la tabla que relaciona el tipo de pago, con el documento y el tipo de movimiento
  id_tipo_pago integer, -- identificador del tipo de pago
  id_tipo_documento integer NOT NULL, -- identificador del tipo de documento
  id_tipo_movimiento integer NOT NULL, -- identificador del tipo de movimiento
  CONSTRAINT pk_id_tipo_pago_doc_mov PRIMARY KEY (id_tipo_pago_doc_mov),
  CONSTRAINT fk_tipo_pago FOREIGN KEY (id_tipo_pago)
      REFERENCES tipopago (id_tipo_pago) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pk_tipo_documento FOREIGN KEY (id_tipo_documento)
      REFERENCES tipodocumento (id_tipo_documento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pk_tipo_movimiento FOREIGN KEY (id_tipo_movimiento)
      REFERENCES tipomovimiento (id_tipo_movimiento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tipopagodocmov
  OWNER TO postgres;
COMMENT ON COLUMN tipopagodocmov.id_tipo_pago_doc_mov IS 'identificador unico de la tabla que relaciona el tipo de pago, con el documento y el tipo de movimiento';
COMMENT ON COLUMN tipopagodocmov.id_tipo_pago IS 'identificador del tipo de pago';
COMMENT ON COLUMN tipopagodocmov.id_tipo_documento IS 'identificador del tipo de documento';
COMMENT ON COLUMN tipopagodocmov.id_tipo_movimiento IS 'identificador del tipo de movimiento';

INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (3, 11, 13);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (3, 58, 13);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (3, 11, 12);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (3, 46, 12);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (3, 47, 12);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (3, 50, 12);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (3, 51, 12);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (3, 55, 12);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (3, 58, 12);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (4, 11, 13);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (4, 58, 13);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (4, 47, 12);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (4, 48, 12);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (4, 53, 12);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (4, 54, 12);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (12, 1, 13);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (12, 7, 13);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (12, 10, 13);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (12, 11, 13);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (12, 48, 12);
INSERT INTO tipopagodocmov(id_tipo_pago, id_tipo_documento, id_tipo_movimiento) VALUES (12, 60, 12);


--PENDIENTES
--INSERTS PARA CUENTA PRESUPUESTO
ALTER TABLE categoriapresupuesto ADD COLUMN cod_sigecof_tnom character varying(2);
INSERT INTO categoriapresupuesto VALUES (6,'6','BECADOS','6');
UPDATE categoriapresupuesto SET cod_sigecof_tnom='1' WHERE id_categoria_presupuesto=1;
UPDATE categoriapresupuesto SET cod_sigecof_tnom='2' WHERE id_categoria_presupuesto=2;
UPDATE categoriapresupuesto SET cod_sigecof_tnom='3' WHERE id_categoria_presupuesto=3;
UPDATE categoriapresupuesto SET cod_sigecof_tnom='5' WHERE id_categoria_presupuesto=4;
UPDATE categoriapresupuesto SET cod_sigecof_tnom='4' WHERE id_categoria_presupuesto=5;
ALTER TABLE categoriapresupuesto ALTER COLUMN cod_sigecof_tnom SET NOT NULL;

CREATE TABLE categoriapresupuestofondodoc
(
  id_categoriapresupuesto_fondo_doc integer NOT NULL,
  id_categoria_presupuesto integer NOT NULL, -- id del tipo de personal
  id_tipo_fondo integer NOT NULL, -- id del tipo del fondo
  id_tipo_documento integer NOT NULL, -- id del tipo de documento
  CONSTRAINT id_categoria_personal_fondo_doc PRIMARY KEY (id_categoriapresupuesto_fondo_doc),
  CONSTRAINT id_categoria_presupuesto FOREIGN KEY (id_categoria_presupuesto)
      REFERENCES categoriapresupuesto (id_categoria_presupuesto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_tipo_doc_pres FOREIGN KEY (id_tipo_documento)
      REFERENCES tipodocumento (id_tipo_documento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_tipo_fondo FOREIGN KEY (id_tipo_fondo)
      REFERENCES tipofondo (id_tipo_fondo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE categoriapresupuestofondodoc
  OWNER TO postgres;
COMMENT ON TABLE categoriapresupuestofondodoc
  IS 'tabla que relaciona el tipo de nomina con el fondo y con  el documento';
COMMENT ON COLUMN categoriapresupuestofondodoc.id_categoria_presupuesto IS 'id del tipo de personal';
COMMENT ON COLUMN categoriapresupuestofondodoc.id_tipo_fondo IS 'id del tipo del fondo';
COMMENT ON COLUMN categoriapresupuestofondodoc.id_tipo_documento IS 'id del tipo de documento';


INSERT INTO categoriapresupuestofondodoc VALUES (1, 1, 3, 50);
INSERT INTO categoriapresupuestofondodoc VALUES (2, 2, 3, 51);
INSERT INTO categoriapresupuestofondodoc VALUES (3, 3, 3, 55);
INSERT INTO categoriapresupuestofondodoc VALUES (4, 4, 8, 54);
INSERT INTO categoriapresupuestofondodoc VALUES (5, 5, 8, 53);
INSERT INTO categoriapresupuestofondodoc VALUES (6, 6, 9, 60);

CREATE TABLE periodonomina
(
  id_periodo_nomina integer NOT NULL, -- se refiere al mismo id que es manejado en sigecof
  descripcion character varying NOT NULL,
  CONSTRAINT id_periodo_nomina PRIMARY KEY (id_periodo_nomina)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE periodonomina
  OWNER TO postgres;
COMMENT ON COLUMN periodonomina.id_periodo_nomina IS 'se refiere al mismo id que es manejado en sigecof';

INSERT INTO periodonomina VALUES (2, 'Quincenal');
INSERT INTO periodonomina VALUES (4, 'Mensual');
INSERT INTO periodonomina VALUES (6, 'Mensual 1era Quincena');
INSERT INTO periodonomina VALUES (7, 'Mensual 2da Quincena');

CREATE TABLE compromisoinicial
(
  id_compromiso_inicial serial NOT NULL, -- id del compromiso inicial
  id_organismo integer NOT NULL, -- id sigefirrhh del organismo
  ano_presupuestario integer NOT NULL, -- ejercicio presupuestario
  id_unidad_administradora integer NOT NULL, -- id de la UA en sigefirrhh
  id_cuentadante integer,
  id_tipo_pago integer, -- identificador del tipo de pago, el cual ya tiene asociado el tipo de fondo.
  id_tipo_documento integer, -- identificador del documento
  nro_documento character varying(10), -- numero del documento
  observacion integer, -- observacion del compromiso
  origen_presupuestario character varying(1), -- Origen presupuestario del compromiso. Puede ser:...
  gaceta_cred_adicional character varying(10), -- numero de la gaceta del credito adicional
  decreto_cred_adicional character varying(10), -- decreto del credito adicional
  fecha_cred_adicional date, -- fecha del creditod adicional
  gaceta_rectificacion character varying(10), -- nro de la gaceta de rectificacion
  decreto_rectificacion character varying(10), -- numero de decreto de la rectificacion
  fecha_rectificacion date, -- fecha de la rectificacion
  nro_expediente bigint, -- numero del expediente que es enviado por sigecof
  nro_compromiso bigint, -- numero del compromiso que es enviado por sigecof
  fecha_registro date, -- fecha de registro del compromiso en sigecof
  CONSTRAINT id_compromiso_inicial_sigecof PRIMARY KEY (id_compromiso_inicial),
  CONSTRAINT id_cuentadante FOREIGN KEY (id_cuentadante)
      REFERENCES cuentadante (id_cuentadante) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_organismo FOREIGN KEY (id_organismo)
      REFERENCES organismo (id_organismo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_tipo_documento FOREIGN KEY (id_tipo_documento)
      REFERENCES tipodocumento (id_tipo_documento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_tipo_pago FOREIGN KEY (id_tipo_pago)
      REFERENCES tipopago (id_tipo_pago) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_unidad_administradora FOREIGN KEY (id_unidad_administradora)
      REFERENCES unidadadministradora (id_unidad_administradora) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT origen_presupuestario CHECK (origen_presupuestario::text = '1'::text OR origen_presupuestario::text = '2'::text OR origen_presupuestario::text = '3'::text)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE compromisoinicial
  OWNER TO postgres;
COMMENT ON TABLE compromisoinicial
  IS 'esta tabla lleva el registro del compromiso inicial, el cual es enviado al sigecof. Asi mismo guarda los datos del mismo que son enviados por el sigecof. La lista de imputaciones por compromiso son almacenadas en la tabla compromiso_inicial_sicecof_imputaciones';
COMMENT ON COLUMN compromisoinicial.id_compromiso_inicial IS 'id del compromiso inicial';
COMMENT ON COLUMN compromisoinicial.id_organismo IS 'id sigefirrhh del organismo';
COMMENT ON COLUMN compromisoinicial.ano_presupuestario IS 'ejercicio presupuestario';
COMMENT ON COLUMN compromisoinicial.id_unidad_administradora IS 'id de la UA en sigefirrhh';
COMMENT ON COLUMN compromisoinicial.id_tipo_pago IS 'identificador del tipo de pago, el cual ya tiene asociado el tipo de fondo. ';
COMMENT ON COLUMN compromisoinicial.id_tipo_documento IS 'identificador del documento ';
COMMENT ON COLUMN compromisoinicial.nro_documento IS 'numero del documento';
COMMENT ON COLUMN compromisoinicial.observacion IS 'observacion del compromiso';
COMMENT ON COLUMN compromisoinicial.origen_presupuestario IS 'Origen presupuestario del compromiso. Puede ser:
1= Monto Ley
2= Credito Adicional
3= Rectificacion
Para el caso de Credito Adicional y Rectificacion se requieren los siguientes satos: numero de gaceta, numero de resolucion y fecha de la gaceta';
COMMENT ON COLUMN compromisoinicial.gaceta_cred_adicional IS 'numero de la gaceta del credito adicional';
COMMENT ON COLUMN compromisoinicial.decreto_cred_adicional IS 'decreto del credito adicional';
COMMENT ON COLUMN compromisoinicial.fecha_cred_adicional IS 'fecha del creditod adicional';
COMMENT ON COLUMN compromisoinicial.gaceta_rectificacion IS 'nro de la gaceta de rectificacion';
COMMENT ON COLUMN compromisoinicial.decreto_rectificacion IS 'numero de decreto de la rectificacion';
COMMENT ON COLUMN compromisoinicial.fecha_rectificacion IS 'fecha de la rectificacion';
COMMENT ON COLUMN compromisoinicial.nro_expediente IS 'numero del expediente que es enviado por sigecof';
COMMENT ON COLUMN compromisoinicial.nro_compromiso IS 'numero del compromiso que es enviado por sigecof';
COMMENT ON COLUMN compromisoinicial.fecha_registro IS 'fecha de registro del compromiso en sigecof';

CREATE TABLE compromisoinicialregularizacion
(
  id_compromiso_inicial_regularizacion serial NOT NULL, -- id de la tabla de regularización de CI
  id_compromiso_inicial integer NOT NULL, -- id del compromiso inicial que fue regularizado
  id_tipo_documento integer, -- identificador del documento
  nro_documento character varying(10), -- numero del documento
  observacion integer, -- observacion del compromiso
  origen_presupuestario character varying(1), -- Origen presupuestario del compromiso. Puede ser:...
  gaceta_cred_adicional character varying(10), -- numero de la gaceta del credito adicional
  decreto_cred_adicional character varying(10), -- decreto del credito adicional
  fecha_cred_adicional date, -- fecha del creditod adicional
  gaceta_rectificacion character varying(10), -- nro de la gaceta de rectificacion
  decreto_rectificacion character varying(10), -- numero de decreto de la rectificacion
  fecha_rectificacion date, -- fecha de la rectificacion
  tarea integer NOT NULL, -- identificardo para la traza por compromiso
  fecha_registro date, -- fecha del registro
  CONSTRAINT id_compromiso_inicial_sigecof_regularizacion PRIMARY KEY (id_compromiso_inicial_regularizacion),
  CONSTRAINT compromiso_inicial_sicecof_re_id_compromiso_inicial_siceco_fkey FOREIGN KEY (id_compromiso_inicial)
      REFERENCES compromisoinicial (id_compromiso_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_tipo_documento FOREIGN KEY (id_tipo_documento)
      REFERENCES tipodocumento (id_tipo_documento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT origen_presupuestario CHECK (origen_presupuestario::text = '1'::text OR origen_presupuestario::text = '2'::text OR origen_presupuestario::text = '3'::text)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE compromisoinicialregularizacion
  OWNER TO postgres;
COMMENT ON TABLE compromisoinicialregularizacion
  IS 'esta tabla lleva el registro del compromiso inicial, el cual es enviado al sigecof. Asi mismo guarda los datos del mismo que son enviados por el sigecof. La lista de imputaciones por compromiso son almacenadas en la tabla compromiso_inicial_sicecof_imputaciones';
COMMENT ON COLUMN compromisoinicialregularizacion.id_compromiso_inicial_regularizacion IS 'id de la tabla de regularización de CI';
COMMENT ON COLUMN compromisoinicialregularizacion.id_compromiso_inicial IS 'id del compromiso inicial que fue regularizado';
COMMENT ON COLUMN compromisoinicialregularizacion.id_tipo_documento IS 'identificador del documento ';
COMMENT ON COLUMN compromisoinicialregularizacion.nro_documento IS 'numero del documento';
COMMENT ON COLUMN compromisoinicialregularizacion.observacion IS 'observacion del compromiso';
COMMENT ON COLUMN compromisoinicialregularizacion.origen_presupuestario IS 'Origen presupuestario del compromiso. Puede ser:
1= Monto Ley
2= Credito Adicional
3= Rectificacion
Para el caso de Credito Adicional y Rectificacion se requieren los siguientes satos: numero de gaceta, numero de resolucion y fecha de la gaceta';
COMMENT ON COLUMN compromisoinicialregularizacion.gaceta_cred_adicional IS 'numero de la gaceta del credito adicional';
COMMENT ON COLUMN compromisoinicialregularizacion.decreto_cred_adicional IS 'decreto del credito adicional';
COMMENT ON COLUMN compromisoinicialregularizacion.fecha_cred_adicional IS 'fecha del creditod adicional';
COMMENT ON COLUMN compromisoinicialregularizacion.gaceta_rectificacion IS 'nro de la gaceta de rectificacion';
COMMENT ON COLUMN compromisoinicialregularizacion.decreto_rectificacion IS 'numero de decreto de la rectificacion';
COMMENT ON COLUMN compromisoinicialregularizacion.fecha_rectificacion IS 'fecha de la rectificacion';
COMMENT ON COLUMN compromisoinicialregularizacion.tarea IS 'identificardo para la traza por compromiso';
COMMENT ON COLUMN compromisoinicialregularizacion.fecha_registro IS 'fecha del registro';

CREATE TABLE compromisoinicialdetalle
(
  id_compromiso_inicial_detalle serial NOT NULL, -- id del registro
  id_compromiso_inicial integer NOT NULL, -- id del compromiso inicial al cual pertenece
  id_partida_uel_especifica integer NOT NULL, -- referencia para obtener: uel, accion especifica, categoria presupuestaria, objeto de gasto y fuente de financiamiento
  monto double precision NOT NULL DEFAULT 0,
  guardado boolean NOT NULL DEFAULT false, -- indica si la imputacion fue guardada o no
  mensajeRta character varying, -- Mensaje que indica si la imputacion fue guardada exitosamente, de lo contrario indicara el motivo por el cual no pudo ser guardado
  CONSTRAINT id_compromiso_inicial_detalle PRIMARY KEY (id_compromiso_inicial_detalle),
  CONSTRAINT id_compromiso_inicial FOREIGN KEY (id_compromiso_inicial)
      REFERENCES compromisoinicial (id_compromiso_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_partida_uel_especifica FOREIGN KEY (id_partida_uel_especifica)
      REFERENCES partidauelespecifica (id_partida_uel_especifica) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE compromisoinicialdetalle
  OWNER TO postgres;
COMMENT ON COLUMN compromisoinicialdetalle.id_compromiso_inicial_detalle IS 'id del registro';
COMMENT ON COLUMN compromisoinicialdetalle.id_compromiso_inicial IS 'id del compromiso inicial al cual pertenece';
COMMENT ON COLUMN compromisoinicialdetalle.id_partida_uel_especifica IS 'referencia para obtener: uel, accion especifica, categoria presupuestaria, objeto de gasto y fuente de financiamiento';
COMMENT ON COLUMN compromisoinicialdetalle.guardado IS 'indica si la imputacion fue guardada o no';
COMMENT ON COLUMN compromisoinicialdetalle.mensajeRta IS 'Mensaje que indica si la imputacion fue guardada exitosamente, de lo contrario indicara el motivo por el cual no pudo ser guardado';

CREATE TABLE compromisoinicialdetalleregularizacion
(
  id_compromiso_inicial_detalle_regularizacion serial NOT NULL, -- id del registro
  id_compromiso_inicial_detalle integer NOT NULL, -- id de la imputacion a regularizar
  monto double precision NOT NULL DEFAULT 0,
  tarea integer NOT NULL, -- identificardo para la traza de la imputacion
  fecha_registro date, -- fecha del registro
  CONSTRAINT id_compromiso_inicial_detalle_regularizacion PRIMARY KEY (id_compromiso_inicial_detalle_regularizacion),
  CONSTRAINT id_compromiso_inicial_detalle FOREIGN KEY (id_compromiso_inicial_detalle)
      REFERENCES compromisoinicialdetalle (id_compromiso_inicial_detalle) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE compromisoinicialdetalleregularizacion
  OWNER TO postgres;
COMMENT ON COLUMN compromisoinicialdetalleregularizacion.id_compromiso_inicial_detalle_regularizacion IS 'id del registro';
COMMENT ON COLUMN compromisoinicialdetalleregularizacion.id_compromiso_inicial_detalle IS 'id de la imputacion a regularizar';
COMMENT ON COLUMN compromisoinicialdetalleregularizacion.tarea IS 'identificardo para la traza de la imputacion';
COMMENT ON COLUMN compromisoinicialdetalleregularizacion.fecha_registro IS 'fecha del registro';

