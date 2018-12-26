#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "qytetet"
require_relative "vista_textual_qytetet"
require_relative "jugador"
require_relative "sorpresa"
require_relative "tipo_sorpresa"
require_relative "tablero"
require_relative "titulo_propiedad"
require_relative "tipo_casilla"
require_relative "casilla"
require_relative "metodo_salir_carcel"
require "singleton"
require_relative "dado"
require_relative "especulador"
require_relative "calle"

module InterfazTextualQytetet
 
  class ControladorQytetet
    attr_reader :vista
    def initialize
      @juego
      @jugador
      @casilla
      @vista = VistaTextualQytetet.new
    end
    
    def inicializacion_juego
      @juego = ModeloQytetet::Qytetet.instance
      nombres = @vista.obtener_nombre_jugadores 
      @juego.inicializar_juego(nombres)
      @jugador = @juego.jugador_actual
      @casilla = @jugador.casilla_actual
      
      puts @juego
      puts "=======" + "jugador Actual" + "========"
      puts @jugador
      gets.chomp
    end    
    
    def desarrollo_juego
      
      
      loop do
        bancarrota = @jugador.saldo <= 0
        if (@jugador.encarcelado)
          metodo = @vista.menu_salir_carcel
          if (metodo == 0)
            libre = @juego.intentar_salir_carcel(ModeloQytetet::Metodo_salir_carcel::TIRANDODADO)
           
          elsif (metodo == 1)
            libre = @juego.intentar_salir_carcel(ModeloQytetet::Metodo_salir_carcel::PAGANDOLIBERTAD)
          end
        end
        if (libre || !@jugador.encarcelado)
          no_tiene_propietario = @juego.jugar
          bancarrota = @jugador.saldo <= 0
          if (!bancarrota)
            if (!@jugador.encarcelado)            
              if (@jugador.casilla_actual.tipo == ModeloQytetet::TipoCasilla::SORPRESA) #preguntar SI CON JUGADOR O CON CASILLA.TIPO
                no_tiene_propietario = @juego.aplicar_sorpresa
               # @jugador.casilla_actual = 
                if (!@jugador.encarcelado)
                  bancarrota = @jugador.saldo <= 0
                  if(!bancarrota)
                    if (@jugador.casilla_actual.tipo == ModeloQytetet::TipoCasilla::CALLE)
                      if (!@jugador.casilla_actual.tengo_propietario)
                        eleccion = @vista.elegir_quiero_comprar
                        if (eleccion == 0)
                          @juego.comprar_titulo_de_propiedad
                        end
                      end
                    end
                  end
                end
              elsif (@jugador.casilla_actual.tipo == ModeloQytetet::TipoCasilla::CALLE)
                if(!@jugador.casilla_actual.tengo_propietario)
                  eleccion = @vista.elegir_quiero_comprar
                  if (eleccion == 0)
                    @juego.comprar_titulo_de_propiedad
                  end
                end
              end
            end
          end
        end
        bancarrota = @jugador.saldo <= 0
        if (!@jugador.encarcelado && !bancarrota && @jugador.tengo_propiedad)
          opcion = @vista.menu_gestion_inmobiliaria
          while opcion != 0 
            titulo = elegir_propiedad(@jugador.propiedades)
            
            if (opcion == 1)
              puedo_edificar = @juego.edificar_casa(titulo.casilla)
              if (!puedo_edificar)
                @vista.mostrar("No se pueden poner mas casas")
              end
               #el parametro que se le pasa es casilla actual del jugador?
            elsif (opcion == 2)
              puedo_edificar = @juego.edificar_hotel(titulo.casilla)
              if (!puedo_edificar)
                @vista.mostrar("No se pueden poner mas hoteles")
              end
            elsif (opcion == 3)
              puedo_vender = @juego.vender_propiedad(titulo.casilla)
              if (!puedo_vender)
                @vista.mostrar("No se puede vender")
              end
            elsif (opcion == 4)
              hipotecar = @juego.hipotecar_propiedad(titulo.casilla)
              if (!hipotecar)
                @vista.mostrar("No se puede hipotecar")
              end
            elsif (opcion == 5)
              cancelar = @juego.cancelar_hipoteca(titulo.casilla)
              if (!cancelar)
                @vista.mostrar("No se puede cancelar hipoteca")
              end
            end
            opcion = @vista.menu_gestion_inmobiliaria
          
            end
        end
           
        
        bancarrota = @jugador.saldo <= 0
        if (!bancarrota)
          @jugador = @juego.siguiente_jugador
          puts "Ha cambiado de jugador"
          puts "===" + " Jugador Actual " + "===="
          puts @jugador
        end
       
        break if bancarrota
      end
      puts "UN JUGADOR HA ENTRADO EN BANCARROTA"
      puts @juego.obtener_ranking
      
    end
    
    
    def elegir_propiedad(propiedades) # lista de propiedades a elegir
        vista.mostrar("\tCasilla\tTitulo")
        
        listaPropiedades= Array.new
        for prop in propiedades  # crea una lista de strings con numeros y nombres de propiedades
                propString= prop.casilla.numero_casilla.to_s+' '+prop.nombre 
                listaPropiedades<<propString
        end
        seleccion = vista.menu_elegir_propiedad(listaPropiedades)  # elige de esa lista del menu
        propiedades.at(seleccion)
   end
   def self.main
     controlador = ControladorQytetet.new
     controlador.inicializacion_juego
     controlador.desarrollo_juego

     
   end
   ControladorQytetet.main
  end
  
end





