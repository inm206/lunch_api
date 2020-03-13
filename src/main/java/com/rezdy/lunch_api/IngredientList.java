package com.rezdy.lunch_api;

import java.util.ArrayList;
import java.util.List;

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
