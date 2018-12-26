/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;


/**
 *
 * @author juliu
 */
public class PruebaQytetet {
    //private static ArrayList<Sorpresa> mazo = new ArrayList();
    //private static Tablero tablero = new Tablero();
    /**
     * @param args the command line arguments
     */

    
   /* private static ArrayList mayorQueCero(){
        
        ArrayList<Sorpresa> nuevo_mazo = new ArrayList();
        for (Sorpresa imazo : mazo) {
            if(imazo.getValor() > 0){
                nuevo_mazo.add(imazo);
            }
        }
        return nuevo_mazo;        
    }
    
    private static ArrayList tipoIrCasilla(){
        
        ArrayList<Sorpresa> nuevo_mazo = new ArrayList();
        for (Sorpresa imazo : mazo) {
            if(imazo.getTipo() == TipoSorpresa.IRACASILLA){
                nuevo_mazo.add(imazo);
            }
        }
        return nuevo_mazo;        
    }
    
    private static ArrayList compararCasilla(TipoSorpresa tip){
        
        ArrayList<Sorpresa> nuevo_mazo = new ArrayList();
        for (Sorpresa imazo : mazo) {
            if(imazo.getTipo() == tip){
                nuevo_mazo.add(imazo);
            }
        }
        return nuevo_mazo;        
    }
        
    void imprimirPrivadas(){
        ArrayList<Sorpresa> mazito = new ArrayList();
        
        mazito = mayorQueCero();
        for (Sorpresa mazo1 : mazito) {
            System.out.println(mazo1.toString());
        } 
        
        mazito = tipoIrCasilla();
        for (Sorpresa mazo1 : mazito) {
            System.out.println(mazo1.toString());
        } 
        
    
        
    }  
    */
    public static void main(String[] args) {
        
        // TODO code application logic here
        Qytetet prueba = Qytetet.getInstance();
        ArrayList<String> nombres = new ArrayList();
        
        nombres.add("jugador1");
        nombres.add("jugador2");
        
        prueba.inicializarJugadores(nombres);
        
        System.out.println(prueba.toString());
   //   prueba.inicializarCartasSorpresa();
   //   prueba.inicializarTablero();
        ArrayList<Sorpresa> mazito = new ArrayList();
        
        /*mazito = mayorQueCero();
        for (Sorpresa mazo1 : mazito) {
            System.out.println(mazo1.toString());
        }
        
        mazito = tipoIrCasilla();
        for (Sorpresa mazo1 : mazito) {
            System.out.println(mazo1.toString());
        } */
        
        
        //Jugador jugador2 = new Jugador("longuerson");
        
         
        
   /*     
        System.out.println(tablero.toString());
        System.out.println(tablero.getCarcel().getNumeroCasilla());
     */   
    }
       
} 
        

