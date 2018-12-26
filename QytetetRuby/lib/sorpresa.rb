# encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module ModeloQytetet
  
require_relative "tipo_sorpresa"

  

  class Sorpresa
    attr_reader :texto, :valor, :tipo
  
    def initialize(texto, valor, tipo)
      @texto = texto
      @valor = valor
      @tipo = tipo
    end
  
    def to_s
      s = "Texto: #{@texto} \n Valor: #{@valor} \n Tipo: #{@tipo} \n\n"
    end
  
  end
end