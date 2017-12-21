$(document).ready(function(){
   
   aplicarListeners();
   
   aplicarListenerBtnSalvar();
   
});

 
 var limparDados = function(){
      $('#id').val('');
      $('#nome').val('');
      $('#preco').val('');
      $('#categoria').val('');
      $('#ingredientes option').attr('selected', false);
     
 }; 
 
 
var aplicarListenerBtnSalvar = function(){
    $('#btn-salvar').click(function(){
        var url='pizzas';
        var dadosPizza =  $('#form-pizza').serialize(); 
        console.log('dados '+ dadosPizza);
        $.post(url,dadosPizza)
          .done(function(pagina) {
             $('#secao-pizza').html(pagina);
             aplicarListeners();
          })
          .fail(function() {
            alert( 'Erro ao salvar');
          })
          .always(function(){
                $('#modal-ingrediente').modal('hide');
            });
              
    }); 
};

var aplicarListeners = function(){
    
  $('#modal-ingrediente').on('hide.bs.modal', limparDados);
   
   $('.btn-editar').click(function(){
       var idPizza = $(this).parents('tr').data('id');
       $.ajax({
          url:'pizzas/'+idPizza,
          type:'GET',
          success: function(pizza){
              $('#id').val(pizza.id);
              $('#nome').val(pizza.nome);
              $('#preco').val(pizza.preco);
              $('#categoria').val(pizza.categoria);
              $('#ingredientes').val(pizza.ingredientes);
             
              pizza.ingredientes.forEach(function(ingrediente){
                  var id = ingrediente.id;
                  $('#ingredientes option[value='+id+']').attr('selected',true);
              })
              
              $('#modal-pizza').modal('show');
                        
              }
       });
    });
    
    $('.btn-deletar').click(function(){
        var id = $(this).parents('tr').data('id');
        var csrf = $('#csrf').val();
        console.log("deletar "+id);
        $.ajax({
            url: 'pizzas/'+id,
            type: 'DELETE',
            headers: {'X-CSRF-TOKEN': csrf},
            success: function(result) {
                 $('tr[data-id="'+id+'"]').remove(); 
                 
                 var pizzas= parseInt($('#quantidade-pizzas').text());
                 $('#quantidade-pizzas').text(pizzas-1);
            }
        });
    });
    
    
    
};