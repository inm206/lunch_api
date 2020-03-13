package com.rezdy.lunch_api;


import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LunchApiApplication {

	private static final Logger log = LoggerFactory.getLogger(LunchApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LunchApiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
//
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			RecipeList response = restTemplate.getForObject("http://www.mocky.io/v2/5c85f7a1340000e50f89bd6c", RecipeList.class);
			List<Recipe> recipes = response.getRecipes();
			log.info(recipes.toString());

			IngredientList response1 = restTemplate.getForObject("http://www.mocky.io/v2/5e69584b2f00002eded8b40e", IngredientList.class);
			List<Ingredient> ingredients = response1.getIngredients();
			log.info(ingredients.toString());
		};
	}

}
