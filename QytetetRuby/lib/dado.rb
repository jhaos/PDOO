# encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require "singleton"
class Dado
  include Singleton
  def tirar
    aleatorio = rand(6)+1
    
  end
end
