let fileElements = document.querySelectorAll("[type=file]");

let imageArea = document.querySelectorAll(".image_area");

fileElements.forEach(item => item.addEventListener('change', preview));

function preview(){
	let index = Array.from(fileElements).indexOf(this);
	if(this.files && this.files[0]) {
		let reader = new FileReader();
		reader.readAsDataURL(this.files[0]);
		reader.onload = function(){
			imageArea[index].innerHTML = '<img src="' + reader.result + '">';			
		}
	}
}

