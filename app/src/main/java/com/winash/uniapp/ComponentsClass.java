package com.winash.uniapp;

public class ComponentsClass implements Cloneable {
    public String name,percentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPercentage() {
        return percentage;
    }

    public ComponentsClass() {
        this.name="";
        this.percentage="";
    }

    public ComponentsClass(String name, String percentage) {
        this.name=name;
        this.percentage=percentage;
    }
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}
