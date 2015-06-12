
package ejemplo10;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name="Profesor")
public class Profesor implements Serializable  {
    
    @Id
    @Column(name="id")
    private int idProfesor;
    
    @Id
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="ape1")
    private String ape1;
    
    @Column(name="ape2")    
    private String ape2;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="ProfesorModulo", joinColumns={@JoinColumn(name="nombre"), @JoinColumn(name="IdProfesor")}, inverseJoinColumns={@JoinColumn(name="IdModulo")})
    private Set<Modulo> modulos=new HashSet();
    

    public Profesor(){ 
    }

    public Profesor(int id, String nombre, String ape1, String ape2) {
        this.idProfesor = id;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }
    
    

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the ape1
     */
    public String getApe1() {
        return ape1;
    }

    /**
     * @param ape1 the ape1 to set
     */
    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    /**
     * @return the ape2
     */
    public String getApe2() {
        return ape2;
    }

    /**
     * @param ape2 the ape2 to set
     */
    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    /**
     * @return the correosElectronicos
     */
    public Set<Modulo> getModulos() {
        return modulos;
    }

    /**
     * @param correosElectronicos the correosElectronicos to set
     */
    public void setModulos(Set<Modulo> modulos) {
        this.modulos = modulos;
    }


    
}
