$(document).ready(function(){
   
   aplicarListeners();
   
});

var aplicarListeners = function(){
    
  $('#btn-salvar').click(function(){
        var url='pizzas';
        var dadosPizza =  $('#form-pizza').serialize(); 
        $.post(url,dadosPizza)
          .done(function(pagina) {
             $('#secao-pizza').html(pagina);
             aplicarListeners();
          })
          .fail(function() {
            alert( 'Erro ao salvar');
          })
          .always('modal-ingrediente').modal('hide')
    }); 
    
    $('.btn-deletar').click(function(){
        var id = $(this).parents('tr').data('id');
        
        $.ajax({
            url: 'pizzas/'+id,
            type: 'DELETE',
            success: function(result) {
                 $('tr[data-id="'+id+'"]').remove(); 
                 
                 var pizzas= parseInt($('#quantidade-pizzas').text());
                 $('#quantidade-pizzas').text(pizzas-1);
            }
        });
        
        
    });
    
};