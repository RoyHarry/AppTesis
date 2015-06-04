/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.io.Serializable;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.primefaces.context.RequestContext;
import util.MyUtil;

/**
 *
 * @author Harry
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private String usernamexxx;
    private String password;
    private UsuarioDao usuarioDao;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    int id;
    
    /////datos usuario//
    private String nombre;
    private String apellidos;
    
    private String nombreVet;

    public LoginBean() {

        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

        this.usuarioDao = new UsuarioDaoImpl();
    }

    public String login(){
        UsuarioVet user = new UsuarioVet();
        user.setUsername(usernamexxx);
        user.setContrasenia(password);

        UsuarioVet uv = new UsuarioVet();
        uv = this.usuarioDao.login(user);
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        
        this.setNombre(uv.getUsuario().getNombre());
        this.setApellidos(uv.getUsuario().getApellp()+" "+uv.getUsuario().getApellm());
        
        veterinariaWorkDao vd=new veterinariaWorkDaoImpl();
        Veterinariowork vet=new Veterinariowork();
        vet=vd.findAll(uv.getIdusuario());
        try {
            if (uv != null) {
                
                nombreVet=vet.getVeterinaria().getNombre();
                
                FacesContext faceContex = FacesContext.getCurrentInstance();
                httpServletRequest = (HttpServletRequest) faceContex.getExternalContext().getRequest();
                httpServletRequest.getSession().setAttribute("sesionUsuario", uv.getUsername());
                return "inicio";

            } else {

                try {
                    System.out.println("HOLIII 5");
//                    FacesContext faceContextt = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Login Error", "Usuario y/o clave incorrectos"));

                    return "index";
                } catch (Exception e) {
                    System.out.println("MMMMOOOO");
                    System.out.println("ERROR2 " + e.getMessage());
                    return "index";
                }
            }
        } catch (Exception e) {
            return "index";
        }


    }

    public String logOut() {

        httpServletRequest.removeAttribute("sesionUsuario");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }

    public String getUsernamexxx() {
        return usernamexxx;
    }

    public void setUsernamexxx(String usernamexxx) {
        this.usernamexxx = usernamexxx;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreVet() {
        return nombreVet;
    }

    public void setNombreVet(String nombreVet) {
        this.nombreVet = nombreVet;
    }
}
   
