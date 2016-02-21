package com.yodes.rest.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@SuppressWarnings("rawtypes")
public class ClientInvocationHandler implements InvocationHandler {

	private GenericClient genericClient;

	public ClientInvocationHandler(GenericClient genericClient) {
		this.genericClient = genericClient;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(genericClient, args);
	}

}
