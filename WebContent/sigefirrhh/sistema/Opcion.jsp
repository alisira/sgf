<%@ taglib uri="/WEB-INF/tags.tld" prefix="tags" %>
<%@ page import="sigefirrhh.login.LoginSession" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/extensions" prefix="x" %>
<%@ page import="sigefirrhh.sistema.ValidadorSesion" %>

<% 

	String rutaTemp = null;

	if ((LoginSession)session.getAttribute("loginSession")!=null){

		if (!((LoginSession)session.getAttribute("loginSession")).isValid()) {
			System.out.println("sesion cerrada1");
			response.sendRedirect("/sigefirrhh/error.html");
		}else{
			ValidadorSesion vs = new ValidadorSesion();
			HttpServletRequest httpServletRequest = (HttpServletRequest)pageContext.getRequest();			
			boolean temp = vs.validarPermiso(httpServletRequest);

			if (!temp){

				response.sendRedirect("/sigefirrhh/sinpermiso.jsp");

			}else{
				%>
				<jsp:include page="/inc/top.jsp"  />
<%
				rutaTemp = "/" + request.getRequestURI().split("/")[1] + "/" + request.getRequestURI().split("/")[2];
				//System.out.println(request.getRequestURI());
			}
		}

	}else{
		System.out.println("sesion cerrada2");
		response.sendRedirect("/sigefirrhh/error.html");
	}

%>
		
