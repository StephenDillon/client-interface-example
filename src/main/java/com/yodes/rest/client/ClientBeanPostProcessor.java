package com.yodes.rest.client;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import com.yodes.rest.RestApi;

@Component
public class ClientBeanPostProcessor implements BeanFactoryPostProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientBeanPostProcessor.class);

	private static boolean ranAlready = false;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		LOGGER.info("Bean factory");
		if (!ranAlready) {

			Map<String, Object> beans = beanFactory.getBeansWithAnnotation(ComponentScan.class);
			List<String> packages = new ArrayList<>();
			packages.add("com.yodes.workout");
			for (String bean : beans.keySet()) {
				Object beanObject = beans.get(bean);
				String classSimpleName = beanObject.getClass().getName();
				if (classSimpleName.contains("$$EnhancerBySpring")) {
					classSimpleName = classSimpleName.split("\\$\\$")[0];
				}
				try {
					Class<?> cls = Class.forName(classSimpleName);
					ComponentScan componentClass = (ComponentScan) cls.getAnnotation(ComponentScan.class);
					if (componentClass != null) {
						String[] packagesComponents = componentClass.value();
						if (packagesComponents != null) {
							for (String basePackage : componentClass.value()) {
								if (!packages.contains(basePackage)) {
									packages.add(basePackage);
								}
							}
						}
					}

				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}

			}

			ClassPathScanningCandidateComponentProvider scanner = new InterfaceClassScanning(false);

			scanner.addIncludeFilter(new AnnotationTypeFilter(RestApi.class));
			for (String scanPackage : packages) {
				for (BeanDefinition bd : scanner.findCandidateComponents(scanPackage)) {
					try {
						Class interfaceClass = ClassLoader.getSystemClassLoader().loadClass(bd.getBeanClassName());
						RestApi restApi = (RestApi) interfaceClass.getAnnotation(RestApi.class);
						GenericClient genericClient = new GenericClient(restApi.service(), restApi.mapping());

						LOGGER.info("Creating proxy for " + interfaceClass);
						Object proxy = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass }, new ClientInvocationHandler(genericClient));
						LOGGER.info("Creating client named " + interfaceClass.getSimpleName() + "Client");
						beanFactory.registerSingleton(interfaceClass.getSimpleName() + "Client", proxy);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		ranAlready = true;
	}

}
