package com.example.Gestion.Controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Gestion.Entidades.Materiales.MaterialesRepositorio;
import com.example.Gestion.Entidades.Productos.Productos;
import com.example.Gestion.Entidades.Productos.ProductosRepositorio;
import com.example.Gestion.Entidades.TipoProducto.Tipo_productoRepositorio;
import com.example.Gestion.Entidades.Usuarios.UsuarioLog;
import com.example.Gestion.Entidades.Usuarios.Usuarios;

@Controller
@RequestMapping("GestionZapaterias/Productos")
public class ProductoControlador {
    
    @Autowired ProductosRepositorio productosRepositorio;
    @Autowired UsuarioLog usuarioLog;
    @Autowired Tipo_productoRepositorio tipo_productoRepositorio;
    @Autowired MaterialesRepositorio materialesRepositorio;

    @GetMapping("/{pagina}")
    public String listaProductos(@PathVariable("pagina") int pagina, @RequestParam(defaultValue = "10") int tamaño,   
        @RequestParam(defaultValue = "idProductos") String orden, @RequestParam(defaultValue = "asc") String direccion, Model model) {

        Sort sort = Sort.by(direccion.equalsIgnoreCase("asc")? Sort.Direction.ASC : Sort.Direction.DESC, orden);
        Pageable pageable = PageRequest.of(pagina, tamaño, sort);
        Page<Productos> productos = productosRepositorio.findByUsuario(usuarioLog.correoUsuario(),pageable);
        model.addAttribute("Productoss", productos);
        model.addAttribute("orden", orden);
        model.addAttribute("direccion", direccion);
        return "Productos/ListaProductos";
    }

    @GetMapping("/Buscar")
    @ResponseBody
    public List<Productos> buscarProdutos(@RequestParam String nombre, Model model){
        return productosRepositorio.findByNombreAndUsuarios(nombre, usuarioLog.correoUsuario());
    }

    @GetMapping("/NuevoProducto")
    public String nuevoProducto(Model model){
        model.addAttribute("Productoss", new Productos());
        model.addAttribute("Materialess", materialesRepositorio.findByUsuarioTipoM(usuarioLog.correoUsuario()));
        model.addAttribute("Tiposs", tipo_productoRepositorio.findAll());
        return "Productos/NuevoProducto";
    }

    @GetMapping("/EditarProducto/{id}")
    public String editarProduto(@PathVariable("id") int id, Model model){
        Optional<Productos> producto = productosRepositorio.findById(id);
        Usuarios usuario = usuarioLog.correoUsuario();
        if(usuario.equals(producto.get().getUsuario())){
            model.addAttribute("Productoss", producto);
            model.addAttribute("Materialess", materialesRepositorio.findByUsuarioTipoM(usuario));
            model.addAttribute("Tiposs", tipo_productoRepositorio.findAll());
            return "Productos/NuevoProducto";
        }else{
            model.addAttribute("error", "Material no encontrado...");
            return "error";
        }
    }

    @GetMapping("/EliminarProducto/{id}")
    public String eliminarProducto(@PathVariable("id") int id, Model model){
        Optional<Productos> producto = productosRepositorio.findById(id);
        if(usuarioLog.correoUsuario().equals(producto.get().getUsuario())){
            productosRepositorio.deleteById(id);
            return "redirect:/GestionZapaterias/Productos/0";
        }else{
            model.addAttribute("error", "Material no encontrado...");
            return "error";
        }
    }

    @PostMapping("/GuardarProducto")
    public String guardarProducto(Productos productos, Model model){
        productos.setUsuario(usuarioLog.correoUsuario());
        productosRepositorio.save(productos);
        return "redirect:/GestionZapaterias/Productos/0";
    }

    @GetMapping("/VerProducto/{id}")
    public String verProducto(@PathVariable("id") int id, Model model){
        Optional<Productos> producto = productosRepositorio.findById(id);
        if(usuarioLog.correoUsuario().equals(producto.get().getUsuario())){
            model.addAttribute("Productoss", producto.get());
            return "Productos/VerProducto";
        }else{
            model.addAttribute("error", "Material no encontrado...");
            return "error";
        }
    }

}
