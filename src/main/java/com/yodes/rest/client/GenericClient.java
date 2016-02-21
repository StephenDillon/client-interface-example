package com.yodes.rest.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.yodes.rest.ListApi;
import com.yodes.workoutclient.WorkoutClientController;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class GenericClient<T> implements ListApi<T> {

	private Logger LOGGER = LoggerFactory.getLogger(WorkoutClientController.class);

	private String service, mapping;

	public GenericClient(String service, String mapping) {
		this.service = service;
		this.mapping = mapping;
	}

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<T> list() {
		LOGGER.debug("Debug request for list");
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(service + mapping, List.class);
		return responseEntity.getBody();
	}
}
