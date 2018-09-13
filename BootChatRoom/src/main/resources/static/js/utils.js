// format hàm này phải trùng với format phía server!
function getCurrentTime() {
	var d = new Date();
	var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

	var hr = d.getHours();
	var min = d.getMinutes();
	var month = months[d.getMonth()];
	var date = d.getDate();
	
	if (min < 10) {
	    min = "0" + min;
	}

	if( hr < 10 ) {
	    hr = "0" + hr;
	}
	
	return month + " " + date + ", " + hr + "h" + min;
}

function escapeHtml(unsafe) {
	return unsafe.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g,
			"&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#039;");
}

// hàm sau thay thế tất các các URL có trong text thành thẻ a
// với href là chính url đó
// aStyle = style của thẻ a
function replacePlainURLsWithLinks(text, aStyle="") {
	var regex = /((http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?)/img;
	// var regex = /(((http|https|ftp|ftps)\:\/\/)?[a-zA-Z0-9\-\.]+\.[a-zA-Z]{2,3}(\/\S*)?)/mg;
	// return text.replace(regex, "<a href='$1' target='_blank' rel='external'>$1</a>");
	return text.replace(regex, function(match, p1) {
		if(p1.startsWith("http")) {
			return "<a href='" + p1 + "' target='_blank' style='" + aStyle + "'>" + p1 + "</a>";
		} else {
			return "<a href='http://" + p1 + "' target='_blank' style='" + aStyle + "'>" + p1 + "</a>";
		}
	});
}

function createSwitchTabEvent(tab_wrapper) {
	var tablinks = tab_wrapper.getElementsByClassName("tablink_wrapper")[0].getElementsByClassName("tablink");
	var tabcontents = tab_wrapper.getElementsByClassName("tabcontent");
	for (var i = 0; i < tablinks.length; i++) {
		// tablinks[i].addEventListener("click", function() {
		// 	console.log(i);		//i luôn = giá trị cuối cùng (=3), do đó đéo làm gì được!
		// })
		tablinks[i].addEventListener("click", showTabi.bind({position: i}));	//Trói đít, nhầm, trói this lại bằng bind. Lúc này this sẽ ko là tablinks[i] nữa mà là đối tượng là trong tham số của hàm bind
	}
	
	//show tab thứ this.position, và active tablink thứ this.position
	function showTabi() {
		//phải chắc chắn rẳng tabcontents và tablinks có cùng size (Hiển nhiên!)
		for (i = 0; i < tabcontents.length; i++) {
			// Get all elements with class="tabcontent" and hide them
			tabcontents[i].style.display = "none";
			
			// Get all elements with class="tablinks" and remove the class "active"
			tablinks[i].className = tablinks[i].className.replace(" active", "");
		}
		// Show the current tab, and add an "active" class to the button that opened the tab
		//document.getElementById(cityName).style.display = "block";
		tabcontents[this.position].style.display = "block";
		tablinks[this.position].className += " active";
	}
}
