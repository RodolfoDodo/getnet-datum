package isolada;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;


import java.io.IOException;

import org.aeonbits.owner.ConfigFactory;
import org.junit.Before;
import org.junit.Test;

import config.Configuracoes;
import factory.CreteDataFactory;
import io.restassured.http.ContentType;
import pojo.Create;

public class CreatePostTest {

	@Before
	public void setUp() {
		Configuracoes configuracoes = ConfigFactory.create(Configuracoes.class);
		baseURI = configuracoes.baseUri();
	}

	 @Test
	    public void createPost() throws IOException {
	        Create create = CreteDataFactory.criarUsuarioComSucesso();

	        given()
	            .contentType(ContentType.JSON)
	            .body(create) // Envia o objeto Create como JSON no corpo da requisição
	        .when()
	            .post("https://reqres.in/api/users")
	        .then()
	            .log().all()
	            .statusCode(201)
	            .body("first_name", containsString("Rodolfo"))
	            .body("email", equalTo("janet.weaver@reqres.in"))
	            .body("id", notNullValue())
	            .body("createdAt", notNullValue());
	    }
	 
	 @Test
	    public void createPostSemName() throws IOException {
	        Create create = CreteDataFactory.criarUsuarioSemName();

	        given()
	            .contentType(ContentType.JSON)
	            .body(create) // Envia o objeto Create como JSON no corpo da requisição
	        .when()
	            .post("https://reqres.in/api/users")
	        .then()
	            .log().all()
	            .statusCode(201)
	            .body("first_name", equalTo(" "))
	            .body("email", equalTo("janet.weaver@reqres.in"))
	            .body("id", notNullValue())
	            .body("createdAt", notNullValue());
	    }
	 
	 @Test
	    public void createPostContrato() throws IOException {
	        Create create = CreteDataFactory.criarUsuarioComSucesso();

	        given()
	            .contentType(ContentType.JSON)
	            .body(create) // Envia o objeto Create como JSON no corpo da requisição
	        .when()
	            .post("https://reqres.in/api/users")
	        .then()
	            .log().all()
	            .statusCode(201)
	            .body(matchesJsonSchemaInClasspath("schemas/postCreateValido.json"), null);
	          
	    }
	 
	 
	 @Test
	    public void createPostSemNameContrato() throws IOException {
	        Create create = CreteDataFactory.criarUsuarioSemName();

	        given()
	            .contentType(ContentType.JSON)
	            .body(create) // Envia o objeto Create como JSON no corpo da requisição
	        .when()
	            .post("https://reqres.in/api/users")
	        .then()
	        .log().all()
            .statusCode(201)
            .body(matchesJsonSchemaInClasspath("schemas/postCreateValidoSemNome.json"), null);
	    }
	

}
