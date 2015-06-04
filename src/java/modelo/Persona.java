package modelo;
// Generated 04-jun-2015 9:45:26 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Persona generated by hbm2java
 */
@Entity
@Table(name="persona"
    ,catalog="tesis"
)
public class Persona  implements java.io.Serializable {


     private Integer idpersona;
     private Rol rol;
     private String nombre;
     private String apellidoPaterno;
     private String apellidoMaterno;
     private Integer dni;
     private Integer codigo;
     private Set usuarios = new HashSet(0);

    public Persona() {
    }

	
    public Persona(Rol rol) {
        this.rol = rol;
    }
    public Persona(Rol rol, String nombre, String apellidoPaterno, String apellidoMaterno, Integer dni, Integer codigo, Set usuarios) {
       this.rol = rol;
       this.nombre = nombre;
       this.apellidoPaterno = apellidoPaterno;
       this.apellidoMaterno = apellidoMaterno;
       this.dni = dni;
       this.codigo = codigo;
       this.usuarios = usuarios;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idpersona", unique=true, nullable=false)
    public Integer getIdpersona() {
        return this.idpersona;
    }
    
    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rol_idrol", nullable=false)
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
    @Column(name="nombre", length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="apellido_paterno", length=45)
    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }
    
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    
    @Column(name="apellido_materno", length=45)
    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }
    
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    
    @Column(name="dni")
    public Integer getDni() {
        return this.dni;
    }
    
    public void setDni(Integer dni) {
        this.dni = dni;
    }

    
    @Column(name="codigo")
    public Integer getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="persona")
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }




}

