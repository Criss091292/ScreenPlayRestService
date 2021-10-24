Feature: Filtrar comments
  Como usuario de la aplicacion
  Necesito poder filtrar los informacion de los comments por campos
  para poder visualizarlos mejor.

  Scenario: consultar por campo existente
    Given el usuario decidio consultar un comment por el campo ("id") con valor (5)
    When el usuario realiza la peticion
    Then deberia obtener resultado exitoso con la informacion relativa al comment solicitado

  Scenario: consultar por campo inexistente
    Given el usuario decidio consultar un comment por un campo inexistente ("noExiste") con valor (5000)
    When el usuario realiza la peticion
    Then deberia obtener resultado de error