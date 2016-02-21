package com.yodes.workout;

import com.yodes.rest.ListApi;
import com.yodes.rest.RestApi;

@RestApi(service = "http://localhost:8080", mapping = "/workouts")
public interface WorkoutApi extends ListApi<Workout> {

}
