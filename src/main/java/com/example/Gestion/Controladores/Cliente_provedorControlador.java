package com.example.Gestion.Controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Gestion.Entidades.ClienteProveedor.Cliente_proveedor;
import com.example.Gestion.Entidades.ClienteProveedor.Cliente_proveedorRepositorio;
import com.example.Gestion.Entidades.Departamentos.DepartamentoRepositorio;
import com.example.Gestion.Entidades.ProveedorTipoMaterial.Proveedor_tipo_material;
import com.example.Gestion.Entidades.ProveedorTipoMaterial.Proveedor_tipo_materialRepositorio;
import com.example.Gestion.Entidades.TipoMaterial.Tipo_material;
import com.example.Gestion.Entidades.TipoMaterial.Tipo_materialRepositorio;
import com.example.Gestion.Entidades.Usuarios.UsuarioLog;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/GestionZapaterias/Proveedores")
public class Cliente_provedorControlador {
    
    @Autowired Cliente_proveedorRepositorio cliente_proveedorRepositorio;
    @Autowired UsuarioLog usuarioLog;
    @Autowired DepartamentoRepositorio departamentoRepositorio;
    @Autowired Tipo_materialRepositorio tipo_materialRepositorio;
    @Autowired Proveedor_tipo_materialRepositorio proveedor_tipo_materialRepositorio;

    /*List<Proveedor_tipo_material> proveedor_tipo_material_Lista = new ArrayList<>();

    @GetMapping("/")
    public String listaMateriales(Model model) {
        List<Cliente_proveedor> proveedores = cliente_proveedorRepositorio.findByUsuarios(usuarioLog.correoUsuario(), true);
        Map<Cliente_proveedor, List<Tipo_material>> proveedorTiposMap = new HashMap<>();

        for (Cliente_proveedor proveedor : proveedores) {
            List<Proveedor_tipo_material> relaciones = proveedor_tipo_materialRepositorio.findByProveedor(proveedor);
            List<Tipo_material> tipos = relaciones.stream().map(Proveedor_tipo_material::getTipo_material).collect(Collectors.toList());
            proveedorTiposMap.put(proveedor, tipos);
        }

        model.addAttribute("ProveedoresMap", proveedorTiposMap);
        return "Proveedores/ListaProveedores";
    }

    @GetMapping("/NuevoProveedor")
    public String nuevoProveedor(Model model){
        proveedor_tipo_material_Lista.clear();
        Cliente_proveedor cliente_proveedor = new Cliente_proveedor();
        model.addAttribute("Tiposs", tipo_materialRepositorio.findAll());
        model.addAttribute("Proveedoress", cliente_proveedor);
        model.addAttribute("Departamentoss", departamentoRepositorio.findAll());
        return "Proveedores/NuevoProveedor";
    }

    @GetMapping("/Nuevo/TipoMaterialProveedor/{id_tipoM}")
    @ResponseBody
    public void nuevoTipoMatereialProveedor(@PathVariable("id_tipoM") int id_tipoM){
        Proveedor_tipo_material proTipM = new Proveedor_tipo_material();
        proTipM.setTipo_material(tipo_materialRepositorio.findById(id_tipoM).get());
        proveedor_tipo_material_Lista.add(proTipM);
    }

    @GetMapping("/Eliminar/TipoMaterialProveedor/{id_tipoM}")
    @ResponseBody
    public void eliminarTipoMaterialProveedor(@PathVariable("id_tipoM") int id_tipoM){
        for(int i = 0; i < proveedor_tipo_material_Lista.size(); i++){
            if(proveedor_tipo_material_Lista.get(i).getTipo_material().getID_Tipo_material() == id_tipoM){
                proveedor_tipo_material_Lista.remove(i);
                break;
            }
        }
    }

    //Modificar Cargar valores de tipo material ya seleccionados
    @GetMapping("/EditarProveedor/{id}")
    public String editarProveedor(@PathVariable("id") int id, Model model){
        Cliente_proveedor cliente_proveedor = cliente_proveedorRepositorio.findById(id).get();
        if(usuarioLog.correoUsuario() == cliente_proveedor.getUsuarios()){
            model.addAttribute("Tiposs", tipo_materialRepositorio.findAll());
            model.addAttribute("Departamentoss", departamentoRepositorio.findAll());
            model.addAttribute("Proveedoress", cliente_proveedor);
            return "Proveedores/NuevoProveedor";
        }else{
            model.addAttribute("error", "Proveedor no encontrado...");
            return "error";
        }  
    }

    @PostMapping("/GuardarProveedor")
    public String guardarProveedor(Cliente_proveedor cliente_proveedor){
        cliente_proveedor.setUsuarios(usuarioLog.correoUsuario());
        cliente_proveedor.setTipo_cliente_proveedor(true);
        Cliente_proveedor proveedor = cliente_proveedorRepositorio.save(cliente_proveedor);

        for(int i = 0; i < proveedor_tipo_material_Lista.size(); i++){
            proveedor_tipo_material_Lista.get(i).setCliente_proveedor(proveedor);
            proveedor_tipo_materialRepositorio.save(proveedor_tipo_material_Lista.get(i));
        }
        proveedor_tipo_material_Lista.clear();
        return "redirect:/GestionZapaterias/Proveedores/";
    }

    @GetMapping("/EliminarProveedor/{id}")
    public String eliminarProveedor(@PathVariable("id")int id, Model model){
        if(usuarioLog.correoUsuario() == cliente_proveedorRepositorio.findById(id).get().getUsuarios()){
            cliente_proveedorRepositorio.deleteById(id);
            return "redirect:/GestionZapaterias/Proveedores/";
        }else{
            model.addAttribute("error", "Proveedor no encontrado...");
            return "error";
        }

    }*/

