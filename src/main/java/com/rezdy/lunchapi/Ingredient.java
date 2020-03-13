package com.rezdy.lunchapi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

// Domain class to contain ingredient data
public class Ingredient {

  private String title;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @JsonProperty("best-before")
  private Date bestBefore;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @JsonProperty("use-by")
  private Date useBy;

  public Ingredient(String title, Date bestBefore, Date useBy) {
    this.title = title;
    this.bestBefore = bestBefore;
    this.useBy = useBy;
  }

  public String getTitle() {
    return title;
  }

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  public Date getBestBefore() {
    return bestBefore;
  }

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  public Date getUseBy() {
    return useBy;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setBestBefore(Date bestBefore) {
    this.bestBefore = bestBefore;
  }

  public void setUseBy(Date useBy) {
    this.useBy = useBy;
  }

  @Override
  public String toString() {
    return "Ingredient{" + "title='" + title + '\'' + ", best-before=" + bestBefore + '\'' + ", " +
        "use-by=" + useBy + '}';
  }

}
