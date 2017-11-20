$(document).ready(function(){
	
	aplicarListeners();
	
	aplicatListenerBtnSalvar();
	
});

var aplicatListenerBtnSalvar = function(){
	$('#btn-salvar').click(function(){
		var url = 'ingredientes';
		var dadosIngrediente = $('#form-ingrediente').serialize();
		
		$.post(url, dadosIngrediente)
			.done(function(pagina){
				$('#secao-ingredientes').html(pagina)
				aplicarListeners();
				console.log('ja salvo ')
			})
			.fail(function(){
				alert('Erro ao salvar!');
				
			})
			.always(function(){
				$('#modal-ingrediente').modal('hide');
			});
	});
}


var limparModal = function(){
	$('#id').val('');
	$('#nome').val('');
	$('#categoria').val('');
};


var aplicarListeners = function(){
	$('#modal-ingrediente').on('hide.bs.modal', limparModal);
	
	$('.btn-editar').on('click', function(){
		var id = $(this).parents('tr').data('id');
		console.log('herennnn '+id)
		$.ajax({
		      type:'GET',
		      url:'ingredientes/'+id,
			     success:function(ingrediente){
				$('#id').val(ingrediente.id);
				$('#nome').val(ingrediente.nome);
				$('#categoria').val(ingrediente.categoria);
				
				$('#modal-ingrediente').modal('show');
			    }
			})
	
	
	$('.btn-deletar').click(function(){
		var id = $(this).parents('tr').data('id');
		console.log('herennnn '+id)
		$.ajax({
			url : "ingredientes/"+id,
			type: 'DELETE',
		    success: function(result) {
		    	$('tr[data-id="'+id+'"]').remove();
				var ingredientes = parseInt( $('#quantidade-ingredientes').text() );
		    	$('#quantidade-ingredientes').text(ingredientes - 1);
		    }
		});
		
	});
})
}

