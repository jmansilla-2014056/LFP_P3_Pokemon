/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import models.Pokedex;
import models.Personaje;
import models.Tokens;
import models.Trampa;
import models.Variables;
import pokemon.Editor;
/**
 * 
 *
 * @author Mansilla
 */
public class ManejarToken {
    static ArrayList<Variables> listaVariables = new ArrayList<>();
    
    public static ArrayList<Pokedex> HacerNiveles(ArrayList<Tokens> LT){
        listaVariables = new ArrayList<>();
        int estado=0;
        Personaje personaje = new Personaje();
        Pokedex pokedex = new Pokedex();
        ArrayList<Pokedex> listaPokedex = new ArrayList<>();
        ArrayList<String> listaRival = new ArrayList<>();
        
        String varName = "";
        
         for(Tokens t: LT){
             
             //0. Abrir Corchete             
            if(estado==0){
                if(t.getId()==16){
                    estado=1;
                    continue;
                }else{
                    System.out.println(estado);
                    Editor.addError(t,"Se esperaba [");   
                }
            }
            
            //1. Que venga la palabra reservada Principal
            if(estado==1){
                if(t.getId()==1){
                    estado=2;
                    continue;
                }else{
                    System.out.println(estado);
                    Editor.addError(t,"Se esperaba Principal");   
                  //Error no viene lo que se espera      
                }
            }
            
            //2. Que venga cerar corchete
            if(estado==2){
                if(t.getId()==17){
                    estado=3;
                    continue;
                }else{
                    System.out.println(estado);
                    Editor.addError(t,"Se esperaba ]");     
                }
            }
            
            //3. Que vengan dos puntos
            if(estado==3){
                if(t.getId()==26){
                    estado=4;
                    continue;
                }else{
                    System.out.println(estado);
                    Editor.addError(t,"Se esperaba :");    
                  //Error no viene lo que se espera      
                }
            }
            
            //4. Que venga Abrir Llaves
            if(estado==4){
                if(t.getId()==18){
                    estado=5;
                    continue;
                }else{
                     Editor.addError(t,"Se esperaba {");  
                  //Error no viene lo que se espera      
                }
            }
            
            //5. Que venga Abrir corchetes o quese cierre la sentecnia principal con }
             if(estado==5){
                if(t.getId()==16){
                    estado=6;
                    continue;
                }else if(t.getId()==19){
                    listaPokedex.add(pokedex);
                    pokedex = new Pokedex();
                    estado = 0;
                    continue;
                }
                else{
                    System.out.println(estado);
                    Editor.addError(t,"Se esperaba [ o }"); 
                  //Error no viene lo que se espera      
                }
            }
            
            //6. que venga la palabra Variables o que venga la palabra personaje o rivales
             if(estado==6){
                if(t.getId()==12){
                    estado=100;
                    continue;
                }
                else if(t.getId()==14){
                    estado=200;
                    continue;
                }
                else if(t.getId()==11){
                    estado=300;
                    continue;
                }
                else{
                    System.out.println(estado);
                  Editor.addError(t,"Se esperaba Variables o Personaje o Rivales");      
                }
            }
             
            //Que venga cerrar Corchete despues de la palabras Personaje, o rival o enemigo 
            if(estado==100 || estado == 200 || estado == 300){
                 if(t.getId()==17){
                    estado++;
                    continue;
                }else{
                     System.out.println(estado);
                     Editor.addError(t,"Se esperaba ]"); 
                     //Error
                 }
            }
            
            //Que vengan dos puntos despues del corchete de personaje o rivales o variables
                if(estado==101 || estado == 201 || estado == 301){
                 if(t.getId()==26){
                    estado++;
                    continue;
                }else{
                     System.out.println(estado);
                     Editor.addError(t,"Se esperaba :"); 
                 }
            }
                
                 //302 Que vengan abrir parentesis 
                if(estado==302){
                    if(t.getId()==20){
                        estado=303;
                         continue;
                    }else{
                        Editor.addError(t,"Se esperaba ("); 
                    }
                }
                
                //Que venga identificador
               if(estado==303){
                    if(t.getId()==32){
                        listaRival.add(t.getLexema());
                        estado=304;
                         continue;
                    }else{
                        System.out.println(estado);
                        Editor.addError(t,"Se esperaba un identetificador"); 
                    }
                }
               
               
               //304 que venga ,  
               if(estado==304){
                    if(t.getId()==25){
                        estado=305;
                         continue;
                    }else{
                        Editor.addError(t,"Se esperaba ,"); 
                    }
                }
               
               //Que venga un entero o un id
               if(estado==305){
                    if(t.getId()==33){
                        int tiempo = Integer.parseInt(t.getLexema());
                        
                        Collections.reverse(pokedex.getTodos());
                        for(String s: listaRival){
                            for(Personaje p:pokedex.getTodos()){
                                if(s.equals(p.getNombre())){
                                    p.setUso(1);
                                    p.setTiempo(tiempo);
                                }
                            }
                        }
                        listaRival.clear();
                        estado=306;
                         continue;
                    }
                    else if(t.getId()==32){
                        listaRival.add(t.getLexema());
                        estado=304;
                         continue;
                    }
                    else{
                        //Error
                        Editor.addError(t,"Se esperaba identificador o  entero"); 
                        System.out.println(estado);
                    }
                }
                 
               
               //306 Que venga cerrar parentesis
               if(estado==306){
                    if(t.getId()==21){
                        estado=307;
                         continue;
                    }else{
                        Editor.addError(t,"Se esperaba )"); 
                        System.out.println(estado);
                    }
                }
               
               //307 Que venga punto y coma
               if(estado==307){
                    if(t.getId()==24){
                        estado = 5;
                         continue;
                    }else{
                        System.out.println(estado);
                        Editor.addError(t,"Se esperaba ;"); 
                    }
                }
     
            
            //Que vengan llaves despues de los dos puntos de para personje o variables
            if(estado==102 || estado == 202){
                 if(t.getId()==18){
                    estado++;
                    continue;
                }else{
                     System.out.println(estado);
                     Editor.addError(t,"Se esperaba }"); 
                     //Error
                 }
            }
            
            //Que venga vengan parentensis depues de los dos puntos de riavles
            if(estado==302){
                 if(t.getId()==20){
                    estado++;
                    continue;
                }else{
                     System.out.println(estado);
                     Editor.addError(t,"Se esperaba ("); 
                     //Error
                 }
            }
            
            //103 Que cirren la llaves de personajes o variables, es importante limpiar el personaje, que vengan corchetes para declar variables o solo el identtificar, y en caso que venga por parte de persoanjes, que sea para sus atributos
            if(estado==103 || estado==203 ){
                if(t.getId()==19 && estado == 103){
                    estado=5;
                    continue;
                }
            
                else if(t.getId()==19 && estado==203){
                     pokedex.getTodos().add(personaje);
                     
                     personaje = new Personaje();
                     estado=5;
                    continue;
                }else if(t.getId()==16){
                    estado++;
                    continue;
                    //Empezar a asignar
                }else if(t.getId()==32 && estado == 103){
                    varName = t.getLexema();
                    estado = 150;
                    continue;
                }
                else{
                    Editor.addError(t,"Se esperaba }, o en caso variables un identificador"); 
                    System.out.println(estado);
                     //Error
                 }
            }
            
            //104 Declaracion de variable se espera la palabra reservada Variable
            if(estado==104){
                if(t.getId()==13){
                    estado=105;
                    continue;
                }else{
                    Editor.addError(t,"Se esperaba Variable");
                    System.out.println(estado);
                     //Error
                 }
            }
            
            //105 Cerrar los Corchetes de Variables
            if(estado==105){
                if(t.getId()==17){
                    estado=106;
                    continue;
                }else{
                    Editor.addError(t,"Se esperaba ]");
                    System.out.println(estado);
                     //Error
                 }
            }
            
            //106 esperar dos puntos de Variables
            if(estado==106){
                if(t.getId()==26){
                    estado=107;
                    continue;
                }else{
                    Editor.addError(t,"Se esperaba :");
                    System.out.println(estado);
                     //Error
                 }
            }
            
            //107 que venga un identificador guardar el nombre de la variables 
            if(estado==107){
                if(t.getId()==32){                    
                    listaVariables.add(new Variables(t.getLexema(),0));
                    estado=108;
                    continue;
                }else{
                    Editor.addError(t,"Se esperaba identificador");
                 }
            }
            
            //108 que venga punto y coma para regresar a 103 o que venga , par regresar a 107 
            if(estado==108){
                if(t.getId()==24){                    
                    estado=103;
                    continue;
                    
                }
                else if(t.getId()==25){                    
                    estado=107;
                    continue;
                }
                else{
                    System.out.println(estado);
                    Editor.addError(t,"Se esperaba ; o ,");
                 }
            }
            
            //150 Que venga guion para asignacion
            if(estado==150){
                if(t.getId()==28){                    
                    estado=151;
                    continue;
                }
                else{
                    System.out.println(estado);
                    Editor.addError(t,"Se esperaba -");
                 }
            }
            
            //151 Que >  para asignacion
            if(estado==151){
                if(t.getId()==15){                    
                    estado=152;
                    continue;
                }
                else{
                    System.out.println(estado);
                    Editor.addError(t,"Se esperaba >");
                 }
            }
            
            //152 puede ser id para asignar o un entero;
            if(estado==152){
                if(t.getId()==32){
                    AsignarVariable(varName, ObtenerValor(t.getLexema()));
                    estado=153;
                    continue;
                }else if(t.getId()==33){
                    AsignarVariable(varName,Integer.parseInt(t.getLexema()));
                    estado=153;
                    continue;
                }
                else{
                    System.out.println(estado);
                    Editor.addError(t,"Se espera identificador o un numero");
                 }
            }

            //153 punto y coma o +, -, /, *
            if(estado==153){
                if(t.getId()==27){
                    estado=-1;                    
                    continue;
                }else if(t.getId()==28){
                    estado=-2;
                    continue;
                }else if(t.getId()==29){
                    estado=-3;
                    continue;
                }else if(t.getId()==30){
                    estado=-4;
                    continue;
                }else if(t.getId()==24){
                    varName ="";
                    estado=103;
                    continue;
                }
                else{
                    System.out.println(estado);
                    Editor.addError(t,"Se espera +,-,/ o *");
                 }
            }
            
            if(estado==-1){
               if(t.getId()==32){
                    SumarVariable(varName, ObtenerValor(t.getLexema()));
                    estado=153;
                    continue;
                }else if(t.getId()==33){
                    SumarVariable(varName,Integer.parseInt(t.getLexema()));
                    estado=153;
                    continue;
                }
                else{
                    System.out.println(estado);
                     Editor.addError(t,"Se espera identificador o un numero");
                 }
                
            }
           
            if(estado==-2){
               if(t.getId()==32){
                    RestarVariable(varName, ObtenerValor(t.getLexema()));
                    estado=153;
                    continue;
                }else if(t.getId()==33){
                    RestarVariable(varName,Integer.parseInt(t.getLexema()));
                    estado=153;
                    continue;
                }
                else{
                    System.out.println(estado);
                    Editor.addError(t,"Se espera identificador o un numero");
                 }
                
            }
            
            if(estado==-3){
               if(t.getId()==32){
                    DividirVariable(varName, ObtenerValor(t.getLexema()));
                    estado=153;
                    continue;
                }else if(t.getId()==33){
                    DividirVariable(varName,Integer.parseInt(t.getLexema()));
                    estado=153;
                    continue;
                }
                else{
                    System.out.println(estado);
                    Editor.addError(t,"Se espera identificador o un numero");
                 }
                
            }
            
            if(estado==-4){
               if(t.getId()==32){
                    MultiplicarVariable(varName, ObtenerValor(t.getLexema()));
                    estado=153;
                    continue;
                }else if(t.getId()==33){
                    MultiplicarVariable(varName,Integer.parseInt(t.getLexema()));
                    estado=153;
                    continue;
                }
                else{
                    System.out.println(estado);
                    Editor.addError(t,"Se espera identificador o un numero");
                 }
                
            }
                               
            
            //204 Que vengan palabras reservadas Nombre, Tipo, Vida, Imagen, Sonido o ataque
            if(estado==204){
                if(t.getId()==2){
                    estado=400;
                    continue;
                }
                else if(t.getId()==3){
                    estado=500;
                    continue;
                }
                else if(t.getId()==4){
                    estado=600;
                    continue;
                }
                else if(t.getId()==5){
                    estado=700;
                    continue;
                }
                else if(t.getId()==6){
                    estado=800;
                    continue;
                }
                else if(t.getId()==7){
                    estado=900;
                    continue;
                }
                 
                else{
                    System.out.println(estado);
                    Editor.addError(t,"Se espera Nombre, Tipo, Vida, Imagen, Sonido o Ataque");
                 }
            }
            
            
            //400-900 Que venga cerrar el corchete para la palabra reservada
            if(estado==400 || estado==500 || estado==600|| estado==700|| estado==800|| estado==900){
                if(t.getId()==17){
                    estado++;
                    continue;
                }
                else{
                    System.out.println(estado);
                     //Error
                 }
            }
            
            //401-901 Que venga :
            if(estado==401 || estado==501 || estado==601|| estado==701|| estado==801|| estado==901){
                if(t.getId()==26){
                    estado++;
                    continue;
                }
                else{
                    System.out.println(estado);
                     Editor.addError(t,"Se espera :");
                 }
            }
            
            
            ////////////////////////////////////////////////////////////////////
            //402 se espera Id el para Nombre
            if(estado==402){    
                if(t.getId()==32){
                 personaje.setNombre(t.getLexema());
                        estado=-100;
                        continue;
                }else{
                    System.out.println(estado);
                     Editor.addError(t,"Se espera identificador");
                 }
            }
            
            //502 se palabra reservada Principiante, Intermedio o Master
            if(estado==502){    
                if(t.getId()==8 || t.getId()==9 || t.getId()==10){
                 personaje.setNivel(t.getLexema());
                        estado=-100;
                        continue;
                }else{
                    System.out.println(estado);
                     Editor.addError(t,"Se espera Principiante, Intermedio o Master");
                 }
            }
            
            //Se espera un numero o una variable para la vida
             if(estado==602){    
                if(t.getId()==33){
                 personaje.setVida(Integer.parseInt(t.getLexema()));
                        estado=-100;
                        continue;
                }else if (t.getId()==32){
                 personaje.setVida(ObtenerValor(t.getLexema()));
                        estado=-100;
                        continue;
                }
                else{
                    System.out.println(estado);
                     Editor.addError(t,"Se espera numero o identificador");
                 }
            }
            
            //Se espera una ruta para el sonido o la imagen
             if(estado==702 || estado ==802){    
                if(t.getId()==31 && estado==702){
                 personaje.setRutaImagen(t.getLexema());
                        estado=-100;
                        continue;
                }else if(t.getId()==31 && estado==802){
                    personaje.setRutaSonido(t.getLexema());
                        estado=-100;
                        continue;
                }
             else{
                    System.out.println(estado);
                    Editor.addError(t,"Se espera una cadena");
                 }
            }
            
             //Que venga punto y coma
              if(estado==-100){    
                if(t.getId()==24){
                        estado=203;
                        continue;
                }else{
                    System.out.println(estado);
                    Editor.addError(t,"Se espera ;");
                     //Error
                 }
            }
             
            
            //Para ataque se espera que abran llaves para ataque
             if(estado==902){    
                if(t.getId()==18){
                        estado=1000;
                        continue;
                }else{
                    System.out.println(estado);
                     Editor.addError(t,"Se espera {");
                 }
            }
            
             //Estado 1000 que vengan parentesis o cerrar llaves, es importante regresar aqui cuando cirren parentesis
             if(estado==1000){    
                if(t.getId()==20){
                        estado=1001;
                        continue;
                }else if(t.getId()==19){
                        estado=203;
                        continue;
                }else{
                    System.out.println(estado);
                     Editor.addError(t,"Se espera ( o }");
                 }
            }
             
              //Se espera la palabra resevada Principiante o Interemedio o Master
             if(estado==1001){    
                if(t.getId()==8){
                        estado=2000;
                        continue;
                }
                else if(t.getId()==9){
                        estado=3000;
                        continue;
                }
                else if(t.getId()==10){
                        estado=4000;
                        continue;
                }                
                else{
                    System.out.println(estado);
                     Editor.addError(t,"Se espera Principiante o Intermedio o Master");
                 }
            }
             
            //Que venga una coma
             if(estado==2000 || estado==3000 || estado==4000 ){    
                if(t.getId()==25){
                        estado++;
                        continue;
                }              
                else{
                    System.out.println(estado);
                     Editor.addError(t,"Se espera ,");
                     //Error
                 }
            }
             
             
             //Que venga una variable o un numero
             if(estado==2001 || estado==3001 || estado==4001 ){    
                if(t.getId()==32){
                       if(estado==2001){
                           personaje.setPrincipiante(ObtenerValor(t.getLexema()));
                           estado = 995;
                           continue;
                       }
                       if(estado==3001){
                           personaje.setIntermedio(ObtenerValor(t.getLexema()));
                           estado = 995;
                           continue;
                       }
                       if(estado==4001){
                           personaje.setMaster(ObtenerValor(t.getLexema()));
                           estado = 995;
                           continue;
                       }
                }       
                else if(t.getId()==33){
                       if(estado==2001){
                           personaje.setPrincipiante(Integer.parseInt(t.getLexema()));
                           estado = 995;
                           continue;
                       }
                       if(estado==3001){
                           personaje.setIntermedio(Integer.parseInt(t.getLexema()));
                           estado = 995;
                           continue;
                       }
                       if(estado==4001){
                           personaje.setMaster(Integer.parseInt(t.getLexema()));
                           estado = 995;
                           continue;
                       }
                }   
                else{
                    System.out.println(estado);
                      Editor.addError(t,"Se espera un identificador o numero");
                 }
            }
             
             
            //995 que cierren parentesis
             if(estado==995){    
                if(t.getId()==21){
                        estado=996;
                        continue;
                }              
                else{
                    System.out.println(estado);
                     Editor.addError(t,"Se espera )");
                 }
            }
              
             
            //996 que cierren llaves o que venga ,
             if(estado==996){    
                if(t.getId()==19){
                        estado=203;
                        continue;
                }    
                else if(t.getId()==25){
                        estado=1000;
                        continue;
                }  
                else{
                    System.out.println(estado);
                    Editor.addError(t,"Se espera } o ,");
                 }
            } 
                         
         }   

         
         //Imprimir Variables
         for(Variables v : listaVariables){
             System.out.println(v.getNombre());
             System.out.println(v.getValor());
         }
         
          System.out.println("-------------------------------------");
         //Imprimir Pokempones
         for(Pokedex p : listaPokedex){
            for(Personaje j:  p.getTodos()){
                System.out.println("---------------------------------");
                System.out.println("Nombre: " +j.getNombre());      
                System.out.println("Tipo: " +j.getNivel());    
                System.out.println("Vida: " +j.getVida()); 
                System.out.println("Imagen: " +j.getRutaImagen());    
                System.out.println("Sonido: " +j.getRutaSonido()); 
                System.out.println("Principiante: " +j.getPrincipiante()); 
                System.out.println("Intermedio: " +j.getIntermedio()); 
                System.out.println("Master: " +j.getMaster());
                System.out.println("Enemigo?: " +j.getUso()); 
                System.out.println("Tiempo: ?" + j.getTiempo()); 
            }
         }
     
         return listaPokedex;
    }
    
