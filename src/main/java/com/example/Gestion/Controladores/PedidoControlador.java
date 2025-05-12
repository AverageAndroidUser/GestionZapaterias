package com.example.Gestion.Controladores;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Gestion.Entidades.ClienteProveedor.Cliente_proveedorRepositorio;
import com.example.Gestion.Entidades.ColoresProductos.Colores_ProcuctosRepositorio;
import com.example.Gestion.Entidades.ColoresProductos.Colores_productos;
import com.example.Gestion.Entidades.DetallesPedidos.Detalles_pedidoRepositorio;
import com.example.Gestion.Entidades.DetallesPedidos.Detalles_pedidos;
import com.example.Gestion.Entidades.Pedidos.Pedidos;
import com.example.Gestion.Entidades.Pedidos.PedidosRepositorio;
import com.example.Gestion.Entidades.Productos.Productos;
import com.example.Gestion.Entidades.Productos.ProductosRepositorio;
import com.example.Gestion.Entidades.TallasProductos.Tallas_productos;
import com.example.Gestion.Entidades.TallasProductos.Tallas_productosRepositorio;
import com.example.Gestion.Entidades.Usuarios.UsuarioLog;
import com.example.Gestion.Entidades.Usuarios.Usuarios;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/GestionZapaterias/Pedidos")
public class PedidoControlador {

    @Autowired PedidosRepositorio pedidosRepositorio;
    @Autowired Detalles_pedidoRepositorio detalles_pedidoRepositorio;
    @Autowired ProductosRepositorio productosRepositorio;
    @Autowired UsuarioLog usuarioLog;
    @Autowired Cliente_proveedorRepositorio clienteRepositorio;
    @Autowired Colores_ProcuctosRepositorio Colores_ProcuctosRepositorio;
    @Autowired Tallas_productosRepositorio Tallas_productosRepositorio;

    @GetMapping("/NuevoPedido")
    public String nuevoPedido(Model model, HttpSession session) { 
        session.removeAttribute("listaDetallesPedido");
        Usuarios usuario = usuarioLog.correoUsuario();
        Map<Integer, String> listaEstados = new LinkedHashMap<>();
        listaEstados.put(1, "Pendiente");
        listaEstados.put(2, "Entregado");
        listaEstados.put(3, "Cancelado");

        model.addAttribute("listaEstadoss", listaEstados);
        model.addAttribute("Pedidoss", new Pedidos());
        model.addAttribute("Productoss", productosRepositorio.findByUsuarioList(usuario));
        model.addAttribute("Clientess", clienteRepositorio.findByUsuarios(usuario, false));
        model.addAttribute("Coloress", Colores_ProcuctosRepositorio.findAll());
        model.addAttribute("Tallass", Tallas_productosRepositorio.findAll());

        return "Pedidos/NuevoPedido";
    }

    @GetMapping("/ProdInf/{id}")
    @ResponseBody
    public Map<String, Object> prodInfo(@PathVariable("id") int id) {
        Optional<Productos> productoOpt = productosRepositorio.findById(id);
        Map<String, Object> response = new HashMap<>();
        
        if (productoOpt.isPresent() && usuarioLog.correoUsuario().equals(productoOpt.get().getUsuario())) {
            Productos producto = productoOpt.get();

            response.put("id", producto.getIdProductos());
            response.put("nombre", producto.getNombre());
            response.put("referencia", producto.getReferencia());
            response.put("precioUnitario", producto.getPrecioUnitario());

            List<Tallas_productos> tallas = Tallas_productosRepositorio.findAll();
            List<Colores_productos> colores = Colores_ProcuctosRepositorio.findAll();

            response.put("tallas", tallas.stream().map(t -> Map.of(
                "id_Tallas_productos", t.getID_Tallas_productos(),
                "nombre", t.getNumero()
            )).collect(Collectors.toList()));

            response.put("colores", colores.stream().map(c -> Map.of(
                "id_Color_Producto", c.getID_Colores_Productos(),
                "nombre", c.getDescripcion()
            )).collect(Collectors.toList()));

            return response;
        }
        return null;
    }

