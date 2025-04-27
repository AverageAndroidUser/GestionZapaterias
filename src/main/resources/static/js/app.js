const signUpBtn = document.getElementById('signUp');
const signInBtn = document.getElementById('signIn');
const container = document.getElementById('container');
const NumeroCelular = document.getElementById('NumeroCelular');

signUpBtn.addEventListener('click', () => {
  container.classList.add('right-panel-active');
});

signInBtn.addEventListener('click', () => {
  container.classList.remove('right-panel-active');
});

NumeroCelular.addEventListener('input', function() {
    let formattedNumber = this.value.replace(/\D/g, '');
    if (formattedNumber.length > 0) {
        formattedNumber = formattedNumber.match(/(\d{0,3})(\d{0,3})(\d{0,4})/);
        this.value = !formattedNumber[2] ? formattedNumber[1] : formattedNumber[1] + '-' + formattedNumber[2] + (formattedNumber[3] ? '-' + formattedNumber[3] : '');
    }
});

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