<% 	if (rutaTemp!=null){//Escribe el html solo si la sesion esta activa y se seteo el rutaTemp
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"	"http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<title>Sigefirrhh</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="/sigefirrhh/style/webstyle.css" 
		rel="stylesheet" type="text/css">
	
	<jsp:include page="/inc/functions.jsp" />
	
</head>

<body onload="scrollToCoordinates(); firstFocus();">
<f:view>
	<x:saveState value="#{opcionForm}" />
    <f:loadBundle basename="sigefirrhh.custMessages" var="custMessages"/>	

	<table width="770"  border="0" cellspacing="0" cellpadding="5" align="left">
		<tr>
			<!-- Menú Izquierdo -->
			<td width="200" valign="top">
				<div align="left">

				</div>
			</td>
			<td width="570" valign="top">
<h:form id="formOpcion">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
					<tr>
						<!-- Agregar y Búsqueda -->
						<td valign="top">							
							<div style="background-color: #f7f7ff;border: 1px solid #e3e4ff;padding: 15px;overflow: auto;width: 100%;">								
								<div style="float:left;width:50%;text-align: left;"><b>Opciones</b></div>
								<div style="float:left;width:50%;text-align: right;">
									<a href="#" onclick="window.open('/sigefirrhh/help/sigefirrhh/sistema/Opcion.jsp','helpwindow',
												'toolbar=1,resizable=1,width=660,height=480,scrollbars=1');">
												<h:graphicImage  url="/images/help/help.gif" />
									</a>
								</div>
							</div>
							
							<div style="background-color: #f7f7ff; border: 1px solid #e3e4ff; padding: 15px; width: 100%;" >
								<div class="bandtable" style="text-align: right;">
									<h:commandButton image="/images/add.gif" action="#{opcionForm.add}"	onclick="javascript:return clickMade()"	rendered="#{!opcionForm.editing&&!opcionForm.deleting&&opcionForm.login.agregar}" />								
								</div>
								
								<f:subview id="search1"	rendered="#{!opcionForm.showData}">								
									<f:verbatim>									
										<div class="bandtable">Buscar</div>																				
									</f:verbatim>
									
									<f:verbatim>
										<div class="datatable">
											Código
									</f:verbatim>						
									<h:inputText size="15" maxlength="15" value="#{opcionForm.findCodigoOpcion}" onchange="javascript:this.value=this.value.toUpperCase()" onkeypress="return keyEnterCheck(event, this)" ></h:inputText>
									<h:commandButton image="/images/find.gif" action="#{opcionForm.findOpcionByCodigoOpcion}" onclick="javascript:return clickMade()"></h:commandButton>								
									<f:verbatim>
										</div>
									</f:verbatim>
																						
									<f:verbatim>										
										<div class="datatable">
											Descripción
									</f:verbatim>												
									<h:inputText size="60" maxlength="60" value="#{opcionForm.findDescripcion}"	onchange="javascript:this.value=this.value.toUpperCase()"
										onkeypress="return keyEnterCheck(event, this)" />									
									<h:commandButton image="/images/find.gif" action="#{opcionForm.findOpcionByDescripcion}" onclick="javascript:return clickMade()" />
									<f:verbatim>
										</div>
									</f:verbatim>
								</f:subview>
							</div>
							
							<f:subview id="viewResultOpcionByCodigoOpcion" rendered="#{opcionForm.showOpcionByCodigoOpcion&&!opcionForm.showData}">
								<f:verbatim>
									<table class="toptable" width="100%">
										<tr>
											<td>
												<table width="100%" class="bandtable">
													<tr>
														<td>
															Resultado de Búsqueda
														</td>
													</tr>
												</table>
											</td>
										</tr>
										
										<tr>
											<td>
								</f:verbatim>
								<h:dataTable id="result"
								            styleClass="datatable"
								            headerClass="standardTable_Header"
								            footerClass="standardTable_Header"
								            rowClasses="standardTable_Row1,standardTable_Row2"
								            columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
								            var="result" value="#{opcionForm.result}"								
								    		rows="10"
								    		width="100%">
									<h:column>
										<h:commandLink value="#{result}" action="#{opcionForm.selectOpcion}" styleClass="listitem">
											<f:param name="idOpcion" value="#{result.idOpcion}" />
										</h:commandLink>
									</h:column>
								</h:dataTable>
                                            <h:panelGrid columns="1" styleClass="scrollerTable2" columnClasses="standardTable_ColumnCentered">
                                                <x:dataScroller id="scroll_3"
                                                    for="result"
                                                    fastStep="10"
                                                    pageCountVar="pageCount"
                                                    pageIndexVar="pageIndex">
        	                                        <f:facet name="first" >
    	                                            	<h:graphicImage url="/images/arrow-first.gif"  />
	                                                </f:facet>
                                                	<f:facet name="last">
                                            	    	<h:graphicImage url="/images/arrow-last.gif"  />
                                        	        </f:facet>
                                    	        	<f:facet name="previous">
                                	            		<h:graphicImage url="/images/arrow-previous.gif"  />
                            	                	</f:facet>
                        	                    	<f:facet name="next">
                    	                        		<h:graphicImage url="/images/arrow-next.gif"  />
                	                            	</f:facet>
            	                                	<f:facet name="fastforward">
        	                                    		<h:graphicImage url="/images/arrow-ff.gif"  />
    	                                        	</f:facet>
	                                            	<f:facet name="fastrewind">
                                            			<h:graphicImage url="/images/arrow-fr.gif"  />
                                            		</f:facet>
                                            	</x:dataScroller>
	                                            <x:dataScroller id="scroll_4"
    	                                        	for="result"
        	                                    	pageCountVar="pageCount"
            	                                	pageIndexVar="pageIndex" >
                	                        	    <h:outputFormat value="#{custMessages['data_scroller_pages']}" >
                    	        	            	    <f:param value="#{pageIndex}" />
                        	        	            	<f:param value="#{pageCount}" />
	                            	                </h:outputFormat>
                                        	    </x:dataScroller>
                                            </h:panelGrid>
                        				<f:verbatim></td>
                    				</tr>
                    			</table></f:verbatim>
							</f:subview>
							
							
							
							<f:subview
								id="viewResultOpcionByDescripcion"
								rendered="#{opcionForm.showOpcionByDescripcion&&
								!opcionForm.showData}">
								<f:verbatim><table class="toptable" width="100%">
									<tr><td>
										<table width="100%" class="bandtable">
											<tr>
												<td>
													Resultado de Búsqueda
												</td>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td></f:verbatim>
                                            <h:dataTable id="result"
                                                styleClass="datatable"
                                                headerClass="standardTable_Header"
                                                footerClass="standardTable_Header"
                                                rowClasses="standardTable_Row1,standardTable_Row2"
                                                columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
                                                var="result"
                                				value="#{opcionForm.result}"

                                                rows="10"
                                                width="100%">
                                				<h:column>
                                					<h:commandLink value="#{result}"
                                						action="#{opcionForm.selectOpcion}"
                                						styleClass="listitem">
                                						<f:param name="idOpcion" value="#{result.idOpcion}" />
                                					</h:commandLink>
                                				</h:column>
                                            </h:dataTable>
                                            <h:panelGrid columns="1" styleClass="scrollerTable2" columnClasses="standardTable_ColumnCentered">
                                                <x:dataScroller id="scroll_3"
                                                    for="result"
                                                    fastStep="10"
                                                    pageCountVar="pageCount"
                                                    pageIndexVar="pageIndex">
        	                                        <f:facet name="first" >
    	                                            	<h:graphicImage url="/images/arrow-first.gif"  />
	                                                </f:facet>
                                                	<f:facet name="last">
                                            	    	<h:graphicImage url="/images/arrow-last.gif"  />
                                        	        </f:facet>
                                    	        	<f:facet name="previous">
                                	            		<h:graphicImage url="/images/arrow-previous.gif"  />
                            	                	</f:facet>
                        	                    	<f:facet name="next">
                    	                        		<h:graphicImage url="/images/arrow-next.gif"  />
                	                            	</f:facet>
            	                                	<f:facet name="fastforward">
        	                                    		<h:graphicImage url="/images/arrow-ff.gif"  />
    	                                        	</f:facet>
	                                            	<f:facet name="fastrewind">
                                            			<h:graphicImage url="/images/arrow-fr.gif"  />
                                            		</f:facet>
                                            	</x:dataScroller>
	                                            <x:dataScroller id="scroll_4"
    	                                        	for="result"
        	                                    	pageCountVar="pageCount"
            	                                	pageIndexVar="pageIndex" >
                	                        	    <h:outputFormat value="#{custMessages['data_scroller_pages']}" >
                    	        	            	    <f:param value="#{pageIndex}" />
                        	        	            	<f:param value="#{pageCount}" />
	                            	                </h:outputFormat>
                                        	    </x:dataScroller>
                                            </h:panelGrid>
                        				<f:verbatim></td>
                    				</tr>
                    			</table></f:verbatim>
							</f:subview>
