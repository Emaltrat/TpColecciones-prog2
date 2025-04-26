package ema.tp6.prog2;

import java.util.Scanner;

public class TP6Prog2 {

    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n¿Cuántos productos desea agregar?: ");
        int cantidadProductos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadProductos; i++) {
            System.out.println("\nProducto " + (i + 1) + ":");
            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Precio: ");
            double precio = scanner.nextDouble();

            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); 

            CategoriaProducto categoria = null;
            while (categoria == null) {
                System.out.print("Categoría (ALIMENTOS, ELECTRONICA, ROPA, HOGAR): ");
                String catInput = scanner.nextLine().toUpperCase();
                try {
                    categoria = CategoriaProducto.valueOf(catInput);
                } catch (IllegalArgumentException e) {
                    System.out.println("Categoría inválida. Intente de nuevo.");
                }
            }

            Producto producto = new Producto(id, nombre, precio, cantidad, categoria);
            inventario.agregarProducto(producto);
        }

        System.out.println("\n--- Listado de Productos ---");
        inventario.listarProductos();

        System.out.print("\nIngrese ID de producto a buscar: ");
        String idBuscar = scanner.nextLine();
        Producto buscado = inventario.buscarProductoPorId(idBuscar);
        if (buscado != null) {
            System.out.println("\nProducto encontrado:");
            buscado.mostrarInfo();
        } else {
            System.out.println("\nProducto no encontrado.");
        }

        System.out.println("\nProductos de categoría ELECTRONICA:");
        for (Producto p : inventario.filtrarPorCategoria(CategoriaProducto.ELECTRONICA)) {
            p.mostrarInfo();
        }

        System.out.print("\nIngrese ID de producto a eliminar: ");
        String idEliminar = scanner.nextLine();
        inventario.eliminarProducto(idEliminar);

        System.out.println("\n--- Productos luego de eliminación ---");
        inventario.listarProductos();

        System.out.println("\nTotal de Stock: " + inventario.obtenerTotalStock());

        Producto mayor = inventario.obtenerProductoConMayorStock();
        if (mayor != null) {
            System.out.println("\nProducto con mayor stock:");
            mayor.mostrarInfo();
        }

        System.out.println("\nProductos con precio entre $1000 y $3000:");
        for (Producto p : inventario.filtrarProductosPorPrecio(1000, 3000)) {
            p.mostrarInfo();
        }

        System.out.println("\nCategorías Disponibles:");
        inventario.mostrarCategoriasDisponibles();

    }
    
}
