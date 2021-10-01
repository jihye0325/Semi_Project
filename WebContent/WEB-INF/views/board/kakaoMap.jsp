<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>키워드로 장소 검색</title>
<style>
	.popup-head {
		background: #fff;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.popup-head img {
		background: none;
		padding: 10px;
	}
	.popup-head a {
		padding-right: 10px;
		font-size: 20px;
		font-weight: bold;
		display: flex;
		align-items: center;
	}
	.map_wrap, 
	.map_wrap * {
	    margin:0;
	    padding:0;
	    font-family:'Malgun Gothic',dotum,'돋움',sans-serif;
	    font-size:14px;
	}
	.map_wrap a, 
	.map_wrap a:hover, 
	.map_wrap a:active {
	    color:rgb(134, 60, 60);
	    text-decoration: none;
	}
	#map {
	    border: 1px solid #4f4f4f;
	}
	.map_wrap {
	    position:relative;
	    width:900px;
	    height:500px; 
	    display: flex;
	}
	#menu_wrap {
	    position:relative;
	    top:0;
	    left:0;
	    bottom:0;
	    width:500px;
	    height: 500px;
	    overflow-y:auto;
	    background: #ffffff;
	    z-index: 1;
	    font-size:12px;
	    border: 1px solid #4f4f4f;
	}
	div.option form {
	    display: flex;
	    justify-content: space-between;
	    align-items: center;
	    padding: 10px;
	}
	#keyword {
		width: 230px;
		height: 30px;
		border: none;
	}
	form>button {
		background: none;
		border: none;
	}
	.bg_white {
	    background:#fff;
	}
	#menu_wrap hr {
	    display: block; 
	    height: 1px;
	    border: 0; 
	    border-top: 2px solid #5F5F5F;
	    margin:3px 0;
	}
	#menu_wrap .option {
	    text-align: center;
	}
	#placesList li {
	    list-style: none;
	}
	#placesList .item {
		/* position:relative; */
		border-bottom:1px solid #888;
		overflow: hidden;
		cursor: pointer;
		min-height: 65px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 0 10px;
	}
	#placesList .item span {
		display: block;
		margin-top:4px;
	}
	#placesList .item h5, 
	#placesList .item .info {
	 text-overflow: ellipsis;
	 overflow: hidden;
	 white-space: nowrap;
	}
	#placesList .item .info {
		padding:10px 0 10px 55px;
	}
	#placesList .info .tel {
		color:#009900;
	}
	#placesList .item .markerbg {
		float:left;
		position:absolute;
		width:36px; 
		height:37px;
		margin:10px 0 0 10px;
		background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;
	}
	#placesList .item .marker_1 {background-position: 0 -10px;}
	#placesList .item .marker_2 {background-position: 0 -56px;}
	#placesList .item .marker_3 {background-position: 0 -102px}
	#placesList .item .marker_4 {background-position: 0 -148px;}
	#placesList .item .marker_5 {background-position: 0 -194px;}
	#placesList .item .marker_6 {background-position: 0 -240px;}
	#placesList .item .marker_7 {background-position: 0 -286px;}
	#placesList .item .marker_8 {background-position: 0 -332px;}
	#placesList .item .marker_9 {background-position: 0 -378px;}
	#placesList .item .marker_10 {background-position: 0 -423px;}
	#placesList .item .marker_11 {background-position: 0 -470px;}
	#placesList .item .marker_12 {background-position: 0 -516px;}
	#placesList .item .marker_13 {background-position: 0 -562px;}
	#placesList .item .marker_14 {background-position: 0 -608px;}
	#placesList .item .marker_15 {background-position: 0 -654px;}
	#pagination {
		margin:10px auto;
		text-align: center;
	}
	#pagination a {
		display:inline-block;
		margin-right:10px;
	}
	#pagination .on {
		font-weight: bold; 
		cursor: default;
		color:#777;
	}
