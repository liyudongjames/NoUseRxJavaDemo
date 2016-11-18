package com.liyudong.home.myrxjava;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */

public class Marvel {
    private String name;
    private List<String> abilities;

    public Marvel(String name, List<String> abilities) {
        this.name = name;
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }
}
