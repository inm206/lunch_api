package com.rezdy.lunchapi;

import java.util.ArrayList;
import java.util.List;

// This is a wrapper class to handle the top level object containing the list of recipes in API
// response
public class RecipeList {

  private List<Recipe> recipes;

  public RecipeList() {
    recipes = new ArrayList<>();
  }

  public List<Recipe> getRecipes() {
    return recipes;
  }

  public void setRecipes(List<Recipe> recipes) {
    this.recipes = recipes;
  }

}
