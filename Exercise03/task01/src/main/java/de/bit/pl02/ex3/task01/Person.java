package de.bit.pl02.ex3.task01;

import java.io.Serializable;

public class Person implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected  float mass; 
    protected   float height;
	public float getMass() {
		return mass;
	}
	public void setMass(float mass) {
		this.mass = mass;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	} 
    public static void main(String[] args)
    { 
    	
    }
}
