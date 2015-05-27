CREATE TABLE menu
(  
  indice character varying(10) NOT NULL,
  nombre character varying(10) NOT NULL  
)
WITH (
  OIDS=FALSE
);
ALTER TABLE menu
  OWNER TO postgres;

INSERT INTO menu(indice, nombre) VALUES (?, ?);

INSERT INTO menu(indice, nombre) VALUES ('10', 'Opcion _1');
INSERT INTO menu(indice, nombre) VALUES ('11', 'Opcion 1_1');
INSERT INTO menu(indice, nombre) VALUES ('12', 'Opcion 1_2');
INSERT INTO menu(indice, nombre) VALUES ('121', 'Opcion 12_1');
INSERT INTO menu(indice, nombre) VALUES ('122', 'Opcion 12_2');
INSERT INTO menu(indice, nombre) VALUES ('13', 'Opcion 1_3');
INSERT INTO menu(indice, nombre) VALUES ('20', 'Opcion _2');
INSERT INTO menu(indice, nombre) VALUES ('21', 'Opcion 2_1');
INSERT INTO menu(indice, nombre) VALUES ('22', 'Opcion 2_2');
INSERT INTO menu(indice, nombre) VALUES ('221', 'Opcion 22_1');
INSERT INTO menu(indice, nombre) VALUES ('30','Opcion _3');


INSERT INTO menu(indice, nombre) VALUES ('2', 'Opcion _2');
INSERT INTO menu(indice, nombre) VALUES ('21', 'Opcion 2_1');
INSERT INTO menu(indice, nombre) VALUES ('22', 'Opcion 2_2');
INSERT INTO menu(indice, nombre) VALUES ('221', 'Opcion 22_1');
INSERT INTO menu(indice, nombre) VALUES ('3','Opcion _3');
INSERT INTO menu(indice, nombre) VALUES ('1', 'Opcion _1');
INSERT INTO menu(indice, nombre) VALUES ('11', 'Opcion 1_1');
INSERT INTO menu(indice, nombre) VALUES ('12', 'Opcion 1_2');
INSERT INTO menu(indice, nombre) VALUES ('121', 'Opcion 12_1');
INSERT INTO menu(indice, nombre) VALUES ('122', 'Opcion 12_2');
INSERT INTO menu(indice, nombre) VALUES ('13', 'Opcion 1_3');

delete from menu

select * from menu order by indice