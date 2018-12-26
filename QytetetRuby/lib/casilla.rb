# encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module ModeloQytetet

#require_relative "titulo_propiedad"
#require_relative "tipo_casilla"



class Casilla
  attr_reader :numero_casilla, :coste, :tipo
  attr_accessor :num_hoteles, :num_casas, :titulo
  
  def initialize(numero_casilla, coste, tipo)
    @numero_casilla = numero_casilla
    @coste = coste
    @tipo = tipo

  end
  
  def self.crear_casilla(numero_casilla, coste, tipo)
    new(numero_casilla, coste, tipo)
  end

  
  def asignar_titulo_propiedad
    #void
    
  end
  
  def soy_edificable
    #boolean
    soyEdificable = false;
    if (@tipo == TipoCasilla::CALLE)
      soyEdificable = true;
    end
    soyEdificable;    
  end
  
  def to_s
    s = "Numero casilla: #{@numero_casilla} \n Coste: #{@coste} \n Tipo: #{@tipo} \n"
    #if(@titulo != nil)
     # s += "Numero hoteles: #{@num_hoteles} \n Numero casas: #{@num_casas} \n Titulo de propiedad: #{@titulo.nombre}\n"
    #end
    return s
  end
  
  private :titulo=, :asignar_titulo_propiedad
  #private_class_method :new

end
end