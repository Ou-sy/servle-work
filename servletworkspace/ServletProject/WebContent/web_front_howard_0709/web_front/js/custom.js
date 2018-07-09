$(document).ready(function () {

    var slide = 0;
    var sliderWidth = 600;
    var sliders = ["slide1.jpeg", "slide2.jpeg", "slide3.jpeg", "slide4.jpeg"];
    var slideCount = sliders.length; //取得陣列長度

    sliderAppend();
    function sliderAppend() {
        var sliderContent = "";
        var sliderDot = "";
        $("#slide_img").width(sliderWidth * slideCount + "px")
        for (i = 0; i < slideCount; i++) {
            sliderContent += "<li><img src='images/ad2/" + sliders[i] + "'></li>";
            sliderDot += "<li></li>";
        }
        $("#slide_img").append(sliderContent);
        $("#slide_node").append(sliderDot);
    }
    function goSlider(slide) {
        width = 0 - sliderWidth * slide + "px";
        $(".slide_img").css("left", width);
        $("#slide_node li").eq(slide).addClass('active').siblings().removeClass();
    }

    $(".slide_node li").hover(function () {
        slide = $(this).index();
        goSlider(slide);
    });
    $("#left").click(function () {
        if (slide - 1 >= 0) {
            slide = slide - 1;
            goSlider(slide);
        }
    });
    $("#right").click(function () {
        if (slide + 1 < slideCount) {
            slide = slide + 1;
            goSlider(slide);
        }
    });
});