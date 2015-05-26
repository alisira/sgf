<%@ page import="sigefirrhh.login.LoginSession" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/extensions" prefix="x" %>
<%@ page import="sigefirrhh.sistema.ValidadorSesion" %>

<% 

	String rutaTemp = null;
	
	if ((LoginSession)session.getAttribute("loginSession")!=null){
	
		if (!((LoginSession)session.getAttribute("loginSession")).isValid()) {
			//System.out.println("sesion cerrada1");
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
		//System.out.println("sesion cerrada2");
		response.sendRedirect("/sigefirrhh/error.html");
	}

 	if (rutaTemp!=null){//Escribe el html solo si la sesion esta activa y se seteo el rutaTemp
%>

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<title>Sigefirrhh</title>
		<link href="/sigefirrhh/style/webstyle.css" rel="stylesheet" type="text/css">
		<script language=javascript> 
		
			    	function windowReport() {
					var number = 
						document.forms['formCategoriaPersonal'].elements['formCategoriaPersonal:reportId'].value;
					var url = '/sigefirrhh/reports/process.jsp?reportName=CategoriaPersonal' + number;
					window.open(url, 'report' + number, 'width=600,height=500,toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=no,copyhistory=no,resizable=yes,left=100,top=50,screenX=100,screenY=50');
		    	}
			
	 	</script>
	 	
		<jsp:include page="/inc/functions.jsp" />
		
	</head>

<body onload="firstFocus();">
	<f:view>
	<x:saveState value="#{categoriaPersonalForm}" />
    <f:loadBundle basename="sigefirrhh.custMessages" var="custMessages"/>	

	<table width="770"  border="0" cellspacing="0" cellpadding="5" align="left">
		<tr>
			<!-- Menï¿½ Izquierdo -->
			<td width="200" valign="top">
				<div align="left">
	                 
				</div>
			</td>
			<td width="570" valign="top">
				<h:form id="formCategoriaPersonal">
				<h:inputHidden id="reportId" value="#{categoriaPersonalForm.reportId}" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
					<tr>
						<!-- Agregar y Busqueda -->
						<td valign="top">
							<table width="100%" class="toptable">
								<tr>
									<td align="left">
										<b>
											Categoría de Personal
										</b>
									</td>
									<td align="right">
										<a href="#" onclick="window.open
											('/sigefirrhh//help/sigefirrhh/base/definiciones/CategoriaPersonal.jsp',
											'helpwindow',
											'toolbar=1,resizable=1,width=660,height=480,scrollbars=1');">
											<h:graphicImage url="/images/help/help.gif" />
										</a>
									</td>
								</tr>
							</table>
							<table width="100%" class="toptable">
								<tr>
									<td align="left">
										<table width="100%" class="bandtable">
											<tr>
												<td style="text-align: right">
													<h:commandButton image="/images/report.gif"
														action="#{categoriaPersonalForm.runReport}"
        												onclick="windowReport()"        												
														rendered="#{!categoriaPersonalForm.editing&&!categoriaPersonalForm.deleting}" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td align="left">
										<h:messages errorClass="error" styleClass="success" />
									</td>
								</tr>
							<f:subview 
								id="searchCategoriaPersonal"
								rendered="#{!categoriaPersonalForm.showData}">
								<f:verbatim><tr>
									<td>
										<table width="100%" class="bandtable">
											<tr>
												<td>
													Buscar
												</td>
											</tr>
										</table>
										
										<table width="100%" class="datatable"></f:verbatim>
														<f:verbatim><tr>
												<td>
													Código
												</td>
												<td width="100%"></f:verbatim>
															<h:inputText size="1"
														maxlength="1"
														value="#{categoriaPersonalForm.findCodCategoria}"
														onchange="javascript:this.value=this.value.toUpperCase()"
														                        								                        								                        								onkeypress="return keyEnterCheck(event, this)"
                        																						
														 />

															<h:commandButton image="/images/find.gif" 
														action="#{categoriaPersonalForm.findCategoriaPersonalByCodCategoria}"
														onclick="javascript:return clickMade()" />
												<f:verbatim></td>
											</tr></f:verbatim>
															<f:verbatim><tr>
												<td>
													Categoría
												</td>
												<td width="100%"></f:verbatim>
															<h:inputText size="20"
														maxlength="20"
														value="#{categoriaPersonalForm.findDescCategoria}"
														onchange="javascript:this.value=this.value.toUpperCase()"
														                        								                        								                        								onkeypress="return keyEnterCheck(event, this)"
                        																						
														 />

															<h:commandButton image="/images/find.gif" 
														action="#{categoriaPersonalForm.findCategoriaPersonalByDescCategoria}"
														onclick="javascript:return clickMade()" />
												<f:verbatim></td>
											</tr></f:verbatim>
											<f:verbatim></table>
									</td>
								</tr>
							</table></f:verbatim>
							</f:subview>
							<f:subview
								id="viewResultCategoriaPersonalByCodCategoria"
								rendered="#{categoriaPersonalForm.showCategoriaPersonalByCodCategoria&&
								!categoriaPersonalForm.showData}">
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
                                				value="#{categoriaPersonalForm.result}"
                                              
                                                rows="10"
                                                width="100%">
                                				<h:column>
                                					<h:commandLink value="#{result}"
                                						action="#{categoriaPersonalForm.selectCategoriaPersonal}"
                                						styleClass="listitem">
                                						<f:param name="idCategoriaPersonal" value="#{result.idCategoriaPersonal}" />
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
        	                                    		<h:graphicImage url="/images/arrow-ff.gif" />
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
								id="viewResultCategoriaPersonalByDescCategoria"
								rendered="#{categoriaPersonalForm.showCategoriaPersonalByDescCategoria&&
								!categoriaPersonalForm.showData}">
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
                                				value="#{categoriaPersonalForm.result}"
                                              
                                                rows="10"
                                                width="100%">
                                				<h:column>
                                					<h:commandLink value="#{result}"
                                						action="#{categoriaPersonalForm.selectCategoriaPersonal}"
                                						styleClass="listitem">
                                						<f:param name="idCategoriaPersonal" value="#{result.idCategoriaPersonal}" />
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
        	                                    		<h:graphicImage url="/images/arrow-ff.gif" />
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
								id="viewDataCategoriaPersonal"
								rendered="#{categoriaPersonalForm.showData}">
								<f:verbatim><table class="toptable" width="100%">
									<tr><td>
										<table width="100%" class="bandtable">
											<tr>
												<td></f:verbatim>
													<h:outputText value="Consultando"
														rendered="#{!categoriaPersonalForm.editing&&!categoriaPersonalForm.deleting}" />
													<h:outputText value="Modificando"
														rendered="#{categoriaPersonalForm.editing&&!categoriaPersonalForm.adding}" />
													<h:outputText value="Eliminando"
														rendered="#{categoriaPersonalForm.deleting}" />
													<h:outputText value="Agregando"
														rendered="#{categoriaPersonalForm.adding}" />
												<f:verbatim></td>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td>	
										</f:verbatim>
