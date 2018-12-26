/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author juliu
 */
abstract public class Casilla {
    protected int numeroCasilla;
    protected int coste;

    

  /*  Casilla(int numeroCasilla, int coste, TituloPropiedad titulo) {
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        this.numHoteles = 0;
        this.numCasas = 0;
        this.tipo = TipoCasilla.CALLE;
        this.asignarTituloPropiedad(titulo);
    }*/

    Casilla(int numeroCasilla, int coste) {
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
    }

    void setNumeroCasilla(int numeroCasilla) {
        this.numeroCasilla = numeroCasilla;
    }

    public int getNumeroCasilla() {
        return numeroCasilla;
    }
    int getCoste() {
        return coste;
    }
    
    public abstract boolean soyEdificable();
    public abstract TipoCasilla getTipo();

    
    
    @Override
    public String toString() {
        String s = "Numero de casilla = " + numeroCasilla + ", \nCoste = " + coste;
       //if(this.getTitulo() != null){    
         //   s += ", numHoteles=" + numHoteles + ", numCasas=" + numCasas + ", titulo=" + titulo +'}'; 
        //}
            return s + "\n"; 
        
    }
    

       
    
    
    
}

   
    
    

