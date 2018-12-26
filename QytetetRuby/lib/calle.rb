# encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
#require_relative "casilla"
#require_relative "tipo_casilla"

module ModeloQytetet
  class Calle < Casilla
    def initialize(numero_casilla, coste, titulo)
      super(numero_casilla, coste, TipoCasilla::CALLE)
      @titulo = titulo
      @num_hoteles = 0
      @num_casas = 0
    end
    
    def asignar_propietario(jugador)
      @titulo.propietario = jugador
      @titulo
      #TituloPropiedad
    end


    
    def calcular_valor_hipoteca
      #int
      h_base = @titulo.hipoteca_base
      parte_entera = @num_casas*0.5*h_base + @num_hoteles*h_base
      cantidad_recibida = h_base + parte_entera
      cantidad_recibida
    end
    
    def cancelar_hipoteca
      #int
      @titulo.hipotecado = false
      valor_hipoteca = calcular_valor_hipoteca
      cantidad_recibida = valor_hipoteca + (valor_hipoteca * 0.1).round
      cantidad_recibida    
    end
    
    def cobrar_alquiler
    #int
    entero = (@num_casas*0.5 + @num_hoteles*2).round
    total_alquiler = coste_alquiler_base + entero;
    @titulo.propietario.modificar_saldo(-total_alquiler);
   
  end

  def edificar_casa
    #int
    @titulo.precio_edificar
  end

  def edificar_hotel
    #int
    @titulo.precio_edificar
  end

  def esta_hipotecada
    #int
    @titulo.hipotecado
  end
  
  def get_coste_hipoteca
    #int
    @titulo.hipoteca_base
  end
  
  def get_precio_edificar
    #int
    @titulo.precio_edificar
    
  end
  
  def hipotecar
    #int
    @titulo.hipotecado = true
    cantidad_recibida = calcular_valor_hipoteca
    cantidad_recibida
  end
  
  def precio_total_comprar
    #int
    
  end
  
  def propietario_encarcelado
    #boolean
    @titulo.propietario.encarcelado
  end
  
  def se_puede_edificar_casa (factor_especulador)
    puedo_edificar_casa = false
    if (@num_casas < 4*factor_especulador)  
      puedo_edificar_casa = true
    end
    puedo_edificar_casa
      #boolean
  end
  
  def se_puede_edificar_hotel (factor_especulador)
      #boolean
      puedo_edificar_hotel = false
      if (@num_casas == 4*factor_especulador && @num_hoteles < 4*factor_especulador)
        puedo_edificar_hotel = true
      end
      puedo_edificar_hotel
  end
  
  
  def tengo_propietario
    #boolean
    tengo = false
    
    if (@propietario != nil)
      tengo = true 
    end
    tengo
  end
  def vender_titulo
    
    @titulo.propietario = nil
    num_casas = 0 
    num_hoteles = 0
    precio_compra = @coste + (@num_casas + @num_hoteles) * @titulo.precio_edificar
    precio_venta = precio_compra + @titulo.factor_revalorizacion*precio_compra
        
    precio_venta
   
  end
  def to_s
    cadena = super
    cadena+= " Coste: #{@coste} Número de casas: #{@num_casas} Número de hoteles: #{@num_hoteles} Titulo de propiedad: #{@titulo.nombre}"
  end
end
end
