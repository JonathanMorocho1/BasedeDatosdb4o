/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplodb4;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;

/**
 *
 * @author ANDRES
 */
public class conexion {
    private ObjectContainer oc;
    
    private void open(){
        this.oc = Db4o.openFile("database.yap");
    }
    
    public boolean Insertar(Persona objeto){
        try {
            this.open();
            oc.set(objeto);
            this.oc.close();
            return true;
        } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
            System.out.println("bdoo.Controlador.guardarPersona() :");
            return false;
        }
    }
    
//OTRA CLASE BD CON UNA VENTANA QUE PODAMOS HACER INSERTAR ELIMINAR CREAR Y MOSTRAR EN UNA TABALA TRAYENDO A TODOS LOS OBJETOS
    
    public Persona buscarPersona(Persona objeto){
        this.open();
        Persona encontrado = null;
        ObjectSet resultados = this.oc.get(objeto);
        
        if(resultados.hasNext()){
            encontrado =(Persona) resultados.next();
        }
        this.oc.close();
        return encontrado;
    }
    
    public boolean Eliminar(Persona objeto) {
        try {
            //CONSULTAMOS LOS OBJETOS ALMACENADOS EN LA BASE DE DATOS Y SI EXISTE UNA COINCIDENCIA LA ELIMINAMOS            
            this.open();
            ObjectSet resultados = this.oc.get(objeto);
            if (resultados.size() > 0) {
                Persona persona = (Persona) resultados.next();
                this.oc.delete(persona);
                this.oc.close();
                return true;
            } else {
                this.oc.close();
                return false;
            }
        } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
            System.out.println("ejemplodb4.ControladorPersona.guardarPersona() : " + e);
            return false;
        }
    }
    public Persona[] Consultar(Persona objeto) {
        try {
            //CONSULTAMOS LOS OBJETOS ALMACENADOS EN LA BASE DE DATOS Y LOS RETORNAMOS EN UN ARREGLO DE TIPO Persona
            Persona[] personas = null;
            this.open();
            ObjectSet resultados = this.oc.get(objeto);
            int i = 0;
            if (resultados.hasNext()) {
                personas = new Persona[resultados.size()];
                while (resultados.hasNext()) {
                    personas[i] = (Persona) resultados.next();
                    i++;
                }
            }
            this.oc.close();
            return personas;
        } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
            System.out.println("ejemplodb4.ControladorPersona.guardarPersona() : " + e);
            return null;
        }
    }
    
     public boolean ActualizarPersona(Persona objeto) {
        try {
            //BUSCAMOS SI EXISTE EL OBJETO, SI ES ASÍ LO ACTUALIZAMOS EN LA BASE DE DATOS
            this.open();
            ObjectSet resultados = this.oc.get(new Persona(null, null, objeto.getID()));
            if (resultados.size() > 0) {                
                Persona resultado = (Persona) resultados.next();
                resultado.setNOMBRE(objeto.getNOMBRE());
                resultado.setAPELLIDOS(objeto.getAPELLIDOS());
                this.oc.set(resultado);
                this.oc.close();
                return true;
            } else {
                this.oc.close();
                return false;
            }
        } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
            System.out.println("ejemplodb4.ControladorPersona.actualizarPersona() : " + e);
            return false;
        }
    }
    
}
