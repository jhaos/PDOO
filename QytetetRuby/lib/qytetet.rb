# encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "jugador"
require_relative "sorpresa"
require_relative "tipo_sorpresa"
require_relative "tablero"
require_relative "titulo_propiedad"
require_relative "tipo_casilla"
require_relative "casilla"
require "singleton"
require_relative "dado"
module ModeloQytetet
 


class Qytetet
  
  include Singleton 
  attr_reader :jugadores, :casillas, :tablero
  attr_accessor :jugador_actual, :carta_actual
  @@SALDO_SALIDA = 1000
  def initialize
    @MAX_JUGADORES = 4;
    @MAX_CARTAS = 10
    @MAX_CASILLAS = 20
    @PRECIO_LIBERTAD = 1000    
    @carta_actual
    @jugador_actual
    @jugadores = Array.new
    @dado = Dado.instance
    @mazo = Array.new
    
    inicializar_cartas_sorpresa
    inicializar_tablero
    
  end
  
  def inicializar_cartas_sorpresa
      @mazo<< Sorpresa.new("Te conviertes en un especulador. Disfruta", 3000, TipoSorpresa::CONVERTIR)
      @mazo<< Sorpresa.new("te hemos pillado haciendo cosas que no debias, ¡a la carcel!", 5, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Alguien ha pagado tu fianza, salesde la carcel.", 0, TipoSorpresa::SALIRCARCEL)
      @mazo<< Sorpresa.new("Mala suerte amigo, has molestado a un pez gordo. ¡A pagar!", -50, TipoSorpresa::PAGARCOBRAR)
      @mazo<< Sorpresa.new("Te has levantado con la pierna derecha, has ganado un premio", 100, TipoSorpresa::PAGARCOBRAR)
      @mazo<< Sorpresa.new("Te ha abducido un ovni y te ha dejado en la 10ª avenida", 10, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Has cogido un taxi y te lleva al 4", 4, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Hacienda te ha pillado te toca pagar una pasta por tus propiedades", 50, TipoSorpresa::PORCASAHOTEL)
      @mazo<< Sorpresa.new("Hacienda se ha equivocado contigo recibes una \"gratificación\" ", -120, TipoSorpresa::PORCASAHOTEL)
      @mazo<< Sorpresa.new("Tus compañeros estan generosos hoy", 100, TipoSorpresa::PORJUGADOR)
      @mazo<< Sorpresa.new("Te has portado mal con tus colegas asi que les indemnizas", -120, TipoSorpresa::PORJUGADOR)
      @mazo<< Sorpresa.new("Te conviertes en EL GRAN especulador. Disfruta", 5000, TipoSorpresa::CONVERTIR)
      #@mazo.shuffle!
  end
  
  def inicializar_jugadores(nombres) 
    for nombre in nombres
      @jugadores << Jugador.new(nombre)
    end
  end
  
  def inicializar_tablero()
    @tablero = Tablero.new()
    
  end
  
  def self.get_saldo_salida
    @@SALDO_SALIDA
  end
  
   # s = "Tablero: \n #{@tablero.to_s} \n\n Cartas Sorpresa: \n #{@mazo} \n\n Jugadores: \n #{@jugadores}"
  def to_s
    s_1 = ""
    for jugador in @jugadores
      s_1 = s_1 + jugador.to_s + "\n"
    end
      
    s_2 = ""
    for s in @mazo.shuffle
      s_2 += s.to_s
    end
    "Jugadores: \n #{s_1} \n\n Tablero: \n #{@tablero} \n\n Sorpresas: \n #{s_2} " 
    end
    
  def get_jugadores
        return @jugadores;
  end
  
  def aplicar_sorpresa
    tiene_propietario = false;
    
    if (@carta_actual.tipo == TipoSorpresa::PAGARCOBRAR)
      @jugador_actual.modificar_saldo(@carta_actual.valor)
    elsif (@carta_actual.tipo == TipoSorpresa::IRACASILLA)
      es_carcel = @tablero.es_casilla_carcel(@carta_actual.valor)
      puts @carta_actual.valor
      @jugador_actual.casilla_actual = @tablero.obtener_casilla_numero(@carta_actual.valor)
      puts "La nueva casilla es " + @jugador_actual.casilla_actual.to_s
      if (es_carcel)       
        encarcelar_jugador
      elsif (@jugador_actual.casilla_actual == TipoCasilla::JUEZ)
        encarcelar_jugador
      elsif (@jugador_actual.casilla_actual == TipoCasilla::PARKING)
        puts "toca descansar"
      else
        
        nueva_casilla = @tablero.obtener_casilla_numero(@carta_actual.valor)
        tiene_propietario = @jugador_actual.actualizar_posicion(nueva_casilla)
          #como preguntar si quiere comprar o no      
      end
    elsif (@carta_actual.tipo == TipoSorpresa::PORCASAHOTEL)
      @jugador_actual.pagar_cobrar_por_casa_y_hotel(@carta_actual.valor)
    elsif (@carta_actual.tipo == TipoSorpresa::PORJUGADOR)
      for jugador1 in @jugadores
        if (jugador1 != @jugador_actual)
          jugador1.modificar_saldo(-@carta_actual.valor)
          @jugador_actual.modificar_saldo(@carta_actual.valor)
        end
      end
    elsif (@carta_actual.tipo == TipoSorpresa::SALIRCARCEL)
      @jugador_actual.carta_libertad = @carta_actual
    elsif (@carta_actual.tipo == TipoSorpresa::CONVERTIR)
      posicion = @jugadores.index(@jugador_actual)
      especulador = @jugador_actual.convertirme(@carta_actual.valor)
      @jugadores[posicion] = especulador
      @jugador_actual = @jugadores[posicion]
      
    else
      @mazo << @carta_actual
    end
    @carta_actual = nil  
    tiene_propietario
  end
  
  def cancelar_hipoteca (casilla)#Casilla
    if(casilla.titulo.hipotecado)
      @jugador_actual.puedo_pagar_hipoteca(casilla)
    else
      puts "no esta hipotecada"
    end
  end
  
  def comprar_titulo_de_propiedad
    @jugador_actual.comprar_titulo
    #boolean
  end
  
  def edificar_casa (casilla)#Casilla
    #boolean
    puedo_edificar = false
    if (casilla.soy_edificable)
      se_puede_edificar = casilla.se_puede_edificar_casa(@jugador_actual.get_factor_especulador)
      if (se_puede_edificar)
        puedo_edificar = @jugador_actual.puedo_edificar_casa(casilla)
        if (puedo_edificar)
          casilla.num_casas = (casilla.num_casas) + 1
          coste_edificar_casa = casilla.edificar_casa
          @jugador_actual.modificar_saldo(-coste_edificar_casa)
        end
      end
    end
    puedo_edificar
  end
  
  def edificar_hotel (casilla)#Casilla
    #boolean
    puedo_edificar = false
    if (casilla.soy_edificable)
      se_puede_edificar = casilla.se_puede_edificar_hotel(@jugador_actual.get_factor_especulador)
      if (se_puede_edificar)
        puedo_edificar = @jugador_actual.puedo_edificar_hotel(casilla)
        if(puedo_edificar)
          casilla.num_casas = 0
          casilla.num_hoteles = (casilla.num_hoteles) + 1
          coste_edificar_hotel = casilla.edificar_hotel
          @jugador_actual.modificar_saldo(-coste_edificar_hotel)
        end
      end
    end
    puedo_edificar
  end
  
  def hipotecar_propiedad (casilla)#casilla
    se_puede_hipotecar = false
    if(!casilla.titulo.hipotecado)
      if ( casilla.soy_edificable)
        se_puede_hipotecar = !casilla.esta_hipotecada
      end
      if(se_puede_hipotecar)
          se_puede_hipotecar = @jugador_actual.puedo_hipotecar(casilla)      
      end
      cantidad_recibida = casilla.hipotecar
      @jugador_actual.modificar_saldo(cantidad_recibida)
      
    else
      puts "ya esta hipotecada"
    end
    se_puede_hipotecar
  end
  
  def inicializar_juego(nombres)
    #void VECTOR <= 4 Y >=2
    inicializar_tablero
    inicializar_cartas_sorpresa 
    inicializar_jugadores(nombres)
    salida_jugadores
  end
  
  def intentar_salir_carcel(metodo)#Metodo_salir_carcel
    #boolean
    libre = false
    if (metodo == Metodo_salir_carcel::TIRANDODADO)
      valor_dado = @dado.tirar
      if (valor_dado > 5)
        puts "El valor del dado es: " + valor_dado.to_s + " y sales de la carcel"
        libre = true
        @jugador_actual.encarcelado = false;
      else
        puts "El valor es: " + valor_dado.to_s + " no sales de la carcel"
      end
    elsif (metodo == Metodo_salir_carcel::PAGANDOLIBERTAD)
      if(@jugador_actual.tengo_saldo(@PRECIO_LIBERTAD))
        @jugador_actual.modificar_saldo(-@PRECIO_LIBERTAD)
        libre = true
  
      else
        puts "No tienes pasta no sales de la carcel"
      end
    end
    if (libre)
      @jugador_actual.encarcelado = false
    end
    libre
  end
  
  def jugar
    #boolean
   
    valor_dado = @dado.tirar
    puts "Valor de la tirada de " + @jugador_actual.nombre + ": " + valor_dado.to_s
    casilla_posicion = @jugador_actual.casilla_actual
    nueva_casilla = @tablero.obtener_nueva_casilla(casilla_posicion, valor_dado)
    puts nueva_casilla
    tiene_propietario = @jugador_actual.actualizar_posicion(nueva_casilla)
    
    if (!nueva_casilla.soy_edificable)
      if (nueva_casilla.tipo == TipoCasilla::JUEZ)
        encarcelar_jugador
      elsif (nueva_casilla.tipo == TipoCasilla::SORPRESA)
        puts @mazo[0]
        @carta_actual = @mazo.first
        @mazo.delete_at(0)
        @mazo << @carta_actual
      end      
    end
    tiene_propietario
  end
    
  def obtener_ranking
    #Tipo vector de de jugadores desde 2 a 4
    ranking = Array.new
     
     for j in @jugadores
       ranking << j.nombre + " " + j.saldo.to_s
     end
     ranking
  end
  
  
  def propiedades_hipotecadas_jugador(hipotecadas) #boolean
    #vector de casillas
    @jugador_actual.obtener_propiedades_hipotecadas(hipotecadas)
    
  end
  
  def siguiente_jugador
    #Jugador
    pos_actual = @jugadores.index(@jugador_actual) 
    puts pos_actual
    pos_siguiente = (pos_actual + 1) % @jugadores.size
    
    @jugador_actual = @jugadores.at(pos_siguiente)
    
    @jugador_actual
  end
  
  def vender_propiedad(casilla)#Casilla
    puedo_vender = false
    if (casilla.soy_edificable)
      puedo_vender = @jugador_actual.puedo_vender_propiedad(casilla)
      if (puedo_vender)
        @jugador_actual.vender_propiedad(casilla)
      end
    end
    puedo_vender
  end
  
  def encarcelar_jugador
    #void
    if (!@jugador_actual.tengo_carta_libertad)
      carcel = tablero.carcel;
      @jugador_actual.ir_a_carcel(carcel);
    else                                             #REVISAR CON ANA
      carta = @jugador_actual.devolver_carta_libertad();
      @mazo << CartaSorpresa.new(carta)
    end
  end
  
  def salida_jugadores
    
    for jugador1 in @jugadores
      jugador1.casilla_actual = @tablero.obtener_casilla_numero(0)
    end
  # @jugadores.each { |j|j.casilla_actual=@tablero.obtener_casilla_numero(0)  }
    
    aleatorio = rand(@jugadores.size - 1) 
    @jugador_actual = @jugadores.at(aleatorio)
   
  end
  private :encarcelar_jugador, :salida_jugadores, :inicializar_cartas_sorpresa, :inicializar_tablero
end
end