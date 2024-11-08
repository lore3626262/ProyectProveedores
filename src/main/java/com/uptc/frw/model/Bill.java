package com.uptc.frw.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "FACTURAS")
public class Bill {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bill_seq")
    @SequenceGenerator(name ="bill_seq",sequenceName = "FACT_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "FECHA")
    private Date fecha;


    @Column(name = "ID_CLIENTE",insertable = false, updatable = false)
    private Long idCliente;


    @Column(name = "ID_VENDEDOR",insertable = false, updatable = false)
    private Long idVendedor;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Persons clienteFactura;

    @ManyToOne
    @JoinColumn(name="ID_VENDEDOR")
    private Persons vendedorFactura;

    @OneToMany(mappedBy = "detailfact")
    private List<Details> details=new ArrayList<>();



    public double getMontoTotal() {
        double total = 0.0;
        for (Details detail:details) {
            Product product=detail.getDetailproducto();
            if(product!=null) {
                total+=product.getPrecioUnitario();
            }
        }
        return total;
    }

    public Persons getClienteFactura() {
        return clienteFactura;
    }

    public void setClienteFactura(Persons clienteFactura) {
        this.clienteFactura = clienteFactura;
    }

    public Persons getVendedorFactura() {
        return vendedorFactura;
    }

    public void setVendedorFactura(Persons vendedorFactura) {
        this.vendedorFactura = vendedorFactura;
    }

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Long idVendedor) {
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", idCliente=" + idCliente +
                ", idVendedor=" + idVendedor +
                '}';
    }
}