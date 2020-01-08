package com.example.getstoredatarest;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String imageFileName;
  private String metaFileName;

  public String getImageFileName() {
    return imageFileName;
  }

  public void setImageFileName(String imageFileName) {
    this.imageFileName = imageFileName;
  }

  public String getMetaFileName() {
    return metaFileName;
  }

  public void setMetaFileName(String metaFileName) {
    this.metaFileName = metaFileName;
  }
}
