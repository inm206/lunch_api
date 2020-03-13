package com.rezdy.lunchapi;

import java.util.ArrayList;
import java.util.List;

// This is a wrapper class to handle the top level object containing the list of ingredients in API
// response
public class IngredientList {

  private List<Ingredient> ingredients;

  public IngredientList() {
    ingredients = new ArrayList<>();
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

}
