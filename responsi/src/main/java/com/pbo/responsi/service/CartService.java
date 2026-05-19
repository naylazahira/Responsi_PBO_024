/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbo.responsi.service;

import com.pbo.responsi.dto.CartItemDTO;
import com.pbo.responsi.model.CartRepository;
import java.util.List;

/**
 *
 * @author Lab Informatika
 */
public class CartService {
    private final CartRepository repository;
    private final DiscountStrategy discountStrategy;
    
    public CartService(CartRepository repository, DiscountStrategy discountStrategy){
        this.repository = repository;
        this.discountStrategy = discountStrategy;
    }
    
    public List<CartItemDTO> getAllItems(){
        return repository.findAll();
    }
    public void addItem (CartItemDTO item){
        repository.save(item);
    }
    public void updateQuantity(String name, int newQty){
        repository.updateQuantity(name, newQty);
        
    }
    public void deleteItem(String name) {
        repository.delete(name);
    }
    
    
    
    public double calculateSubTotal(List<CartItemDTO> items){
        double total = 0;
        for (CartItemDTO i : items){
            total += i.getPrice()* i.getQuantity();
        } return total;
    }
    
    public double calculateDiscount(double subtotal){
        return discountStrategy.calculateDiscount(subtotal);
    }
    
    public double calculateTotal(double subtotal, double discount){
        return subtotal - discount;
    }
    
    public String getDiscountName(){
        return discountStrategy.getDiscountName();
    }
}
