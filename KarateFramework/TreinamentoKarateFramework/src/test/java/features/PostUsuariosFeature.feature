Feature: Cadastra usuario
  Background: Informo a base URL
    #Given url "https://serverest.dev/"
    Given url baseUrl
     #declaraçao de objeto
    * def Utils = Java.type('utils.Utils')


  Scenario: Inclui usuario com json body no scenario

    * def nome = "teste karate"
    * def email = Utils.gerarStringAleatoria(6)+"@qa.com.br"
    * def password = "teste"
    * def administrador = "true"
    * def body =
    """
    {
        "nome": "#(nome)",
        "email": "#(email)",
        "password": "#(password)",
        "administrador": "#(administrador)"
    }
    """
    Given path '/usuarios'
    And request body
    When method post
    Then status 201


  Scenario: Inclui usuario com json body por arquivo
    * def body = read('classpath:/jsons/usuarios.json')
    * set body.nome = "teste karate"
    * set body.email = Utils.gerarStringAleatoria(6)+"@qa.com.br"
    * set body.password = "teste"
    * set body.administrador = "true"
    Given path '/usuarios'
    And request body
    When method post
    Then status 201

