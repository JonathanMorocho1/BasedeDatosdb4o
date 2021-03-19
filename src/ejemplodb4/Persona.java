/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplodb4;

/**
 *
 * @author ANDRES
 */
public class Persona {
    private String NOMBRE, APELLIDOS;
    private int ID;

    public Persona(String NOMBRE, String APELLIDOS, int ID) {
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.ID = ID;
    }

    public Persona() {
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "{" + "" + NOMBRE + "," + APELLIDOS + "," + ID + '}';
    }
    
}
