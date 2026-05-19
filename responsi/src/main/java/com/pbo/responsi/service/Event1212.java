/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbo.responsi.service;

/**
 *
 * @author Lab Informatika
 */
public class Event1212 implements DiscountStrategy {
    private final double DISCOUNT_RATE = 0.12;
    
    @Override
    public double calculateDiscount(double totalAmount) {
        return totalAmount * DISCOUNT_RATE;
    }

    @Override
    public String getDiscountName() {
        return "Event 12.12 (Diskon 12%)";
    }
    
}
