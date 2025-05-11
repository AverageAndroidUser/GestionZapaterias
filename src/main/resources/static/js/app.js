const signUpBtn = document.getElementById('signUp');
const signInBtn = document.getElementById('signIn');
const container = document.getElementById('container');
const NumeroCelular = document.getElementById('NumeroCelular');

if (signUpBtn && signInBtn && container) {
  signUpBtn.addEventListener('click', () => {
    container.classList.add('right-panel-active');
  });

  signInBtn.addEventListener('click', () => {
    container.classList.remove('right-panel-active');
  });
}

if (NumeroCelular) {
  NumeroCelular.addEventListener('input', function () {
    this.value = this.value.replace(/\D/g, '') // elimina letras
      .replace(/(\d{3})(\d{0,3})(\d{0,4}).*/, function (_, p1, p2, p3) {
        return [p1, p2, p3].filter(Boolean).join('-');
      });
  });
}

$(document).ready(function() {
  $('#departamentoLista').change(function() {
    var departamentoId = $(this).val();
    $.ajax({
      url: '/Municipios/' + departamentoId,
      type: 'GET',
      success: function(data){
        $('#municipioLista').empty();
        $.each(data, function(index, municipio){
          $('#municipioLista').append('<option value="' + municipio.id_Municipios + '">' + municipio.nombre + '</option>');
          //console.log("------> "+ ciudad.nombreCiu);
          //console.log("------> "+ ciudad.id_Ciudad);
        });
      }
    });
  });
});

$(document).ready(function() {
  $('#proveedorLista').change(function() {
    var proveedorId = $(this).val();
    $.ajax({
      url: '/TipoMateriales/' + proveedorId,
      type: 'GET',
      success: function(data){
        $('#tipoMaterialLista').empty();
        $.each(data, function(index, TipoMaterial){
          $('#tipoMaterialLista').append('<option value="' + TipoMaterial.tipo_material.id_Tipo_material + '">' + TipoMaterial.tipo_material.descripcion + '</option>');
        });
      }
    });
  });
});

function buscarMateriales() {
  var keyword = document.getElementById("barraBusqueda").value;
  fetch(`/GestionZapaterias/Materiales/Buscar?nombre=` + keyword)
      .then(response => response.json())
      .then(data => {
          console.log(data);  // Verifica la estructura del JSON devuelto
          var table = document.getElementById("tablaMateriales");
          table.innerHTML = ''; //valores en el html como "clienteProveedores.nombre" nombre se pone en minuscula por que no lo lee como "Nombre" lo mismo con "Descripcion"
          data.forEach(material => {
              table.innerHTML += `
                  <tr>
                      <td>${material.nombre}</td>
                      <td>${material.descripcion}</td>
                      <td>${material.cantidad}</td>
                      <td>${material.costoUnidad}</td>
                      <td>${material.stockMinimo}</td>
                      <td>${material.fechaActualizacion}</td>
                      <td>${material.clienteProveedores.nombre}</td> 
                      <td>${material.tipoMaterial.descripcion}</td>
                      <td>
                          <a href="/GestionZapaterias/Materiales/EditarMaterial/${material.id_Materiales}">EDITAR</a>
                          <a href="/GestionZapaterias/Materiales/EliminarMaterial/${material.id_Materiales}">ELIMINAR</a>
                      </td>
                  </tr>
              `;
          });
      });
}

function agregarProTipM(idTipo, button) {
  console.log("------> ");
  var xhr = new XMLHttpRequest();
  xhr.open("GET", '/GestionZapaterias/Proveedores/Nuevo/TipoMaterialProveedor/' + encodeURIComponent(idTipo), true);
  xhr.onreadystatechange = function () {
      if (xhr.readyState == 4 && xhr.status == 200) {
          button.disabled = true;
          button.textContent = "AGREGADO"

          var botonEliminar = document.getElementById('botonEliminar_' + idTipo);
          botonEliminar.disabled = false;
      }
  };
  xhr.send();
}

function eliminarProTipM(idTipo, button) {
  var xhr = new XMLHttpRequest();
  xhr.open("GET", '/GestionZapaterias/Proveedores/Eliminar/TipoMaterialProveedor/' + encodeURIComponent(idTipo), true);
  xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var botonAgregar = document.getElementById('botonAgregar_' + idTipo);
        botonAgregar.disabled = false;
        botonAgregar.textContent = "AGREGAR";

        var botonEliminar = document.getElementById('botonEliminar_' + idTipo);
        botonEliminar.disabled = true;
      }
  };
  xhr.send();
}

