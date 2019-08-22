/**
 * 
 */
package com.ing.tp.controller;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 278684
 *
 */

@RestController
public class ProviderController {

	private static final String PIVOTAL_ENDPOINT = "PWC";
	private static final String PIVOTAL_ENDPOINT_URL = "https://api.run.pivotal.io/v2/info";
	private static final String BLUEMIX_ENDPOINT = "BLU";
	private static final String BLUEMIX_ENDPOINT_URL = "https://api.ng.bluemix.net/v2/info";
	private static final long TIMEOUT_IN_SECONDS = 3;
	private static Map<String, String> providerLocation;


	@PostConstruct
	private void init() {
		providerLocation = new HashMap<>();
		providerLocation.put(PIVOTAL_ENDPOINT, PIVOTAL_ENDPOINT_URL);
		providerLocation.put(BLUEMIX_ENDPOINT, BLUEMIX_ENDPOINT_URL);
	}

	@GetMapping(value = "/cf")
	public String getInfo() {

		return "";
	}

	@GetMapping(value = "/cf/{provider}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getInfo(@PathVariable String provider) throws UnsupportedProviderException {

		final String endpointUrl = providerLocation.get(provider);
		if (null == endpointUrl) {
			throw new UnsupportedProviderException("Provider " + provider + " is not supported.");
		}

		RestTemplate restTemplate = new RestTemplate();// restBuilder.rootUri(endpointUrl).setReadTimeout(Duration.ofSeconds(TIMEOUT_IN_SECONDS)).build();
		String apiResponse = restTemplate.getForEntity(endpointUrl, String.class).getBody();

		return apiResponse;
	}

}
