package com.example.gem.tv;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by gem on 6/25/18.
 */

public class Movie {
  private long id;
  private String title;
  private String studio;
  private int image;
  private String imageUrl;

  public Movie() {
  }

  public Movie(long id, String title, String studio) {
    this.id = id;
    this.title = title;
    this.studio = studio;
  }

  public Movie(long id, String title, String studio, int image) {
    this.id = id;
    this.title = title;
    this.studio = studio;
    this.image = image;
  }

  public Movie(String title, String studio, String imageUrl) {
    this.title = title;
    this.studio = studio;
    this.imageUrl = imageUrl;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setStudio(String studio) {
    this.studio = studio;
  }

  public void setImage(int image) {
    this.image = image;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public int getImage() {
    return image;
  }

  public String getStudio() {
    return studio;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public URI getImageURI() {
    try {
      return new URI(getImageUrl());
    } catch (URISyntaxException e) {
      return null;
    }
  }

  @Override
  public String toString() {
    return "Movie{" +
        "id=" + id +
        ", title='" + title + '\'' +
        '}';
  }
}
