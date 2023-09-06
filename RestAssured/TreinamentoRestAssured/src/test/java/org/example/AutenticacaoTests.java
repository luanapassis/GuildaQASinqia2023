package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.utils.Utils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AutenticacaoTests {


    public String flowCadastrarUsuarioParaAutenticar()
    {
        String email = "teste_"+ Utils.gerarStringAleatoria(5)+"@gmail.com";

        JSONObject user = new JSONObject();
        user.put("nome", "teste");
        user.put("email", email);
        user.put("password", "teste");
        user.put("administrador", "true");

        Response response =
                given().
                        baseUri("https://serverest.dev").
                        basePath("/usuarios").
                        contentType(ContentType.JSON).
                        body(user.toString()).
                        when().
                        post().
                        then().
                        extract().response();

        System.out.println("Response body do cadastro aqui >> "+response.asString());
        System.out.println("Email aqui>> "+email);

        return email;
    }

    public String flowLoginParaAutenticar()
    {
        String email = flowCadastrarUsuarioParaAutenticar();

        JSONObject user = new JSONObject();
        user.put("email", "teste_q06DE@gmail.com");
        user.put("password", "teste");

        String token =
                given().
                        baseUri("https://serverest.dev").
                        basePath("/login").
                        contentType(ContentType.JSON).
                        body(user.toString()).
                        when().
                        post().
                        then().
                        extract().path("authorization");

        System.out.println("Token aqui >> "+token);

        return token;
    }

    @Test
    public void cadastrarProdutoAutenticado()
    {
        String token = flowLoginParaAutenticar();

        String nomeProduto = Utils.gerarStringAleatoria(10);

        JSONObject produto = new JSONObject();
        produto.put("nome", nomeProduto);
        produto.put("preco", 50);
        produto.put("descricao", "teste");
        produto.put("quantidade", 100);

        Response response =
                given().
                        baseUri("https://serverest.dev").
                        basePath("/produtos").
                        header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6InRlc3RlIiwiZW1haWwiOiJ0ZXN0ZV9xMDZERUBnbWFpbC5jb20iLCJpYXQiOjE2OTM5NTcyOTQsImV4cCI6MTY5Mzk1Nzg5NH0._AV-DN0xc-MPhDz0Yo1kM5lPFZlWOVZo5w2yQSG6ZlQ").
                        contentType(ContentType.JSON).
                        body(produto.toString()).
                        when().
                        post().
                        then().
                        extract().response();

        System.out.println("Response body do cadastro de produto aqui >> "+response.asString());

    }
}
