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
public class TituloPropiedad {
    private String nombre;
    private boolean hipotecado = false;
    private int alquilerBase;
    private int factorRevalorizacion; //PREGUNTAR ANA SI INT O FLOAT
    private int hipotecaBase;
    private int precioEdificar;
    Jugador propietario = null;
    private Casilla casilla = null;

    TituloPropiedad(String nombre, int alquilerBase, int factorRevalorizacion, int hipotecaBase, int precioEdificar) {
        this.nombre = nombre;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizacion = factorRevalorizacion;
        this.hipotecaBase = hipotecaBase;
        this.precioEdificar = precioEdificar;
    }
    
    public String getNombre() {
        return nombre;
    }
    boolean isHipotecado() {
        return hipotecado;
    }
    int getAlquilerBase() {
        return alquilerBase;
    }
    float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }
    int getHipotecaBase() {
        return hipotecaBase;
    }
    int getPrecioEdificar() {
        return precioEdificar;
    }    
    void setHipotecado(boolean hipotecado) {
        this.hipotecado = hipotecado;
    }

    public Casilla getCasilla() {
        return casilla;
    }

   @Override
   public String toString() {
       return "\n" + "Nombre de la propiedad = " + nombre + "\nEsta hipotecada=" + hipotecado + "\nAlquiler base = " + alquilerBase + 
               "\nFactor de revalorizacion = " + factorRevalorizacion + "\nValor hipoteca base = " + hipotecaBase + "\nPrecio de edificacion = " + precioEdificar;
    }
   
   boolean propietarioEncarcelado(){
       return propietario.getEncarcelado();
   }
   void setCasilla(Casilla casilla){
       this.casilla = casilla;
   }
   void setPropietario(Jugador propietario){
       this.propietario = propietario;
   }   
   boolean tengoPropietario(){
       return propietario!=null;
   }
   void cobrarAlquiler(int coste){
       propietario.modificarSaldo(-coste);
   }
    
}
// METODO CALCULAR VALOR