    static int ObtenerValor(String varN){
        int val = 0;
        boolean capturar=false;
        for(Variables v: listaVariables ){
            if(v.getNombre().equals(varN)){
                capturar = true;
                val = v.getValor();
                break;
            }
        }
        
        if(!capturar){
            //Error
           
        }   
        return val;
    } 
    
    static void AsignarVariable(String varN,int number){
        boolean capturar=false;
        
        for(Variables v: listaVariables ){
            if(v.getNombre().equals(varN)){
                capturar = true;
                v.setValor(number);
                break;
            }
        }
        
        if(!capturar){
            //Error
        }        
    }
    
    static void MultiplicarVariable(String varN,int number){
        boolean capturar=false;
        Collections.reverse(listaVariables);
        for(Variables v:listaVariables  ){
            if(v.getNombre().equals(varN)){
                capturar = true;
                v.setValor(v.getValor()*number);
                break;
            }
        }
        
        if(!capturar){
            //Error
        }        
    }
    
    static void DividirVariable(String varN,int number){
        Collections.reverse(listaVariables);
        boolean capturar=false;
        for(Variables v: listaVariables ){
            if(v.getNombre().equals(varN)){
                capturar = true;
                v.setValor((int)v.getValor()/number);
                break;
            }
        }
        
        if(!capturar){
            //Error
        }        
    }
    
    static void RestarVariable(String varN,int number){
        Collections.reverse(listaVariables);
        boolean capturar=false;
        for(Variables v: listaVariables ){
            if(v.getNombre().equals(varN)){
                capturar = true;
                v.setValor((int)v.getValor()-number);
                break;
            }
        }
        
        if(!capturar){
            //Error
        }        
    }
    
    static void SumarVariable(String varN,int number){
        Collections.reverse(listaVariables);
        boolean capturar=false;
        for(Variables v: listaVariables ){
            if(v.getNombre().equals(varN)){
                capturar = true;
                v.setValor((int)v.getValor()+number);
                break;
            }
        }
        
        if(!capturar){
            //Error
        }        
    }
    
}
