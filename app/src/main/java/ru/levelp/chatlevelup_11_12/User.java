package ru.levelp.chatlevelup_11_12;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject{
    @PrimaryKey
    private String id;
    private String name;
    private String description;
    private String image;

    public User(){

    }

    public User(String id, String name) {
        this.name = name;
        this.id = id;
    }




    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {

        return id;
    }

    public String getImage() {
        return image;
    }
}