    //Maneja la lista de materiales por proveedor por Sesion de usuario para evitar conflictos cuando multiples usuarios usen el controlador
    @GetMapping("/")
    public String listaMateriales(Model model) {
        List<Cliente_proveedor> proveedores = cliente_proveedorRepositorio.findByUsuarios(usuarioLog.correoUsuario(), true);
        Map<Cliente_proveedor, List<Tipo_material>> proveedorTiposMap = new HashMap<>();

        for (Cliente_proveedor proveedor : proveedores) {
            List<Proveedor_tipo_material> relaciones = proveedor_tipo_materialRepositorio.findByProveedor(proveedor);
            List<Tipo_material> tipos = relaciones.stream()
                    .map(Proveedor_tipo_material::getTipo_material)
                    .collect(Collectors.toList());
            proveedorTiposMap.put(proveedor, tipos);
        }

        model.addAttribute("ProveedoresMap", proveedorTiposMap);
        return "Proveedores/ListaProveedores";
    }

    // Mostrar formulario para nuevo proveedor
    @GetMapping("/NuevoProveedor")
    public String nuevoProveedor(Model model, HttpSession session) {
        session.removeAttribute("listaTipoMaterial"); // limpia lista anterior
        Cliente_proveedor cliente_proveedor = new Cliente_proveedor();

        model.addAttribute("Tiposs", tipo_materialRepositorio.findAll());
        model.addAttribute("Proveedoress", cliente_proveedor);
        model.addAttribute("Departamentoss", departamentoRepositorio.findAll());
        return "Proveedores/NuevoProveedor";
    }

