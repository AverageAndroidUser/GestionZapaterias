package com.example.Gestion.Entidades.Pedidos;

import com.example.Gestion.Entidades.ClienteProveedor.Cliente_proveedor;
import com.example.Gestion.Entidades.EstadoPedido.Estado_pedido;
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
    private int ID_Pedidos;
    private String Fecha_registro;
    private String Fecha_entrega;
    private int CostoTotal;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "ID_Cliente_Proveedor")
    private Cliente_proveedor cliente_proveedor;

    @ManyToOne
    @JoinColumn(name = "ID_Estado_pedido")
    private Estado_pedido estado_pedido;

    public Pedidos() {
    }

    public Pedidos(int ID_Pedidos, String Fecha_registro, String Fecha_entrega, int CostoTotal, Usuarios usuario,
            Cliente_proveedor cliente_proveedor, Estado_pedido estado_pedido) {
        this.ID_Pedidos = ID_Pedidos;
        this.Fecha_registro = Fecha_registro;
        this.Fecha_entrega = Fecha_entrega;
        this.CostoTotal = CostoTotal;
        this.usuario = usuario;
        this.cliente_proveedor = cliente_proveedor;
        this.estado_pedido = estado_pedido;
    }

    public int getID_Pedidos() {
        return ID_Pedidos;
    }

    public void setID_Pedidos(int iD_Pedidos) {
        ID_Pedidos = iD_Pedidos;
    }

    public String getFecha_registro() {
        return Fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        Fecha_registro = fecha_registro;
    }

    public String getFecha_entrega() {
        return Fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        Fecha_entrega = fecha_entrega;
    }

    public int getCostoTotal() {
        return CostoTotal;
    }

    public void setCostoTotal(int costoTotal) {
        CostoTotal = costoTotal;
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

    public Estado_pedido getEstado_pedido() {
        return estado_pedido;
    }

    public void setEstado_pedido(Estado_pedido estado_pedido) {
        this.estado_pedido = estado_pedido;
    }

    

    
}
