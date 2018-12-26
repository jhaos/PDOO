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
public class Tablero {
    private ArrayList<Casilla> casillas;
    private Casilla carcel;

    Tablero() {
       this.inicializar();
    }

    
    ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    Casilla getCarcel() {
        return carcel;
    }

    @Override
    public String toString() {
        String total = "";
        for (Casilla casilla : casillas) {
         
        total += casilla.toString(); 
        
        }
        total += carcel.toString();
        return total;
    }
    

    private void inicializar() {
        casillas = new ArrayList(Qytetet.MAX_CASILLAS);
        
        casillas.add(new OtraCasilla(0, 0, TipoCasilla.SALIDA)); 
        casillas.add(new Calle(1, 320, new TituloPropiedad("Islas Bienaventuradas", 50, 5, 150, 35)));
        casillas.add(new Calle(2, 350, new TituloPropiedad("Barca de Caronte", 60, 5, 170, 50)));
        casillas.add(new OtraCasilla(3, 0, TipoCasilla.SORPRESA));
        casillas.add(new Calle(4, 400, new TituloPropiedad("Campos Asphodelos", 75, 5, 190, 60)));
        casillas.add(new OtraCasilla(5, 0, TipoCasilla.CARCEL));
        casillas.add(new Calle(6, 425, new TituloPropiedad("Río Lete", 80, 5, 200, 75)));
        casillas.add(new Calle(7, 450, new TituloPropiedad("Morada de Tánatos", 90, 5, 215, 90)));
        casillas.add(new OtraCasilla(8, 0, TipoCasilla.SORPRESA));
        casillas.add(new Calle(9, 485, new TituloPropiedad("Templo de Perséfone", 105, 5, 240, 115)));
        casillas.add(new OtraCasilla(10, 0, TipoCasilla.PARKING));
        casillas.add(new Calle(11, 510, new TituloPropiedad("Jaula Cancerbero", 120, 5, 260, 130)));
        casillas.add(new OtraCasilla(12, 150, TipoCasilla.IMPUESTO));
        casillas.add(new Calle(13, 540, new TituloPropiedad("Pasaje de los hecatónquiros", 130, 5, 285, 150)));
        casillas.add(new Calle(14, 555, new TituloPropiedad("Campos eliseos", 150, 5, 295, 165)));
        casillas.add(new OtraCasilla(15, 0, TipoCasilla.JUEZ));
        casillas.add(new Calle(16, 580, new TituloPropiedad("Laguna Estigia", 160, 5, 300, 185)));
        casillas.add(new OtraCasilla(17, 0, TipoCasilla.SORPRESA));
        casillas.add(new Calle(18, 630, new TituloPropiedad("Castillo de Hades", 250, 5, 350, 300)));
        casillas.add(new Calle(19, 680, new TituloPropiedad("Tártaro", 300, 5, 360, 310)));
        carcel = casillas.get(5);
        
    }
    
    boolean esCasillaCarcel(int numeroCasilla){
        boolean esCarcel = false;
        if(numeroCasilla == carcel.getNumeroCasilla()){
            esCarcel = true;
        }
        return esCarcel;
    }
    Casilla obtenerCasillaNumero(int numeroCasilla){
        return casillas.get(numeroCasilla);
    }
    Casilla obtenerNuevaCasilla(Casilla casilla, int desplazamiento){
       return casillas.get((casillas.indexOf(casilla) + desplazamiento) % Qytetet.MAX_CASILLAS);
    }

}
