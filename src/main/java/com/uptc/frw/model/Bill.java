package com.uptc.frw.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "FACTURAS")
public class Bill {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "FECHA")
    private Date fecha;


    @Column(name = "ID_CLIENTE")
    private Long idCliente;


    @Column(name = "ID_VENDEDOR")
    private Long idVendedor;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Persons clienteFactura;

    @ManyToOne
    @JoinColumn(name="ID_VENDEDOR")
    private Persons vendedorFactura;

    @OneToMany(mappedBy = "detailfact")
    private List<Details> details;

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