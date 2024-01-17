//Nome da suite de teste
describe('Teste Guilda', () => {

  beforeEach(function () {
    cy.fixture('example').then(function (data) {
      this.data = data;
    })
  })
  

  //caso de testes
  it('Login com sucesso', () => 
  {
    //passos do teste
    cy.visit('https://the-internet.herokuapp.com/login')

    cy.get('#username').type('tomsmith')
    cy.get('#password').type('SuperSecretPassword!')
    cy.get('[type="submit"]').click()
    cy.get('h2').should('contain','Secure Area')
    //outra forma de se validar
    cy.get('h2').should('be.visible')    
  })

  it('Validando checkbox marcado', () => 
  {
    cy.visit('https://the-internet.herokuapp.com/checkboxes')

    cy.get('#checkboxes > :nth-child(3)').should('be.checked')
  })

  it('Validando checkbox não marcado', () => 
  {    
    cy.visit('https://the-internet.herokuapp.com/checkboxes')

    cy.get('#checkboxes > :nth-child(1)').should('not.be.checked')
  })
  
  it('Validando elemento hiden',() =>
  {
    cy.visit('https://the-internet.herokuapp.com/dynamic_loading/1')
    cy.get("button:contains('Start')").click()
    cy.get('#finish > h4').should('be.visible') 
  })

  it('Utilizando then', () =>
  {
    /*
    O código comentado, segundo a documentação do Cypress, não é recomendado
        const button = cy.get('button')
        button.click()
    */

    cy.visit('https://the-internet.herokuapp.com/dynamic_loading/1')
    cy.get("button:contains('Start')").click()
    cy.get('#finish > h4').should('be.visible').then( ($txt) =>
    {
        expect($txt.text()).to.eq("Hello World!")
    })
  })

  it('Utilizando base url',() =>
  {
    cy.visit('/key_presses')
  })

  it('Teste debug e log', () => 
  {
    cy.visit('https://the-internet.herokuapp.com/login')

    //a cada debug, utilizando a interface do cypress, 
    //ele irá pausar ate que o usuário continue a operação
    //mas para funcionar o devtools deve estar aberto
    
    cy.get('#username').type('tomsmith').debug()
    cy.get('#password').type('SuperSecretPassword!').debug()
    cy.get('[type="submit"]').click().debug()
    cy.get('h2').should('contain','Secure Area').debug()  
    //log no console da interface do cypress e no console do navegador
    cy.log("Testando log do cypress")
  })

  it('Teste que falha', () => 
  {
    cy.visit('https://the-internet.herokuapp.com/login')

    cy.get('#username').type('tomsmith')
    cy.get('#password').type('SuperSecretPassword!')
    cy.get('[type="submit"]').click()
    cy.get('h2').should('contain','Secure Area falhando')  
  })

  it('Utilizando fixtures', () => 
  {
    cy.fixture('example').then((dataTest) => {
      cy.visit('https://the-internet.herokuapp.com/login')
      cy.get('#username').type(dataTest.email)
    })    
  })

  // a diferença está no function() 
  // se não o teste não vai enxergar o this.data
  it('Utilizando fixtures 2', function ()
  {
    cy.visit('https://the-internet.herokuapp.com/login')
      cy.get('#username').type(this.data.email)
  })

  it('Utilizando Command', () => 
  {
    cy.visit('https://the-internet.herokuapp.com/login')
      cy.clickCommand('[type="submit"]');
  })
  

  


})