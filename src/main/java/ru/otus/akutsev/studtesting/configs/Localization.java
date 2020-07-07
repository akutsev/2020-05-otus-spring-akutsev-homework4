package ru.otus.akutsev.studtesting.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class Localization {

	private static final String PATH_TO_BUNDLES = "classpath:/il8n/bundle";
	private static final String ENCODING = "UTF-8";

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(PATH_TO_BUNDLES);
		messageSource.setDefaultEncoding(ENCODING);

		return messageSource;
	}
}
