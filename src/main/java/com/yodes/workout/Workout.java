package com.yodes.workout;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class Workout {

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "The duration of the workout", required = true)
	private String duration;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "The entensity of the workout", required = true)
	private String entensity;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "Any comments on the workout", required = true)
	private String comments;

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the entensity
	 */
	public String getEntensity() {
		return entensity;
	}

	/**
	 * @param entensity
	 *            the entensity to set
	 */
	public void setEntensity(String entensity) {
		this.entensity = entensity;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

}
