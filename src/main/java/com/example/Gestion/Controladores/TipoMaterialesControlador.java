package com.example.Gestion.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Gestion.Entidades.ClienteProveedor.Cliente_proveedorRepositorio;
import com.example.Gestion.Entidades.ProveedorTipoMaterial.Proveedor_tipo_material;
import com.example.Gestion.Entidades.ProveedorTipoMaterial.Proveedor_tipo_materialRepositorio;

@Controller
@RequestMapping("/TipoMateriales")
public class TipoMaterialesControlador {

    @Autowired Cliente_proveedorRepositorio cliente_proveedorRepositorio;
    @Autowired Proveedor_tipo_materialRepositorio proveedor_tipo_materialRepositorio;

    @GetMapping("/{id}")
    @ResponseBody
    public List<Proveedor_tipo_material> listaTipoMateriales(@PathVariable("id")int id) {
        return proveedor_tipo_materialRepositorio.findByProveedor(cliente_proveedorRepositorio.findById(id).get());
    }
    
}
