$(document).ready(function() {
	if(localStorage.getItem("RemoveItemSuccess")) {
		toastr.info('Il prodotto è stato rimosso');
	    localStorage.clear();
	}
	if(localStorage.getItem("RemoveItemsSuccess")) {
		toastr.info('I prodotti selezionati sono stati rimossi.');
	    localStorage.clear();
	}
	if(localStorage.getItem("RemoveCategorySuccess")) {
		toastr.info('La categoria è stata rimossa');
	    localStorage.clear();
	}
	if(localStorage.getItem("RemoveCategoriesSuccess")) {
		toastr.info('Le categorie selezionate sono state rimosse.');
	    localStorage.clear();
	}
	
	$('.delete-product').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/
		
		var id=$(this).attr('id');

		bootbox.confirm({
			message: "Sicuro di voler rimuovere questo prodotto? L\'operazione è irriversibile.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: {'id':id},
						contentType: "application/json",
						dataType: "json",
						success: function(res) {
							var obj = JSON.parse(res);
							
							if(!obj) {
								toastr.error('Attenzione! Non puoi rimuovere questo prodotto.');
							}
							else  {
								localStorage.setItem("RemoveItemSuccess", true);
								location.reload();
							}
							console.log(res); 
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	});
	
	$('.delete-category').on('click', function (){
		/*<![CDATA[*/
		var pathList = /*[[@{/}]]*/'categoryList';
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/
		
		var id=$(this).attr('id');

		bootbox.confirm({
			message: "Sicuro di voler rimuovere questa categoria? L\'operazione è irriversibile.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: {'id':id},
						contentType: "application/json",
						dataType: "json",
						success: function(res) {
							var obj = JSON.parse(res);
							
							if(!obj) {
								toastr.error('Attenzione! Non puoi rimuovere questa categoria.');
							}
							else  {
								localStorage.setItem("RemoveCategorySuccess", true);
								window.location.replace(pathList);
							}
							console.log(res); 
							},
						error: function(res){
							console.log(res); 
							window.location.replace(pathList);
							}
					});
				}
			}
		});
	});
	
	
	
//	$('.checkboxBook').click(function () {
//        var id = $(this).attr('id');
//        if(this.checked){
//            bookIdList.push(id);
//        }
//        else {
//            bookIdList.splice(bookIdList.indexOf(id), 1);
//        }
//    })
	
	$('#deleteSelected').click(function() {
		var idList= $('.checkboxProduct');
		var bookIdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				bookIdList.push(idList[i]['id'])
			}
		}
		
		console.log(bookIdList);
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Sicuro di voler rimuovere i prodotti selezionati? L\'operazione è irriversibile.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(bookIdList),
						contentType: "application/json",
						success: function(res) {
							if(res){
								localStorage.setItem("RemoveItemsSuccess", true);
								location.reload()
							}
							else {
								toastr.error('Attenzione! Non puoi rimuovere i prodotti selezionati.');
							}	
							console.log(res); 
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	});
	
	$("#selectAllCategory").click(function() {
		if($(this).prop("checked")==true) {
			$(".checkboxCategory").prop("checked",true);
		} else if ($(this).prop("checked")==false) {
			$(".checkboxCategory").prop("checked",false);
		}
	})
	
	$('#deleteCategorySelected').click(function() {
		var idList= $('.checkboxCategory');
		var categoryIdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				categoryIdList.push(idList[i]['id'])
			}
		}
		
		console.log(categoryIdList);
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Sicuro di voler rimuovere le categorie selezionate? L\'operazione è irriversibile.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(categoryIdList),
						contentType: "application/json",
						success: function(res) {
							if(res){
								localStorage.setItem("RemoveItemsSuccess", true);
								location.reload()
							}
							else {
								toastr.error('Attenzione! Non puoi rimuovere le categorie selezionate.');
							}	
							console.log(res); 
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	});
	
	$("#selectAllProduct").click(function() {
		if($(this).prop("checked")==true) {
			$(".checkboxProduct").prop("checked",true);
		} else if ($(this).prop("checked")==false) {
			$(".checkboxProduct").prop("checked",false);
		}
	})
});