    // Agregar tipo de material a la lista de sesión
    @GetMapping("/Nuevo/TipoMaterialProveedor/{id_tipoM}")
    @ResponseBody
    public void nuevoTipoMatereialProveedor(@PathVariable("id_tipoM") int id_tipoM, HttpSession session) {
        List<Proveedor_tipo_material> lista = (List<Proveedor_tipo_material>) session.getAttribute("listaTipoMaterial");
        if (lista == null) {
            lista = new ArrayList<>();
        }

        boolean existe = lista.stream().anyMatch(ptm -> ptm.getTipo_material().getID_Tipo_material() == id_tipoM);
        if (!existe) {
            Proveedor_tipo_material proTipM = new Proveedor_tipo_material();
            proTipM.setTipo_material(tipo_materialRepositorio.findById(id_tipoM).get());
            lista.add(proTipM);
        }

        session.setAttribute("listaTipoMaterial", lista);
    }

    // Eliminar tipo de material de la lista de sesión
    @GetMapping("/Eliminar/TipoMaterialProveedor/{id_tipoM}")
    @ResponseBody
    public void eliminarTipoMaterialProveedor(@PathVariable("id_tipoM") int id_tipoM, HttpSession session) {
        List<Proveedor_tipo_material> lista = (List<Proveedor_tipo_material>) session.getAttribute("listaTipoMaterial");
        if (lista != null) {
            lista.removeIf(ptm -> ptm.getTipo_material().getID_Tipo_material() == id_tipoM);
            session.setAttribute("listaTipoMaterial", lista);
        }
    }

    // Guardar proveedor junto a su lista de tipos de material
    @PostMapping("/GuardarProveedor")
    public String guardarProveedor(Cliente_proveedor cliente_proveedor, HttpSession session) {
        cliente_proveedor.setUsuarios(usuarioLog.correoUsuario());
        cliente_proveedor.setTipo_cliente_proveedor(true);
        Cliente_proveedor proveedorGuardado = cliente_proveedorRepositorio.save(cliente_proveedor);

        List<Proveedor_tipo_material> lista = (List<Proveedor_tipo_material>) session.getAttribute("listaTipoMaterial");
        if (lista != null) {
            for (Proveedor_tipo_material ptm : lista) {
                ptm.setCliente_proveedor(proveedorGuardado);
                proveedor_tipo_materialRepositorio.save(ptm);
            }
            session.removeAttribute("listaTipoMaterial");
        }

        return "redirect:/GestionZapaterias/Proveedores/";
    }

    // Editar proveedor existente (con carga de datos básica)
    @GetMapping("/EditarProveedor/{id}")
    public String editarProveedor(@PathVariable("id") int id, Model model, HttpSession session) {
        Optional<Cliente_proveedor> optional = cliente_proveedorRepositorio.findById(id);
        if (optional.isPresent() && optional.get().getUsuarios().equals(usuarioLog.correoUsuario())) {
            Cliente_proveedor cliente_proveedor = optional.get();

            // Precargar los tipos de material del proveedor en la sesión
            List<Proveedor_tipo_material> tiposActuales = proveedor_tipo_materialRepositorio.findByProveedor(cliente_proveedor);
            session.setAttribute("listaTipoMaterial", tiposActuales);

            model.addAttribute("Tiposs", tipo_materialRepositorio.findAll());
            model.addAttribute("Departamentoss", departamentoRepositorio.findAll());
            model.addAttribute("Proveedoress", cliente_proveedor);
            return "Proveedores/NuevoProveedor";
        } else {
            model.addAttribute("error", "Proveedor no encontrado o sin permisos.");
            return "error";
        }
    }

    // Eliminar proveedor
    @GetMapping("/EliminarProveedor/{id}")
    public String eliminarProveedor(@PathVariable("id") int id, Model model) {
        Optional<Cliente_proveedor> optional = cliente_proveedorRepositorio.findById(id);
        if (optional.isPresent() && optional.get().getUsuarios().equals(usuarioLog.correoUsuario())) {
            cliente_proveedorRepositorio.deleteById(id);
            return "redirect:/GestionZapaterias/Proveedores/";
        } else {
            model.addAttribute("error", "Proveedor no encontrado o sin permisos.");
            return "error";
        }
    }

}
