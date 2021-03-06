package com.example.javaegypitanidinfoextractor;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void EgyptianIDControllerShouldReturnExtractedInfoFromAValidEgyptianID() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/extract-info-from-id/?egyptianIDNumber=29709040103299",
				String.class)).contains("Cairo");
	}
	
	@Test
	public void EgyptianIDControllerShouldNotReturnExtractedInfoFromAValidEgyptianID() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/extract-info-from-id/?egyptianIDNumber=2970904010329910",
				String.class)).contains("Invalid Egyptian ID Number");
	}
}