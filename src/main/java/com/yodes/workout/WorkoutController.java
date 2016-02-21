package com.yodes.workout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkoutController implements WorkoutApi {

	@Autowired
	private WorkoutService workoutService;

	@Override
	@RequestMapping(value = "/workouts", method = RequestMethod.GET, produces = "application/json")
	public List<Workout> list() {
		return workoutService.list();
	}

}