    @GetMapping("/DetallePedido/agregarDetalle/{idProducto}/{idTalla}/{idColor}/{cantidad}/{idDetallePedido}")
    @ResponseBody
    public void nuevoDetallePedido(@PathVariable int idProducto, @PathVariable int idTalla, @PathVariable int idColor,
            @PathVariable int cantidad, @PathVariable Optional<Integer> idDetallePedido, HttpSession session) {

        List<Detalles_pedidos> lista = (List<Detalles_pedidos>) session.getAttribute("listaDetallesPedido");
        if (lista == null) {
            lista = new ArrayList<>();
        }

        // Si viene un ID de detalle, intentamos actualizar
        if (idDetallePedido.isPresent()) {
            for (Detalles_pedidos detalle : lista) {
                if (idDetallePedido.get().equals(detalle.getID_Detalles_pedidos())) {
                    detalle.setProductos(productosRepositorio.findById(idProducto).orElse(null));
                    detalle.setTallas_producto(Tallas_productosRepositorio.findById(idTalla).orElse(null));
                    detalle.setColores_productos(Colores_ProcuctosRepositorio.findById(idColor).orElse(null));
                    detalle.setCantidad(cantidad);
                    session.setAttribute("listaDetallesPedido", lista);
                    return;
                }
            }
        }

        // Si no hay ID o no se encontró, se busca por producto + talla + color
        Optional<Detalles_pedidos> existente = lista.stream()
                .filter(d -> d.getProductos().getIdProductos() == idProducto &&
                            d.getTallas_producto().getID_Tallas_productos() == idTalla &&
                            d.getColores_productos().getID_Colores_Productos() == idColor)
                .findFirst();

        if (existente.isPresent()) {
            existente.get().setCantidad(cantidad);
        } else {
            Detalles_pedidos nuevo = new Detalles_pedidos();
            nuevo.setProductos(productosRepositorio.findById(idProducto).orElse(null));
            nuevo.setTallas_producto(Tallas_productosRepositorio.findById(idTalla).orElse(null));
            nuevo.setColores_productos(Colores_ProcuctosRepositorio.findById(idColor).orElse(null));
            nuevo.setCantidad(cantidad);
            lista.add(nuevo);
        }

        session.setAttribute("listaDetallesPedido", lista);
    }



    @GetMapping("/DetallePedido/eliminarDetalle/{idProducto}/{idTalla}/{idColor}/{idDetallePedido}")
    @ResponseBody
    public void eliminarDetallePedido(  @PathVariable("idProducto") int idProducto,  @PathVariable("idTalla") int idTalla,
        @PathVariable("idColor") int idColor,  @PathVariable("idDetallePedido") Optional<Integer> idDetallePedido, HttpSession session) {

        List<Detalles_pedidos> lista = (List<Detalles_pedidos>) session.getAttribute("listaDetallesPedido");
        if (lista != null) {
            lista.removeIf(ptm -> ptm.getProductos().getIdProductos() == idProducto
                    && ptm.getColores_productos().getID_Colores_Productos() == idColor
                    && ptm.getTallas_producto().getID_Tallas_productos() == idTalla);
            session.setAttribute("listaDetallesPedido", lista);
        }
    }

    @PostMapping("/GuardarPedido")
    public String guardarPedido(Pedidos pedidos, HttpSession session) {
        pedidos.setUsuario(usuarioLog.correoUsuario());
        Pedidos pedidoGuardado = pedidosRepositorio.save(pedidos);

        List<Detalles_pedidos> listaEnSesion = (List<Detalles_pedidos>) session.getAttribute("listaDetallesPedido");

        if (listaEnSesion != null) {
            // Obtener los detalles actuales en la base de datos
            List<Detalles_pedidos> detallesExistentes = detalles_pedidoRepositorio.findByPedidos(pedidoGuardado);

            // Identificadores de los detalles enviados por el usuario
            Set<Integer> idsRecibidos = listaEnSesion.stream()
                .filter(d -> d.getID_Detalles_pedidos() != null)
                .map(Detalles_pedidos::getID_Detalles_pedidos)
                .collect(Collectors.toSet());

            // Eliminar detalles que ya no están en sesión
            for (Detalles_pedidos existente : detallesExistentes) {
                if (existente.getID_Detalles_pedidos() != null && !idsRecibidos.contains(existente.getID_Detalles_pedidos())) {
                    detalles_pedidoRepositorio.delete(existente);
                }
            }

            // Guardar (crear o actualizar) los detalles actuales
            for (Detalles_pedidos detalle : listaEnSesion) {
                detalle.setPedidos(pedidoGuardado);
                detalles_pedidoRepositorio.save(detalle);
            }

            session.removeAttribute("listaDetallesPedido");
        }

        return "redirect:/GestionZapaterias/Pedidos/0";
    }


    @GetMapping("EliminarPedido/{id}")
    public String eliminarPedido(@PathVariable("id") int id, Model model){
        Optional<Pedidos> pedido = pedidosRepositorio.findById(id);
        if(usuarioLog.correoUsuario().equals(pedido.get().getUsuario())){
            pedidosRepositorio.deleteById(id);
            return "redirect:/GestionZapaterias/Pedidos/0";
        }else{
            model.addAttribute("error", "Material no encontrado...");
            return "error";
        }
    }

