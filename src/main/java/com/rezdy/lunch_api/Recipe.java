package com.rezdy.lunch_api;

import java.util.List;

public class Recipe {

  private String title;
  private List<String> ingredients;

  public Recipe(String title, List<String> ingredients) {
    this.title = title;
    this.ingredients = ingredients;
  }

  public String getTitle() {
    return title;
  }

  public List<String> getIngredients() {
    return ingredients;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setIngredients(List<String> ingredients) {
    this.ingredients = ingredients;
  }

  @Override
  public String toString() {
    return "Recipe{" + "title='" + title + '\'' + ", ingredients=" + ingredients + '}';
  }
}
