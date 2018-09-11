/**
 * Created by Administrator on 2018/9/2 0002.
 */
$(function close() {
  $(".jd_close_card").click(function () {
    $(".header_card").hide();
  });

// $(".top_shortcut .fl").mouseenter(function () {
//   $(".top_shortcut .dt").addClass("current_bg");
//   $(".top_shortcut .dd").show(100);
// }).mouseleave(function () {
//   $(".top_shortcut  .dt").removeClass("current_bg");
//   $(".top_shortcut .dd").hide(100);
// });
$(".my_jd_box").mouseenter(function () {
  $(".my_jd_box .my_jd").addClass("current_bg");
  $(".my_jd_box .my_jd_info").show(100);
}).mouseleave(function () {
  $(".my_jd_box .my_jd").removeClass("current_bg");
  $(".my_jd_box .my_jd_info").hide(100);
});

$(".web_nav_box").mouseenter(function () {
  $(".web_nav_box .web_nav").addClass("current_bg");
  $(".web_nav_box .web_nav_info").show(100);
}).mouseleave(function () {
  $(".web_nav_box .web_nav").removeClass("current_bg");
  $(".web_nav_box .web_nav_info").hide(100);
});

$(".nav_dd li").each(function (index,ele) {
  $(ele).mouseenter(function () {
    $(".nav_dd li").removeClass("nav_menu_bg");
    $(this).addClass("nav_menu_bg");
  }).mouseleave(function () {
    $(this).removeClass("nav_menu_bg");
  });
});
});

function aa(){
  var pic = document.getElementById("pic");
  var inner = document.getElementById("inner");
  var li = document.getElementById("ul").getElementsByTagName("a");
  var prev = document.getElementById("prev");
  var next = document.getElementById("next");
  //设置索引初始值，点击自增或者自减，根据index值来给按钮添加颜色
  var index = 1;
  //左点击事件
  prev.onclick = function(){
    //调用动画函数，传入正的600，为每次的偏移量
    animate(1882);
    //设置索引的范围，不能小于1
    if(index==1){
      index=3;
    }else{
      index--;
    }
    //调用添加颜色函数
    showButton();
  }
  //右点击事件
  next.onclick = function(){
    //调用动画函数，传入负的600，为每次的偏移量
    animate(-1882);
    //设置索引的范围，不能超过5
    if(index==3){
      index=1;
    }else{
      index++;
    }
    showButton();
  }
  //动画函数，offset参数为偏移量
  function animate(offset){
    //获取现在.inner盒子的位置加上偏移量 赋值 给.inner盒子
    inner.style.left = parseInt(inner.style.left) + offset + "px";
    //判断新的位置，如果小于-3000则变为-600px，如果大于-600则变为-3000px
    if(parseInt(inner.style.left) < -5646){
      inner.style.left = -1882 + "px";
    }
    if(parseInt(inner.style.left) > -1882){
      inner.style.left = -5646 + "px";
    }
  }
  //按钮添加颜色函数
  function showButton(){
    //遍历每个a元素，如果有active类 则替换为空字符串，也就是移除这个类
    for(var i=0;i<li.length;i++){
      if(li[i].className=="active"){
        li[i].className="";
        //移除后就没必要去循环了，做一个优化。
        break;
      }
    }
    //根据当前的index值，找到对应的a元素添加active类
    li[index-1].className="active";
  }
  //遍历五个按钮
  for(var i=0;i<li.length;i++){
    //给五个按钮添加点击事件
    li[i].onclick=function(){
      //获取当前的id值
      var id = parseInt(this.getAttribute("id"));
      //减去原来的index值，乘以-600 得到偏移量，调用偏移函数
      var offset = (id-index) * -1882;
      //调用偏移函数
      animate(offset);
      //把index的值更新
      index = id;
      //调用改变背景色函数
      showButton();
    }
  }
}
function cc(){
  var pic = document.getElementById("pic");
  var inner = document.getElementById("inner");
  var li = document.getElementById("ul").getElementsByTagName("a");
  var prev = document.getElementById("prev");
  var next = document.getElementById("next");
  var index = 1;
  var timer = null;
  //设置一个变量来存放自动轮播定时器
  var timer2 = null;
  var state = false;
  prev.onclick = function(){
    if(state){
      return;
    }
    animate(1882);
    if(index==1){
      index=3;
    }else{
      index--;
    }
    showButton();
  }
  next.onclick = function(){
    if(state){
      return;
    }
    animate(-1882);
    if(index==3){
      index=1;
    }else{
      index++;
    }
    showButton();
  }

  function animate(offset){
    state = true;
    var time = 300;
    var interval = 10;
    var speed = offset/(time/interval);

    var newLeft = parseInt(inner.style.left) + offset;
    function go(){
      clearInterval(timer);
      if((speed < 0 && parseInt(inner.style.left) > newLeft) || (speed > 0 && parseInt(inner.style.left) < newLeft)){
        inner.style.left = parseInt(inner.style.left) + speed + "px";
        timer=setInterval(go,interval);
      }else{
        state = false;
        inner.style.left = newLeft + "px";
        if(parseInt(inner.style.left) < -5646){
          inner.style.left = -1882 + "px";
        }
        if(parseInt(inner.style.left) > -1882){
          inner.style.left = -5646 + "px";
        }
      }
    }
    go();

  }
  function showButton(){
    for(var i=0;i<li.length;i++){
      if(li[i].className=="active"){
        li[i].className="";
        break;
      }
    }
    li[index-1].className="active";
  }
  for(var i=0;i<li.length;i++){
    li[i].onclick=function(){
      var id = parseInt(this.getAttribute("id"));
      var offset = (id-index) * -1882;
      if(state){
        return;
      }
      animate(offset);
      index = id;
      showButton();
    }
  }
  //通过定时器来不断的点击 next按钮 来实现轮播效果.
  function play(){
    timer2 = setInterval(function(){
      next.onclick();
    },3000);
  }
  //停止轮播函数，清除定时器
  function stop(){
    clearInterval(timer2);
  }
  //给.pic添加移进悬浮和移出事件
  pic.onmouseover = stop;
  pic.onmouseout = play;
  //第一次访问页面开始轮播
  play();
}
function ff() {


$(".adv_items li").each(function (index,ele) {
  var $index=0;
  $(ele).mouseenter(function () {
    $(".adv_items li").removeClass("tab_current");
    if(index%2==0){
      $index = index;
      $(ele).addClass("tab_current");
      $(".tab_items div").each(function (index,mele) {
        if($index === index){
          $(this).show();
        }else {
          $(this).hide();
        }
      });
    }


  });

});
}
module.exports = {
  close,
  aa,
  cc,
  ff
}
