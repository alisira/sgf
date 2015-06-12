
package ejemplo08;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="compromisoinicialdetalle")
public class CompromisoInicialDetalle implements Serializable {
    
    @Id
    @Column(name="id_compromiso_inicial_detalle") 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idCompromisoInicialDetalle;  
    
    @Column(name="monto")
    private Double monto;
    
    @ManyToOne
    @JoinColumn(name="id_compromiso_inicial")
    private CompromisoInicial compromisoInicial;
    
    public CompromisoInicialDetalle() {
        
    }
    
    public CompromisoInicialDetalle(int idCorreo,String direccionCorreo, CompromisoInicial profesor) {
        this.idCompromisoInicialDetalle=idCorreo;       
        this.compromisoInicial=profesor;
    }
    
    public CompromisoInicialDetalle(CompromisoInicial comproIni, Double monto) {        
        this.compromisoInicial=comproIni;
        this.monto = monto;
    }

    public int getIdCompromisoInicialDetalle() {
        return idCompromisoInicialDetalle;
    }

    public void setIdCompromisoInicialDetalle(int idCompromisoInicialDetalle) {
        this.idCompromisoInicialDetalle = idCompromisoInicialDetalle;
    }   

    public CompromisoInicial getCompromisoInicial() {
        return compromisoInicial;
    }

    public void setCompromisoInicial(CompromisoInicial compromisoInicial) {
        this.compromisoInicial = compromisoInicial;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
    


}
