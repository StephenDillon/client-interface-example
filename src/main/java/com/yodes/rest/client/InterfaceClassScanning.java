package com.yodes.rest.client;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

public class InterfaceClassScanning extends ClassPathScanningCandidateComponentProvider {

	public InterfaceClassScanning(boolean useDefaultFilters) {
		super(useDefaultFilters);
	}

	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return true;
	}

}
