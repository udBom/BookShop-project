function quantity(cseq, index, maxQuantity){
	var temp = "quantity";
	temp += index;
	var quantity = document.getElementById(temp).value;
	var user_id = $("#user_id").val();
	if(quantity <= 1){
		quantity = 1;
	} else if(quantity > maxQuantity){
		alert("현재 선택한 품목의 남은 수량은 "+maxQuantity+"권 입니다.");
		quantity = maxQuantity;
	}
	$.ajax({
    	url :'updateCart',
	    type :'POST',
	    async : false,
	    data : {'cseq':cseq, 'quantity':quantity, 'user.id' : user_id},
 	})
	    	.done(function(fragment){
	    		 $("#listTable").replaceWith(fragment);
	});
}

function cartDelete(cseq){
	var user_id = $("#user_id").val();
	$.ajax({
	    	url :'deleteCart',
	    type :'POST',
	    data : {'cseq':cseq, 'user.id' : user_id},
 	})
	    	.done(function(fragment){
	    		 $("#listTable").replaceWith(fragment);
	});
}

function checkedBox(cseq, checked){
	var user_id = $("#user_id").val();
	if(checked == false){
		$.ajax({
    	url :'checkedCart',
	    type :'POST',
	    data : {'cseq':cseq, 'user.id' : user_id, 'checked' : true},
	 	})
			.done(function(fragment){
		    		 $("#listTable").replaceWith(fragment);
		});
	}else{
		$.ajax({
    	url :'checkedCart',
	    type :'POST',
	    data : {'cseq':cseq, 'user.id' : user_id, 'checked' : false},
	 	})
			.done(function(fragment){
		    		 $("#listTable").replaceWith(fragment);
		});
	}
}

function allCheckedBox(allChecked){
	var user_id = $("#user_id").val();
	if(allChecked == 0){
		$.ajax({
    	url :'allCheckedCart',
	    type :'POST',
	    data : {'user.id' : user_id, 'checked' : true},
	 	})
			.done(function(fragment){
		    		 $("#listTable").replaceWith(fragment);
		});
	}else{
		$.ajax({
    	url :'allCheckedCart',
	    type :'POST',
	    data : {'user.id' : user_id, 'checked' : false},
	 	})
			.done(function(fragment){
		    		 $("#listTable").replaceWith(fragment);
		});
	}
}

