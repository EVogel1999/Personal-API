package com.example.personal;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.HttpMethod.*;

@SpringBootApplication
public class PersonalApplication {

	private static final String ADMIN = "ADMIN";

	public static void main(String[] args) {
		SpringApplication.run(PersonalApplication.class, args);
	}

	@EnableWebMvc
	@Configuration
	public class WebConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**");
		}
	}

	@Configuration
	public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.httpBasic().and().authorizeRequests()
					.antMatchers(POST).hasRole(ADMIN)
					.antMatchers(PATCH).hasRole(ADMIN)
					.antMatchers(DELETE).hasRole(ADMIN);
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			Dotenv dotenv = Dotenv.load();

			auth.inMemoryAuthentication()
					.withUser(dotenv.get("MY_USERNAME"))
					.password(dotenv.get("MY_PASSWORD"))
					.roles("ADMIN");
		}

	}
}
