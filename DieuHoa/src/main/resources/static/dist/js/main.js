$(document).ready(function() {

	$('.nBtn, .table .eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
		// for update user
		if (text == 'Edit') {
			$.get(href, function(thuonghieu, status) {
				$('.myFormUpdate #id').val(thuonghieu.id);
				$('.myFormUpdate #tenTH').val(thuonghieu.tenTH);
				$('.myFormUpdate #dateCreated').val(thuonghieu.dateCreated);
				
			});
			$('.myFormUpdate #updateModal').modal();
			
		} else {
			// for creating user
			$('.myFormCreate #tenTH').val('');
			$('.myFormCreate #myModalCreate').modal();
		}
	});
});
$(document).ready(function() {

	$('.nLSP, .table .eLSP').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
		// for update user
		if (text == 'Edit') {
			$.get(href, function(loaisanpham, status) {
				$('.myFormUpdate #id').val(loaisanpham.id);
				$('.myFormUpdate #name').val(loaisanpham.name);
				$('.myFormUpdate #dateCreated').val(loaisanpham.dateCreated);
				
			});
			$('.myFormUpdate #updateModal').modal();
			
		} else {
			// for creating user
			$('.myFormCreate #name').val('');
			$('.myFormCreate #myModalCreate').modal();
		}
	});
});
$(document).ready(function() {

	$('.nLM, .table .eLM').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
		// for update user
		if (text == 'Edit') {
			$.get(href, function(loaimay, status) {
				$('.myFormUpdate #id').val(loaimay.id);
				$('.myFormUpdate #name').val(loaimay.name);
				$('.myFormUpdate #dateCreated').val(loaimay.dateCreated);
				
			});
			$('.myFormUpdate #updateModal').modal();
			
		} else {
			// for creating user
			$('.myFormCreate #name').val('');
			$('.myFormCreate #myModalCreate').modal();
		}
	});
});
$(document).ready(function() {

	$('.nCN, .table .eCN').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
		// for update user
		if (text == 'Edit') {
			$.get(href, function(congnghe, status) {
				$('.myFormUpdate #id').val(congnghe.id);
				$('.myFormUpdate #name').val(congnghe.name);
				$('.myFormUpdate #dateCreated').val(congnghe.dateCreated);
				
			});
			$('.myFormUpdate #updateModal').modal();
			
		} else {
			// for creating user
			$('.myFormCreate #name').val('');
			$('.myFormCreate #myModalCreate').modal();
		}
	});
});
$(document).ready(function() {

	ClassicEditor
	.create( document.querySelector( '#editor' ), {
		ckfinder: {
			uploadUrl: '/ckfinder/connector?command=QuickUpload&type=Files&responseType=json',
		},
		toolbar: [ 'ckfinder', 'imageUpload', '|', 'heading', '|', 'bold', 'italic', '|', 'undo', 'redo' ]
	} )
	.catch( error => {
		console.error( error );
	} );
});
$(document).ready(function() {
	CKFinder.widget( 'ckfinder-widget', {
		displayFoldersPanel: false,
		width: '100%',
		height: 700
	} );
});

$(function() {
	var images = [];
	$('.image-confirm-delete').on('click', function(e) {
		e.preventDefault();
		var id = $(this).data('id');
		var name = $(this).data('name');
		images.push(name);
		$('#removeImages').val(images);
		$('#imageindex' + id).hide();

	});

});