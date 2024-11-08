package com.uptc.frw.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "DETALLES")
public class Details {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "detail_seq")
    @SequenceGenerator(name ="detail_seq",sequenceName ="DETALLE_SEQ",allocationSize=1 )
    private Long id;



    @Column(name = "ID_FACTURA",insertable = false, updatable = false)
    private Long idFactura;

    @Column(name = "CANTIDAD")
    private int cantidad;

    @Column(name = "PRECIO_VENTA")
    private double precioVenta;


    @Column(name = "ID_PRODUCTO",insertable = false, updatable = false)
    private Long idProducto;

    @ManyToOne
    @JoinColumn(name="ID_FACTURA")
    private Bill detailfact;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO")
    private Product detailproducto;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Bill getDetailfact() {
        return detailfact;
    }

    public void setDetailfact(Bill detailfact) {
        this.detailfact = detailfact;
    }

    public Product getDetailproducto() {
        return detailproducto;
    }

    public void setDetailproducto(Product detailproducto) {
        this.detailproducto = detailproducto;
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", idFactura=" + idFactura +
                ", cantidad=" + cantidad +
                ", precioVenta=" + precioVenta +
                ", idProducto=" + idProducto +
                '}';
    }
}