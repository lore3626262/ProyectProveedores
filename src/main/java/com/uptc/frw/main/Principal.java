package com.uptc.frw.main;

import com.uptc.frw.model.Bill;
import com.uptc.frw.model.Details;
import com.uptc.frw.model.Persons;
import com.uptc.frw.model.Product;
import com.uptc.frw.persistence.PersistenceUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class Principal {
    public static void main(String[] args) {
        EntityManager em = PersistenceUtil.getEntityManager();
        //Un metodo que a partir del id del vendedor muestre el valor total de las ventas de este.
        em.getTransaction().begin();
        Persons vendedor =em.find(Persons.class, 1);
        if (vendedor != null) {
            List<Bill> bills=vendedor.getBillsVendedor();
            if (!bills.isEmpty()) {
                double totalVentas = 0.0;
                for (Bill bill : bills) {
                    System.out.println("-------VENTAS-------");
                    System.out.println("ID VENTA: "+ bill.getId());
                    System.out.println("FECHA VENTA: "+bill.getFecha());
                    System.out.println("ID CLIENTE : "+bill.getClienteFactura());
                    System.out.println("ID VENDEDOR :"+bill.getVendedorFactura());

                    totalVentas += bill.getMontoTotal();
                }

                System.out.println("TOTAL VENTAS : "+totalVentas);
            }else {
                System.out.println("-------el vendedor no ha hecho ventas-------");
            }
        }else{
            System.out.println("no existe el cliente");
        }


        em.close();

    }






    public static void insercionTablas(){
        EntityManager em = PersistenceUtil.getEntityManager();

        // Verificar si la transacción ya está activa
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }

        // INSERCIONES DE PERSONAS EN LA TABLA PERSONAS
        Persons person = new Persons();
        person.setNombres("Sara Sofia");
        person.setApellidos("Martinez");
        person.setFechaNacimiento(new Date());
        person.setTipoDocumento("CC");
        person.setNumeroDocumento("1002331685");
        em.persist(person);

        Persons person2 = new Persons();
        person2.setNombres("Juan David");
        person2.setApellidos("Lopez");
        person2.setFechaNacimiento(new Date());
        person2.setTipoDocumento("CC");
        person2.setNumeroDocumento("1002331686");
        em.persist(person2);

        Persons person3 = new Persons();
        person3.setNombres("Maria Paula");
        person3.setApellidos("Garcia");
        person3.setFechaNacimiento(new Date());
        person3.setTipoDocumento("CC");
        person3.setNumeroDocumento("1002331687");
        em.persist(person3);

        Persons person4 = new Persons();
        person4.setNombres("Carlos Andres");
        person4.setApellidos("Rodriguez");
        person4.setFechaNacimiento(new Date());
        person4.setTipoDocumento("CC");
        person4.setNumeroDocumento("1002331688");
        em.persist(person4);

        Persons person5 = new Persons();
        person5.setNombres("Sandra Lorena");
        person5.setApellidos("Rodriguez");
        person5.setFechaNacimiento(new Date());
        person5.setTipoDocumento("CC");
        person5.setNumeroDocumento("1002331688");
        em.persist(person5);

        // INSERCIONES DE PRODUCTOS
        Product product = new Product();
        product.setNombre("esmalte");
        product.setPrecioUnitario(20000.00);
        em.persist(product);

        Product product2 = new Product();
        product2.setNombre("crema");
        product2.setPrecioUnitario(35000.00);
        em.persist(product2);

        Product product3 = new Product();
        product3.setNombre("shampoo");
        product3.setPrecioUnitario(15000.00);
        em.persist(product3);

        Product product4 = new Product();
        product4.setNombre("labial");
        product4.setPrecioUnitario(18000.00);
        em.persist(product4);

        Product product5 = new Product();
        product5.setNombre("mascarilla");
        product5.setPrecioUnitario(25000.00);
        em.persist(product5);

        // INSERCIONES DE FACTURAS
        Persons cliente = em.find(Persons.class, person2.getId());  // Usar ID de person2 como cliente
        Persons vendedor = em.find(Persons.class, person.getId()); // Usar ID de person como vendedor

        List<Bill> facturas = new ArrayList<>(); // Lista para almacenar facturas
        for (int i = 0; i < 5; i++) {
            Bill bill = new Bill();
            bill.setFecha(new Date());
            bill.setClienteFactura(cliente);
            bill.setVendedorFactura(vendedor);
            em.persist(bill);
            facturas.add(bill); // Agregar factura a la lista
        }

        // INSERCIONES DE DETALLES DE FACTURA
        Product productoDetalle = em.find(Product.class, product.getId());
        for (Bill currentBill : facturas) {
            for (int i = 0; i < 5; i++) {
                Details details = new Details();
                details.setDetailfact(currentBill); // Asignar factura al detalle
                details.setCantidad(5);
                details.setPrecioVenta(20000.0);
                details.setDetailproducto(productoDetalle);
                em.persist(details);
                currentBill.getDetails().add(details); // Añadir detalle a la lista de detalles de la factura
            }
        }

        // RELACIONES MUCHOS A MUCHOS ENTRE PROVEEDORES Y PRODUCTOS
        Persons proveedor1 = em.find(Persons.class, person.getId());
        Persons proveedor2 = em.find(Persons.class, person2.getId());
        Persons proveedor3 = em.find(Persons.class, person3.getId());
        Persons proveedor4 = em.find(Persons.class, person4.getId());
        Persons proveedor5 = em.find(Persons.class, person5.getId());  // Se usa el mismo objeto como ejemplo

        Product prod1 = em.find(Product.class, product.getId());
        Product prod2 = em.find(Product.class, product2.getId());
        Product prod3 = em.find(Product.class, product3.getId());
        Product prod4 = em.find(Product.class, product4.getId());
        Product prod5 = em.find(Product.class, product5.getId());

        // Vinculando proveedores y productos (Relación Muchos a Muchos)
        proveedor1.getProducts().add(prod1);
        prod1.getProveedor().add(proveedor1);

        proveedor2.getProducts().add(prod2);
        prod2.getProveedor().add(proveedor2);

        proveedor3.getProducts().add(prod3);
        prod3.getProveedor().add(proveedor2);

        proveedor4.getProducts().add(prod4);
        prod4.getProveedor().add(proveedor3);

        proveedor5.getProducts().add(prod5);
        prod5.getProveedor().add(proveedor3);

        // Persistir las relaciones de muchos a muchos
        em.persist(proveedor1);
        em.persist(proveedor2);
        em.persist(proveedor3);
        em.persist(proveedor4);
        em.persist(proveedor5);

        em.getTransaction().commit();
        em.close();
    }

    public static void consultFacturas(){
        //Crear un metodo que permita consultar todas las facturas de un cliente.
        EntityManager em = PersistenceUtil.getEntityManager();

        Persons cliente = em.find(Persons.class, 6);
        if (cliente != null) {
            //si el cliente existe obtine las facturas
            List<Bill> facturas =cliente.getBillsclien();
            if(!facturas.isEmpty()){
                for (Bill factura: facturas) {
                    System.out.println("------------------------------------------: ");
                    System.out.println("Factura encontada ID: " + factura.getId());
                    System.out.println("Fecha: " + factura.getFecha());
                    System.out.println("ID Cliente: " + factura.getIdCliente());
                    System.out.println("ID Vendedor: " + factura.getIdVendedor());
                }
            }else {
                System.out.println("El cliente no tiene facturas");
            }
        }else {
            System.out.println("El cliente no existe");
        }
        em.close();
    }

    public static void consultProveedores(){
        //Crear un metodo que permita consultar los productos que provee un proveedor.
        EntityManager em = PersistenceUtil.getEntityManager();
        Persons provehedor = em.find(Persons.class, 27);
        //CREACION DE UN PRODUCTO ASOCIADO


        if (provehedor != null) {
            List<Product> products = provehedor.getProducts();
            if(!products.isEmpty()) {
                for (Product product : products) {
                    //imprimimos los productos
                    System.out.println("PRODUCTOS PROVEHEDORES");
                    System.out.println("ID: " + product.getId());
                    System.out.println("NOMBRE PRODUCTO " + product.getNombre());
                    System.out.println("PRECIO UNITARIO " + product.getPrecioUnitario());
                }
            }else{
                System.out.println("no hay productos");
            }
        }else{
            System.out.println("no hay proveedores");
        }
        em.close();
    }
}
