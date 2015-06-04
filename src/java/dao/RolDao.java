/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Rol;

/**
 *
 * @author Harry
 */
public interface RolDao {
    public List<Rol> findAll();
    public boolean create(Rol rol);
    public boolean update(Rol rol);
    public boolean delete(Rol rol);
    public List<Rol> listarTipos();
    public int retornarId(String nombreTipo);
}
