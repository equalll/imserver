package com.cniao5.model;

import java.io.Serializable;

public class Tag implements Serializable {

    private static final long serialVersionUID = -7566019377630564855L;

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
