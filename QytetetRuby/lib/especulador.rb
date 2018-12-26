# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

#require_relative "jugador"

module ModeloQytetet
  
  class Especulador < Jugador
    def initialize (jugador, fianza)
      copiar_jugador(jugador)
      @fianza = fianza
    end
    
    def convertirme (fianza)
      especulador = Especulador.new(self, fianza)#
      especulador
    end
    
    def get_factor_especulador
      doble_factor = @@factor_especulador * 2
      doble_factor
    end
    
    def pagar_impuesto
      modificar_saldo(-(@casilla_actual.coste / get_factor_especulador))
    end
    def ir_a_carcel(casilla)#Casilla
      #void
      salgo_carcel = pagar_fianza
      if (!salgo_carcel)
        @casilla_actual = casilla
        @encarcelado = true
      end
      salgo_carcel
    end
    
    def pagar_fianza()
      tengo_saldo = @fianza < @saldo
      if (tengo_saldo)
        @saldo = @saldo - @fianza;
      end
      tengo_saldo;
    end
    def to_s
      casilla = super
      casilla += " Fianza = #{@fianza}"
      casilla
    end
  end
end
