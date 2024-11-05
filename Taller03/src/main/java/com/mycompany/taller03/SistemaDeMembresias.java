/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author scabezas
 */
public class SistemaDeMembresias {
    private static List<Membresia> planesDisponibles = new ArrayList<>();
    private static List<Caracteristica> caracteristicasExtrasDisponibles = new ArrayList<>();

    public static void main(String[] args) {
        inicializarDatos();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione un plan de membresía:");
        for (int i = 0; i < planesDisponibles.size(); i++) {
            System.out.println(i + 1 + ". " + planesDisponibles.get(i).nombre + " - $" + planesDisponibles.get(i).costoBase);
        }

        int seleccionPlan = scanner.nextInt() - 1;
        if (seleccionPlan < 0 || seleccionPlan >= planesDisponibles.size()) {
            System.out.println("Selección no válida.");
            System.exit(-1);
        }

        Membresia membresiaSeleccionada = planesDisponibles.get(seleccionPlan);
        System.out.println("¿Desea agregar características adicionales? (sí/no)");
        String respuesta = scanner.next();

        if (respuesta.equalsIgnoreCase("sí")) {
            agregarCaracteristicasExtras(membresiaSeleccionada, scanner);
        }

        System.out.println("¿Cuántas personas se registrarán con esta membresía?");
        int numeroDePersonas = scanner.nextInt();

        double costoTotal = membresiaSeleccionada.calcularCostoTotal();

        // Aplicar descuentos por membresía grupal
        if (numeroDePersonas >= 2) {
            costoTotal *= 0.9; // 10% de descuento
        }

        // Aplicar descuentos especiales
        if (costoTotal > 400) {
            costoTotal -= 50;
        } else if (costoTotal > 200) {
            costoTotal -= 20;
        }

        System.out.printf("El costo total para la membresía seleccionada es: $%.2f\n", costoTotal);
        scanner.close();
    }

    private static void inicializarDatos() {
        // Planes de membresía
        planesDisponibles.add(new Membresia("Básica", 100, false));
        planesDisponibles.add(new Membresia("Premium", 200, true));
        planesDisponibles.add(new Membresia("Familiar", 150, false));

        // Características adicionales
        caracteristicasExtrasDisponibles.add(new Caracteristica("Entrenador personal", 30));
        caracteristicasExtrasDisponibles.add(new Caracteristica("Clases grupales", 20));
        caracteristicasExtrasDisponibles.add(new Caracteristica("Acceso a áreas VIP", 50));
    }

    private static void agregarCaracteristicasExtras(Membresia membresia, Scanner scanner) {
        System.out.println("Seleccione características adicionales:");
        for (int i = 0; i < caracteristicasExtrasDisponibles.size(); i++) {
            System.out.println(i + 1 + ". " + caracteristicasExtrasDisponibles.get(i).nombre + " - $" + caracteristicasExtrasDisponibles.get(i).costo);
        }

        System.out.println("Ingrese el número de la característica a añadir (0 para finalizar):");
        while (true) {
            int seleccionCaracteristica = scanner.nextInt() - 1;
            if (seleccionCaracteristica == -1) break;

            if (seleccionCaracteristica < 0 || seleccionCaracteristica >= caracteristicasExtrasDisponibles.size()) {
                System.out.println("Selección no válida.");
            } else {
                Caracteristica caracteristica = caracteristicasExtrasDisponibles.get(seleccionCaracteristica);
                membresia.agregarCaracteristica(caracteristica);
                System.out.println("Característica " + caracteristica.nombre + " añadida.");
            }
        }
    }
}
