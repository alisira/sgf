package ejemplo08;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="compromisoinicial")
public class CompromisoInicialLight implements Serializable {  
   
    private static final long serialVersionUID = -4889076316115181467L;

    @Id
    @Column(name="id_compromiso_inicial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idCompromisoInicial;

    @Column(name="id_organismo")
    private Integer idOrganismo;

    @Column(name="ano")
    private Integer ano;

    @Column(name="tarea")
    private Integer tarea;
    
    @OneToMany(mappedBy="compromisoinicial",cascade= CascadeType.ALL)
    //@JoinColumn(name="id_compromiso_inicial")
    private Set<CompromisoInicialDetalleLight> compromisoInicialDetalle;    
   	
    public CompromisoInicialLight(){

    }

    
    public int getIdCompromisoInicial() {
        return idCompromisoInicial;
    }

    public void setIdCompromisoInicial(int idCompromisoInicial) {
        this.idCompromisoInicial = idCompromisoInicial;
    }

    public Integer getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(Integer idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getTarea() {
        return tarea;
    }

    public void setTarea(Integer tarea) {
        this.tarea = tarea;
    }

    public Set<CompromisoInicialDetalleLight> getCompromisoInicialDetalle() {
        return compromisoInicialDetalle;
    }

    public void setCompromisoInicialDetalle(Set<CompromisoInicialDetalleLight> compromisoInicialDetalle) {
        this.compromisoInicialDetalle = compromisoInicialDetalle;
    }
   
}