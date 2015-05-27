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
