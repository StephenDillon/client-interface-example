package com.yodes.rest;

import java.util.List;

import io.swagger.annotations.ApiOperation;

public interface ListApi<T> {

	@ApiOperation(value = "list resource", nickname = "list resource")
	List<T> list();

}
