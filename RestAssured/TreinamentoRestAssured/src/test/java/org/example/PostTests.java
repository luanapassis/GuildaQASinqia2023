package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.DTO.UsuarioDTO;
import org.example.utils.Utils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import org.json.JSONObject;

public class PostTests {

    @Test
    public void cadastrarUsuarioComString()
    {
        String email = "teste_"+Utils.gerarStringAleatoria(5)+"@gmail.com";
        String user =   "{\n" +
                        "  \"nome\": \"Fulano da Silva\",\n" +
                        "  \"email\": \""+email+"\",\n" +
                        "  \"password\": \"teste\",\n" +
                        "  \"administrador\": \"true\"\n" +
                        "}";

        Response response =
        given().
                baseUri("https://serverest.dev").
                basePath("/usuarios").
                contentType(ContentType.JSON).
                body(user).
                when().
                post().
                then().
                extract().response();

        //Irá printar no output o response body
        System.out.println("Response body aqui >> "+response.asString());
    }

    @Test
    public void cadastrarUsuarioComJsonObject()
    {
        String email = "teste_"+Utils.gerarStringAleatoria(5)+"@gmail.com";

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

        //Irá printar no output o response body
        System.out.println("Response body aqui >> "+response.asString());
    }

    @Test
    public void cadastrarUsuarioComObjeto()
    {
        String email = "teste_"+Utils.gerarStringAleatoria(5)+"@gmail.com";

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("Teste");
        usuarioDTO.setEmail(email);
        usuarioDTO.setPassword("teste");
        usuarioDTO.setAdministrador("true");

        Response response =
                given().
                        baseUri("https://serverest.dev").
                        basePath("/usuarios").
                        contentType(ContentType.JSON).
                        body(usuarioDTO).
                        when().
                        post().
                        then().
                        extract().response();

        //Irá printar no output o response body
        System.out.println("Response body aqui >> "+response.asString());
    }
}
