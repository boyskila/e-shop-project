package com.team.eshop.shoptest;

import com.team.eshop.athentification.*;
import com.team.eshop.files.Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;

import com.team.eshop.files.ShoppingCart;
import com.team.eshop.gui.Gui;
import com.team.eshop.products.*;

public class TheShop {
    private static List<Product> pantsList = new ArrayList<Product>();
    private static List<Product> shoes = new ArrayList<Product>();
    private static List<Product> jackets = new ArrayList<Product>();
    private static List<User> admins = new ArrayList<User>();
    // private static List<Product> cart = new ArrayList<Product>();
    private static Map<String, List<Product>> allProducts = new HashMap<String, List<Product>>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Files f = Files.getInstance();
        File f1 = new File("adminList.srz");
        if (f1.exists()) {

            admins = f.deserialize("adminList.srz");
        } else {

            Admin boyko = new Admin("Boyko", "Lalov", "boko", "koko");
            Admin iva = new Admin("Iva", "Krusteva", "iva", "ivet");
            Admin mincho = new Admin("Mincho", "Petkov", "batmin", "min");

            getAdmins().add(boyko);
            getAdmins().add(iva);
            getAdmins().add(mincho);
            f.saveFile(getAdmins(), "adminList.srz");
        }

        File f2 = new File("pants.srz");
        if (f2.exists()) {

            pantsList = f.deserialize("pants.srz");

        } else {
            Pants e9Crazy = new Pants("p1", "E9", "Crazy", "Orange", "30", 99.00, 899.00, 0, false, 0, 0);
            Pants e9Rondo = new Pants("p2", "E9", "Rondo VS", "Yellow-Gray", "29", 159.99, 1499.00, 65, false, 0, 0);
            Pants abkOldstone = new Pants("p3", "ABK", "Oldstone Automne", "Brown", "28", 150.99, 1399.00, 50, false,
                    0, 0);
            Pants abkDenim = new Pants("p4", "ABK", "Yoda denim", "blue", "28", 109.99, 999.00, 20, false, 0, 0);

            Pants mammut = new Pants("p5", "Mammut", "Realization", "Black", "32", 99.99, 899.99, 350, false, 0, 0);

            Pants patagonia = new Pants("p6", "Patagonia", "Quandary", "Gray", "32", 99.00, 899.00, 30, false, 0, 0);

            getPantsList().add(e9Crazy);
            getPantsList().add(e9Rondo);
            getPantsList().add(abkOldstone);
            getPantsList().add(abkDenim);
            getPantsList().add(mammut);
            getPantsList().add(patagonia);
            f.saveFile(getPantsList(), "pants.srz");
        }

        File f3 = new File("shoes.srz");
        if (f3.exists()) {

            shoes = f.deserialize("shoes.srz");

        } else {
            Shoes scarpaRapid = new Shoes("s1", "Scarpa", "Rapid", "Black-orange", "45", 159.00, 999.00, 15, false, 0,
                    0);
            Shoes scarpaMojito = new Shoes("s2", "Scarpa", "Mojito Patchwork", "rainbow", "40", 179.00, 1499.00, 20,
                    false, 0, 0);
            Shoes scarpaMargarita = new Shoes("s3", "Scarpa", "Margarita GTX", "Gray-Pink", "39", 139.00, 1199.00, 25,
                    false, 0, 0);
            Shoes scarpaGame = new Shoes("s4", "Scarpa", "Game", "Blue", "43", 169.00, 1299.00, 15, false, 0, 0);
            Shoes scarpaBoostic = new Shoes("s5", "Scarpa", "Boostic", "Black-orange", "41", 159.00, 1099.00, 35,
                    false, 0, 0);
            Shoes scarpaCanvas = new Shoes("s6", "Scarpa", "Visual Canvas", "Lip Gloss", "39", 149.00, 1299.00, 25,
                    false, 0, 0);

            getShoes().add(scarpaRapid);
            getShoes().add(scarpaMojito);
            getShoes().add(scarpaMargarita);
            getShoes().add(scarpaGame);
            getShoes().add(scarpaBoostic);
            getShoes().add(scarpaCanvas);
            f.saveFile(getShoes(), "shoes.srz");
        }

        File f4 = new File("jackets.srz");
        if (f4.exists()) {

            jackets = f.deserialize("jackets.srz");

        } else {

            Jackets bdBelayParka = new Jackets("j1", "Black Diamond", "Belay Parka", "Green", "XL", 259.00, 2299.00,
                    50, false, 0, 0);
            Jackets bdAlpineParka = new Jackets("j2", "Black Diamond", "Alpine Parka", "Yellow", "L", 359.00, 3299.00,
                    30, false, 0, 0);
            Jackets patagonia = new Jackets("j3", "Patagonia", "W'S", "Green", "XS", 459.00, 4299.00, 20, false, 0, 0);
            Jackets directAlpine = new Jackets("j4", "Direct Alpine", "Guide", "Blue - green", "XL", 379.00, 2599.00,
                    50, false, 0, 0);
            Jackets bdPointShell = new Jackets("j5", "Black Diamond", "Point Shell", "Orange", "M", 429.00, 4099.00,
                    50, false, 0, 0);
            Jackets bdHibridHoody = new Jackets("j6", "Black Diamond", "Hibrid Hoody", "Red", "XL", 439.00, 4099.00,
                    35, false, 0, 0);
            Jackets northFaceGatebreak = new Jackets("j7", "North Face", "Gatebreak Down Jacket", "Shiny Red", "XXL",
                    499.00, 4500.00, 22, false, 0, 0);
            Jackets mammutParinaco = new Jackets("j8", "Mammut", "Parinaco Jacket", "Orange", "XL", 359.00, 3500.00,
                    20, false, 0, 0);
            Jackets norronaLofoten = new Jackets("j9", "Norrona", "Lofoten", "Orange", "L", 599.00, 5500.00, 20, false,
                    0, 0);

            getJackets().add(bdBelayParka);
            getJackets().add(bdAlpineParka);
            getJackets().add(patagonia);
            getJackets().add(directAlpine);
            getJackets().add(bdPointShell);
            getJackets().add(bdHibridHoody);
            getJackets().add(northFaceGatebreak);
            getJackets().add(mammutParinaco);
            getJackets().add(norronaLofoten);
            f.saveFile(getJackets(), "jackets.srz");
        }
        getAllProducts().put("Pants", pantsList);
        getAllProducts().put("Shoes", shoes);
        getAllProducts().put("Jackets", jackets);

        new Gui();
    }

    public static List<User> getAdmins() {
        return admins;
    }

    public static void setAdmins(List<User> adminList) {
        TheShop.admins = adminList;
    }

    public static List<Product> getPantsList() {
        return pantsList;
    }

    public static void setPantsList(List<Product> pantsList) {
        TheShop.pantsList = pantsList;
    }

    public static List<Product> getShoes() {
        return shoes;
    }

    public static void setShoes(List<Product> shoes) {
        TheShop.shoes = shoes;
    }

    public static List<Product> getJackets() {
        return jackets;
    }

    public static void setJackets(List<Product> jackets) {
        TheShop.jackets = jackets;
    }

    public static Map<String, List<Product>> getAllProducts() {
        return allProducts;
    }

    public static void setAllProducts(Map<String, List<Product>> allProducts) {
        TheShop.allProducts = allProducts;
    }

}
