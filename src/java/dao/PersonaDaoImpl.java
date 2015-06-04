/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import modelo.Persona;
import modelo.Usuario;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Harry
 */
public class PersonaDaoImpl implements PersonaDao, Serializable{

    @Override
    public Usuario findByUsuario(Usuario usuario) {
        Usuario usu=null;
        
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Usuario WHERE user=='"+usuario.getUser()+"'";
        
        try{
            //session.beginTransaction();
            usu=(Usuario) session.createQuery(hql).uniqueResult();
            //session.beginTransaction().commit();
            //System.out.println("hOLA");
            System.out.println("UsuarIO: "+usu.getUser()+" NOM: "+usu.getPersona().getNombre());
        }catch(Exception e){
            session.beginTransaction().rollback();
        }
        session.close();
        return usu;
    }

    @Override
    public Usuario login(Usuario usuario) {
        Usuario usu=this.findByUsuario(usuario);
        
        if(usu!=null){
            if(!usuario.getPass().equals(usu.getPass())){
                usu=null;
            }
        }
        return usu;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> listadoUsuarios=null;
        
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Usuario";
        
        try{
            //session.beginTransaction();
            listadoUsuarios = session.createQuery(hql).list();
            
            //session.beginTransaction().commit();
            
            for(Usuario usu:listadoUsuarios){
            
                System.out.println("UsuarIO: "+usu.getUser()+" NOM: "+usu.getPersona().getNombre()
                        +" TIPO: "+usu.getPersona().getRol().getNombre());
            }
            
            System.out.println("hOLA");
        }catch(Exception e){
            session.beginTransaction().rollback();
        }
        session.close();
        return listadoUsuarios;
    }

    @Override
    public List<Persona> findAllUsuarios() {
        List<Persona> listadoUsuarios=null;
        
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Persona";
        
        try{
            //session.beginTransaction();
            listadoUsuarios = session.createQuery(hql).list();
            
            //session.beginTransaction().commit();
            
            for(Persona usu:listadoUsuarios){
            
                System.out.println("UsuarIO: "+usu.getIdpersona()+" NOM: "+usu.getNombre()
                        +" TIPO: "+usu.getRol().getNombre());
            }
            
            System.out.println("hOLA");
        }catch(Exception e){
            session.beginTransaction().rollback();
        }
        session.close();
        return listadoUsuarios;
    }

    @Override
    public boolean create(Usuario usuario) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            sesion.save(usuario);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
        }
        sesion.close();
        
        return estado;
    }

    @Override
    public boolean update(Usuario usuario) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            
            sesion.update(usuario);
            estado=true;
            sesion.getTransaction().commit();
            System.out.println("CONNNNCHEEEE: "+usuario.getIdusuario()+" --"+usuario.getUser()+"--"
                    +usuario.getPass());
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
        }
        sesion.close();
        
        return estado;
    }

    @Override
    public boolean delete(Usuario usuario) {
         boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            
            sesion.delete(usuario);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
            System.out.println("ERROr"+e.getMessage());
        }
        sesion.close();
        
        return estado;
    }

    @Override
    public boolean createU(Persona usu) {
        boolean estado=false;
        System.out.println("usuario: "+usu.getNombre()+" "+usu.getApellidoPaterno()+" "+usu.getApellidoMaterno());
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            sesion.save(usu);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
        }
        sesion.close();
        
        return estado;
    }

    @Override
    public boolean updateU(Persona usu) {
        boolean estado=false;
        System.out.println("usuario: "+usu.getRol().getIdrol()+" "+usu.getNombre()+" "+usu.getApellidoPaterno()+" "+usu.getApellidoMaterno());
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            sesion.update(usu);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
        }
        sesion.close();
        
        return estado;
    }

    @Override
    public boolean deleteU(Persona usu) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            
            sesion.delete(usu);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
            System.out.println("ERROr"+e.getMessage());
        }
        sesion.close();
        
        return estado;
    }

    @Override
    public int usuId(String nom) {
        int id=-1;
        Persona us=new Persona();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            us= (Persona) session.createQuery("from Persona where nombre='"+nom+"'").uniqueResult();
            System.out.println("USUARIO: "+us.getNombre()+us.getRol().getNombre());
            id=us.getIdpersona();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return id;
    }

    @Override
    public int usuvetId(String nom) {
        int id=-1;
        Usuario us=new Usuario();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            us= (Usuario) session.createQuery("from Usuario where user='"+nom+"'").uniqueResult();
            System.out.println("USUARIO: "+us.getIdusuario()+us.getUser()+" "+us.getPass());
            id=us.getIdusuario();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return id;
    }

    
}
