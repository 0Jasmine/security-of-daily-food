package com.jasmine.securityoffood.entities;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;

public class Food {
    @TableField("foodID")
    private int foodID;

    @TableField(exist = false)
    List<Product> fields;

    public int getFoodID() {
        return foodID;
    }
    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public double getEnergy() {
        return energy;
    }
    public void setEnergy(double energy) {
        this.energy = energy;
    }
    public double getProtein() {
        return protein;
    }
    public void setProtein(double protein) {
        this.protein = protein;
    }
    public double getCarbohydrate() {
        return carbohydrate;
    }
    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }
    public double getFat() {
        return fat;
    }
    public void setFat(double fat) {
        this.fat = fat;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @TableField("foodName")
    private String foodName;
    private double energy;
    private double protein;
    private double carbohydrate;
    private double fat;
    private String type;
}
