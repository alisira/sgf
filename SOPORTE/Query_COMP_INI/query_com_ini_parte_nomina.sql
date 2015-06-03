SELECT 
  cuentapresupuesto.id_cuenta_presupuesto, 
  conceptofijo.monto
FROM 
  public.conceptotipopersonal, 
  public.concepto, 
  public.tipopersonal, 
  public.conceptocuenta, 
  public.cuentapresupuesto, 
  public.conceptofijo, 
  public.trabajador, 
  public.clasificacionpersonal, 
  public.categoriapersonal, 
  public.dependencia
WHERE 
  concepto.id_concepto = conceptotipopersonal.id_concepto AND
  tipopersonal.id_tipo_personal = conceptotipopersonal.id_tipo_personal AND
  tipopersonal.id_clasificacion_personal = clasificacionpersonal.id_clasificacion_personal AND
  conceptocuenta.id_concepto_tipo_personal = conceptotipopersonal.id_concepto_tipo_personal AND
  cuentapresupuesto.id_cuenta_presupuesto = conceptocuenta.id_cuenta_presupuesto AND
  conceptofijo.id_concepto_tipo_personal = conceptotipopersonal.id_concepto_tipo_personal AND
  trabajador.id_trabajador = conceptofijo.id_trabajador AND
  trabajador.id_dependencia = dependencia.id_dependencia AND
  clasificacionpersonal.id_categoria_personal = categoriapersonal.id_categoria_personal AND
  concepto.descripcion = 'SUELDO BASICO'
ORDER BY
  concepto.descripcion ASC;
