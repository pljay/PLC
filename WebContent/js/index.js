//轮播图
$(function(){
	var $imgs = $("#banner_box li"), // 所有轮播的图片盒子
		len = $imgs.length, // 轮播图片张数
		imgWidth = $imgs.outerWidth(), // 获取轮播图片盒子宽度
		currentIndex = 1, // 当前显示图片的索引
		nextIndex = 2, // 即将显示图片的索引
		timer = null,
		isRunning = false; // 运动动画是否正在由人为触发执行
	// 克隆节点
	var _first = $imgs.first().clone(true),
		_last = $imgs.last().clone(true);
	// 将克隆节点添加到ul内部的开始结尾处
	$("#banner_list").append(_first).prepend(_last);
	len += 2; // 图片张数增加2张
	// 设置 ul 宽度
	$("#banner_list").css("width", len * imgWidth);

	// 初始显示第1张图片内容
	$("#banner_list").css({top:0, left:-1*imgWidth});

	/* 动态创建小圆点 */
	// 连接小圆点布局时字符串结构
	var html = "";
	for (var i = 0; i < len - 2; i++) {
		html += "<div></div>";
	}
	// 将html中字符串表示的元素节点包装到 jQuery 对象中
	// 添加到 div#pages 中
	$(html).appendTo("#circle").first().addClass("btn");

	// 添加小圆点鼠标移入事件
	$("#circle").delegate("div", "mouseover", function(){
		// 获取移入的当前小圆点索引
		var _index = $(this).index();
		// 更新即将显示图片索引
		nextIndex = _index + 1;
		// 轮播
		move();
		isRunning = true;
	});

	$("#left, #right").on("selectstart", function(){
		return false;
	});

	// 上一页
	$("#left").click(function(){
		nextIndex = currentIndex - 1;
		move();
		isRunning = true;
	});

	// 下一页
	$("#right").click(function(){
		move();
		isRunning = true;
	});

	// 鼠标移入/出显示图片的盒子范围
	$("#banner_box").hover(function(){
		clearInterval(timer);
	}, function(){
		timer = setInterval(move, 3000);
	}).trigger("mouseleave");

	// 自动轮播
	// timer = setInterval(move, 3000);

	// 轮播函数
	function move() {
		if (isRunning)
			return;
		// 计算轮播过程中水平定位位置
		var _left = -1 * nextIndex * imgWidth;
		// 即将添加 current 类样式的小圆点索引
		var circleIndex = nextIndex - 1;
		if (circleIndex < 0)
			circleIndex = len - 3;
		else if (circleIndex >= len - 2)
			circleIndex = 0;

		// 修改小圆点显示样式
		$("#circle div").eq(circleIndex).addClass("btn")
					   .siblings().removeClass("btn");
		// 修改nextIndex、currentIndex
		currentIndex = nextIndex;
		nextIndex++;
		// 运动动画
		$("#banner_list").animate({left:_left}, 1000, function(){
			isRunning = false;
			if (nextIndex >= len){
				currentIndex = 1;
				nextIndex = 2;
				$("#banner_list").css("left", -1 * imgWidth);
			} else if (currentIndex == 0) {
				currentIndex = len - 2;
				nextIndex = len - 1;
				$("#banner_list").css("left", -1 * imgWidth * (len - 2));
			}
		});
	}
	


	$(".dl_1").hover(function(){
		var _index = $(this).index();

		$(".box_1").eq(_index-1).fadeIn();
	})
	
	$(".dl_1").on("mouseleave",function(){
		var _index = $(this).index();
		$(".box_1").eq(_index-1).fadeOut();
	});

	
});
