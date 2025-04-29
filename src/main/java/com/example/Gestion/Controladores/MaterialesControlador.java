package com.example.Gestion.Controladores;

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

import com.example.Gestion.Entidades.Materiales.Materiales;
import com.example.Gestion.Entidades.Materiales.MaterialesRepositorio;
import com.example.Gestion.Entidades.TipoMaterial.Tipo_materialRepositorio;
import com.example.Gestion.Entidades.Usuarios.UsuarioLog;
import com.example.Gestion.Entidades.ClienteProveedor.Cliente_proveedorRepositorio;

@Controller
@RequestMapping("/GestionZapaterias/Materiales")
public class MaterialesControlador {
    
    @Autowired MaterialesRepositorio materialesRepositorio;
    @Autowired Cliente_proveedorRepositorio cliente_proveedorRepositorio;
    @Autowired Tipo_materialRepositorio tipo_materialRepositorio;
    @Autowired UsuarioLog usuarioLog;

    @GetMapping("/{pagina}")
    public String listaMateriales(@PathVariable("pagina") int pagina, @RequestParam(defaultValue = "10") int tamaño,   
        @RequestParam(defaultValue = "cantidad") String orden, @RequestParam(defaultValue = "asc") String direccion, Model model) {

        Sort sort = Sort.by(direccion.equalsIgnoreCase("asc")? Sort.Direction.ASC : Sort.Direction.DESC, orden);
        Pageable pageable = PageRequest.of(pagina, tamaño, sort);
        Page<Materiales> materiales = materialesRepositorio.findByUsuarios(usuarioLog.correoUsuario(), pageable);
        model.addAttribute("Materialess", materiales);
        model.addAttribute("Proveedores", cliente_proveedorRepositorio.findByUsuarios(usuarioLog.correoUsuario(), true));
        model.addAttribute("orden", orden);
        model.addAttribute("direccion", direccion);
        return "Materiales/ListaMateriales";
    }

    @GetMapping("/NuevoMaterial")
    public String nuevoMaterial(Model model){
        model.addAttribute("Proveedoress", cliente_proveedorRepositorio.findByUsuarios(usuarioLog.correoUsuario(),true));
        model.addAttribute("TiposMaterialess", tipo_materialRepositorio.findAll());
        model.addAttribute("Materialess", new Materiales());
        return "Materiales/NuevoMaterial";
    }

    @PostMapping("/GuardarMaterial")
    public String guardarMaterial(Materiales materiales, Model model){
        materiales.setUsuarios(usuarioLog.correoUsuario());
        materialesRepositorio.save(materiales);
        return "redirect:/GestionZapaterias/Materiales/0";
    }

    @GetMapping("/EditarMaterial/{id}")
    public String editarMaterial(@PathVariable("id")int id, Model model){
        model.addAttribute("Materialess", materialesRepositorio.findById(id).get());
        model.addAttribute("Proveedoress", cliente_proveedorRepositorio.findByUsuarios(usuarioLog.correoUsuario(),true));
        model.addAttribute("TiposMaterialess", tipo_materialRepositorio.findAll());
        return "Materiales/NuevoMaterial";

    }

    @GetMapping("/EliminarMaterial/{id}")
    public String eliminarMaterial(@PathVariable("id")int id){
        materialesRepositorio.deleteById(id);
        return "redirect:/GestionZapaterias/Materiales/0";
    }
}