function buscarProductos() {
  var keyword = document.getElementById("barraBusqueda").value;
  fetch(`/GestionZapaterias/Productos/Buscar?nombre=` + keyword)
      .then(response => response.json())
      .then(data => {
          console.log(data);  
          var table = document.getElementById("tablaProductos");
          table.innerHTML = ''; 
          data.forEach(producto => {
              table.innerHTML += `
                  <tr>
                      <td>${producto.nombre}</td>
                      <td>${producto.descripcion}</td>
                      <td>${producto.precioUnitario}</td>
                      <td>${producto.costo_unidad}</td>
                      <td>${producto.referencia}</td>
                      <td>${producto.tipo_producto.descripcion}</td>
                      <td>${producto.materiales.nombre}</td> 
                      <td>
                          <a href="/GestionZapaterias/Productos/EditarProducto/${producto.idProductos}">EDITAR</a>
                          <a href="/GestionZapaterias/Productos/EliminarProducto/${producto.idProductos}">ELIMINAR</a>
                      </td>
                  </tr>
              `;
          });
      });
}

//----------------------------PEDIDOS----------------------------//

let contadorDetalle = 0;

document.getElementById("btnAgregarDetalle").addEventListener("click", function () {
    const idProducto = document.getElementById("productoInfo").value;
    if (!idProducto) {
        alert("Seleccione un producto antes de agregar.");
        return;
    }

    fetch(`/GestionZapaterias/Pedidos/ProdInf/${idProducto}`)
        .then(response => response.json())
        .then(data => {
            if (data) {
                contadorDetalle++;

                const detalleHTML = `
                <div class="card mb-3 detalle-item" id="detalle-${contadorDetalle}" data-producto="${data.id}" data-precio="${data.precioUnitario}">
                    <div class="card-body">
                        <h5 class="card-title">${data.nombre}</h5>
                        <p><strong>Referencia:</strong> ${data.referencia}</p>
                        <p><strong>Precio Unitario:</strong> $${data.precioUnitario.toLocaleString()}</p>

                        <div class="row mb-2">
                            <div class="col-md-3">
                                <label>Talla:</label>
                                <select class="form-select talla-select">
                                    <option value="" hidden>Seleccione</option>
                                    ${data.tallas.map(t => `<option value="${t.id_Tallas_productos}">${t.nombre}</option>`).join('')}
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label>Color:</label>
                                <select class="form-select color-select">
                                    <option value="" hidden>Seleccione</option>
                                    ${data.colores.map(c => `<option value="${c.id_Color_Producto}">${c.nombre}</option>`).join('')}
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label>Cantidad:</label>
                                <input type="number" min="1" class="form-control cantidad-input" value="1">
                            </div>
                            <div class="col-md-3 d-flex flex-column justify-content-end">
                                <p class="mt-4"><strong>Subtotal:</strong> $<span class="subtotal">0</span></p>
                            </div>
                        </div>

                        <button type="button" class="btn btn-primary btnAgregarServidor" data-id="${contadorDetalle}">
                            <i class="bi bi-plus-square"></i> Agregar
                        </button>

                        <button type="button" class="btn btn-danger btnEliminarServidor" data-id="${contadorDetalle}">
                            <i class="bi bi-trash"></i> Eliminar
                        </button>
                    </div>
                </div>
                `;

                document.getElementById("contenedorDetalles").insertAdjacentHTML('beforeend', detalleHTML);
                actualizarTotales();
            }
        })
        .catch(err => console.error("Error cargando producto:", err));
});

