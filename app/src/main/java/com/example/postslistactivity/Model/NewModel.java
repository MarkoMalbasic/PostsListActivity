package com.example.postslistactivity.Model;

public class NewModel {

    public String title, explanation, description;
    public byte[] image;

    public NewModel() {
    }

    public NewModel(String title, byte[] image, String explanation, String description) {
        this.title = title;
        this.image = image;
        this.explanation = explanation;
        this.description = description;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
