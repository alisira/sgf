 DELETE FROM public.compromisoinicial WHERE id_compromiso_inicial >= '182'::integer

INSERT INTO public.compromisoinicial( id_organismo, ano, tarea, estatus, id_unidad_administradora, id_cuentadante, id_tipo_pago, id_tipo_documento, documento, observacion, origen_presupuestario, expediente, compromiso, fecha_registro, id_tipo_fondo) 
VALUES ( '11'::integer, '2014'::integer, '1'::integer, '0'::integer, '18'::integer, '1'::integer, '1'::integer, '0'::integer, '6677'::text, 'prueba'::text, '1'::text, '42'::bigint, '9999'::bigint, '2015-06-03'::date, '1'::integer)
returning id_compromiso_inicial


INSERT INTO public.compromisoinicial( id_organismo, ano, tarea, estatus, id_unidad_administradora, id_cuentadante, id_tipo_pago, id_tipo_documento, documento, observacion, origen_presupuestario, expediente, compromiso, fecha_registro, id_tipo_fondo) 
insert into compromisoinicial       (id_organismo,  ano, tarea, estatus, id_unidad_administradora, id_cuentadante,id_tipo_pago,id_tipo_fondo,id_tipo_documento,documento,observacion,origen_presupuestario   ,expediente,compromiso,fecha_registro) 
 VALUES ( '11'::integer, '2014'::integer, '1'::integer, '0'::integer, '18'::integer, '1'::integer, '1'::integer, '0'::integer,  '1'::integer, '6677'::text, 'prueba'::text, '1'::text, '42'::bigint, '9999'::bigint, '2015-06-03'::date)
returning id_compromiso_inicial    

 returning id_compromiso_inicial