<h:form id="categoriaPersonalForm">
                            				<f:verbatim><table class="datatable" width="100%">
                        					<tr>
	                        					<td colspan="2">
                                    				<table align="center">
                                    					<tr>
                                    						<td></f:verbatim>
                                                				<h:outputText
                                                					value="¿Seguro que desea eliminar?"
                                                					rendered="#{categoriaPersonalForm.deleting}" />
                                        				
                                                				<h:commandButton value="Si" 
                                                					image="/images/yes.gif"
                                                					action="#{categoriaPersonalForm.deleteOk}"
                                                					onclick="javascript:return clickMade()"
                                                					rendered="#{categoriaPersonalForm.deleting}" />
                                                			
                                                				<h:commandButton value="Guardar" 
                                                					image="/images/save.gif"
                                                					action="#{categoriaPersonalForm.save}"
                                                					onclick="javascript:return clickMade()"
                                        	        				rendered="#{categoriaPersonalForm.editing}" />
                                        	        			<h:commandButton value="Cancelar" 
			                                    					image="/images/cancel.gif"
			                                    					action="#{categoriaPersonalForm.abort}"
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
	                        							size="1"
	                        							id="codCategoria"
	                        							maxlength="1"
                        								value="#{categoriaPersonalForm.categoriaPersonal.codCategoria}"
                        								readonly="#{!categoriaPersonalForm.editing}"
                        								onchange="javascript:this.value=this.value.toUpperCase()"
                        								                        								                        								                        								onkeypress="return keyEnterCheck(event, this)"
                        								                        																						                        								required="true">
                        							</h:inputText>
                        								
                        								<f:verbatim><span class="required"> *</span></f:verbatim>													<h:message for="codCategoria" styleClass="error"/>
                        						<f:verbatim></td>
                        					</tr></f:verbatim>
                        					<f:verbatim><tr>
                        						<td>
                        							Categoría
                        						</td>
                        						<td></f:verbatim>
					                        		<h:inputText
	                        							size="20"
	                        							id="descCategoria"
	                        							maxlength="20"
                        								value="#{categoriaPersonalForm.categoriaPersonal.descCategoria}"
                        								readonly="#{!categoriaPersonalForm.editing}"
                        								onchange="javascript:this.value=this.value.toUpperCase()"
                        								                        								                        								                        								onkeypress="return keyEnterCheck(event, this)"
                        								                        																						                        								required="true">
                        							</h:inputText>
                        								
                        								<f:verbatim><span class="required"> *</span></f:verbatim>													<h:message for="descCategoria" styleClass="error"/>
                        						<f:verbatim></td>
                        					</tr></f:verbatim>
                        				<f:verbatim></table>
                        				<table class="datatable" align="center">
                        					<tr>
                        						
                        						<td></f:verbatim>
                                    				<h:outputText
                                    					value="¿Seguro que desea eliminar?"
                                    					rendered="#{categoriaPersonalForm.deleting}" />
                            				
                                    				<h:commandButton value="Si" 
                                    					image="/images/yes.gif"
                                    					action="#{categoriaPersonalForm.deleteOk}"
                                    					onclick="javascript:return clickMade()"
                                    					rendered="#{categoriaPersonalForm.deleting}" />
                                    			
                                    				<h:commandButton value="Guardar" 
                                    					image="/images/save.gif"
                                    					action="#{categoriaPersonalForm.save}"
                                    					onclick="javascript:return clickMade()"
                            	        				rendered="#{categoriaPersonalForm.editing}" />
                                    				<h:commandButton value="Cancelar" 
                                    					image="/images/cancel.gif"
                                    					action="#{categoriaPersonalForm.abort}"
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

