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