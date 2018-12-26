# encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module ModeloQytetet
  

#require_relative "jugador"
#require_relative "sorpresa"
#require_relative "tipo_sorpresa"
#require_relative "titulo_propiedad"
#require_relative "tipo_casilla"
#require_relative "casilla"
#require_relative "calle"

class Tablero
  
    attr_reader :casillas
    attr_reader :carcel
    
    def initialize
      @casillas = Array.new
      inicializar
      @carcel = @casillas.at(5);
    end
  
    def inicializar
      @casillas<< Casilla.crear_casilla(0, 0, TipoCasilla::SALIDA)
      
      @casillas<< Calle.new(1, 320, TituloPropiedad.new("Islas Bienaventuradas", 50, 5, 150, 35))
      @casillas.last.titulo.casilla = @casillas.last
      @casillas<< Calle.new(2, 350, TituloPropiedad.new("Barca de Caronte", 60, 5, 170, 50))
      @casillas.last.titulo.casilla = @casillas.last

      @casillas<< Casilla.crear_casilla(3, 0, TipoCasilla::SORPRESA)
      @casillas<< Calle.new(4, 400, TituloPropiedad.new("Campos Asphodelos", 75, 5, 190, 60))
      @casillas.last.titulo.casilla = @casillas.last
      @casillas<< Casilla.crear_casilla(5, 0, TipoCasilla::CARCEL)
      @casillas<< Calle.new(6, 450, TituloPropiedad.new("Río Lete", 80, 5, 200, 75))
      @casillas.last.titulo.casilla = @casillas.last
      @casillas<< Calle.new(7, 0, TituloPropiedad.new("Morada de Tánatos", 90, 5, 215, 90))
      @casillas.last.titulo.casilla = @casillas.last
      @casillas<< Casilla.crear_casilla(8, 0, TipoCasilla::SORPRESA)
      @casillas<< Calle.new(9, 485, TituloPropiedad.new("Templo de Perséfone", 105, 5, 240, 115))
      @casillas.last.titulo.casilla = @casillas.last
      @casillas<< Casilla.crear_casilla(10, 0, TipoCasilla::PARKING)
      @casillas<< Calle.new(11, 510, TituloPropiedad.new("Jaula de Cancerbero", 120, 5, 260, 130))
      @casillas.last.titulo.casilla = @casillas.last
      @casillas<< Casilla.crear_casilla(12, 150, TipoCasilla::IMPUESTO)
      @casillas<< Calle.new(13, 540, TituloPropiedad.new("Pasaje de los hecatónquiro", 130, 5, 285, 150))
      @casillas.last.titulo.casilla = @casillas.last
      @casillas<< Calle.new(14, 555, TituloPropiedad.new("Campos eliseos", 150, 5, 295, 165))
      @casillas.last.titulo.casilla = @casillas.last
      @casillas<< Casilla.crear_casilla(15, 0, TipoCasilla::JUEZ)
      @casillas<< Calle.new(16, 580, TituloPropiedad.new("Laguna Estigia", 160, 5, 300, 185))
      @casillas.last.titulo.casilla = @casillas.last
      @casillas<< Casilla.crear_casilla(17, 0, TipoCasilla::SORPRESA)
      @casillas<< Calle.new(18, 630, TituloPropiedad.new("Castillo de Hades", 250, 5, 350, 300))
      @casillas.last.titulo.casilla = @casillas.last
      @casillas<< Calle.new(19, 680, TituloPropiedad.new("Tártaro", 300, 5, 360, 310))
      @casillas.last.titulo.casilla = @casillas.last
      
    end
    
    def es_casilla_carcel(numero_casilla)
      es_carcel = false
      if(numero_casilla == @carcel.numero_casilla)
        
        es_carcel = true    
      end
      es_carcel
    end
    
    def obtener_casilla_numero(numero)
      @casillas.at(numero)
    end
    
    def obtener_nueva_casilla(casilla, desplazamiento)#casilla de tipo casilla desplazamiento de tipo int
      #Casilla
      #if (@casilla != nil)
      #nueva_posicion = casilla.numero_casilla + desplazamiento
      #if(nueva_posicion > 19)
      #  nueva_posicion %= 20
      #end
      #nueva_casilla = @casilla.at(nueva_posicion)
     #end
     cas = @casillas.at((@casillas.index(casilla) + desplazamiento) % 19)
    
     cas
    end
    
    def to_s
      s = ""
      for ncasilla in @casillas
        s += ncasilla.to_s     
      end
      s
    end
    private :inicializar
end
end