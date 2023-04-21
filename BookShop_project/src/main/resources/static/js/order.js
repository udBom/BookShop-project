function maxPoint(maxPoint){
	let inputValue = $('.order_point_input').val(); 
	if(inputValue < 0){
		$('.order_point_input').val(0);
	}else if(inputValue > maxPoint){
		if(maxPoint >= $('#total_price').val()){
			maxPoint = $('#total_price').val();
		}
		$('.order_point_input').val(maxPoint);
	}
	setTotalInfo();
}

$(".order_point_input_btn").on("click", function(){
	let maxPoint = $('#userPoint').val();
	console.log(maxPoint);
	let deliveryPrice = 0;
	let totalPrice = $('#total_price').val();
	console.log(totalPrice);
	if(!(maxPoint >= totalPrice)){
		if(totalPrice >= 30000){
			deliveryPrice = 0;
		} else if(totalPrice == 0){
			deliveryPrice = 0;
		} else {
			deliveryPrice = 3000;	
		}
		maxPoint =(parseInt(totalPrice) + deliveryPrice);
		console.log(maxPoint);
	}
		
	let state = $(this).data("state");
	
	if(state == 'N'){
		/* 모두사용 */
		//값 변경
		$(".order_point_input").val(maxPoint);
		//글 변경
		$(".order_point_input_btn_Y").css("display", "inline-block");
		$(".order_point_input_btn_N").css("display", "none");
	} else if(state == 'Y'){
		/* 취소 */
		//값 변경
		$(".order_point_input").val(0);
		//글 변경
		$(".order_point_input_btn_Y").css("display", "none");
		$(".order_point_input_btn_N").css("display", "inline-block");		
	}	
	setTotalInfo();
});

$(document).ready(function(){
	setTotalInfo();
});

function setTotalInfo(){

	let totalPrice = 0;				// 총 가격
	let totalCount = 0;				// 총 갯수
	let totalPoint = 0;				// 총 마일리지
	let deliveryPrice = 0;			// 배송비
	let usePoint = 0;				// 사용 포인트(할인가격)
	let finalTotalPrice = 0;		// 최종 가격(총 가격 + 배송비)
	let userPoint = 0;
	let curPoint = 0;
	// 총 가격
	totalPrice = $('#total_price').val();
	// 총 갯수
	totalCount = $('#totalCount').val();
	// 총 마일리지
	totalPoint = $('#total_point').val();
		
	userPoint = $('#userPoint').val();

	/* 배송비 결정 */
	if(totalPrice >= 30000){
		deliveryPrice = 0;
	} else if(totalPrice == 0){
		deliveryPrice = 0;
	} else {
		deliveryPrice = 3000;	
	}
	
	finalTotalPrice = (parseInt(totalPrice) + deliveryPrice);	
	
	/* 사용 포인트 */
	usePoint = $(".order_point_input").val();
	
	curPoint = (parseInt(userPoint) - usePoint);
	finalTotalPrice = finalTotalPrice - usePoint;	
	
	$(".userPoint_span").text(curPoint);
	$(".totalPrice_span").text(totalPrice.toLocaleString());
	$(".goods_kind_div_count").text(totalCount);
	$(".totalPoint_span").text(totalPoint.toLocaleString());
	$(".delivery_price_span").text(deliveryPrice.toLocaleString());	
	$(".finalTotalPrice_span").text(finalTotalPrice.toLocaleString());		
	$(".usePoint_span").text(usePoint.toLocaleString());	
	
}


$(".order_btn").on("click", function(){
	alert('결제가 완료되었습니다.')
	/* 사용 포인트 */
	$("input[name='used_point']").val($(".order_point_input").val());
	
	/* 서버 전송 */
	$("#requestOrder").submit();	
	
});


function orderRefund(oseq){
	if (confirm("환불처리를 진행하시겠습니까?") == true){    //확인
		$.ajax({
	    	url :'refundOrder',
		    type :'POST',
		    data : {'oseq' : oseq},
		 	})
				.done(function(fragment){
			    		 $("#listTable").replaceWith(fragment);
			});
	}else{   //취소
		return;
	}
}