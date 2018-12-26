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
public class Jugador {
    protected boolean encarcelado = false;
    protected String nombre;
    protected int saldo = 7500;
    protected ArrayList <TituloPropiedad> propiedades = new ArrayList();
    protected Casilla casillaActual = null;
    protected Sorpresa cartaLibertad = null;
    static int factorEspeculador = 1;

    Jugador(String nombre) {
        this.nombre = nombre;
    }
    
   public boolean getEncarcelado() {
        return encarcelado;
    }
   
   Jugador(Jugador jugador){
       encarcelado = jugador.encarcelado;
       nombre = jugador.nombre;
       saldo = jugador.saldo;
       propiedades = jugador.propiedades;
       casillaActual = jugador.casillaActual;
       cartaLibertad = jugador.cartaLibertad;
   }
   
   Especulador convertirme(int fianza){
       Especulador especulador = new Especulador(this, fianza);
       return especulador;
   }

    @Override
    public String toString() {
        return   "Nombre Jugador = " + nombre + "\nsaldo = " + saldo + "\nLista de propiedades:\n" + propiedades.toString() + "\n";
    }

    String getNombre() {
        return nombre;
    }

    public ArrayList<TituloPropiedad> getPropiedades() {
        return propiedades;
    }

    Sorpresa getCartaLibertad() {
        return cartaLibertad;
    }

    
    public Casilla getCasillaActual() {
        return casillaActual;
    }
    void setEncarcelado(boolean encarcelado) {
        this.encarcelado = encarcelado;
    }
    void setCasillaActual(Casilla casillaActual) {
        this.casillaActual = casillaActual;
    }
    void setCartaLibertad(Sorpresa cartaLibertad) {
        this.cartaLibertad = cartaLibertad;
    }

    void setPropiedades(ArrayList<TituloPropiedad> propiedades) {
        this.propiedades = propiedades;
    }
    
    boolean comprarTitulo(){
        boolean puedoComprar = false;
        TituloPropiedad tituloNuevo;
        if(casillaActual.soyEdificable()){
            boolean tengoPropietario = ((Calle)casillaActual).tengoPropietario();
            if(!tengoPropietario){
                int costeCompra = casillaActual.getCoste();
                if(costeCompra <= saldo){
                    tituloNuevo = ((Calle)casillaActual).asignarPropietario(this);
                    puedoComprar = true;
                    propiedades.add(tituloNuevo);
                    this.modificarSaldo(-costeCompra);
                    
                }
            }
        }
        return puedoComprar;
    }
    public boolean tengoPropiedad(){
        boolean tengoPropiedades = false;
        if (propiedades.size() > 0){
            tengoPropiedades = true;
        }
        return tengoPropiedades;
    }
    public boolean actualizarPosicion(Casilla casilla){
        
        boolean tienePropietario = false;
        if(casilla.getNumeroCasilla() < casillaActual.getNumeroCasilla()){
            modificarSaldo(Qytetet.SALDO_SALIDA);
        }
        this.setCasillaActual(casilla);
        
        if(casilla.soyEdificable()){
            tienePropietario = ((Calle)casilla).tengoPropietario();
            if (tienePropietario){
                boolean encarcelado = false;
                encarcelado = ((Calle)casilla).propietarioEncarcelado();
                if(!encarcelado){
                    ((Calle)casillaActual).cobrarAlquiler(); // PREGUNTAR SI ASI ESTA BIEN       
                }
            }
        }    
        if(casilla.getTipo() == TipoCasilla.IMPUESTO){
           int coste = this.casillaActual.getCoste();
           pagarImpuesto();
        }
        return tienePropietario;
    } 
    
    void pagarImpuesto(){
        modificarSaldo(-(casillaActual.getCoste() / factorEspeculador));
    }
    Sorpresa devolverCartaLibertad(){
        Sorpresa aux;
        aux = cartaLibertad;
        cartaLibertad = null;
        return aux;
                
    }
    
