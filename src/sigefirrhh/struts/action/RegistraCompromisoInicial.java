
package registracompromisoinicial;
import java.math.BigDecimal;
import java.sql.Date;
import sigecof.clRegistroCompromisoInicial;
import sigecof.CompromisoInicialDTO;
import sigecof.ImputacionesCompromisoInicialDTO;
import java.util.ArrayList;
import sigecof.ExpedienteTO;
import sigecof.SesionTO;
import sigecof.UsuarioWFTO;
import sigecof.WorkItemTO;
/**
 *
 * @author Rafael Sarria
 */
public class RegistraCompromisoInicial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Registra_Compromiso();
        PDD_Compromiso();
    }
    
    private static void PDD_Compromiso() {
        clRegistroCompromisoInicial PDDCompromiso = new clRegistroCompromisoInicial();
        Boolean Resulta;
        PDDCompromiso.setAnho_fiscal(2014);
        PDDCompromiso.setCod_Sigecof("37");
        PDDCompromiso.setNumero_expediente(4166);
        PDDCompromiso.setEstatus(1);
        Resulta = PDDCompromiso.PDDCompromiso();
        System.out.println("PDD Compromiso = " + Resulta);
    }
    private  static Integer Crear_Expediente() {
        clRegistroCompromisoInicial CrearExpediente = new clRegistroCompromisoInicial();
        ExpedienteTO expedienteTO = new ExpedienteTO();
        UsuarioWFTO usuarioTO = new UsuarioWFTO();
        SesionTO sesionTO = new SesionTO();
        WorkItemTO workitemTO = new WorkItemTO();
        
        expedienteTO.setAnho(2014);
        expedienteTO.setIdOrganismo("37");
        expedienteTO.setIdProceso("P_RBT_REG_COMP");
        expedienteTO.setDenominacionProceso("Registro del Compromiso");
        expedienteTO.setIdUsuario("MPPDP_SARAHI");
        expedienteTO.setNombreUsuario("SARAHI");
        expedienteTO.setDescripcion("Registro del Compromiso");
        expedienteTO.setObservacion("Registro de Compromiso por Webservice");
        expedienteTO.setNombre("Registro de Compromiso por Webservice");
        expedienteTO.setInstancia("0");
        usuarioTO.setIdUsuario("MPPDP_SARAHI");
        usuarioTO.setIpUsuario("10.90.24.79");
        usuarioTO.setRol("R_ANA_ADMI_II");
        
        
        
        sesionTO.setIdUsuario("MPPDP_SARAHI");
        sesionTO.setIpMaquina("10.90.24.79");
        sesionTO.setMaquina("DESARROLLO WEBSERVICE DIGEMAFE");
        sesionTO.setPrograma("Registro del Compromiso");
        sesionTO.setFechaInicio(null);
        sesionTO.setFechaFinalizacion(null);
        sesionTO.setFechaMovimiento(null);
        //sesionTO.setIdSesion(null);

        CrearExpediente.setExpedienteTO(expedienteTO);
        CrearExpediente.setUsuarioTO(usuarioTO);
        CrearExpediente.setSesionTO(sesionTO);
        System.out.println("Se va a llamar a la clase cliente de CrearExpediente ");
        workitemTO = CrearExpediente.CrearExpediente();
        
        System.out.println("Numero Expediente: " + workitemTO.getExpediente().getIdExpediente());
        System.out.println("Numero WorkItem: " + workitemTO.getIdWorkItem());
        System.out.println("Descripción    : " + workitemTO.getDescripcion());
        System.out.println("-------------------------------");
        return workitemTO.getExpediente().getIdExpediente();
    }
    
    private  static void Registra_Compromiso() {
        clRegistroCompromisoInicial Ci = new clRegistroCompromisoInicial();
        CompromisoInicialDTO CiDTO = new CompromisoInicialDTO();
        ArrayList<ImputacionesCompromisoInicialDTO> ImpCiDTO = new ArrayList<ImputacionesCompromisoInicialDTO>();
        ImputacionesCompromisoInicialDTO registro = new ImputacionesCompromisoInicialDTO();
        Ci.setAnho_fiscal(2014);
        Ci.setCod_Sigecof("37");
        Ci.setCod_unidad_administ("00011");
        Ci.setPersid_cuentadante(13863);
        Ci.setTipo_pago(3);
        Ci.setTipo_fondo(3);
        Ci.setTipo_documento(47);
        Ci.setNumero_documento("1005");
        Ci.setOrigen_presupuestario("S");
        Ci.setObservacion("Registro de compromiso realizado por el Webservice");
        Ci.setGaceta_credito_adicional(null);
        Ci.setDecreto_credito_adicional(null);
        Ci.setFecha_credito_adicional(null);
        Ci.setGaceta_rectificacion(null);
        Ci.setDecreto_rectificacion(null);
        Ci.setFecha_rectificacion(null);
        Ci.setNumero_expediente(Crear_Expediente());
        //Ci.setNumero_compromiso(null);
        
            
        ImpCiDTO = new ArrayList<ImputacionesCompromisoInicialDTO>();
        registro.setFuenteFinanciamiento("1");
        registro.setCodUnidadEjecutora("00024");
        registro.setCategoriaPresupuestaria("370001001");
        registro.setObjetoGasto("401030100");
        registro.setMonto(BigDecimal.valueOf(3000));
        ImpCiDTO.add(registro);
        
        registro = new ImputacionesCompromisoInicialDTO();
        registro.setFuenteFinanciamiento("1");
        registro.setCodUnidadEjecutora("00024");
        registro.setCategoriaPresupuestaria("370001001");
        registro.setObjetoGasto("401020100");
        registro.setMonto(BigDecimal.valueOf(3000));
        ImpCiDTO.add(registro);
        
        registro = new ImputacionesCompromisoInicialDTO();
        registro.setFuenteFinanciamiento("1");
        registro.setCodUnidadEjecutora("00024");
        registro.setCategoriaPresupuestaria("370001001");
        registro.setObjetoGasto("401010100");
        registro.setMonto(BigDecimal.valueOf(3000));
        ImpCiDTO.add(registro);
        
        Ci.setDetalles_compromiso(ImpCiDTO);
        System.out.println("Se va a llamar a la clase cliente de Registro de Compromiso, Expediente " + Ci.getNumero_expediente());
        CiDTO = Ci.RegistroCompromiso();
        
        System.out.println("Numero Expediente: " + CiDTO.getNumeroExpediente());
        System.out.println("Numero Compromiso: " + CiDTO.getNumeroCompromiso());
        System.out.println("-------------------------------");
        System.out.println("Fue Guardado? " + CiDTO.getDetallesCompromiso().get(0).isGuardado());
        System.out.println("Mensaje: " + CiDTO.getDetallesCompromiso().get(0).getMensajeRta());
    }
}
