package id.ac.umn.mauricemarvin_00000033942_if570l_c_uts.model;

import java.util.ArrayList;

public class Sfx {
    private int id;
    private String name;
    private String category;

    public Sfx(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getId(){
        return id;
    }
}
