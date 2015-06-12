/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo08;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CompromisoInicialDetalle")
public class CompromisoInicialDetalleLight implements Serializable  {  
    
    @Id
    @Column(name="id_compromiso_inicial_detalle")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idCompromisoInicialDetalle;

    @Column(name="monto")
    private Double monto;

    @ManyToOne
    @JoinColumn(name="id_compromiso_inicial")
    private CompromisoInicialLight compromisoinicial;

    public CompromisoInicialDetalleLight(){
    }

    public CompromisoInicialDetalleLight(CompromisoInicialLight comproIni, Double monto){
        this.compromisoinicial = comproIni;           
        this.monto = monto;           
    }        

    public CompromisoInicialDetalleLight( Double monto){            
        this.monto = monto;            
    }

    public Integer getIdCompromisoInicialDetalle() {
        return idCompromisoInicialDetalle;
    }

    public void setIdCompromisoInicialDetalle(Integer idCompromisoInicialDetalle) {
        this.idCompromisoInicialDetalle = idCompromisoInicialDetalle;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public CompromisoInicialLight getCompromisoinicial() {
        return compromisoinicial;
    }

    public void setCompromisoinicial(CompromisoInicialLight compromisoinicial) {
        this.compromisoinicial = compromisoinicial;
    }
	
}