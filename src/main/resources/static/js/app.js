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

function buscarProductos() {
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