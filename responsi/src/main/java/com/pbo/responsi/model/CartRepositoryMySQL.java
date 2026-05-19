/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbo.responsi.model;

import com.pbo.responsi.config.ConnectionDb;
import com.pbo.responsi.dto.CartItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lab Informatika
 */
public class CartRepositoryMySQL implements CartRepository{
    public CartRepositoryMySQL(){
        initTable();
    }
    private void initTable(){
        String ddl = "CREATE TABLE IF NOT EXIST cart_items("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(255) NOT NULL UNIQUE,"
                + "price DOUBLE NOT NULL"
                + "quantity INT NOT NULL"
                + ")";
        try(Connection conn = ConnectionDb.getConnection();
        Statement stmt = conn.createStatement()){
            stmt.execute(ddl);
        } catch (SQLException e){
            throw new RuntimeException("Gagal inisialisasi table " + e.getMessage());
        }
    }

    @Override
    public List<CartItemDTO> findAll() {
        List<CartItemDTO> result = new ArrayList<>();
        String sql = "SELECT name, price, quantity FROM cart_items";
        try(Connection conn = ConnectionDb.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                result.add(new CartItemDTO(
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("quantity")
                ));
            }
        } catch (SQLException e){
            throw new RuntimeException("Gagal mengambil data: " + e.getMessage());
        }
        return result;
    }

    @Override
    public void save(CartItemDTO item) {
        String sql = "INSERT INTO cart_items(name, price, quantity) VALUES(?, ?, ?";
        try(Connection conn = ConnectionDb.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Gagal menyimpan item:  " + e.getMessage());
        }
    }

    @Override
    public void updateQuantity(String name, int newQty) {
        String sql = "UPDATE cart_items SET quantity = ? WHERE name = ?";
        try(Connection conn = ConnectionDb.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, newQty);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Gagal update quantity: " + e.getMessage());
        }
    }

    @Override
    public void delete(String name) {
        String sql = "DELETE FROM cart_items WHERE name = ?";
        try(Connection conn = ConnectionDb.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Gagal MENGHAPUS ITEM: " + e.getMessage());
        }
    }
    
}