</style>
</head>
<body>
	<div class="popup-wrap">
		<div class="popup">
			<div class="popup-head">
			<img src="<%= request.getContextPath() %>/resources/images/board/close.png" width="30px;" id="close" onclick="popupClose()">
			<a onclick="savePosition()">지도 첨부<img src="<%= request.getContextPath() %>/resources/images/board/upload.png" width="30px;"></a>
			</div>		
		    <div class="map_wrap">
		        <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
		        <div id="menu_wrap" class="bg_white">
		            <div class="option">
		                <div>
		                    <form onsubmit="searchPlaces(); return false;">
		                    	<input type="text" value="" id="keyword" size="15"> 
		                        <button type="submit"><img src="<%=request.getContextPath()%>/resources/images/search.png" alt="검색" width="30px"></button> 
		                    </form>
		                </div>
		            </div>
		            <hr>
		            <ul id="placesList"></ul>
		            <div id="pagination"></div>
		        </div>
		    </div>
	    </div>
    </div>

    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=82c45421c71244b795d3daf522874452&libraries=services"></script>
    <script>
	    function getContextPath() {
	        return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	    }
        // 마커를 담을 배열
        var markers = [];
        // 선택한 마커
        var selectedMarker = [];
        var placenames = [];
        
        var icon = new kakao.maps.MarkerImage(
        		'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png',
                new kakao.maps.Size(23, 33));
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
            mapOption = {
                center: new kakao.maps.LatLng(33.499505, 126.529767), // 지도의 중심좌표 : 제주시
                level: 6 // 지도의 확대 레벨
            };  
        // 지도 생성 
        var map = new kakao.maps.Map(mapContainer, mapOption); 
        // 장소 검색 객체
        var ps = new kakao.maps.services.Places();
        // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우
        var infowindow = new kakao.maps.InfoWindow({zIndex:1});
        // 키워드 검색을 요청
        function searchPlaces() {
            let keyword = document.getElementById('keyword').value;
            if (!keyword.replace(/^\s+|\s+$/g, '')) {
                alert('키워드를 입력해주세요!');
                return false;
            }
            // 장소검색을 요청
            ps.keywordSearch( keyword, placesSearchCB ); 
        }
        // 장소검색이 완료됐을 때 호출되는 콜백함수
        function placesSearchCB(data, status, pagination) {
            if (status === kakao.maps.services.Status.OK) {
                // 정상적으로 검색 & return이 존재
                // 마커를 리스트에 담아두고 목록에 마우스 오버하는 경우에만 지도에 표시
                displayPlaces(data);
                // 페이지 번호를 표출
                displayPagination(pagination);
            } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            	// 정상적 검색 & return 없음
                alert('검색 결과가 존재하지 않습니다.');
                return;
            } else if (status === kakao.maps.services.Status.ERROR) {
                alert('검색 결과 중 오류가 발생했습니다.');
                return;
            }
        }
        // 검색 결과 목록과 마커를 표출
        function displayPlaces(places) {
            let listEl = document.getElementById('placesList'),           
            menuEl = document.getElementById('menu_wrap'),
            fragment = document.createDocumentFragment(), 
            bounds = new kakao.maps.LatLngBounds(), 
            listStr = '';
            
            // 검색 결과 목록에 추가된 항목들을 제거합니다 -> 기존 리스트 일단 삭제
            removeAllChildNods(listEl);
            // 지도에 표시되고 있는 마커를 제거합니다 -> 기존 마커 제거
            removeMarker();
            
            // 검색결과 리스트 만들기
            for ( let i=0; i<places.length; i++ ) {
                // placePosition 배열 -> 각 장소의 x, y 좌표 저장
                let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
                    marker = addMarker(placePosition, i),
                    itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
                // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기 위해 LatLngBounds 객체에 좌표를 추가
                bounds.extend(placePosition);
                // 검색결과 항목에 mouseover 했을때 마커와 인포윈도우 표시합니다
                // mouseout 했을 때는 모두 안보이도록
                 (function(marker,  title) {
                    itemEl.onmouseover = function() {
                    	displayMarker(marker, title);
                    }
                    itemEl.onmouseout = function () {
                    	marker.setMap(null);
                    	showMarker(selectedMarker);
                    }

                    itemEl.onclick = function () {
                  		var a = selectedMarker.length;
                 		var bool = true;
                  		if(a == 0) {
	              			bool = true;
                  		} else if (a == 5){
                  			alert('장소는 최대 5개까지 추가할 수 있습니다.');
                  			bool = false;
                  		} else {
 	                  		for(var i = 0; i < a; i++) {
	                  			if(selectedMarker[i].getPosition().equals(marker.getPosition())) {
	                  				bool = false;
	                  			}
	                  		}
                  		}
                  		if(bool) {
                  			selectedMarker.push(marker);
	              			placenames.push(title);
                  		}
                  		console.log(selectedMarker.length);
                  		showMarker;
                    }
                })
                
                (marker, places[i].place_name); 
                
                fragment.appendChild(itemEl);
            } 
            // 검색결과 항목들을 검색결과 목록 Element에 추가
            listEl.appendChild(fragment);
            menuEl.scrollTop = 0;
            // 검색된 장소 위치를 기준으로 지도 범위를 재설정
            map.setBounds(bounds);
        }
        // 검색결과 항목을 Element로 반환하는 함수입니다
        function getListItem(index, places) {
            let el = document.createElement('li'),
            itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                        '<div class="info">' +
                        '   <h5>' + places.place_name + '</h5>';
                        
            itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                        '</div>';
            el.innerHTML = itemStr;
            el.className = 'item';
            return el;
        }
        // 마커를 생성하고 지도 위에 마커를 표시하는 함수
        // param으로 위치와 인덱스 전달
        function addMarker(position, idx, title) {
            let imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url
                imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
                imgOptions =  {
                    spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
                    spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                    offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
                },
                markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
                    marker = new kakao.maps.Marker({
                    position: position, // 마커의 위치
                    image: markerImage 
                });
            markers.push(marker);  // 배열에 생성된 마커를 추가
            return marker;
        }
        
        // 선택한 마커 보이도록
        function showMarker() {
        	for( let i = 0; i < selectedMarker.length; i++) {
        		selectedMarker[i].setImage(icon);
        		selectedMarker[i].setMap(map);
        	}
        }
        
        // 마커 보이도록
        function displayMarker(marker, title) {
        	marker.setMap(map);
        }
            
        // 지도 위에 표시되고 있는 마커를 모두 제거합니다
        function removeMarker() {
            for ( let i = 0; i < markers.length; i++ ) {
                markers[i].setMap(null);
            }   
            markers = [];
        }
        // 검색결과 목록 하단에 페이지번호를 표시
        function displayPagination(pagination) {
            let paginationEl = document.getElementById('pagination'),
                fragment = document.createDocumentFragment(),
                i; 
            // 기존에 추가된 페이지번호를 삭제
            while (paginationEl.hasChildNodes()) {
                paginationEl.removeChild (paginationEl.lastChild);
            }
            for (i=1; i<=pagination.last; i++) {
                let el = document.createElement('a');
                el.href = "#";
                el.innerHTML = i;
                if (i===pagination.current) {
                    el.className = 'on';
                } else {
                    el.onclick = (function(i) {
                        return function() {
                            pagination.gotoPage(i);
                        }
                    })(i);
                }
                fragment.appendChild(el);
            }
            paginationEl.appendChild(fragment);
        }
        // 검색결과 목록의 자식 Element를 제거
        function removeAllChildNods(el) {   
            while (el.hasChildNodes()) {
                el.removeChild (el.lastChild);
            }
        }
        
        function popupClose() {
        	document.querySelector(".popup-wrap").style.display = "none";
        }
    </script>
</body>
</html>