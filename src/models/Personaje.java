/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Mansilla
 */
public class Personaje {
    
    private String nombre;
    private int Tipo;
    private String nivel;
    private int vida;
    private String rutaImagen;
    private String rutaSonido;
    private int principiante;
    private int intermedio;
    private int master;
    private boolean estado;
    private int uso;
    private int tiempo;

    public Personaje(String nombre, int Tipo, int vida, String rutaImagen, String rutaSonido, int principiante, int intermedio, int master, int tiempo) {
        this.nombre = nombre;
        this.Tipo = Tipo;
        this.vida = vida;
        this.rutaImagen = rutaImagen;
        this.rutaSonido = rutaSonido;
        this.principiante = principiante;
        this.intermedio = intermedio;
        this.master = master;
        this.estado = true;
        this.uso = 0;
        this.tiempo = tiempo;
        
        switch (Tipo) {
            case 1:
                this.nivel = "Principiante";
                break;
            case 2:
                this.nivel = "Intermedio";
                break;
            case 3:
                this.nivel = "Master";
                break;
            default:
                break;
        }
            
        
    }
    
     public Personaje(String nombre, int Tipo, int vida, String rutaImagen, String rutaSonido, int principiante, int intermedio, int master, int uso,int tiempo) {
        this.nombre = nombre;
        this.Tipo = Tipo;
        this.vida = vida;
        this.rutaImagen = rutaImagen;
        this.rutaSonido = rutaSonido;
        this.principiante = principiante;
        this.intermedio = intermedio;
        this.master = master;
        this.estado = true;
        this.uso = uso;
        this.tiempo = tiempo;
        
        
        switch (Tipo) {
            case 1:
                this.nivel = "Principiante";
                break;
            case 2:
                this.nivel = "Intermedio";
                break;
            case 3:
                this.nivel = "Master";
                break;
            default:
                break;
        }
            
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    
    
    public Personaje() {
        this.estado = true;
        this.uso = 0;        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int Tipo) {
        switch (Tipo) {
            case 1:
                this.nivel = "Principiante";
                break;
            case 2:
                this.nivel = "Intermedio";
                break;
            case 3:
                this.nivel = "Master";
                break;
            default:
                break;
        }

        this.Tipo = Tipo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
        
         switch (nivel) {
            case "Principiante":
                this.Tipo = 1;
                break;
            case "Intermedio":
                this.Tipo = 2;
                break;
            case "Master":
                this.Tipo = 3;
                break;
            default:
                break;
        }
        
    }

    
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getRutaSonido() {
        return rutaSonido;
    }

    public void setRutaSonido(String rutaSonido) {
        this.rutaSonido = rutaSonido;
    }

    public int getPrincipiante() {
        return principiante;
    }

    public void setPrincipiante(int principiante) {
        this.principiante = principiante;
    }

    public int getIntermedio() {
        return intermedio;
    }

    public void setIntermedio(int intermedio) {
        this.intermedio = intermedio;
    }

    public int getMaster() {
        return master;
    }

    public void setMaster(int master) {
        this.master = master;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getUso() {
        return uso;
    }

    public void setUso(int uso) {
        this.uso = uso;
    }
    
    
    
    
    

    
}
