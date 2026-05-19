/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbo.responsi.controller;

import com.pbo.responsi.dto.CartItemDTO;
import com.pbo.responsi.service.CartService;
import com.pbo.responsi.view.CartView;
import java.util.List;

/**
 *
 * @author Lab Informatika
 */
public class CartController {
    private final CartService service;
    private final CartView view;
    
    public CartController(CartService service, CartView view){
        this.service = service;
        this.view = view;
        initListeners();
        refreshView();
        
    }

    private void initListeners() {
        view.onAdd(e -> handleAdd());
        view.onUpdate(e -> handleUpdate());
        view.onDelete(e -> handleDelete());
        view.onTableSelect(e -> handleTableSelect());
    }

    private void refreshView() {
        List<CartItemDTO> items = service.getAllItems();
        double subtotal = service.calculateSubTotal(items);
        double discount = service.calculateDiscount(subtotal);
        double total = service.calculateTotal(subtotal, discount);
        view.showCartItems(items, subtotal, discount, total, service.getDiscountName());
    }

    private void handleAdd() {
        String name= view.getNameInput().trim();
        String priceStr= view.getPriceInput().trim();
        String qtyStr= view.getQtyInput().trim();
        
        if (name.isEmpty() || priceStr.isEmpty() || qtyStr.isEmpty()) {
            view.showMessage("Field harus di isi");
            return;
        }
        try {
            double price = Double.parseDouble(priceStr);
            int qty = Integer.parseInt(qtyStr);
            refreshView();
            view.clearForm();
        } catch (NumberFormatException e) {
            view.showMessage("Harga dan quantity harus berupa angka.");
        } catch (RuntimeException e){
            view.showMessage("Gagal pilih item: " + e.getMessage());
        }
    }

    private void handleUpdate() {
        String name= view.getSelectedRowItemName();
        String qtyStr= view.getQtyInput().trim();
        
        if (name == null) {
            view.showMessage("pilih item dari tabel terlebih ahulu");
            return;
        }
        
        if (qtyStr.isEmpty()) {
            view.showMessage("isi field qty terlebih dahulu.");
            return;
        }
        try {
            double price = Double.parseDouble(qtyStr);
            int qty = Integer.parseInt(qtyStr);
            refreshView();
            view.clearForm();
        } catch (NumberFormatException e) {
            view.showMessage("Harga dan quantity harus berupa angka.");
        } catch (RuntimeException e){
            view.showMessage("Gagal pilih item: " + e.getMessage());
        }
        
        
    }

    private void handleDelete() {
        String name= view.getSelectedRowItemName();
         
        if (name == null) {
            view.showMessage("pilih item dari tabel terlebih ahulu");
            return;
        }
        try {
            service.deleteItem(name);
            refreshView();
            view.clearForm();
        } catch (RuntimeException e){
            view.showMessage("Gagal pilih item: " + e.getMessage());
        }
    }

    private void handleTableSelect() {
        String name= view.getSelectedRowItemName();
         
        if (name == null) return;
        
        List<CartItemDTO> items = service.getAllItems();
        items.stream()
                .filter(i -> i.getName().equalsIgnoreCase(name))
                .findFirst()
                .ifPresent(i -> view.setForm(i.getName(), i.getPrice(), i.getQuantity()));
                
    }
}
