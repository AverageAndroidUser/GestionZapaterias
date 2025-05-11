package com.example.Gestion.Controladores;

import java.util.Optional;

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
import com.example.Gestion.Entidades.Usuarios.UsuarioLog;

@Controller
@RequestMapping("/GestionZapaterias/Clientes")
public class ClienteControlador {

    @Autowired Cliente_proveedorRepositorio cliente_proveedorRepositorio;
    @Autowired UsuarioLog usuarioLog;
    @Autowired DepartamentoRepositorio departamentoRepositorio;

    @GetMapping("/")
    public String listaClientes(Model model){
        model.addAttribute("Clientess", cliente_proveedorRepositorio.findByUsuarios(usuarioLog.correoUsuario(), false));
        return "Clientes/ListaClientes";
    }

    @GetMapping("/NuevoCliente")
    public String nuevoCliente(Model model){
        model.addAttribute("Clientess", new Cliente_proveedor());
        model.addAttribute("Departamentoss", departamentoRepositorio.findAll());
        return "Clientes/NuevoCliente";
    }

    @GetMapping("EditarCliente/{id}")
    public String editarCleinte(@PathVariable("id") int id, Model model){
        Optional<Cliente_proveedor> cliente = cliente_proveedorRepositorio.findById(id);
        if(usuarioLog.correoUsuario().equals(cliente.get().getUsuarios())){
            model.addAttribute("Clientess", cliente);
            model.addAttribute("Departamentoss", departamentoRepositorio.findAll());
            return "Clientes/NuevoCliente";
        }else{
            model.addAttribute("error", "Proveedor no encontrado o sin permisos.");
            return "error";
        }
    }

    @GetMapping("EliminarCliente/{id}")
    public String eliminarCliente(@PathVariable("id") int id, Model model){
        Optional<Cliente_proveedor> cliente = cliente_proveedorRepositorio.findById(id);
        if(usuarioLog.correoUsuario().equals(cliente.get().getUsuarios())){
            cliente_proveedorRepositorio.delete(cliente.get());
            return "redirect:/GestionZapaterias/Clientes/";
        }else{
            model.addAttribute("error", "Cliente no encontrado o sin permisos.");
            return "error";
        }
    }

    @PostMapping("/GuardarCliente")
    public String guardarCliente(Cliente_proveedor cliente_proveedor, Model model){
        cliente_proveedor.setUsuarios(usuarioLog.correoUsuario());
        cliente_proveedor.setTipo_cliente_proveedor(false);
        cliente_proveedorRepositorio.save(cliente_proveedor);
        return "redirect:/GestionZapaterias/Clientes/";
    }

    @GetMapping("/VerCliente/{id}")
    public String verCliente(@PathVariable("id") int id, Model model){
        Optional<Cliente_proveedor> cliente = cliente_proveedorRepositorio.findById(id);
        if(usuarioLog.correoUsuario().equals(cliente.get().getUsuarios())){
            model.addAttribute("Clientess", cliente.get());
            return "Clientes/VerCliente";
        }else{
            model.addAttribute("error", "Cliente no encontrado...");
            return "error";
        }
    }

}
