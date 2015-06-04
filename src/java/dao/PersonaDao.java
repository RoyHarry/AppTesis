/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Persona;
import modelo.Usuario;

/**
 *
 * @author Harry
 */
public interface PersonaDao {
   public Usuario findByUsuario(Usuario usuario);
    public Usuario login(Usuario usuario);
    public List<Usuario> findAll();
    public List<Persona> findAllUsuarios();
    public boolean create(Usuario usuario);
    public boolean update(Usuario usuario);
    public boolean delete(Usuario usuario);
    
    public boolean createU(Persona usu);
    public boolean updateU(Persona usu);
    public boolean deleteU(Persona usu);
    public int usuId(String nom);
    public int usuvetId(String nom); 
}