    public int getSaldo(){
        return saldo;
    }
    void irACarcel(Casilla casilla){
        setCasillaActual(casilla);
        setEncarcelado(true);
    }
    void modificarSaldo(int cantidad){
        saldo += cantidad;
    }
    int obtenerCapital(){
        int totalPropiedades = 0, total = 0;
        for (TituloPropiedad propiedades1 : propiedades) {
            totalPropiedades += propiedades1.getPrecioEdificar() + 
                    propiedades1.getPrecioEdificar() * (((Calle)propiedades1.getCasilla()).getNumHoteles() + ((Calle)propiedades1.getCasilla()).getNumCasas());
            if(propiedades1.isHipotecado()){
                totalPropiedades -= propiedades1.getHipotecaBase();
            }
        }
        total = getSaldo() + totalPropiedades;
        return total;
    }
    ArrayList<Casilla> obtenerPropiedadesHipotecadas(boolean hipotecada){
        ArrayList<Casilla> hipotecadas = new ArrayList();
       
                for (TituloPropiedad propiedades1 : propiedades) {
                    if (propiedades1.isHipotecado())
                        hipotecadas.add(propiedades1.getCasilla());
                }
        return hipotecadas;
    }
    void pagarCobrarPorCasaYHotel(int cantidad){
        int numeroTotal = cuantasCasasHotelesTengo();        
        modificarSaldo(numeroTotal * cantidad);
        
    }
    boolean pagarLibertad(int cantidad){
        boolean tengoSaldo = tengoSaldo(cantidad);
        if(tengoSaldo)
            modificarSaldo(-cantidad);
        return tengoSaldo;
    }
    boolean puedoEdificarCasa(Casilla casilla){
        boolean esMia = esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;
        if (esMia){
            ((Calle)casilla).sePuedeEdificarCasa(factorEspeculador);
            int costeEdificarCasa = ((Calle)casilla).getPrecioEdificar();
            tengoSaldo = tengoSaldo(costeEdificarCasa);
        }
        return tengoSaldo;
        
    }
    boolean puedoEdificarHotel(Casilla casilla){
        boolean esMia = esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;
        if(esMia){
            ((Calle)casilla).sePuedeEdificarHotel(factorEspeculador);
            int costeEdificarHotel = ((Calle)casilla).getPrecioEdificar();
            tengoSaldo = tengoSaldo(costeEdificarHotel);
        }
        return tengoSaldo;
    }
    boolean puedoHipotecar(Casilla casilla){
        boolean puedoHipotecar = false;
        if(this.esDeMiPropiedad(casilla)){
            puedoHipotecar = true;                   
        }
        return puedoHipotecar;
    }
    boolean puedoPagarHipoteca(Casilla casilla){
        boolean puedoPagar = false;
        if(puedoHipotecar(casilla)){
            int cantidad = ((Calle)casilla).cancelarHipoteca();
            cantidad += (int)(cantidad*0.1);
            puedoPagar = tengoSaldo(cantidad);
            if(puedoPagar)
                modificarSaldo(-cantidad);
        }
        return puedoPagar;
    }
    boolean puedoVenderPropiedad(Casilla casilla){
        boolean esMia = esDeMiPropiedad(casilla);
        boolean puedoVender = false;
        if(!((Calle)casilla).estaHipotecada() && esMia)
            puedoVender = true;
        return puedoVender;
    }
    boolean tengoCartaLibertad(){
        boolean tengoLaCarta = false;
        
        if(cartaLibertad != null)
            tengoLaCarta = true;
            
           return tengoLaCarta;
    }
    void venderPropiedad(Casilla casilla){
        int precioVenta = ((Calle)casilla).venderTitulo();
        System.out.println("Dinero recibido al vender la propiedad " + precioVenta);
        modificarSaldo(precioVenta);
        eliminarDeMisPropiedades(casilla);
    }
    int cuantasCasasHotelesTengo(){
        int total = 0;
        for (TituloPropiedad propiedades1 : propiedades) {
            total = total + ((Calle)propiedades1.getCasilla()).getNumCasas() + ((Calle)propiedades1.getCasilla()).getNumHoteles();
        }
        
        return total;
        
    }
    void eliminarDeMisPropiedades(Casilla casilla){
       if (this.esDeMiPropiedad(casilla)){
           propiedades.remove(((Calle)casilla).getTitulo());
       }    
    }
    boolean esDeMiPropiedad(Casilla casilla){
        boolean esMia = false;
        for (TituloPropiedad propiedades1 : propiedades) {
            if(propiedades1.getCasilla() == casilla){
                esMia = true;
            }
        }
        return esMia;
    }
    boolean tengoSaldo(int cantidad){
        return saldo > cantidad;
    }
}
