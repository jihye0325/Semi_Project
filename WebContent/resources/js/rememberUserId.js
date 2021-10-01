const userId = document.querySelector("#userId");
const remember = document.querySelector("#remember");
let rememberId = getCookie("rememberId");
userId.value = rememberId;

if(userId.value != '') {
	remember.checked = true;
}

remember.addEventListener('change', function(){
	if(this.checked) {
		setCookie("remember", userId.value, 7);
	} else {
		deleteCookie("rememberId");
	}
})

userId.addEventListener('keyup', function(){
	if(remember.checked) {
		setCookie("rememberId", userId.value, 7);
	}
})

function setCookie(cookieName, value, exdays){	
	
    let exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);

    let cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    
    document.cookie = cookieName + "=" + cookieValue;
}

function deleteCookie(cookieName){
	let expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}

function getCookie(cookieName) {
    cookieName = cookieName + '=';
    let cookieData = document.cookie;
    let start = cookieData.indexOf(cookieName);
    let cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        let end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}