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
public class OtraCasilla extends Casilla {
    protected TipoCasilla tipo;
    
    OtraCasilla(int numeroCasilla, int coste, TipoCasilla tipo){
        super(numeroCasilla, coste);
        this.tipo = tipo;
    }

    @Override
    public TipoCasilla getTipo() {
        return tipo;
    }
 
    
    @Override
    public boolean soyEdificable(){
        boolean soyEdificable = false;
        
        return soyEdificable;
    }
}
