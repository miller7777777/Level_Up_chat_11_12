package ru.levelp.chatlevelup_11_12;


public class User {

    private String id;
    private String name;
    private String description;
    private String image;

    public User(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
