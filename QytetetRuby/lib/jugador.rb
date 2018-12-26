# encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module ModeloQytetet
#require_relative "especulador"

require_relative "sorpresa"
require_relative "tipo_sorpresa"
require_relative "tablero"
require_relative "titulo_propiedad"
require_relative "tipo_casilla"
require_relative "casilla"
  
class Jugador
  attr_reader :saldo, :nombre
  attr_accessor :encarcelado, :casilla_actual, :propiedades, :carta_libertad
  
  @@factor_especulador = 1
  
  def initialize(nombre)
    @encarcelado = false
    @nombre = nombre
    @saldo = 7500
    @propiedades = Array.new
    @casilla_actual = nil
    @carta_libertad = nil
  end
  
  def copiar_jugador(jugador)

    @encarcelado = jugador.encarcelado
    @nombre = jugador.nombre
    @saldo = jugador.saldo
    @propiedades = Array.new(jugador.propiedades)
    @casilla_actual = jugador.casilla_actual
    @carta_libertad = jugador.carta_libertad
  end
  
  def convertirme(fianza)
    especulador = Especulador.new(self, fianza)
    especulador
  end
  
  def tengo_propiedad
    #boolean
    tengo_propiedades = false;
    if (@propiedades.count > 0)
      tengo_propiedades = true;
    end

    tengo_propiedades;

  end
  
  def to_s
    cadena = "Nombre: #{@nombre} \n Encarcelado: #{@encarcelado} \n Saldo: #{@saldo}\n Casilla actual: #{@casilla_actual}\n Carta libertad #{@carta_libertad}\n"
    @propiedades.each{|propiedad| cadena += "\n" + propiedad.to_s}
    cadena
  end
  
  def actualizar_posicion(casilla) #Casilla
    tiene_propietario = false
    if (casilla.numero_casilla < @casilla_actual.numero_casilla)
      modificar_saldo(Qytetet.get_saldo_salida)
    end
    @casilla_actual = casilla
    if (@casilla_actual.soy_edificable)
      tiene_propietario = @casilla_actual.tengo_propietario
      if (tiene_propietario)
        encarcelado = false
        encarcelado = @casilla_actual.propietario_encarcelado
        if(!encarcelado)
          @casilla_actual.cobrar_alquiler
        end
      end    
    end
    if (casilla.tipo == TipoCasilla::IMPUESTO)
      pagar_impuesto 
    end
    
    tiene_propietario
    
  end
  
  def comprar_titulo  
    #boolean
    puedo_comprar = false
   
    if (@casilla_actual.soy_edificable)
      tengo_propietario = @casilla_actual.tengo_propietario
      if (!tengo_propietario)
        coste_compra = @casilla_actual.coste
        if (coste_compra <= @saldo)
          puts "Compramos la propiedad por " + coste_compra.to_s
          nuevo_titulo = @casilla_actual.asignar_propietario(self)
          puedo_comprar = true
          @propiedades << (nuevo_titulo)
          modificar_saldo(-coste_compra)
        end
      end
    end
    puedo_comprar
  end  
  
  def devolver_carta_libertad
    #Sorpresa
    aux = carta_libertad
    cartaLibertad = nil;
        
    aux;
    
  end
  
  def ir_a_carcel(casilla)#Casilla
    #void
    @casilla_actual = casilla
    @encarcelado = true
  end
  
  def modificar_saldo(cantidad)#int
    #void
    @saldo += cantidad;
    
  end
  
  def obtener_capital
    #int
    total_propiedades = 0, total = 0;
        for propiedades1 in @propiedades
            total_propiedades += propiedades1.precio_edificar + 
            propiedades1.precio_edificar * (propiedades1.casilla.num_hoteles + propiedades1.casilla.num_casas)      
            if(propiedades1.hipotecado)
                total_propiedades -= propiedades1.hipoteca_base
            end
        end
    total = @saldo + total_propiedades
    total
  end
  
  def obtener_propiedades_hipotecadas(hipotecada)#boolean
    #Vecto de Titulo de propiedad
    hipotecadas = Array.new
    
        for propiedades1 in @propiedades
          if (propiedades1.hipotecado = hipotecada)
            hipotecadas << propiedades1;
          end
        end                  
        
         hipotecadas
  end
  
  def pagar_cobrar_por_casa_y_hotel (cantidad) #int
    #void
    numero_total = cuantas_casas_hoteles_tengo
    modificar_saldo(numero_total * cantidad)
  end
  
  def pagar_impuesto
    modificar_saldo(-(@casilla_actual.coste / @@factor_especulador)) 
  end
  
  def pagar_libertad(cantidad) #int
    #void
    tengo_saldo = tengo_saldo(cantidad)
    if(tengo_saldo)
      modificar_saldo(-cantidad)
    end
    tengo_saldo
  end
  
  def get_factor_especulador
    @@factor_especulador
  end
  
  def puedo_edificar_casa(casilla) #Casilla
    #boolean
    es_mia = es_de_mi_propiedad(casilla)
    tengo_saldo = false
    
    if (es_mia)
      casilla.se_puede_edificar_casa(get_factor_especulador)
      coste_edificar_casa = casilla.get_precio_edificar
      tengo_saldo = tengo_saldo(coste_edificar_casa)
    end
    tengo_saldo
  end
   
  def puedo_edificar_hotel(casilla)#Casilla
    #boolean
    es_mia = es_de_mi_propiedad(casilla)
    tengo_saldo = false
    
    if (es_mia)
      casilla.se_puede_edificar_hotel(get_factor_especulador)
      coste_edificar_hotel = casilla.get_precio_edificar
      tengo_saldo = tengo_saldo(coste_edificar_hotel)
    end
    tengo_saldo
  end
  
  def puedo_hipotecar(casilla)#casilla
    puedo_hipotecar_casilla = false
    if (es_de_mi_propiedad(casilla))
      puedo_hipotecar_casilla = true
    end
    puedo_hipotecar_casilla
    
  end
  
  def puedo_pagar_hipoteca(casilla)#Casilla
   
    #boolean
    if(puedo_hipotecar(casilla))     
      cantidad = casilla.cancelar_hipoteca
      cantidad += (cantidad*0.1).round
      puedo_pagar = tengo_saldo(cantidad)
      if(puedo_pagar)
        modificar_saldo(-cantidad)
        
      end
    end
      puedo_pagar
  end
  
  def puedo_vender_propiedad(casilla)#Casilla
    #boolean
    es_mia = es_de_mi_propiedad(casilla)
    puedo_vender = false
    if(!casilla.esta_hipotecada && es_mia)
        puedo_vender = true   
    end
    puedo_vender
  end
  
  def tengo_carta_libertad
    #boolean
    tengo_carta = false;
    if(carta_libertad != nil)
      tengo_carta = true;
    end
    tengo_carta
  end
  
  def vender_propiedad(casilla)#casilla
    #void
    precio_venta = casilla.vender_titulo
    puts "Dinero recibido al venderla propiedad " + precio_venta.to_s
    modificar_saldo(precio_venta)
    eliminar_de_mis_propiedades(casilla)
    
  end
  
  def cuantas_casas_hoteles_tengo
    #int
    total = 0
    for propiedades1 in @propiedades 
      total = total + propiedades1.casilla.num_hoteles + propiedades1.casilla.num_casas;
    end
    total;  
  end
  
  def eliminar_de_mis_propiedades(casilla)#Casilla// preguntar por esta funcion
    #void
   
      
    @propiedades.delete_at(@propiedades.index(casilla.titulo))
      
   
  end
  
  def es_de_mi_propiedad (casilla)#Casilla
    #boolean
    es_mia = false;
    for propiedades1 in @propiedades
      if(propiedades1.casilla == casilla)
        es_mia = true
      end
    end
    es_mia   
  end
  
  def tengo_saldo(cantidad)#int
    #boolean
    return @saldo >= cantidad
  end
  private :cuantas_casas_hoteles_tengo, :eliminar_de_mis_propiedades, :es_de_mi_propiedad #2, :tengo_saldo
  
end
end
