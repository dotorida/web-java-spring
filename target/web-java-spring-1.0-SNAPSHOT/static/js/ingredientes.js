$(document).ready(function(){

   aplicarListeners();
    
      aplicarListenerBtnSalvar();
  
 })
 
 var aplicarListenerBtnSalvar = function(){
     
     $('#btn-salvar').click(function(){
        
        var url='ingredientes';
        var dadosIngrediente =  $('#form-ingrediente').serialize(); 
       // console.log(dadosIngrediente);
        $.post(url,dadosIngrediente)
          .done(function(pagina) {
             $('#secao-ingrediente').html(pagina);
             aplicarListeners();
          })
          .fail(function() {
            alert( 'Erro ao salvar');
          })
          .always(function(){
               $('#modal-ingrediente').modal('hide');
            })
    });
 }
 
 var limparDados = function(){
       $('#id').val('');
       $('#nome').val('');
       $('#categoria').val('');
 }
 
 
    var aplicarListeners = function(){
      
     $('#modal-ingrediente').on('hide.bs.modal', limparDados);
      
      $('.btn-editar').click(function(){
         var id = $(this).parents('tr').data('id'); 
         var url ='ingredientes/'+id;
         
         $.get(url)
            .success(function(ingrediente){
                
                $('#id').val(ingrediente.id);
                $('#nome').val(ingrediente.nome);
                $('#categoria').val(ingrediente.categoria);
            
                $('#modal-ingrediente').modal('show');
            });
         
      });
      
      
    
    $('.btn-deletar').click(function(){
       
       var id = $(this).parents('tr').data('id'); 
       var ingredientes = $('#quantidade-ingredientes').text();
       
       $.ajax({
                url: 'ingredientes/'+id,
                type: 'DELETE',
                success: function(result) {
                  $('tr[data-id="'+id+'"]').remove();     
                   $('#quantidade-ingredientes').text(ingredientes-1);
                }
        });
    })
 }
  
  

   
   
  