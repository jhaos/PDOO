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
public class Especulador extends Jugador {
    private int fianza;
    static int factorEspeculador = 2;
    
    Especulador(Jugador jugador, int fianza) {
        super(jugador);
        this.fianza = fianza;
    }
    
    Especulador convertirme(int fianza){
        return this;
    }
    
    @Override
    void pagarImpuesto(){
        modificarSaldo(-(casillaActual.coste / factorEspeculador));
    }
    
    boolean pagarFianza(){
        boolean tengoSaldo = fianza < saldo;
        if (tengoSaldo){
            modificarSaldo(-fianza);
        }
        return tengoSaldo;
    }
    
    boolean irCarcel(OtraCasilla casilla){
        boolean salgoCarcel = pagarFianza();
        if(!salgoCarcel){
            casillaActual = casilla;
            encarcelado = true;
        }
        return salgoCarcel;
    }

    @Override
    public String toString() {
        String cadena = super.toString();
        cadena += "Especulador{" + "fianza=" + fianza + '}';
        return cadena;
    }


}

