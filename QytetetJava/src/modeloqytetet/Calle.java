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
public class Calle extends Casilla {
    protected int numHoteles;
    protected int numCasas;
    protected TituloPropiedad titulo;
    
    Calle(int numeroCasilla, int coste, TituloPropiedad titulo){
        super(numeroCasilla, coste);
        this.titulo = titulo;
        numCasas = 0;
        numHoteles = 0;
    }
    @Override
    public TipoCasilla getTipo(){
        return TipoCasilla.CALLE;
    }
    int getNumHoteles() {
        return numHoteles;
    }
    int getNumCasas() {
        return numCasas;
    }
    
    TituloPropiedad getTitulo() {
        return titulo;
    }
    void setNumHoteles(int numHoteles) {
        this.numHoteles = numHoteles;
    }
    void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }
    private void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }
    TituloPropiedad asignarPropietario(Jugador propietario){
       titulo.setPropietario(propietario);
       return titulo;
    }
    int calcularValorHipoteca(){
       int hipotecaBase = titulo.getHipotecaBase();
       int entera = (int)(numCasas*0.5*hipotecaBase + numHoteles*hipotecaBase);
       int cantidadRecibida = hipotecaBase + entera;
       return cantidadRecibida;
    }
    int cancelarHipoteca(){
        titulo.setHipotecado(false);
        int valorHipoteca = calcularValorHipoteca();
        int cantidadRecibida = (int)(valorHipoteca + valorHipoteca*0.1);
        return cantidadRecibida;
    }
    
    void cobrarAlquiler(){
       int entero = (int)(getNumCasas()*0.5 + getNumHoteles()*2);
       int totalAlquiler = costeAlquilerBase() + entero;
       titulo.propietario.modificarSaldo(titulo.propietario.getSaldo() + totalAlquiler); //titulo.propietario.obtenerCapital()
    }
    int costeAlquilerBase(){
        return titulo.getAlquilerBase();
    }
    int edificarCasa(){
       return titulo.getPrecioEdificar();
    }
    int edificarHotel(){
        return titulo.getPrecioEdificar();
    }
    
    boolean estaHipotecada(){ 
        return titulo.isHipotecado(); 
    }
    int getCosteHipoteca(){
        return titulo.getHipotecaBase();
    }
    int getPrecioEdificar(){
        return titulo.getPrecioEdificar();
    }
    int hipotecar(){ 
        this.titulo.setHipotecado(true);
        int cantidad = (int)(calcularValorHipoteca());
        return cantidad;
    }
    
    boolean propietarioEncarcelado(){
        return titulo.propietarioEncarcelado();
    }
    boolean sePuedeEdificarCasa(int factor){
        boolean puedoEdificarCasa = false;
        if (numCasas < 4*factor){
            puedoEdificarCasa = true;
        }
        return puedoEdificarCasa;
    }
    boolean sePuedeEdificarHotel(int factor){
        boolean puedoEdificarHotel = false;
        if (numCasas == 4*factor && numHoteles*factor < 4){
            puedoEdificarHotel = true;
        }
        return puedoEdificarHotel;
        
    }
    @Override
    public boolean soyEdificable(){
        boolean soyEdificable = true;
        
        return soyEdificable;
    }
   
    public boolean tengoPropietario(){
        return titulo.tengoPropietario();
    }
    int venderTitulo(){
        this.titulo.setPropietario(null);
        this.setNumCasas(0);
        this.setNumHoteles(0);
        int precioCompra = coste + (numCasas + numHoteles) * titulo.getPrecioEdificar();
        int precioVenta = (int) (precioCompra + titulo.getFactorRevalorizacion()*precioCompra);
        
        return precioVenta;
    }
    void asignarTituloPropiedad(TituloPropiedad titulo){
        this.setTitulo(titulo);
        titulo.setCasilla(this);
        
    }
}
