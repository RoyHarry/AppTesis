/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import modelo.Usuario;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Harry
 */
public class UsuarioDaoImpl implements UsuarioDao, Serializable{

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
    public List<Usuario> findAll() {
        List<Usuario> listadoUsuarios=null;
        
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM UsuarioVet";
        
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

    
    
}