</h:form>
							<f:subview 
								id="viewDataOpcion"
								rendered="#{opcionForm.showData}">
								<f:verbatim><table class="toptable" width="100%">
									<tr><td>
										<table width="100%" class="bandtable">
											<tr>
												<td></f:verbatim>
													<h:outputText value="Consultando"
														rendered="#{!opcionForm.editing&&!opcionForm.deleting}" />
													<h:outputText value="Modificando"
														rendered="#{opcionForm.editing&&!opcionForm.adding}" />
													<h:outputText value="Eliminando"
														rendered="#{opcionForm.deleting}" />
													<h:outputText value="Agregando"
														rendered="#{opcionForm.adding}" />
												<f:verbatim></td>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td>	
										</f:verbatim>
<h:form id="opcionForm">

                            				<f:verbatim><table class="datatable" width="100%">
                        					<tr>
	                        					<td colspan="2">
                                    				<table align="center">
                                    					<tr>
                                    						<td></f:verbatim>
                                                				<h:commandButton value="Modificar" 
                                                					image="/images/modify.gif"
                                                					action="#{opcionForm.edit}"
                                                					onclick="javascript:return clickMade()"
                                                					rendered="#{!opcionForm.editing&&!opcionForm.deleting&&opcionForm.login.modificar}" />
                                                				<h:commandButton value="Eliminar"
                                                					image="/images/delete.gif"
                                                					action="#{opcionForm.delete}"
                                                					onclick="javascript:return clickMade()"
                                                					rendered="#{!opcionForm.editing&&!opcionForm.deleting&&opcionForm.login.eliminar}" />
                                                				<h:outputText
                                                					value="¿Seguro que desea eliminar?"
                                                					rendered="#{opcionForm.deleting}" />
                                        				
                                                				<h:commandButton value="Si" 
                                                					image="/images/yes.gif"
                                                					action="#{opcionForm.deleteOk}"
                                                					onclick="javascript:return clickMade()"
                                                					rendered="#{opcionForm.deleting}" />
                                                			
                                                				<h:commandButton value="Guardar" 
                                                					image="/images/save.gif"
                                                					action="#{opcionForm.save}"
                                                					onclick="javascript:return clickMade()"
                                        	        				rendered="#{opcionForm.editing}" />
                                        	        			<h:commandButton value="Cancelar" 
			                                    					image="/images/cancel.gif"
			                        								action="#{opcionForm.abort}"
			                        								immediate="true"
			                        								onclick="javascript:return clickMade()"
                	        										 />	
                                                                <f:verbatim>
                                                			</td>
                                                		</tr>
            										</table>
												</td>
											</tr></f:verbatim>
                        					<f:verbatim><tr>
                        						<td>
                        							Código
                        						</td>
                        						<td></f:verbatim>
					                        		<h:inputText
	                        							size="10"
	                        							id="Codigo"
	                        							maxlength="10"
                        								value="#{opcionForm.opcion.codigoOpcion}"
                        								readonly="#{!opcionForm.editing}"
                        								onchange="javascript:this.value=this.value.toUpperCase()"
                        								onkeypress="return keyEnterCheck(event, this)"
                        																						                        								required="true">
                        							</h:inputText>
                        								
                        								<f:verbatim><span class="required"> *</span></f:verbatim>                        						<f:verbatim></td>
                        					</tr></f:verbatim>
                        					<f:verbatim><tr>
                        						<td>
                        							Descripción
                        						</td>
                        						<td></f:verbatim>
					                        		<h:inputText
	                        							size="60"
	                        							id="Descripcion"	                        							maxlength="60"
                        								value="#{opcionForm.opcion.descripcion}"
                        								readonly="#{!opcionForm.editing}"
                        								onchange="javascript:this.value=this.value.toUpperCase()"
                        								onkeypress="return keyEnterCheck(event, this)"
                        																						                        								required="true">
                        							</h:inputText>
                        								
                        								<f:verbatim><span class="required"> *</span></f:verbatim>                        						<f:verbatim></td>
                        					</tr></f:verbatim>
                        					<f:verbatim><tr>
                        						<td>
                        							Ruta
                        						</td>
                        						<td></f:verbatim>
					                        		<h:inputText
	                        							size="65"
	                        							id="Ruta"
                        								value="#{opcionForm.opcion.ruta}"
                        								readonly="#{!opcionForm.editing}"
                        								onkeypress="return keyEnterCheck(event, this)"                        								
                        								required="true" /><f:verbatim><span class="required"> *</span></f:verbatim>                        						<f:verbatim></td>
                        					</tr></f:verbatim>
                        					
                        					
                        					<f:verbatim><tr>
                        						<td>
                        							Jerarquia
                        						</td>
                        						<td></f:verbatim>
					                        		<h:inputText
	                        							id="Jerarquia"
                        								value="#{opcionForm.opcion.jerarquia}"
                        								readonly="#{!opcionForm.editing}"
                        								onkeypress="return keyEnterCheck(event, this)"                        								
                        								required="true" /><f:verbatim><span class="required"> *</span></f:verbatim>                        						<f:verbatim></td>
                        					</tr></f:verbatim>
                        					
                        					<f:verbatim><tr>
                        						<td>
                        							Tipo
                        						</td>
                        						<td></f:verbatim>
                                                    <h:selectOneMenu value="#{opcionForm.opcion.tipo}"
	                                                    id="Tipo"
                                                    	disabled="#{!opcionForm.editing}"
                        								                        								                                                    	immediate="false"
                                                    	required="true"
														title="Tipo">
														<f:selectItem itemLabel="Seleccione" itemValue="0" />
                                                        <f:selectItems value="#{opcionForm.listTipo}" />
														<f:validator validatorId="eforserver.RequiredJSFValidator"/>
                                                    </h:selectOneMenu><f:verbatim><span class="required"> *</span></f:verbatim>                        						<f:verbatim></td>
                        					</tr></f:verbatim>
                        				<f:verbatim></table>
                        				<table>
                        					<tr>
                        						<td></f:verbatim>
                                    				<h:commandButton value="Modificar" 
                                    					image="/images/modify.gif"
                                    					action="#{opcionForm.edit}"
                                    					onclick="javascript:return clickMade()"
                                    					rendered="#{!opcionForm.editing&&!opcionForm.deleting&&opcionForm.login.modificar}" />
                                    				<h:commandButton value="Eliminar"
                                    					image="/images/delete.gif"
                                    					action="#{opcionForm.delete}"
                                    					onclick="javascript:return clickMade()"
                                    					rendered="#{!opcionForm.editing&&!opcionForm.deleting&&opcionForm.login.eliminar}" />
                                    				<h:outputText
                                    					value="¿Seguro que desea eliminar?"
                                    					rendered="#{opcionForm.deleting}" />
                            				
                                    				<h:commandButton value="Si" 
                                    					image="/images/yes.gif"
                                    					action="#{opcionForm.deleteOk}"
                                    					onclick="javascript:return clickMade()"
                                    					rendered="#{opcionForm.deleting}" />
                                    			
                                    				<h:commandButton value="Guardar" 
                                    					image="/images/save.gif"
                                    					action="#{opcionForm.save}"
                                    					onclick="javascript:return clickMade()"
                            	        				rendered="#{opcionForm.editing}" />
													<h:commandButton value="Cancelar" 
                                    					image="/images/cancel.gif"
                        								action="#{opcionForm.abort}"
                        								immediate="true"
                        								onclick="javascript:return clickMade()"
                	        							 />
                                                    <f:verbatim>
                                    			</td>
                                    		</tr>
										</table></f:verbatim>
</h:form>
                        				<f:verbatim></td>
                        			</tr>
                        		</table></f:verbatim>
                        	</f:subview>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</f:view>
</body>
</html>


<%
}
%>
