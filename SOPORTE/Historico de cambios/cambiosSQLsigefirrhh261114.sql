
INSERT INTO niveleducativo  VALUES(16,'N','NO POSEE ESTUDIO',16);
ALTER TABLE personal DROP CONSTRAINT personal_nivel_educativo;

ALTER TABLE personal ADD  CONSTRAINT personal_nivel_educativo CHECK (nivel_educativo::text = 'P'::text OR nivel_educativo::text = 'D'::
text OR nivel_educativo::text = 'T'::text OR nivel_educativo::text = 'S'::text OR nivel_educativo::text = 'U'::text OR nivel_educativo::text = 'G'::
text OR nivel_educativo::text = 'C'::text OR nivel_educativo::text = 'O'::text OR nivel_educativo::text = 'I'::text OR nivel_educativo::text = 'H'::
text OR nivel_educativo::text = 'E'::text OR nivel_educativo::text = 'L'::text OR nivel_educativo::text = 'M'::text OR nivel_educativo::text = 'R'::                                             text OR nivel_educativo::text = 'N'::text);

ALTER TABLE unidadadministradora ALTER column cod_unidad_administ TYPE character varying(10);

ALTER TABLE unidadejecutora ALTER column cod_unidad_ejecutora TYPE character varying(10);

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
ALTER TABLE unidadadministradora ADD COLUMNA ano_presupuestario integer;
ALTER TABLE unidadadministradora ADD COLUMNA cod_pagadora character varying(10);
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


ALTER TABLE unidadejecutora ADD CONSTRAINT unidadejecutora_id_organismo_fkey FOREIGN KEY (id_organismo)  REFERENCES organismo (id_organismo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE unidadejecutora ADD COLUMNA ano_presupuestario integer;
-- OJO: se debe crear un procedimiento que ponga 2013  a el ano presupuestario de las columnas ya existentes
ALTER TABLE unidadejecutora ADD CONSTRAINT unidadejecutora_id_organismo_cod_unidad_ejecutora_ano_fisca_key UNIQUE (id_organismo, cod_unidad_ejecutora, ano_presupuestario);

ALTER TABLE personal ADD CONSTRAINT personal_cedula_key UNIQUE (cedula);

CREATE TABLE cuentadante
(
  id_cuentadante serial NOT NULL, -- id del cuentadante
  id_organismo integer, -- id del organismo
  cedula integer, -- cedula del cuentadante
  cod_unidad_administ character varying(10), -- codigo de la unidad administradora para la cual es cuentadante
  fecha_designacion date,
  ano_presupuestario integer,
  estatus character varying(1),
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

CREATE TABLE tipo_fondo
(
  id_tipo_fondo serial NOT NULL, -- identificador unico de la tabla
  denominacion character varying,
  CONSTRAINT tipo_fondo_pkey PRIMARY KEY (id_tipo_fondo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tipo_fondo
  OWNER TO postgres;
COMMENT ON COLUMN tipo_fondo.id_tipo_fondo IS 'identificador unico de la tabla';

INSERT INTO tipo_fondo VALUES (3, 'Avance para el personal activo');
INSERT INTO tipo_fondo VALUES (8, 'Avance para el pago del Personal Jubilado y Pensionado');
INSERT INTO tipo_fondo VALUES (9, 'Avance para el pago del Personal Becado');

CREATE TABLE cuentadante_cuenta_fondo
(
  id_cuentadante_cuenta_fondo serial NOT NULL, -- id de la tabla id_cuentadante_cuenta_fondo
  id_cuentadante integer, -- id del cuentadante
  id_tipo_fondo integer,
  cod_banco character varying(5), -- codigo de la institucion bancaria
  numero_cuenta_bancaria character varying(30), -- numero de la cuenta
  ano_presupuestario integer, -- ejercicio presupuestario
  status character varying(1),
  CONSTRAINT cuentadante_cuenta_fondo_pkey PRIMARY KEY (id_cuentadante_cuenta_fondo),
  CONSTRAINT cuentadante_cuenta_fondo_id_cuentadante_fkey FOREIGN KEY (id_cuentadante)
      REFERENCES cuentadante (id_cuentadante) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT cuentadante_cuenta_fondo_id_tipo_fondo_fkey FOREIGN KEY (id_tipo_fondo)
      REFERENCES tipo_fondo (id_tipo_fondo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cuentadante_cuenta_fondo
  OWNER TO postgres;
COMMENT ON COLUMN cuentadante_cuenta_fondo.id_cuentadante_cuenta_fondo IS 'id de la tabla id_cuentadante_cuenta_fondo';
COMMENT ON COLUMN cuentadante_cuenta_fondo.id_cuentadante IS 'id del cuentadante';
COMMENT ON COLUMN cuentadante_cuenta_fondo.cod_banco IS 'codigo de la institucion bancaria';
COMMENT ON COLUMN cuentadante_cuenta_fondo.numero_cuenta_bancaria IS 'numero de la cuenta';
COMMENT ON COLUMN cuentadante_cuenta_fondo.ano_presupuestario IS 'ejercicio presupuestario';

ALTER TABLE proyecto ALTER COLUMN enunciado TYPE character varying;
ALTER TABLE accioncentralizada ALTER COLUMN denominacion TYPE character varying;



