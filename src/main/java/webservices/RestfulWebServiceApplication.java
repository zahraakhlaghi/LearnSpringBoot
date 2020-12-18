package webservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@SpringBootApplication
public class RestfulWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServiceApplication.class, args);
	}


	@Bean
	public AcceptHeaderLocaleResolver localeResolver() {
	   AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
	   localeResolver.setDefaultLocale(Locale.ENGLISH);
	   return localeResolver;
	}
	

}
