package com.yodes.workout;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class WorkoutService {

	public List<Workout> list() {
		List<Workout> workouts = new ArrayList<>();
		workouts.add(create("medium", "burpees, yuk!", "forever"));
		workouts.add(create("short", "omg pushups!!", "tabata"));
		workouts.add(create("long", "watch out for traffic!", "too long"));
		return workouts;
	}

	private Workout create(String entensity, String comments, String duration) {
		Workout workout = new Workout();
		workout.setEntensity(entensity);
		workout.setComments(comments);
		workout.setDuration(duration);
		return workout;
	}

}
