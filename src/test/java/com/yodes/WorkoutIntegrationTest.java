package com.yodes;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.yodes.workout.Workout;
import com.yodes.workout.WorkoutApi;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ClientInterfaceExampleApplication.class)
@WebAppConfiguration
public class WorkoutIntegrationTest {

	@Autowired
	@Qualifier("WorkoutApiClient")
	private WorkoutApi workoutApi;

	@Test
	public void testClientIntegration() {
		List<Workout> workouts = workoutApi.list();
		TestCase.assertEquals(3, workouts.size());
	}

}
