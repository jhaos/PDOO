# # encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  
require_relative "jugador"
require_relative "sorpresa"
require_relative "tipo_sorpresa"
require_relative "tablero"
require_relative "tipo_casilla"
require_relative "casilla"


class TituloPropiedad

  attr_accessor :hipotecado, :casilla, :propietario #tengo que ponerlo para llamar a casilla en jugador??
  attr_reader :alquiler_base, :factor_revalorizacion, :hipoteca_base, :precio_edificar, :nombre
  
  def initialize(nombre, alquiler_base, factor_revalorizacion, hipoteca_base, precio_edificar)
    @nombre = nombre
    @hipotecado = false
    @alquiler_base = alquiler_base
    @factor_revalorizacion = factor_revalorizacion
    @hipoteca_base = hipoteca_base
    @precio_edificar = precio_edificar
    @casilla = nil
    @propietario = nil
  end
  
  def to_s
    s = "\n Nombre: #{@nombre}  Hipotecado: #{@hipotecado}  Alquiler base: #{@alquiler_base}  Factor revalorizacion: #{@factor_revalorizacion}  Hipoteca base: #{@hipoteca_base}  Precio edificar: #{@precio_edificar}  Casilla :#{@casilla} \n "
  end
  
  def cobrar_alquiler(coste)
    #void
    @propietario.modificar_saldo(-coste)
  end
  
  def tengo_propietario_encarcelado()
    @propietario.encarcelado
  end
  
  def tengo_propietario()
    #boolean
    return @propietario!=nil
  end
  
end
end