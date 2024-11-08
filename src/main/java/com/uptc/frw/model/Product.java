package com.uptc.frw.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "PRODUCTOS")
public class Product {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "PRECIO_UNITARIO")
    private double precioUnitario;

    @OneToMany(mappedBy = "detailproducto")
    private List<Details> detailsProduct;

    @ManyToMany
    @JoinTable(name = "PROVEEDOR_PRODUCTO",
            joinColumns = @JoinColumn(name = "ID_PRODUCTO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PROVEEDOR")

    )
    private List<Persons> Proveedor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioUnitario=" + precioUnitario +
                '}';
    }
}