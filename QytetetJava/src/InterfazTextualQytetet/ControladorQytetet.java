/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazTextualQytetet;

import java.util.ArrayList;
import modeloqytetet.Qytetet;
import modeloqytetet.Jugador;
import modeloqytetet.Casilla;
import modeloqytetet.Calle;
import modeloqytetet.OtraCasilla;
import modeloqytetet.Dado;
import modeloqytetet.MetodoSalirCarcel;
import modeloqytetet.Tablero;
import modeloqytetet.TipoCasilla;
import modeloqytetet.TituloPropiedad;
/**
 *
 * @author juliu
 */
public class ControladorQytetet {
    public static Qytetet juego;
    public static Jugador jugador;
    public static Casilla casilla;
    public static VistaTextualQytetet vista = new VistaTextualQytetet();
    
    public void inicializacionJuego(){
        juego = Qytetet.getInstance();
        ArrayList<String> nombre = new ArrayList<>();
        nombre = vista.obtenerNombreJugadores();
       
        juego.inicializarJuego(nombre);
        jugador = juego.getJugadorActual(); 
        casilla = jugador.getCasillaActual();
        
        juego.toString();
        System.out.println("====" + "Jugador Actual" + "====");
        System.out.println(jugador);
        System.out.println("\n");
        
    }
    
    public void desarrolloJuego(){
        boolean bancarrota;
        bancarrota = jugador.getSaldo() <= 0;
        do{
            boolean libre = false;
            if (jugador.getEncarcelado()){
                int metodo = vista.menuSalirCarcel();
                if (metodo == 0){
                    libre = juego.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
                }else{
                    libre = juego.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
                }    
            }
            if (libre || !jugador.getEncarcelado()){
                boolean noTienePropietario = juego.jugar();
                bancarrota = jugador.getSaldo() <= 0;
                if (!bancarrota){
                    if (!jugador.getEncarcelado()){
                        if (((OtraCasilla)jugador.getCasillaActual()).getTipo() == TipoCasilla.SORPRESA){
                            noTienePropietario = juego.aplicarSorpresa();
                            if (!jugador.getEncarcelado()){
                                bancarrota = jugador.getSaldo() <= 0;
                                if (!bancarrota){
                                    if (((OtraCasilla)jugador.getCasillaActual()).getTipo() == TipoCasilla.CALLE){
                                        if (!((Calle)jugador.getCasillaActual()).tengoPropietario()){
                                            int eleccion = vista.elegirQuieroComprar();
                                            if (eleccion == 0){
                                                juego.comprarTituloPropiedad();
                                            }
                                        }
                                    }
                                }
                            }
                        }else if (((OtraCasilla)jugador.getCasillaActual()).getTipo() == TipoCasilla.CALLE){
                            if (!((Calle)jugador.getCasillaActual()).tengoPropietario()){
                                int eleccionDos = vista.elegirQuieroComprar();
                                if(eleccionDos == 0){
                                    juego.comprarTituloPropiedad();
                                }
                            }
                        }
                    }
                }
            }
           
            bancarrota = jugador.getSaldo() <= 0;
            if (!jugador.getEncarcelado() && !bancarrota && jugador.tengoPropiedad()){
                int opcion = vista.menuGestionInmobiliaria();
                while (opcion != 0){
                    TituloPropiedad titulo = elegirPropiedad(jugador.getPropiedades());
                    if (opcion == 1){
                        boolean puedoEdificar = juego.edificarCasa(titulo.getCasilla());
                        if (!puedoEdificar){
                            vista.mostrar("No puedes edificar mas casas");
                        }
                    }else if (opcion == 2){
                        boolean puedoEdificar = juego.edificarHotel(titulo.getCasilla());
                        if (!puedoEdificar){
                            vista.mostrar("No puedes edificar mas hoteles");
                        }
                    }else if (opcion == 3){
                        boolean puedoVender = juego.venderPropiedad(titulo.getCasilla());
                        if (!puedoVender){
                            vista.mostrar("No puedes vender");
                        }
                    }else if (opcion == 4){
                        boolean puedoHipotecar = juego.hipotecarPropiedad(titulo.getCasilla());
                        if (!puedoHipotecar){
                            vista.mostrar("No puedes hipotecar la propiedad");
                        }
                    }else if (opcion == 5){
                        juego.cancelarHipoteca(titulo.getCasilla());
                    }
                opcion = vista.menuGestionInmobiliaria();
                }
            }
            
        bancarrota = jugador.getSaldo() <= 0;
        if (!bancarrota){
            jugador = juego.siguienteJugador();
            System.out.println("Ha cambiado de jugador \n");
            System.out.println("====" + "Jugador Actual" + "====");
            System.out.println(jugador);
            System.out.println("\n");
        }
        }while (!bancarrota);
        System.out.println("JUEGO FINALIZADO, UN JUGADOR HA ENTRADO EN BANCARROTA");
        juego.obtenerRanking().toString();
    }
    
    public TituloPropiedad elegirPropiedad(ArrayList<TituloPropiedad> propiedades){ 
 //este metodo toma una lista de propiedades y genera una lista de strings, con el numero y nombre de las propiedades
 //luego llama a la vista para que el usuario pueda elegir.
        vista.mostrar("\tCasilla\tTitulo");
        int seleccion = 0;
        ArrayList<String> listaPropiedades= new ArrayList();
        for ( TituloPropiedad prop: propiedades) {
                listaPropiedades.add( "\t"+prop.getCasilla().getNumeroCasilla()+"\t"+prop.getNombre());
        seleccion=vista.menuElegirPropiedad(listaPropiedades);  
        
         }
        return propiedades.get(seleccion);
    }
 
    public static void main(String[] args){
        ControladorQytetet controlador = new ControladorQytetet();
        controlador.inicializacionJuego();
        controlador.desarrolloJuego();
    }
    
    
    
}
