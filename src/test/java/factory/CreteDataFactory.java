package factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.Create;

public class CreteDataFactory {
	 public static Create criarUsuario() throws IOException {
	        ObjectMapper objectMapper = new ObjectMapper(); 
	        Create create = objectMapper.readValue(new FileInputStream("src/test/resources/requestBody/create.json"), Create.class);
	        return create;
	    }

	    public static Create criarUsuarioComSucesso() throws IOException {
	        return criarUsuario();
	    }

	    public static Create criarUsuarioSemName() throws IOException {
	        Create createUsuarioSemNome = criarUsuario();
	        createUsuarioSemNome.setFirst_name(" "); 
	        return createUsuarioSemNome;
	    }

	   
}
