$(function(){
	
$(".dl_1").hover(function(){
		var _index = $(this).index();

		$(".box_1").eq(_index).fadeIn();
	})
	
	$(".dl_1").on("mouseleave",function(){
		var _index = $(this).index();
		$(".box_1").eq(_index).fadeOut();
	});
});