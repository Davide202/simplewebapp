package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@ActiveProfiles("integration")
class DemoApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ObjectMapper mapper;
	@Autowired
	private ServerProperties serverProperties;
	private String basePath;
	TestRestTemplate testRestTemplate;

	@PostConstruct
	void init(){
		testRestTemplate = new TestRestTemplate();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		basePath = String.format("http://localhost:%s",serverProperties.getPort());
	}

	@Test
	void actuator() throws JsonProcessingException, JSONException {
		ResponseEntity<String> response = testRestTemplate.getForEntity( basePath+"/actuator", String.class);
		assertEquals(HttpStatus.OK,response.getStatusCode() );

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
	}

	@Test
	void parseDAte() throws ParseException {
		String dateasstring = "01/01/2009";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(dateasstring);
		System.out.println(date);
	}

	@Test
	void serialization() throws IOException, ClassNotFoundException {
		File tempFile = File.createTempFile("aaaa",null);
		tempFile.deleteOnExit();

		ObjectOutput s = new ObjectOutputStream(new FileOutputStream(tempFile));
			s.writeObject("Today");
			s.writeObject(new Date());
			s.writeObject("Today2");
				ZoneId zoneId = ZoneId.systemDefault();
				LocalDate localDate = LocalDate.now().plusDays(1);
				ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
				Instant instant =  zonedDateTime.toInstant();
				OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(instant,zoneId);
				Date fromOffsetDateTime = Date.from(offsetDateTime.toInstant());
				Date tomorrow = Date.from(instant);
			s.writeObject(tomorrow);
		s.flush();

		ObjectInputStream is = new ObjectInputStream(new FileInputStream(tempFile));
			String today = (String)is.readObject();
			Date date = (Date)is.readObject();
			String today2 = (String)is.readObject();
			Date date2 = (Date)is.readObject();
		is.close();

        logger.info("\n{}  {}  \n{}  {}", today,date, today2,date2);

	}

}
