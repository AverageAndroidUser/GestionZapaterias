package com.example.Gestion.Entidades.Pedidos;

import com.example.Gestion.Entidades.ClienteProveedor.Cliente_proveedor;
import com.example.Gestion.Entidades.Usuarios.Usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedidos;
    private String fechaEntrega;
    private Byte estado_pedido;

    @ManyToOne
    @JoinColumn(name = "ID_Usuarios")
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "ID_Cliente_Proveedor")
    private Cliente_proveedor cliente_proveedor;
    
    public Pedidos() {
    }

    public Pedidos(int ID_Pedidos, String Fecha_entrega, Usuarios usuario,
            Cliente_proveedor cliente_proveedor, Byte estado_pedido) {
        this.idPedidos = ID_Pedidos;
        this.fechaEntrega = Fecha_entrega;
        this.usuario = usuario;
        this.cliente_proveedor = cliente_proveedor;
        this.estado_pedido = estado_pedido;
    }

    public int getIdPedidos() {
        return idPedidos;
    }

    public void setIdPedidos(int iD_Pedidos) {
        idPedidos = iD_Pedidos;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fecha_entrega) {
        fechaEntrega = fecha_entrega;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Cliente_proveedor getCliente_proveedor() {
        return cliente_proveedor;
    }

    public void setCliente_proveedor(Cliente_proveedor cliente_proveedor) {
        this.cliente_proveedor = cliente_proveedor;
    }

    public Byte getEstado_pedido() {
        return estado_pedido;
    }

    public void setEstado_pedido(Byte estado_pedido) {
        this.estado_pedido = estado_pedido;
    }

    

    
}
