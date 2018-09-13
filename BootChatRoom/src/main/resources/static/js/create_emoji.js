$(document).ready(function () {
	$("#message").emojioneArea({
		/* dùng 2 thuộc tính sau để có thể hiển thị emoji trên
		 * thẻ div đồng thời lưu giá trị đó dạng text trên thẻ textarea */
		shortnames: true,
		saveEmojisAs: "shortname",
		
		pickerPosition: "left",
		tonesStyle: "bullet",
		emojiPlaceholder: ":smile_cat:",
		placeholder: "Write a message, TAB to open emoji",
		search: false,
		buttonTitle: "Insert emoji. Use the TAB key to insert emoji faster",
		pickerPosition: "top",
		events: {
			keypress: function (editor, event) {
		    	if(event.keyCode === 13 && !event.shiftKey) {
		    		var yourMessage = $("#message").data("emojioneArea").getText();
		    		messageInput.value = yourMessage;
		    		sendMessage(event);
		    		hideEmojiPicker();
		    	}
		    }, click: function (editor, event) {
		        hideEmojiPicker();
		    }
		}
	});
});

var myInterval = setInterval(function() {
	emojionearea = document.getElementsByClassName("emojionearea")[0];
	emojionearea_editor = document.getElementsByClassName("emojionearea-editor")[0];
	emojionearea_button = document.getElementsByClassName("emojionearea-button")[0];
	emojionearea_picker = document.getElementsByClassName("emojionearea-picker")[0];
	
	if(emojionearea_editor != undefined && emojionearea_button!=undefined && 
			emojionearea_picker!=undefined) {
		clearInterval(myInterval);
		customInputMessage();
	}
}, 100);

function customInputMessage() {
	emojionearea_button.style.right = "";
	emojionearea_button.style.left = "3px";
	emojionearea_button.style.top = "6px";
	
	emojionearea_picker.style.right = "";
	emojionearea_picker.style.left = "0";
	
	// create button upload photo
	var btnUploadPhoto = document.createElement("div");
	btnUploadPhoto.setAttribute("class", "btn_upload_file2");
	btnUploadPhoto.setAttribute("type", "button");
	btnUploadPhoto.setAttribute("title", "Upload photo");
	btnUploadPhoto.setAttribute("onclick", "chooseFile();");
	emojionearea.appendChild(btnUploadPhoto);
	
	// create button insert sticker
	var btnSticker = document.createElement("div");
	btnSticker.setAttribute("class", "btn_sticker");
	btnSticker.setAttribute("type", "button");
	btnSticker.setAttribute("title", "Insert sticker");
	btnSticker.setAttribute("onclick", "showSticker();");
	emojionearea.appendChild(btnSticker);
	
	// create button insert gif
	var btnGif = document.createElement("div");
	btnGif.setAttribute("class", "btn_gif");
	btnGif.setAttribute("type", "button");
	btnGif.setAttribute("title", "Insert gif");
	btnGif.setAttribute("onclick", "showGif();");
	emojionearea.appendChild(btnGif);
	
	// create button like
	var btnLike = document.createElement("div");
	btnLike.setAttribute("class", "btn_like");
	btnLike.setAttribute("type", "button");
	btnLike.setAttribute("title", "Like!");
	btnLike.setAttribute("onclick", "sendLike();");
	emojionearea.appendChild(btnLike);
	
	// create sticker picker
	var stickerPicker = document.createElement("div");
	stickerPicker.setAttribute("class", "sticker-picker hidden");
	stickerPicker.style.opacity = '0';
	emojionearea.appendChild(stickerPicker);
	//createStickerPickerContent(stickerPicker); Thay vì dùng nó thì dùng hàm dưới
	var imgUrls = [];
	imgUrls[0] = contextPath + "/img/pepe_frog/pepe_the_frog1.png";
	imgUrls[1] = contextPath + "/img/qoobee/qoobee_1.gif";
	imgUrls[2] = contextPath + "/img/yao_ming/yao_ming_1.jpg";
	var functionNames = [];
	functionNames[0] = getPepeStickers;
	functionNames[1] = getStickersForTab2;
	functionNames[2] = getStickersForTab3;
	createPickerContent(stickerPicker, "tab_sticker_wrapper", imgUrls, functionNames);
	
	// create gif picker
	var gifPicker = document.createElement("div");
	gifPicker.setAttribute("class", "gif-picker hidden");
	gifPicker.style.opacity = '0';
	emojionearea.appendChild(gifPicker);
	var imgUrls2 = [];
	imgUrls2[0] = contextPath + "/img/despicable_me/despicable_me.png";
	imgUrls2[1] = contextPath + "/img/dragonball/dragonball.png";
	imgUrls2[2] = contextPath + "/img/favicon.png";
	// chú ý: ko được tận dụng biến functionNames để dùng vào việc tạo gif, vì Javascript
	// có hàm callback, nên khi thay đổi functionNames, sẽ dẫn tới việc gọi hàm:
	// createPickerContent(stickerPicker, "tab_sticker_wrapper", imgUrls, functionNames);
	// ở trên bị sai!
	var functionNames2 = [];
	functionNames2[0] = getDespicableMeGif;
	functionNames2[1] = getDragonballGif;
	functionNames2[2] = getGifsForTab3;
	createPickerContent(gifPicker, "tab_gif_wrapper", imgUrls2, functionNames2);
}

function hideEmojiPicker() {
	document.getElementsByClassName("emojionearea-button")[0].classList.remove("active");
	document.getElementsByClassName("emojionearea-picker")[0].classList.add("hidden");
}
