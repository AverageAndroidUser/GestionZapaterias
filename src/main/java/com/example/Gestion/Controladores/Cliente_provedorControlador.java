package com.example.Gestion.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Gestion.Entidades.ClienteProveedor.Cliente_proveedor;
import com.example.Gestion.Entidades.ClienteProveedor.Cliente_proveedorRepositorio;
import com.example.Gestion.Entidades.Departamentos.DepartamentoRepositorio;
import com.example.Gestion.Entidades.Municipios.Municipios;
import com.example.Gestion.Entidades.Usuarios.UsuarioLog;

@Controller
@RequestMapping("/GestionZapaterias/")
public class Cliente_provedorControlador {
    
    @Autowired Cliente_proveedorRepositorio cliente_proveedorRepositorio;
    @Autowired UsuarioLog usuarioLog;
    @Autowired DepartamentoRepositorio departamentoRepositorio;

    @GetMapping("/Proveedor")
    public String listaMateriales(Model model) {
        List<Cliente_proveedor> Cliente_proveedor = cliente_proveedorRepositorio.findByUsuarios(usuarioLog.correoUsuario(),true );
        model.addAttribute("Proveedoress", Cliente_proveedor);
        return "Proveedores/ListaProveedores";
    }

    @GetMapping("/Proveedor/NuevoProveedor")
    public String nuevoProveedor(Model model){
        Cliente_proveedor cliente_proveedor = new Cliente_proveedor();
        cliente_proveedor.setMunicipio(new Municipios());
        model.addAttribute("Proveedoress", cliente_proveedor);
        model.addAttribute("Departamentoss", departamentoRepositorio.findAll());
        return "Proveedores/NuevoProveedor";
    }

    @GetMapping("/Proveedor/EditarProveedor/{id}")
    public String editarProveedor(@PathVariable("id") int id, Model model){
        Cliente_proveedor cliente_proveedor = cliente_proveedorRepositorio.findById(id).get();
        model.addAttribute("Departamentoss", departamentoRepositorio.findAll());
        model.addAttribute("Proveedoress", cliente_proveedor);
        return "Proveedores/NuevoProveedor";
    }

    @PostMapping("/Proveedor/GuardarProveedor")
    public String guardarProveedor(Cliente_proveedor cliente_proveedor){
        cliente_proveedor.setUsuarios(usuarioLog.correoUsuario());
        cliente_proveedor.setTipo_cliente_proveedor(true);
        cliente_proveedorRepositorio.save(cliente_proveedor);
        return "redirect:/GestionZapaterias/Proveedor";
    }

    @GetMapping("/Proveedor/EliminarProveedor/{id}")
    public String eliminarProveedor(@PathVariable("id")int id){
        cliente_proveedorRepositorio.deleteById(id);
        return "redirect:/GestionZapaterias/Proveedor";
    }

}
