Feature: Busca lista de usuários de acordo com os parametros informados na query string
  Background: Informo a base URL
    #Given url "https://serverest.dev/"
    Given url baseUrl


  Scenario: Teste basico imprimindo o response
    Given path '/usuarios'
    When method get
    Then print response

  Scenario: Teste basico filtrando usuario e imprimindo o response
    Given path '/usuarios'
    And params {'_id' : '0uxuPY0cbmQhpEz1'}
    When method get
    Then print response

  Scenario: Validando o status code
    Given path '/usuarios'
    When method get
    Then status 200

  Scenario: Validando o header
    Given path '/usuarios'
    When method get
    Then match header content-type == 'application/json; charset=utf-8'

  Scenario: Validando o header
    Given path '/usuarios'
    When method get
    Then match header content-type contains 'application/json'

  Scenario: Validando o response body
    Given path '/usuarios'
    And params {'nome' : 'Fulano da Silva'}
    When method get
    Then match response.quantidade == 8
    And match response.usuarios[0].nome == 'Fulano da Silva'
    And match karate.lowerCase(response.usuarios[0].nome) == 'fulano da silva'
    And match response.usuarios[0].nome contains 'Fulano'
    #####sem passar o indice, verifica se algum item da lista contem chave e valor determinado
    And match response contains deep {"usuarios":[{"nome": "Fulano da Silva"}]}
    And match each response.usuarios contains {"password": '#notnull'}
    And match each response.usuarios contains {"password": '#present'}
    And match response.usuarios == '#array'
    And match response.usuarios[0].nome == '#string'
    And match response.quantidade == '#present'
    And assert response.quantidade >= 0
    And assert response.quantidade < 100
    And assert response.usuarios.length >= 0
    And assert response.usuarios.length <= 100






