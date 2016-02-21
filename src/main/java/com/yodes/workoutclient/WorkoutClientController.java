package com.yodes.workoutclient;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yodes.workout.Workout;
import com.yodes.workout.WorkoutApi;

import io.swagger.annotations.ApiOperation;

/**
 * Rest endpoint to try out dynamic client
 */
@RestController
public class WorkoutClientController {

	private Logger LOGGER = LoggerFactory.getLogger(WorkoutClientController.class);

	@Autowired
	@Qualifier("WorkoutApiClient")
	private WorkoutApi workoutApi;

	@ApiOperation(value = "list workouts using clients", nickname = "list workouts using clients")
	@RequestMapping(value = "/workoutclient", method = RequestMethod.GET, produces = "application/json")
	public List<Workout> list() {
		LOGGER.info("Received request to get workouts via the client");
		return workoutApi.list();
	}

}
