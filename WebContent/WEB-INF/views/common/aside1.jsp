<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>corona_weather</title>
    <link href="<%=request.getContextPath()%>/resources/css/aside/aside1.css" rel="stylesheet"></link>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    
    </head>
<body>  
    <div class="aside1">
        <div class="corona">
            <a href="http://ncov.mohw.go.kr/" target='_blank'>
                <p>제주도<br>코로나 현황<br>
                <span class="cday"></span></p>
             
                <div>
                    <img class="cimg" src="<%=request.getContextPath()%>/resources/images/coronaman.png">
                    <img class="cimg" src="<%=request.getContextPath()%>/resources/images/coronawoman.png">
                </div>
                <p>누적확진자</p>
                <div class="corona_sum">
                <div></div>
                </div>
                
                <p>신규확진자</p>
				<div class="corona_today">
				<div class="today"></div>
				<img class="up" src="<%=request.getContextPath()%>/resources/images/up.png">
				</div>   
            </a>
            
        </div>

        <div class="weather">
        	<p>제주도 <br>실시간 날씨</p>
            <div class="weather_info" onclick="window.open('https://www.weather.go.kr/w/index.do#close')">
                <img class="wimg"></img>
           		<div class="weatherapi">
           			<div class="winfo"></div>
           			<div class="temp"></div>
           		</div>
            </div>
            <div class="rain"></div>
        </div>
    </div>
    
    <!-- 날씨 api : https://openweathermap.org/api 사용 -->
    <!-- 오픈키지움 -->
    <script>
    	$(function(){
    		var apiURI = "http://api.openweathermap.org/data/2.5/weather?q="+ "jeju"+ "&units=metric"+ "&lang=kr" + "&appid="+"366c7f6f33d24a9a02e7fe4f0e5dd808";
    		$.ajax({
    			url : apiURI,
    			dataType : "json",
    			type : "GET",
    			async : "false",
    			success: function(w){
                    var temp = w.main.temp;
                    $('.weatherapi').append(temp + "°C");
                    
                    var imgURL = "http://openweathermap.org/img/w/" + w.weather[0].icon + ".png"; 
                    $('.wimg').attr("src", imgURL);
                    
                    var type = w.weather[0].description;
                    
                    if(type == "Clouds") 
                    	type = "구름";
                    else if(type == "Rain")
                    	type = "비";
                    else if(type == "Snow")
                    	type = "눈";
                    else 
                    	type = "맑음";
                    $('.winfo').append(type);
                    
                    var rain = w.clouds.all;
                    $('.rain').append("강수확률 : " + rain + "%");
                    
                    // console.log(w);
                    //console.log("현재온도 : "+ w.main.temp);
                    //console.log("날씨 : "+ w.weather[0].main );
                    //console.log("상세날씨설명 : "+ w.weather[0].description );
                    //console.log("날씨 이미지 : "+ w.weather[0].icon );
                    //console.log("바람   : "+ w.wind.speed );
                    //console.log("나라   : "+ w.sys.country );
                    //console.log("도시이름  : "+ w.name );
                    //console.log("구름  : "+ (w.clouds.all) +"%" );
    			}
    		})
    	});
    </script>
   	<!-- 코로나api -->
   	<script>
   	$(document).ready(function(){
   		// 20210925
   		var today = new Date();
   		var yesterday = new Date(today.setDate(today.getDate() - 1));
   		var year = yesterday.getFullYear();
   		var month = ('0' + (yesterday.getMonth() + 1)).slice(-2);
   		var day = ('0' + yesterday.getDate()).slice(-2);
   		var dayString = year + month + day;
   		var date = year + "." + month + "." + day;
   		console.log(dayString);
   	// ** 오픈키 지움**
   	var apiURI ="http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=19zIv0PcvB4V%2Bh9O4ybMH4sZavKxBn8pw5ZiqXXY68DCsElnOCIP9MQVi4Knuv4FRT56PlqsJn9iwYP25G30nw%3D%3D&pageNo=1&numOfRows=10&startCreateDt=" + dayString + "&endCreateDt=" + dayString;
		console.log(apiURI);
   		$.ajax({
   			url : apiURI,
   			type : "GET",
   			dataType: "xml",
   			success: function(response){
   				xmlParsing(response);
   				console.log(response);
   			},
   			error : function(xhr, status, msg){
   				console.log("상태값:" + status + "http에러메시지:" + msg);
   			}
   		});
   	   	
   		function xmlParsing(data) {
   			var sum = ``;
   			var today = '';
   			console.log(data);
   			
   			$(data).find('item').each(function(index, item){
   				if($(item).find('gubun').text() == '제주'){
   					console.log($(item));
   					console.log($(item).find('defCnt').text());
   					sum = $(item).find('defCnt').text();
   					today =$(item).find('incDec').text();
   				}
   				$('.cday').html("- "+ date + " 기준 -");
   				$('.corona_sum').html(sum);
   				$('.today').html(today);
   			});
   		}	
   		
   	});
   	
   	</script>
</body>
</html>




