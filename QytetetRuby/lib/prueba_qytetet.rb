#encoding: utf-8
#To change this license header, choose License Headers in Project Properties.
#To change this template file, choose Tools | Templates
#and open the template in the editor.

module ModeloQytetet
  

require_relative "sorpresa"
require_relative "tipo_sorpresa"
require_relative "tablero"
require_relative "titulo_propiedad"
require_relative "tipo_casilla"
require_relative "casilla"
require_relative "qytetet"

  
  class PruebaQytetet
   
    
 
 #   @@mazo = Array.new
  
  #  def self.inicializar_sorpresas
  #    @@mazo<< Sorpresa.new("te hemos pillado haciendo cosas que no debias, ¡a la carcel!", 9, TipoSorpresa::IRACASILLA)
  #    @@mazo<< Sorpresa.new("Alguien ha pagado tu fianza, salesde la carcel.", 0, TipoSorpresa::SALIRCARCEL)
  #    @@mazo<< Sorpresa.new("Mala suerte amigo, has molestado a un pez gordo. ¡A pagar!", 200, TipoSorpresa::PAGARCOBRAR)
  #    @@mazo<< Sorpresa.new("Te has levantado con la pierna derecha, has ganado un premio", 100, TipoSorpresa::PAGARCOBRAR)
  #    @@mazo<< Sorpresa.new("Te ha abducido un ovni y te ha dejado en la 10ª avenida", 10, TipoSorpresa::IRACASILLA)
  #    @@mazo<< Sorpresa.new("Has cogido un taxi y te lleva al 23", 23, TipoSorpresa::IRACASILLA)
  #    @@mazo<< Sorpresa.new("Hacienda te ha pillado te toca pagar una pasta por tus propiedades", 50, TipoSorpresa::PORCASAHOTEL)
  #    @@mazo<< Sorpresa.new("Hacienda se ha equivocado contigo recibes una \"gratificación\" ", 120, TipoSorpresa::PORCASAHOTEL)
  #    @@mazo<< Sorpresa.new("Tus compañeros estan generosos hoy", 100, TipoSorpresa::PORJUGADOR)
  #    @@mazo<< Sorpresa.new("Te has portado mal con tus colegas asi que les indemnizas", 120, TipoSorpresa::PORJUGADOR)
  #  end
  
  #  def self.mayorQueCero
    
  #    nuevo_mazo = Array.new  
  #    for nmazo in @@mazo    
  #      if nmazo.valor > 0 
  #        nuevo_mazo << nmazo 
  #      end   
  #    end   
  #  end
    
    
  
  #  def self.tipoIrCasilla
  #  
  #    nuevo_mazo = Array.new
  #    for nmazo in @@mazo
  #      if nmazo.tipo == TipoSorpresa::IRACASILLA
  #        nuevo_mazo << nmazo
  #      end
  #    end
  #  end
  #
  #  def self.compararCasilla(tipo_sorpresa)
  #    nuevo_mazo = Array.new
  #    for nmazo in @@mazo
  #      if nmazo.tipo == tipo_sorpresa
  #        nuevo_mazo << nmazo
  #      end
  #    end
  #  end
  #
  def self.main
      
     # inicializar_sorpresas()
      #puts mayorQueCero()
     # puts tipoIrCasilla
     # puts compararCasilla(TipoSorpresa::PORJUGADOR)
     # tablero = Tablero.new
     # puts tablero.to_s
      
      prueba = Qytetet.instance
      nombres = Array.new
      nombres << "jugador 1"
      nombres << "jugador 2"
      
      
      prueba.inicializar_jugadores(nombres)
      #prueba.inicializar_cartas_sorpresa
      #prueba.inicializar_tablero
      puts prueba.to_s
      
    end
  
  end
  PruebaQytetet.main
end