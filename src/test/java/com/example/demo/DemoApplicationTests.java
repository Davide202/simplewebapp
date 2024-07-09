package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

	TestRestTemplate testRestTemplate = new TestRestTemplate();

	@Autowired
	ObjectMapper mapper;

	@PostConstruct
	void init(){
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	@Test
	void actuator() throws JsonProcessingException, JSONException {
		ResponseEntity<String> response = testRestTemplate.
				getForEntity( "http://localhost:8080/actuator", String.class);

		Object body = mapper.convertValue(response.getBody(),new TypeReference<>(){});
		//System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body));


		JsonNode node;
		node = mapper.valueToTree(response.getBody());
		//System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node));
		node = mapper.convertValue(response.getBody(), JsonNode.class);

		//System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node));
		node = mapper.readTree(response.getBody().toString());
		String href = node.path("_links").path("self").path("href").asText();
		//System.out.println(href);

		System.out.println( mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node) );

		JSONObject jsonObject = new JSONObject(response.getBody().toString());
		//System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject.toString()));


		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void parseDAte() throws ParseException {
		String dateasstring = "01/01/2009";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(dateasstring);
		System.out.println(date);
	}

}
