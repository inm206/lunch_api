package com.rezdy.lunch_api;

import java.util.ArrayList;
import java.util.List;

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
