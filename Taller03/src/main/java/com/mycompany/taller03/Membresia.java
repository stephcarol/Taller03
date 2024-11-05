/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller03;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scabezas
 */
public class Membresia {
    String nombre;
    double costoBase;
    boolean esPremium;
    List<Caracteristica> caracteristicasExtras;

    public Membresia(String nombre, double costoBase, boolean esPremium) {
        this.nombre = nombre;
        this.costoBase = costoBase;
        this.esPremium = esPremium;
        this.caracteristicasExtras = new ArrayList<>();
    }

    public double calcularCostoTotal() {
        double costoTotal = costoBase;
        for (Caracteristica caracteristica : caracteristicasExtras) {
            costoTotal += caracteristica.costo;
        }

        // Aplica un recargo del 15% si es premium
        if (esPremium) {
            costoTotal += costoTotal * 0.15;
        }

        return costoTotal;
    }

    public void agregarCaracteristica(Caracteristica caracteristica) {
        caracteristicasExtras.add(caracteristica);
    }
}
