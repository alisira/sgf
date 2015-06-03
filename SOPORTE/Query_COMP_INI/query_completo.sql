SELECT  
			  sum( CASE WHEN f.cod_frecuencia_pago = 1 THEN (cf.monto)                
			  WHEN f.cod_frecuencia_pago = 2 THEN (cf.monto)                
			  WHEN f.cod_frecuencia_pago = 3 THEN (cf.monto)                
			  WHEN f.cod_frecuencia_pago = 4 THEN (cf.monto)                
			  WHEN f.cod_frecuencia_pago = 5 THEN (cf.monto)                
			  WHEN f.cod_frecuencia_pago = 6 THEN (cf.monto)                
			  WHEN f.cod_frecuencia_pago = 7 THEN (cf.monto)                
			  WHEN f.cod_frecuencia_pago = 8 THEN (cf.monto)                
			  WHEN f.cod_frecuencia_pago = 9 THEN (cf.monto)           END )  as monto,
			  
			  ctapre.cod_presupuesto, ctapre.descripcion,  uel.id_unidad_ejecutora, uel.cod_unidad_ejecutora, uel.nombre, 
			  uelesp.categoria_presupuesto, 
			  pue.id_fuente_financiamiento,
			  pue.id_partida_uel_especifica, dep.id_organismo, ctapre.id_cuenta_presupuesto

from frecuenciapago f,  frecuenciatipopersonal ftp, unidadadministradora uad, administradorauel au, dependencia dep, categoriapersonal catper,    
clasificacionpersonal cper, trabajador t, conceptofijo cf, concepto c, tipopersonal tp,    conceptotipopersonal ctp, conceptocuenta ccta, cuentapresupuesto ctapre,
 partidauelespecifica pue,    unidadejecutora uel, uelespecifica uelesp, 
 accioncentralizada acc right join accionespecifica ace on  ace.id_accion_centralizada = acc.id_accion_centralizada    left join proyecto pro on ace.id_proyecto = pro.id_proyecto    
 where 
			uelesp.id_accion_especifica = ace.id_accion_especifica
			and uel.id_unidad_ejecutora = uelesp.id_unidad_ejecutora
			and pue.id_uel_especifica = uelesp.id_uel_especifica
			and ctapre.id_cuenta_presupuesto = pue.id_cuenta_presupuesto
			and ccta.id_cuenta_presupuesto = ctapre.id_cuenta_presupuesto
			and ccta.id_concepto_tipo_personal = ctp.id_concepto_tipo_personal     
			and c.id_concepto = ctp.id_concepto
			and cf.id_concepto_tipo_personal = ctp.id_concepto_tipo_personal
			AND cf.id_trabajador = t.id_trabajador 
			and cper.id_clasificacion_personal = tp.id_clasificacion_personal
			and catper.id_categoria_personal =  cper.id_categoria_personal
			and t.id_tipo_personal = tp.id_tipo_personal 
			AND t.id_dependencia = dep.id_dependencia
			AND dep.id_administradora_uel = au.id_administradora_uel 
			AND uel.id_unidad_ejecutora = au.id_unidad_ejecutora
			AND uad.id_unidad_administradora = au.id_unidad_administradora
			AND tp.id_tipo_personal = ctp.id_tipo_personal
			AND ftp.id_tipo_personal = tp.id_tipo_personal
			AND ftp.id_frecuencia_tipo_personal = cf.id_frecuencia_tipo_personal
			and f.id_frecuencia_pago = ftp.id_frecuencia_pago 		
 and ace.anio IN  ( 2014 )  
 and f.cod_frecuencia_pago IN (1,2,3)
 --and ctp.id_concepto_tipo_personal  in (17,30)
 group by ctapre.id_cuenta_presupuesto, ctapre.cod_presupuesto, ctapre.descripcion,  uel.id_unidad_ejecutora, uel.cod_unidad_ejecutora, uel.nombre,
uelesp.categoria_presupuesto, pue.id_fuente_financiamiento, pue.id_partida_uel_especifica, dep.id_organismo
 order by  uelesp.categoria_presupuesto, ctapre.cod_presupuesto  


select * from cuentapresupuesto
12;"4.01.01.01.00";"SUELDOS BÁSICOS PERSONAL FIJO A TIEMPO COMPLETO";"D";11;0;**********Este es el que esta asociado
14;"4.01.01.18.00";"REMUNERACIONES AL PERSONAL CONTRATADO";"D";11;0;

select * from conceptocuenta  where id_concepto_tipo_personal in (17, 30) and id_cuenta_presupuesto in (12,14)
11;30;12**********Este es el que esta asociado

select * from conceptotipopersonal where id_concepto_tipo_personal in (17, 30)
select * from conceptotipopersonal where id_tipo_personal in (15,13) and id_concepto in (2)
17;2;15;19
30;2;13;12

los que quiero q salgan 17,30,1721,361
15;"CONTRATADOS";"SUELDO BASICO";17
13;"EMPLEADOS";"SUELDO BASICO";30

select * from tipopersonal where id_tipo_personal in (15,13)
13;"01";"EMPLEADOS";"N";"N";"S";"S";"0";"0";11;11;19;1;1;"S";"S";"S";"S";"S";"N";"S";"N";331;"1";34;11;"S";23**********Este es el que esta asociado
15;"03";"CONTRATADOS";"N";"N";"N";"S";"0";"0";11;11;21;2;1;"S";"S";"S";"N";"S";"S";"S";"N";331;"1";342;11;"S";24

select * from concepto order by descripcion
2;"0001";"SUELDO BASICO";"N";"D";0;"S";"S";"N";"N";"N";"S";"N";"N";"N";"N";"S";"N";"N";"N";"N";"S";"S";"S";"N";"S";12;"S";"N";" ";11;0;"";;"N";"S";"S";109;;"N";;"N"; **********Este es el que esta asociado

select * from partidauelespecifica where id_cuenta_presupuesto in (12,14)


