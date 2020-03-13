package com.rezdy.lunch_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class MainController {
    private List<Recipe> recipes;
    private List<Recipe> possibleRecipes;
    private List<Ingredient> ingredients;
    private List<String> safeIngredients;
    private List<String> notBestIngredients;

    private List<Recipe> bestBeforeFlag;

    private static final Logger log = LoggerFactory.getLogger(LunchApiApplication.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            RecipeList response = restTemplate.getForObject("http://www.mocky.io/v2/5c85f7a1340000e50f89bd6c", RecipeList.class);
            this.recipes = response.getRecipes();
//            log.info(recipes.toString());

            IngredientList response1 = restTemplate.getForObject("http://www.mocky.io/v2/5e69584b2f00002eded8b40e", IngredientList.class);
            this.ingredients = response1.getIngredients();
//            log.info(ingredients.toString());
            Date todayDate = new Date();
//            DateTimeComparator dateTimeComparator = DateTimeComparator.getDateOnlyInstance();
            Predicate<Ingredient> byUseBy = ingredient -> ingredient.getUseBy().compareTo(todayDate) == 1;
            Predicate<Ingredient> byBestBefore = ingredient -> ingredient.getBestBefore().compareTo(todayDate) == -1;
            this.safeIngredients = ingredients.stream().filter(byUseBy).map(p-> p.getTitle()).collect(Collectors.toList());
            this.notBestIngredients = ingredients.stream().filter(byUseBy).filter(byBestBefore).map(p-> p.getTitle()).collect(Collectors.toList());
//            log.info(safeIngredients.toString());
            log.info(notBestIngredients.toString());

            Predicate<Recipe> byAvailable = recipe -> safeIngredients.containsAll(recipe.getIngredients());
            Predicate<Recipe> byBestBeforeFlag = recipe -> !Collections.disjoint(recipe.getIngredients(), notBestIngredients);
            this.possibleRecipes = recipes.stream().filter(byAvailable).collect(Collectors.toList());
            log.info(possibleRecipes.toString());

            this.bestBeforeFlag = this.possibleRecipes.stream().filter(byBestBeforeFlag).collect(Collectors.toList());
            Collections.sort(this.possibleRecipes, Comparator.comparing(recipe -> bestBeforeFlag.contains(recipe)));
            log.info(possibleRecipes.toString());
        };
    }

    @RequestMapping("/")
    public String index() {
        return "Got spring functional";
    }

    @GetMapping("/lunch")
    public List<Recipe> lunch() {
        return this.possibleRecipes;
    }

}