    @GetMapping("/{pagina}")
    public String listaPedidos(@PathVariable("pagina") int pagina, @RequestParam(defaultValue = "10") int tamaño,
        @RequestParam(defaultValue = "idPedidos") String orden, @RequestParam(defaultValue = "asc") String direccion,
        @RequestParam(required = false) Byte estado, Model model) {

        Pageable pageable = PageRequest.of(pagina, tamaño);

        Page<Pedidos> paginaPedidos;

        if (estado != null) {
            paginaPedidos = pedidosRepositorio.findByUsuarioAndEstado(usuarioLog.correoUsuario(), estado, pageable);
        } else {
            paginaPedidos = pedidosRepositorio.findByUsuario(usuarioLog.correoUsuario(), pageable);
        }

        // Mapas para detalles y totales
        Map<Pedidos, List<Productos>> detallesPedidos = new LinkedHashMap<>();
        Map<Pedidos, Double> totalPorPedido = new LinkedHashMap<>();

        for (Pedidos pedido : paginaPedidos.getContent()) {
            List<Detalles_pedidos> detalles = detalles_pedidoRepositorio.findByPedidos(pedido);
            double total = detalles.stream()
                    .mapToDouble(d -> d.getCantidad() * d.getProductos().getPrecioUnitario())
                    .sum();
            detallesPedidos.put(pedido, detalles.stream().map(Detalles_pedidos::getProductos).collect(Collectors.toList()));
            totalPorPedido.put(pedido, total);
        }

        // Orden por costo total si se solicitó
        if (orden.equals("costoTotal")) {
            Comparator<Map.Entry<Pedidos, Double>> comparator = Map.Entry.comparingByValue();
            if (direccion.equalsIgnoreCase("desc")) {
                comparator = comparator.reversed();
            }

            List<Map.Entry<Pedidos, Double>> ordenados = new ArrayList<>(totalPorPedido.entrySet());
            ordenados.sort(comparator);

            Map<Pedidos, List<Productos>> detallesOrdenados = new LinkedHashMap<>();
            Map<Pedidos, Double> totalesOrdenados = new LinkedHashMap<>();
            for (Map.Entry<Pedidos, Double> entry : ordenados) {
                detallesOrdenados.put(entry.getKey(), detallesPedidos.get(entry.getKey()));
                totalesOrdenados.put(entry.getKey(), entry.getValue());
            }
            detallesPedidos = detallesOrdenados;
            totalPorPedido = totalesOrdenados;
        }

        model.addAttribute("Pedidoss", detallesPedidos);
        model.addAttribute("Totales", totalPorPedido);
        model.addAttribute("paginaa", paginaPedidos);
        model.addAttribute("orden", orden);
        model.addAttribute("direccion", direccion);
        model.addAttribute("estado", estado); // para mantener selección en el filtro
        return "Pedidos/ListaPedidos";
    }


    //Editar pedido
    @GetMapping("/EditarPedido/{id}")
    public String editarPedido(@PathVariable("id") int id, Model model, HttpSession session){
        Optional<Pedidos> pedido = pedidosRepositorio.findById(id);
        Usuarios usuario = usuarioLog.correoUsuario();
        if(usuario.equals(pedido.get().getUsuario())){
            
            List<Detalles_pedidos> detalles = detalles_pedidoRepositorio.findByPedidos(pedido.get());
            session.setAttribute("listaDetallesPedido", detalles);

            Map<Integer, String> listaEstados = new LinkedHashMap<>();
            listaEstados.put(1, "Pendiente");
            listaEstados.put(2, "Entregado");
            listaEstados.put(3, "Cancelado");

            model.addAttribute("listaEstadoss", listaEstados);
            model.addAttribute("Pedidoss", pedido.get());
            model.addAttribute("Clientess", clienteRepositorio.findByUsuarios(usuario, false));
            model.addAttribute("Tallass", Tallas_productosRepositorio.findAll());
            model.addAttribute("Coloress", Colores_ProcuctosRepositorio.findAll());
            model.addAttribute("Productoss", productosRepositorio.findByUsuarioList(usuario));
            return "Pedidos/NuevoPedido";

        }else{
            model.addAttribute("error", "Pedido no encontrado, o sin permisos.");
            return "error";
        }
    }

    @GetMapping("/VerPedido/{id}")
    public String verPedido(@PathVariable("id") int id, Model model){
        Optional<Pedidos> pedido = pedidosRepositorio.findById(id);
        if(usuarioLog.correoUsuario().equals(pedido.get().getUsuario())){
            List<Detalles_pedidos> detalles = detalles_pedidoRepositorio.findByPedidos(pedido.get());
            double total = detalles.stream()
            .mapToDouble(d -> d.getCantidad() * d.getProductos().getPrecioUnitario())
            .sum();
            model.addAttribute("TotalPedido", total);
            model.addAttribute("Detalless", detalles);
            model.addAttribute("Pedidoss", pedido.get());
            return "Pedidos/VerPedido";
        }else{
            model.addAttribute("error", "Pedido no encontrado, o sin permisos.");
            return "error";
        }
    }
}
