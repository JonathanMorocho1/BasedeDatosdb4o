/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplodb4;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANDRES
 */
public class ControladorPersona extends conexion {
    
    public ControladorPersona(){
    }
    
    public boolean guardarPersona(String NOMBRE, String APELLIDOS, int ID){
        Persona persona = new Persona(NOMBRE, APELLIDOS, ID);
        return this.Insertar(persona);
    }

    public DefaultTableModel personas() {
        String titulos[] = {"Id", "Nombre", "Apellidos"};
        DefaultTableModel dtm = new DefaultTableModel(null, titulos);
        Persona persona = null;
        Persona[] p = this.Consultar(persona);
        if (p != null) {
            for (Persona per : p) {
                Object[] cli = new Object[3];
                cli[0] = per.getID();
                cli[1] = per.getNOMBRE();
                cli[2] = per.getAPELLIDOS();
                dtm.addRow(cli);
            }
        }
        return dtm;
    }
    
    public boolean eliminarPersona(int id) {
        if (id > 0) {
            Persona persona = new Persona(null, null, id);
            return this.Eliminar(persona);
        } else {
            return false;
        }
    }
    
    public boolean actualizarPersona(int id, String nombre, String apellidos) {
        Persona personaNueva = new Persona(nombre, apellidos, id);        
        return this.ActualizarPersona(personaNueva);
    }

}
