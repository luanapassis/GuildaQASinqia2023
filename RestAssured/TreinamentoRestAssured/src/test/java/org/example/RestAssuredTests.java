package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.baseURI;


public class RestAssuredTests {

    @BeforeTest
    public void beforeTest()
    {
        baseURI = "https://serverest.dev";
    }

    @Test
    public void estruturaBasica()
    {
        given().
                baseUri("https://serverest.dev").
                basePath("/usuarios/{_id}").
                pathParams("_id", "00U35jMlgQQk3vEr").
                when().
                get().
                then().
                statusCode(400);
    }

    @Test
    public void semBaseUri()
    {
        //ao utilizar o baseUri no Before Test, não existe necessidade de
        //repetir no given
        //mas se for utilizado, o passado no given irá prevalecer
        given().
                //baseUri("https://serverest.dev").
                basePath("/usuarios/{_id}").
                pathParams("_id", "00U35jMlgQQk3vEr").
                when().
                get().
                then().
                statusCode(400);
    }

    @Test
    public void extraindoResponse()
    {
        //realizamos a codificação até o then e fazemos a extração do response body
        //essa extração será atribuída para a variável response do tipo Response
        //para isso foi preciso do import "import io.restassured.response.Response;"
        Response response =
                given().
                basePath("/usuarios/{_id}").
                pathParams("_id", "00U35jMlgQQk3vEr").
                when().
                get().
                then().
                extract().response();

        //Irá printar no output o response body
        System.out.println("Response body aqui >> "+response.asString());
    }

    @Test
    public void outraFormaDeExtrairResponse()
    {
        //realizamos a codificação do given e when e atribuímos o retorno
        //para a variável response do tipo Response
        //e logo após realizamos o assert
        Response response = given().
                basePath("/usuarios/{_id}").
                pathParams("_id", "00U35jMlgQQk3vEr").
                when().
                get();

        //realizando a validação do status code que está
        //contido na variável response
        response.then().statusCode(400);

        System.out.println("Response Body aqui >> \n"+response.body().asString());
        System.out.println("Status Code aqui >> \n"+response.statusCode());
        System.out.println("Headers aqui >> \n"+response.headers());
    }

    @Test
    public void utilizandoMatch()
    {
        //necessário o import "import static org.hamcrest.Matchers.*;"
        //vamos utilizar outra rota de get do Serverest

        given().baseUri("https://serverest.dev").
                basePath("/usuarios").
                when().
                get().
                then().
                statusCode(200).
                body(
                        "quantidade", equalTo(15),
                        "usuarios[0].nome", equalTo("Fulano da Silva"),
                        "usuarios[0].nome", equalToIgnoringCase("fulano da silva"),
                        "usuarios[0].nome", containsString("Silva"),
                        "usuarios", hasItem(hasEntry("email", "fulano@qa.com")),
                        "usuarios", hasSize(15),
                        "$", hasKey("quantidade"),
                        "usuarios", everyItem(hasKey("password")),
                        "quantidade", not(equalTo(0)),
                        "usuarios.password", everyItem(not(empty())),
                        "usuarios.password", everyItem(notNullValue()),
                        "usuarios[0].email", not(empty()),
                        "usuarios", hasSize(greaterThan(0)),
                        "usuarios", hasSize(lessThan(100)),
                        "usuarios", not (emptyArray())
                ).
                headers("content-type", containsString("application/json"));
    }

}
