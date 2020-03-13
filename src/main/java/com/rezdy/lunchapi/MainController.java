package com.rezdy.lunchapi;

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

// This is the main controller class to provide acceptance criteria functionality
@RestController
public class MainController {
    private List<Recipe> recipes;
    private List<Recipe> possibleRecipes;
    private List<Ingredient> ingredients;
    private List<String> safeIngredients;
    private List<String> notBestIngredients;

    private List<Recipe> bestBeforeFlag;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    //  This CommandLineRunner function handles API data fetch for available ingredients and recipes
    //  as well as filtering of ingredients past use-by date, and flagging of ingredients past
    //  best-before date to sort final recipes sent back to client
    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            // Current date to compare use-by and best-before dates against
            Date todayDate = new Date();

            // Predicates for filtering
            Predicate<Ingredient> byUseBy =
                ingredient -> ingredient.getUseBy().compareTo(todayDate) == 1;
            Predicate<Ingredient> byBestBefore =
                ingredient -> ingredient.getBestBefore().compareTo(todayDate) == -1;
            Predicate<Recipe> byAvailable = recipe -> safeIngredients
                .containsAll(recipe.getIngredients());
            Predicate<Recipe> byBestBeforeFlag = recipe -> !Collections
                .disjoint(recipe.getIngredients(), notBestIngredients);

            // GET request for recipes from API endpoint
            RecipeList response = restTemplate.getForObject("http://www.mocky" +
                ".io/v2/5c85f7a1340000e50f89bd6c", RecipeList.class);
            this.recipes = response.getRecipes();

            // GET request for ingredients from API endpoint
            IngredientList response1 = restTemplate.getForObject("http://www.mocky" +
                ".io/v2/5e69584b2f00002eded8b40e", IngredientList.class);
            this.ingredients = response1.getIngredients();

            this.safeIngredients =
                ingredients.stream().filter(byUseBy).map(p-> p.getTitle())
                    .collect(Collectors.toList());
            this.notBestIngredients =
                ingredients.stream().filter(byUseBy).filter(byBestBefore).map(p-> p.getTitle())
                    .collect(Collectors.toList());
            this.possibleRecipes = recipes.stream().filter(byAvailable)
                .collect(Collectors.toList());
            this.bestBeforeFlag = this.possibleRecipes.stream().filter(byBestBeforeFlag)
                .collect(Collectors.toList());

            Collections.sort(this.possibleRecipes, Comparator.comparing(recipe -> bestBeforeFlag
                .contains(recipe)));
        };
    }

    @RequestMapping("/")
    public String index() {
        return "To get lunch recipes, make a GET request to /lunch instead";
    }

    @GetMapping("/lunch")
    public List<Recipe> lunch() {
        return this.possibleRecipes;
    }

}
