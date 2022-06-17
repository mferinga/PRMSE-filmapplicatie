package com.joost.filmapplicatie.Domain;

public class Actor {

    private int id;
    private String name;
    private String imageURL;
    private String character;

    public Actor(String name) {
        this.name = name;
    }

    public Actor(int id, String name, String imageURL, String character) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.character = character;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getCharacter() {
        return character;
    }
}
