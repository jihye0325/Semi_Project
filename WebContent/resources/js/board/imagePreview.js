// input type file 요소들 : querySelector는 배열 
let fileElements = document.querySelectorAll("[type=file]");

// div image_area 요소들 
let imageArea = document.querySelectorAll(".image_area");

// change 이벤트가 발생하는 상황 (file 태그에 첨부가 발생한 상황)에 preview 함수 동작 
fileElements.forEach(item => item.addEventListener('change', preview));

function preview() {
	let index = Array.from(fileElements).indexOf(this);
	if(this.files && this.files[0]) {
		// fileReader 객체 이용해서 첨부된 파일을 DataURL 형식으로 저장하기
		let reader = new FileReader();
		reader.readAsDataURL(this.files[0]);
		// 이미지가 저장이 되면
		reader.onload = function() {
			imageArea[index].innerHTML = '<img src="' + reader.result + '">';
		}
	}
}