document.addEventListener("click", function (e) {
    // Botón Agregar al servidor
    if (e.target.closest('.btnAgregarServidor')) {
        const btn = e.target.closest(".btnAgregarServidor");
        const idDetalle = btn.dataset.id;
        const contenedor = document.getElementById(`detalle-${idDetalle}`);
        const idProducto = contenedor.dataset.producto;
        const idTalla = contenedor.querySelector(".talla-select").value;
        const idColor = contenedor.querySelector(".color-select").value;
        const cantidad = parseInt(contenedor.querySelector(".cantidad-input").value || "1");
        const precio = parseFloat(contenedor.dataset.precio);

        if (!idTalla || !idColor || cantidad <= 0) {
            alert("Seleccione talla, color y cantidad antes de agregar.");
            return;
        }
        fetch(`/GestionZapaterias/Pedidos/DetallePedido/agregarDetalle/${idProducto}/${idTalla}/${idColor}/${cantidad}/${idDetalle}`)
            .then(() => {
                // Actualiza subtotal
                const subtotal = cantidad * precio;
                contenedor.querySelector(".subtotal").textContent = subtotal.toLocaleString();
                actualizarTotales();

                // Desactiva el botón Agregar
                btn.disabled = true;
            })
            .catch(err => console.error("Error al agregar:", err));
    }

    // Botón Eliminar
    if (e.target.closest('.btnEliminarServidor')) {
        const btn = e.target.closest(".btnEliminarServidor");
        const idDetalle = btn.dataset.id;
        const contenedor = document.getElementById(`detalle-${idDetalle}`);
        const idProducto = contenedor.dataset.producto;
        const idTalla = contenedor.querySelector(".talla-select").value;
        const idColor = contenedor.querySelector(".color-select").value;

        if (idTalla && idColor) {
            fetch(`/GestionZapaterias/Pedidos/DetallePedido/eliminarDetalle/${idProducto}/${idTalla}/${idColor}/${idDetalle}`)
                .then(() => {
                    contenedor.remove();
                    actualizarTotales();
                })
                .catch(err => console.error("Error al eliminar:", err));
        } else {
            contenedor.remove();
            actualizarTotales();
        }
    }
});

// Recalcula subtotales y total general
function actualizarTotales() {
    let total = 0;
    document.querySelectorAll(".detalle-item").forEach(item => {
        const precio = parseFloat(item.dataset.precio);
        const cantidad = parseInt(item.querySelector(".cantidad-input")?.value || "0");
        const subtotal = precio * cantidad;
        item.querySelector(".subtotal").textContent = subtotal.toLocaleString();
        total += subtotal;
    });

    document.getElementById("totalGeneral").textContent = total.toLocaleString();
}
//----------------------------FIN PEDIDOS----------------------------//

document.addEventListener("click", function (e) {
    // Botón Agregar al servidor
    if (e.target.closest('.btnAgregarServidorEditado')) {
        const btn = e.target.closest(".btnAgregarServidorEditado");
        const idDetalle = btn.dataset.id;
        const contenedor = document.getElementById(`detalle-${idDetalle}`);
        const idProducto = contenedor.dataset.producto;
        const idTalla = contenedor.querySelector(".talla-select").value;
        const idColor = contenedor.querySelector(".color-select").value;
        const cantidad = parseInt(contenedor.querySelector(".cantidad-input").value || "1");
        const precio = parseFloat(contenedor.dataset.precio);

        if (!idTalla || !idColor || cantidad <= 0) {
            alert("Seleccione talla, color y cantidad antes de agregar.");
            return;
        }
        fetch(`/GestionZapaterias/Pedidos/DetallePedido/agregarDetalle/${idProducto}/${idTalla}/${idColor}/${cantidad}/${idDetalle}`)
            .then(() => {
                // Actualiza subtotal
                const subtotal = cantidad * precio;
                contenedor.querySelector(".subtotal").textContent = subtotal.toLocaleString();
                actualizarTotales();

                // Desactiva el botón Agregar
                btn.disabled = true;
            })
            .catch(err => console.error("Error al agregar:", err));
    }

    // Botón Eliminar
    if (e.target.closest('.btnEliminarServidorEditado')) {
        const btn = e.target.closest(".btnEliminarServidorEditado");
        const contenedor = btn.closest(".detalle-item");
        const idDetalle = contenedor.querySelector(".detalle-id").value; // Cambiado para tomar el valor del input hidden
        const idProducto = contenedor.dataset.producto;
        const idTalla = contenedor.querySelector(".talla-select").value;
        const idColor = contenedor.querySelector(".color-select").value;

        if (idTalla && idColor) {
            fetch(`/GestionZapaterias/Pedidos/DetallePedido/eliminarDetalle/${idProducto}/${idTalla}/${idColor}/${idDetalle}`)
                .then(() => {
                    contenedor.remove();
                    actualizarTotales();
                })
                .catch(err => console.error("Error al eliminar:", err));
        } else {
            contenedor.remove();
            actualizarTotales();
        }
    }
});