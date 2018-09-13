// Hiện tại ko dùng hàm này, mà dùng hàm createPickerContent
/*
function createStickerPickerContent(stickerPicker) {
	// create tabs for sticker picker
	var tab_sticker_wrapper = document.createElement("div");
	tab_sticker_wrapper.setAttribute("id", "tab_sticker_wrapper");
	
		// tab links
		var tablink_wrapper = document.createElement("div");
		tablink_wrapper.setAttribute("class", "tablink_wrapper");
	
			var btnPepe = document.createElement("div");
			btnPepe.setAttribute("class", "tablink active");
				var imgPepe = document.createElement("img");
				imgPepe.src = contextPath + "/img/pepe_frog/pepe_the_frog1.png";
				btnPepe.appendChild(imgPepe);
			var btn2 = document.createElement("div");
			btn2.setAttribute("class", "tablink");
			btn2.innerHTML = "Hehe";
			var btn3 = document.createElement("div");
			btn3.setAttribute("class", "tablink");
			btn3.innerHTML = "Haha";
	
		tablink_wrapper.appendChild(btnPepe);
		tablink_wrapper.appendChild(btn2);
		tablink_wrapper.appendChild(btn3);
	
		// tab contents: có bao nhiêu button ở trên thì cần bấy nhiêu tab content
		var pepeTabContent = document.createElement("div");
		pepeTabContent.setAttribute("class", "tabcontent");
		pepeTabContent.setAttribute("style", "display: block");

		var tab2 = document.createElement("div");
		tab2.setAttribute("class", "tabcontent");

		var tab3 = document.createElement("div");
		tab3.setAttribute("class", "tabcontent");
		
	tab_sticker_wrapper.appendChild(tablink_wrapper);
	tab_sticker_wrapper.appendChild(pepeTabContent);
	tab_sticker_wrapper.appendChild(tab2);
	tab_sticker_wrapper.appendChild(tab3);
	
	stickerPicker.appendChild(tab_sticker_wrapper);
	
	createSwitchTabEvent(tab_sticker_wrapper);
	
	// get pepe_frog sticker from server (get stickers for tab 1)
	getPepeStickers(pepeTabContent);
	
	btn2.addEventListener("click", function() {
		if(tab2.innerHTML === "") {
			// (get stickers for tab 2)
			getStickersForTab2(tab2);
		}
	});

	btn3.addEventListener("click", function() {
		if(tab3.innerHTML === "") {
			// (get stickers for tab 3)
			getStickersForTab3(tab3);
		}
	});
	
}
*/

function getPepeStickers(wrapper) {
	getStickers(wrapper, "/rest/stickers/pepe-frog/", "sendSticker");
}

function getStickersForTab2(wrapper) {
	getStickers(wrapper, "/rest/stickers/qoobee/", "sendSticker");
}

function getStickersForTab3(wrapper) {
	getStickers(wrapper, "/rest/stickers/yao-ming/", "sendSticker");
}

function showSticker() {
	if(sticker_picker === undefined)
		sticker_picker = document.getElementsByClassName("sticker-picker")[0];
	var btn_sticker = document.getElementsByClassName("btn_sticker")[0];
	
	hideStickerOnClickOutside(sticker_picker, btn_sticker);
	
	if(sticker_picker.classList.contains("hidden")) {
		showStickerPicker(sticker_picker);
	} else {
		hideStickerPicker(sticker_picker);
	}
}

function sendSticker(img) {
	sendStickerOrGif(img, 3);
}

function sendLike() {
	sendStickerOrGif(contextPath + "/img/sticker_like.png", 5);
}
