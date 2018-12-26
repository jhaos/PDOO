/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 *
 * @author juliu
 */
public class Qytetet {
    private static final Qytetet instance = new Qytetet();
    public final int MAX_JUGADORES = 4;
    static final int MAX_CARTAS = 10;
    static final int MAX_CASILLAS = 20;
    static final int PRECIO_LIBERTAD = 1000;
    static final int SALDO_SALIDA = 1000;
    private Tablero tablero;
    private Jugador jugadorActual;
    private ArrayList<Jugador> jugadores = new ArrayList(MAX_JUGADORES);
    private static ArrayList<Sorpresa> mazo;
    private Sorpresa cartaActual;
    //private Dado dado = Dado.getInstance();
    
    private Qytetet(){
       inicializarTablero();  
       inicializarCartasSorpresa();
       
       
    }
    
    public Sorpresa getCartaActual(){
        return cartaActual;
    }
    public static Qytetet getInstance(){
        return Qytetet.instance;
    }
   // private static class QytetetHolder{
       // private static final Qytetet INSTANCE = new Qytetet();
    //}e

    public Jugador getJugadorActual() {
        return jugadorActual;
    }
    
   
    public boolean aplicarSorpresa(){
        boolean tienePropietario = false;
        
        if (cartaActual.getTipo() == TipoSorpresa.PAGARCOBRAR){
            jugadorActual.modificarSaldo(cartaActual.getValor());
        }else if (cartaActual.getTipo() == TipoSorpresa.IRACASILLA) {
            boolean esCarcel = tablero.esCasillaCarcel(cartaActual.getValor());
            if(esCarcel){
                encarcelarJugador();
            }else{
                Casilla nuevaCasilla = tablero.obtenerCasillaNumero(cartaActual.getValor());
                System.out.println("la nueva casilla es:" + nuevaCasilla);
                if (nuevaCasilla.getTipo() == TipoCasilla.CALLE){
                    jugadorActual.actualizarPosicion(((Calle)nuevaCasilla));
                }else {
                    jugadorActual.actualizarPosicion(((OtraCasilla)nuevaCasilla));
                }
                    
                //if(!jugadorActual.getCasillaActual().tengoPropietario()){}
                    //como pregunto si quiere comprarle o no?
            }
        }else if (cartaActual.getTipo() == TipoSorpresa.PORCASAHOTEL){
            jugadorActual.pagarCobrarPorCasaYHotel(cartaActual.getValor());
        }else if (cartaActual.getTipo() == TipoSorpresa.PORJUGADOR){
            for (Jugador jugador1 : jugadores){
                if(jugador1 != jugadorActual){
                    if (cartaActual.getValor() < 0){
                        jugador1.modificarSaldo(-cartaActual.getValor());
                        jugadorActual.modificarSaldo(cartaActual.getValor());
                    }else {
                        jugador1.modificarSaldo(-cartaActual.getValor());
                        jugadorActual.modificarSaldo(cartaActual.getValor());
                    }
                }
            }
        }else if (cartaActual.getTipo() == TipoSorpresa.SALIRCARCEL){
            jugadorActual.setCartaLibertad(cartaActual);
        }else if (cartaActual.getTipo() == TipoSorpresa.CONVERTIRME){
            int posicion = jugadores.indexOf(jugadorActual);
            Especulador especulador = jugadorActual.convertirme(cartaActual.getValor());
            jugadores.add(posicion, especulador);
            jugadorActual = jugadores.get(posicion);
        }else {
            mazo.add(cartaActual);
        }
        
        return tienePropietario;
        //tenemos que ver que tipo de sorpresa es.
        //has caido en un tipo pagar/cobrar entonces debemos modificar el saldo del juegador
        //Has caido en ir a casilla entonces preguntamos si es la carcel o no,si es carcel entonces
        //encarcelar jugador.
        //sino con actualizarPosicion podemso actualizar la posicion, y ahi
        //debemos ver si la casilla tiene propietario o no, yo frecerle que la compre.
        //si tiene propietario tienes que ver si hay hoteles o casas.
        //si es pagar por jugador entonces tendremos que cambiar los saldos de los jugadores
        //afectados
        //si la carta es tipoSalirCarcel carta libertad del jugador pasa a true;
        //mientras no sea de tipoSalirCarcel entonces devuelves al mazo.
    }
    public void cancelarHipoteca(Casilla casilla){
        if(((Calle)casilla).getTitulo().isHipotecado())
            jugadorActual.puedoPagarHipoteca(casilla);
        else
            System.out.println("no esta hipotecada");
    }
    public boolean comprarTituloPropiedad(){
        return jugadorActual.comprarTitulo();
    }
    public boolean edificarCasa(Casilla casilla){
        boolean puedoEdificar = false;
        
        if (casilla.soyEdificable()){
            boolean sePuedeEdificar = ((Calle)casilla).sePuedeEdificarCasa(jugadorActual.factorEspeculador);
            if (sePuedeEdificar){
                puedoEdificar = jugadorActual.puedoEdificarCasa(casilla);
                if (puedoEdificar){
                    ((Calle)casilla).setNumCasas(((Calle)casilla).getNumCasas() + 1);
                    int costeEdificarCasa = ((Calle)casilla).edificarCasa();
                    jugadorActual.modificarSaldo(-costeEdificarCasa);
                }           
            }
        }
        
        return puedoEdificar;
    }
    public boolean edificarHotel(Casilla casilla){
        boolean puedoEdificar = false;
        
        if (casilla.soyEdificable()){
            boolean sePuedeEdificar = ((Calle)casilla).sePuedeEdificarCasa(jugadorActual.factorEspeculador);
            if (sePuedeEdificar){
                puedoEdificar = jugadorActual.puedoEdificarCasa(casilla);
                if (puedoEdificar){
                    ((Calle)casilla).setNumCasas(((Calle)casilla).getNumCasas() + 1);
                    int costeEdificarCasa = ((Calle)casilla).edificarCasa();
                    jugadorActual.modificarSaldo(-costeEdificarCasa);
                }           
            }
        }
        
        return puedoEdificar;
    }
        
    
    Jugador getJugador(){
        return jugadorActual;
    }
    public boolean hipotecarPropiedad(Casilla casilla){
        boolean sePuedeHipotecar = false;
        if(!((Calle)casilla).getTitulo().isHipotecado()){
            if(casilla.soyEdificable()){
                sePuedeHipotecar = !((Calle)casilla).estaHipotecada();
            }
            if(sePuedeHipotecar)
                sePuedeHipotecar = jugadorActual.puedoHipotecar(casilla);
            int cantidadRecibida = ((Calle)casilla).hipotecar();
            jugadorActual.modificarSaldo(cantidadRecibida);
        }else
            System.out.println("ya esta hipotecada");
        return sePuedeHipotecar;

    }
    public void inicializarJuego(ArrayList<String> nombres){
        inicializarJugadores(nombres);
        inicializarTablero();
        inicializarCartasSorpresa();
        salidaJugadores();
        
    }
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        boolean libre = false;
        if (metodo == MetodoSalirCarcel.TIRANDODADO){
            int valorDado = GUIQytetet.Dado.getInstance().nextNumber();;
            if(valorDado > 5){
                System.out.println("el valor del dado es " + valorDado + "y sales de la carcel");
                libre = true;
            }else
                System.out.println("el valor del dado es " + valorDado + " y NO sales de la carcel");

        }else if (metodo == MetodoSalirCarcel.PAGANDOLIBERTAD){
            if(jugadorActual.tengoSaldo(PRECIO_LIBERTAD)){
                jugadorActual.modificarSaldo(-PRECIO_LIBERTAD);
                System.out.println("Has pagado el precio de la libertad");
                libre = true;
            }
        }
        if(libre){
            jugadorActual.setEncarcelado(libre);
        }//seguir rellenando
        return libre;
    }
    public boolean jugar(){
        int valorDado = GUIQytetet.Dado.getInstance().nextNumber();
        System.out.println("el valor de la tirado de " + jugadorActual.getNombre() + " ha sido de " + valorDado);
        Casilla casillaPosicion = jugadorActual.getCasillaActual();
        Casilla nuevaCasilla = tablero.obtenerNuevaCasilla(casillaPosicion, valorDado);
        System.out.println("La nueva casilla es: \n" + nuevaCasilla);
        boolean tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
        
        if(!nuevaCasilla.soyEdificable()){
            if(((OtraCasilla)nuevaCasilla).getTipo() == TipoCasilla.JUEZ){
                encarcelarJugador();
            }else if(((OtraCasilla)nuevaCasilla).getTipo() == TipoCasilla.SORPRESA){
                System.out.println(mazo.get(0));
                cartaActual = mazo.get(0);
                mazo.remove(0);
                mazo.add(cartaActual);
            }
        }
        return tienePropietario;
            
    }
     public ArrayList obtenerRanking(){
   
        ArrayList <String> ranking = new ArrayList();
        
        for (Jugador j : jugadores){
            ranking.add(j.getNombre() + j.getSaldo());
        }
        return ranking;
    }
     
    ArrayList<Casilla> propiedadesHipotecadasJugador(boolean hipotecadas){        
        return jugadorActual.obtenerPropiedadesHipotecadas(hipotecadas);
    }
    
    public Jugador siguienteJugador(){
        
        int posicionActual, pos_siguiente;
        posicionActual = jugadores.indexOf(jugadorActual);
        pos_siguiente = (posicionActual + 1) % jugadores.size();
        jugadorActual = jugadores.get(pos_siguiente);
        
        return jugadorActual;
    }
    public boolean venderPropiedad(Casilla casilla){
        boolean puedoVender = false, esMia = false;
        if (casilla.soyEdificable()){
            puedoVender = jugadorActual.puedoVenderPropiedad(casilla);
            if (puedoVender)
                jugadorActual.venderPropiedad(casilla);
            /*esMia = jugadorActual.esDeMiPropiedad(casilla);                 //REVISAR CON ANA
            jugadorActual.modificarSaldo(casilla.venderTitulo());
            jugadorActual.eliminarDeMisPropiedades(casilla);*/
        }
        return puedoVender;
        
    }
    
    void encarcelarJugador(){
        if(!jugadorActual.tengoCartaLibertad()){
            Casilla carcel = tablero.getCarcel();
            jugadorActual.irACarcel(carcel);
        }else{                                                              //REVISAR CON ANA
            Sorpresa carta = jugadorActual.devolverCartaLibertad();
            mazo.add(carta);
        }
    }
    private void inicializarCartasSorpresa(){
        mazo = new ArrayList(MAX_CARTAS);
        
        mazo.add(new Sorpresa ("Te conviertes en un especulador. Disfruta", 3000, TipoSorpresa.CONVERTIRME));
        mazo.add(new Sorpresa ("Alguien ha pagado tu fianza, salesde la carcel.", 0, TipoSorpresa.SALIRCARCEL));
        mazo.add(new Sorpresa ("Te hemos pillado haciendo cosas que no debias, ¡a la carcel!", tablero.getCarcel().getNumeroCasilla() , TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Mala suerte amigo, has molestado a un pez gordo. ¡A pagar!", -200, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("Te has levantado con la pierna derecha, has ganado un premio", 100, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("Te ha abducido un ovni y te ha dejado en la 10ª avenida", 10, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Has cogido un taxi y te lleva al 4", 4, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Hacienda te ha pillado te toca pagar una pasta por tus propiedades", 50, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Hacienda se ha equivocado contigo recibes una \"gratificación\" ", 120, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Tus compañeros estan generosos hoy", 100, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("Te has portado mal con tus colegas asi que les indemnizas", -120, TipoSorpresa.PORJUGADOR));
        
        mazo.add(new Sorpresa ("Te conviertes en EL GRAN especulador. Disfruta", 5000, TipoSorpresa.CONVERTIRME));
        
        Collections.shuffle(mazo);

    }
    private void inicializarTablero(){
        tablero = new Tablero();
        
        
        
    }
    
    void inicializarJugadores(ArrayList<String> nombres){
        
        for(String nom : nombres) {
           jugadores.add(new Jugador(nom));
                
        }
        
    }
    
    void salidaJugadores(){
        for (Jugador jugador1 : jugadores) {
            jugador1.setCasillaActual(tablero.obtenerCasillaNumero(0));
        }
        int ran;
        Random aleatorio = new Random();
        ran = aleatorio.nextInt(jugadores.size()-1);
        
        jugadorActual = jugadores.get(ran);
    }
    
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }

    @Override
    public String toString() {
        String s = "";
        s = "Qytetet" + "tablero=" + tablero.toString() + ", CartasSorpresa=" + mazo.toString() + "";
      //  for (Jugador jugador1 : jugador) {
            s+= ", jugador=" + jugadores.toString();
      //  }
        return s;
    }
    
    
}

    