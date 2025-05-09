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