/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Usuario;

/**
 *
 * @author Harry
 */
public interface UsuarioDao {
    public boolean create(Usuario usuario);
    public boolean update(Usuario usuario);
    public boolean delete(Usuario usuario);
    public List<Usuario> findAll();
}
