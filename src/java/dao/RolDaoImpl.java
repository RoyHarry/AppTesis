/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Rol;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Harry
 */
public class RolDaoImpl implements RolDao, Serializable{

    @Override
    public List<Rol> findAll() {
        List<Rol> listadoTipoUsuarios=null;
        
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Rol";
        
        try{
            //session.beginTransaction();
            listadoTipoUsuarios = session.createQuery(hql).list();
            
            //session.beginTransaction().commit();
            System.out.println("hOLA");
        }catch(Exception e){
            session.beginTransaction().rollback();
        }
        session.close();
        return listadoTipoUsuarios;
    }

    @Override
    public boolean create(Rol rol) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            sesion.save(rol);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
            System.out.println("ERROR AL CREAR");
        }
        sesion.close();
        
        return estado;
    }

    @Override
    public boolean update(Rol rol) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        //tipoUsuario.setIdtipo(10);
        System.out.println("TIPO"+rol.getIdrol()+" "+rol.getNombre()+" "+rol.getPrioridad());
        try{
            sesion.getTransaction().begin();
            
            //Tipousuario tipousuariodb=(Tipousuario) sesion.load(Tipousuario.class, tipoUsuario.getIdtipo());
            
            sesion.update(rol);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
        }
        sesion.close();
        
        return estado;
    }

    @Override
    public boolean delete(Rol rol) {
        boolean estado=false;
        System.out.println("IDDDD: "+rol.getIdrol());
        System.out.println("NOMBRE: "+rol.getNombre());
        Session sesion=HibernateUtil.getSessionFactory().openSession();
//        Tipousuario tipoUsuario=new Tipousuario();
//        tipoUsuario.setIdtipo(id);
        
        try{
            sesion.getTransaction().begin();
            sesion.delete(rol);
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
    public List<Rol> listarTipos() {
        List<Rol> listaFacultad = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            listaFacultad = session.createSQLQuery("Select nombre from Rol").list();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return listaFacultad;
    }

    @Override
    public int retornarId(String nombreTipo) {
        int id=-1;
        Rol tp=new Rol();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tp= (Rol) session.createQuery("from Rol where nombre='"+nombreTipo+"'").uniqueResult();
            System.out.println("TIPOSUSU: "+tp.getNombre()+" "+tp.getIdrol());
            id=tp.getIdrol();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return id;
    }
    
}
