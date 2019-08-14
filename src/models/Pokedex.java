
package models;

import java.util.ArrayList;
import pokemon.Pokemon;

/**
 *
 * @author Mansilla
 */
public class Pokedex {
   private  ArrayList<Personaje> todos;
   private ArrayList<Personaje> aliados;
   private ArrayList<Personaje> enemigos;

    public Pokedex(ArrayList<Personaje> todos, ArrayList<Personaje> aliados, ArrayList<Personaje> enemigos) {
        this.todos = todos;
        this.aliados = aliados;
        this.enemigos = enemigos;
    }

    public Pokedex() {
        this.todos = new ArrayList<>(); 
        this.aliados = new ArrayList<>();
        this.enemigos = new ArrayList<>();
    }
       
    public ArrayList<Personaje> getAliados() {
        return aliados;
    }

    public void setAliados(ArrayList<Personaje> aliados) {
        this.aliados = aliados;
    }

    public ArrayList<Personaje> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(ArrayList<Personaje> enemigos) {
        this.enemigos = enemigos;
    }

    public ArrayList<Personaje> getTodos() {
        return todos;
    }

    public void setTodos(ArrayList<Personaje> todos) {
        this.todos = todos;
    }
   
    
   public void ataqueAliado(int x, int y){
       try{
       int vida = getEnemigos().get(y).getVida(); 
       switch (getAliados().get(x).getTipo()) {
           case 1:
               getEnemigos().get(y).setVida(vida - getAliados().get(x).getPrincipiante());
               break;
           case 2:
               getEnemigos().get(y).setVida(vida - getAliados().get(x).getIntermedio());
               break;
           case 3:
               getEnemigos().get(y).setVida(vida - getAliados().get(x).getIntermedio());
               break;
           default:
               break;
       }
       
       if(getEnemigos().get(y).getVida() <= 0){
           getEnemigos().get(y).setEstado(false);
       }             
       }catch(IndexOutOfBoundsException v){
           
       }
   } 
    
      public void ataqueEnemigo(int x, int y){
       int vida = getAliados().get(x).getVida(); 
       switch (getEnemigos().get(y).getTipo()) {
           case 1:
               getAliados().get(x).setVida(vida - getEnemigos().get(y).getPrincipiante());
               break;
           case 2:
               getAliados().get(x).setVida(vida - getEnemigos().get(y).getIntermedio());
               break;
           case 3:
               getAliados().get(x).setVida(vida - getEnemigos().get(y).getIntermedio());
               break;
           default:
               break;
       }
       
       if(getAliados().get(x).getVida() <= 0){
           getAliados().get(x).setEstado(false);
       }             
   } 
   
}