$(document).ready(function(){
	var i=0;
	
	$(".div-attr-contained").each(function() {
		$('#button-del-attr-'+i).click(function() {
//        alert( this.id );
      	  for(var j=0; j<=i; j++){
        	if(this.id===('button-del-attr-'.concat(j))){
//        		alert( "button "+j);
        		$(".del-attr-"+j).remove();
        		break;
        	}
      	  }
		});
		i++;
	});
    
    $("#btn-attr").click(function(){
        $("#toAppend").append('<div class="form-group del-attr-'+i+'">'+
						'<label class="col-md-2 control-label" for="model">Attributo ' +
							'</label>' +
						'<div class="col-md-8">' +
							'<input type="text" class="form-control" name="product.productAttributes['+i+'].name"' +
								'id="product.productAttributes'+i+'.name"' +
								'placeholder="Nome Attributo" />' +
							'<textarea type="text" class="form-control" name="product.productAttributes['+i+'].value"' +
								'id="product.productAttributes'+i+'.value"' + 
								'placeholder="Valore Attributo" /> <span class="help-block">' +
								'Aggiungi (opzionalmente) un nome e una descrizione di una caratteristica del prodotto</span>' +
							'<button type="button" id="button-del-attr-'+i+'" class="btn btn-danger " ' +
								'style="margin-bottom:15px;">Delete Attributo</button>' +
						'</div>' +
					'</div>');
        tinymce.init({ selector:'textarea' });
        
        $('#button-del-attr-'+i).click(function() {
//        	alert( this.id );
        	for(var j=0; j<=i; j++){
          		if(this.id===('button-del-attr-'.concat(j))){
//          		alert( "button "+j);
          			$(".del-attr-"+j).remove();
          			break;
          		}
        	}
        });
        
        i++;
    });
});

$(document).ready(function(){
	var i=0;
	
	$(".div-cat-contained").each(function() {
		$('#button-del-cat-'+i).click(function() {
//        alert( this.id );
      	  for(var j=0; j<=i; j++){
        	if(this.id===('button-del-cat-'.concat(j))){
//        		alert( "button "+j);
        		$(".del-cat-"+j).remove();
        		break;
        	}
      	  }
		});
		i++;
	});
    
    $("#btn-cat").click(function(){
        $("#toAppend").append('<div class="form-group del-cat-'+i+'">'+
						'<label class="col-md-2 control-label" for="model">Sotto-Categoria ' +
							'</label>' +
						'<div class="col-md-8">' +
							'<input type="text" class="form-control" name="categories['+i+'].name"' +
								'id="categories'+i+'.name"' +
								'placeholder="nome Sotto-Categoria" />' +
							'<span class="help-block">' +
								'Aggiungi (opzionalmente) il nome di ' +
							'una Sotto-Categoria</span>' +
							'<button type="button" id="button-del-cat-'+i+'" class="btn btn-danger " ' +
								'style="margin-bottom:15px;">Delete Sotto-Categoria</button>' +
						'</div>' +
					'</div>');
        
        $('#button-del-cat-'+i).click(function() {
//        	alert( this.id );
        	for(var j=0; j<=i; j++){
          		if(this.id===('button-del-cat-'.concat(j))){
//          		alert( "button "+j);
          			$(".del-cat-"+j).remove();
          			break;
          		}
        	}
        });
        
        i++;
    });
});

//chiamata ajax con il metodo $.post di jquery per avere le sottocategorie relative alla
//categoria selezionata
$(document).ready(function(){
	$("#category").change(function() {
		if($.isNumeric($('#category option:selected').val())){
			$.post("subcategoriesByCategoryId", {categoryId : $('#category option:selected').val()}, function(data, status){
				if (status == 'success') {
					//pulisci le sottocategorie caricate in precedenza prima di
					//aggiungere quelle della categoria selezionata
			        $('#subcategory').find('option')
				    .remove()
				    .end().append('<option selected="selected">' + 
				    		'Nessuna Sotto-Categoria</option>');
			        var categoryList = JSON.parse(data);
			        for(i in categoryList){
				        $('#subcategory').append($('<option>', {
						    value: categoryList[i].name,
						    text: categoryList[i].name
						}));
			        }
		        }
		    });
		} else {
			$.post("subcategoriesByCategoryName", {categoryName : $('#category option:selected').val()}, function(data, status){
				if (status == 'success') {
					//pulisci le sottocategorie caricate in precedenza prima di
					//aggiungere quelle della categoria selezionata
			        $('#subcategory').find('option')
				    .remove()
				    .end().append('<option selected="selected">' + 
				    		'Nessuna Sotto-Categoria</option>');
			        var categoryList = JSON.parse(data);
			        for(i in categoryList){
				        $('#subcategory').append($('<option>', {
						    value: categoryList[i].name,
						    text: categoryList[i].name
						}));
			        }
		        }
		    });
		}
	});
});

//se vi è un errore nella chiamata ajax per ottenere le sottocategorie
//elimina quelle caricate in precedenza
$(document).ajaxError(function(){
	 $('#subcategory').find('option')
	    .remove()
	    .end().append('<option selected="selected" disabled="disabled">' + 
	    		'Per favore selezionare un\'opzione...</option>');
});
