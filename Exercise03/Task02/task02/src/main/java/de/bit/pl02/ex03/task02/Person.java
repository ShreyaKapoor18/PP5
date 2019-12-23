package de.bit.pl02.ex03.task02;


public class Person {
    private double mass;
    private double height;
   
    public Person(double mass, double height) {
        this.mass = mass;
        this.height = height;
    }
   
    public double getMass() {
        return mass;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
}
