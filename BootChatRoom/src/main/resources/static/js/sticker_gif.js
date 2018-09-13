
/**
 * 
 * @param picker [Element] Cái này là wrapper
 * @param tabWrapperID id của tabwrapper cần tạo
 * @param tablinkImages [Array] 1 mảng chứa các URL của image để hiển thị trên nút chuyển tab,
 * số lượng tab = kích thước mảng này (Tất cả các nút chuyển tab đều chứa ảnh)
 * Chú ý: các URL này chỉ là đường dẫn tương đối, và CÓ CẢ contextPath rồi!
 * Nếu các URL này ở server khác thì vẫn OK
 * @param getContentFunctions [Array] 1 mảng chứa tên các hàm để lấy content đổ vào từng tab
 * @returns void
 */
function createPickerContent(picker, tabWrapperID, tablinkImages, getContentFunctions) {
	// create tabs for sticker picker
	var tab_wrapper = document.createElement("div");
	tab_wrapper.setAttribute("id", tabWrapperID);
	
	// tab links
	var tablink_wrapper = document.createElement("div");
	tablink_wrapper.setAttribute("class", "tablink_wrapper");
	tab_wrapper.appendChild(tablink_wrapper);
	
	var tabContents = [];
	
	var getTabContent = function() {
		if(tabContents[this.position].innerHTML === "") {
			getContentFunctions[this.position](tabContents[this.position]);
		}
	}
	
	// create tab contents
	for(var i=0; i<tablinkImages.length; i++) {
		tabContents[i] = document.createElement("div");
		tabContents[i].setAttribute("class", "tabcontent");
		if(i===0) tabContents[i].setAttribute("style", "display: block");
		
		tab_wrapper.appendChild(tabContents[i]);
	}

	// Các nút chuyển tab (tab link)
	for(var i=0; i<tablinkImages.length; i++) {
		var btnTabLink = document.createElement("div");
		if(i===0) {
			btnTabLink.setAttribute("class", "tablink active");
			getContentFunctions[i](tabContents[i]);
		} else {
			btnTabLink.setAttribute("class", "tablink");
			btnTabLink.addEventListener("click", getTabContent.bind({position: i}));
		}
		
		var innerImg = document.createElement("img");
		innerImg.src = tablinkImages[i];
		btnTabLink.appendChild(innerImg);
		
		tablink_wrapper.appendChild(btnTabLink);
	}
	
	picker.appendChild(tab_wrapper);
	
	createSwitchTabEvent(tab_wrapper);
}

/**
 * Lấy sticker, gif từ server (dùng RESTful)
 * @param wrapper [Element] thẻ này sẽ chứa tất cả các sticker lấy được
 * @param restUrl [String] RESTful để lấy data
 * @param eventWhenClickToSticker [String] tên hàm sự kiện khi click vào sticker/gif
 * @returns
 */
function getStickers(wrapper, restUrl, eventWhenClickToSticker) {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var json = JSON.parse(this.responseText);
			
			var gridContainer = document.createElement("div");
			gridContainer.setAttribute("class", "grid-container");
			
			for(var i=0; i<json.length; i++) {
				var gridItem = document.createElement("div");
				var imgUrl = json[i].url;
				if(!imgUrl.startsWith("http")) {
					imgUrl = contextPath + imgUrl;
				}
				gridItem.setAttribute("class", "grid-item");
				gridItem.setAttribute("title", json[i].title);
				gridItem.innerHTML = '<img src="' + imgUrl + '" onclick="' + eventWhenClickToSticker + '(this)"/>';
				gridContainer.appendChild(gridItem);
			}
			
			wrapper.appendChild(gridContainer);
		}
	};
	xhttp.open("GET", contextPath + restUrl, true);
	xhttp.send();
}

//hàm này để hiển thị cả gif nữa
function showStickerPicker(sticker_picker) {
	sticker_picker.classList.remove("hidden");
	
	setTimeout(function() {
		sticker_picker.style.opacity = 1;
	}, 10);
}

//hàm này để hide cả gif nữa
//sticker_picker có transition = 0.3s
function hideStickerPicker(sticker_picker) {
	sticker_picker.style.opacity = 0;
	setTimeout(function() {
		sticker_picker.classList.add("hidden");
	}, 400);
}

const isVisible = elem => !!elem && !!( elem.offsetWidth || elem.offsetHeight || elem.getClientRects().length );
function hideStickerOnClickOutside(element, exceptElement) {
 const outsideClickListener = event => {
 	if(event.target == exceptElement) return;
     if (!element.contains(event.target)) { // or use: event.target.closest(selector) === null
         if (isVisible(element)) {
         	hideStickerPicker(element);
             removeClickListener();
         }
     }
 }

 const removeClickListener = () => {
     document.removeEventListener('click', outsideClickListener)
 }

 document.addEventListener('click', outsideClickListener)
}

/**
 * Send a sticker or a gif to server
 * @param img [Element/String] image to send, or URL of image to send
 * @param [int] type kiểu của img (sticker = 3, gif = 4)
 * @returns
 */
function sendStickerOrGif(img, type) {
	var url;
	if(typeof img === 'object') {
		url = img.getAttribute("src");
	} else if(typeof img === 'string') {
		url = img;
	}
	
	// Tạm thời sẽ gửi sticker về server như gửi ảnh
	// send message for other members in this room
	// nếu dùng img.src thì sẽ có cả contextPath, sẽ lỗi!
	if(url.includes(contextPath)) {
		url = url.substring(contextPath.length, url.length);
	}
	
    if (stompClient) {
		var chatMessage = new ChatMessage(0, userId, fullname, url, "", STR_CHAT, type);
		// có vẻ như stompClient tự động send cả csrf token rồi!
		stompClient.send(contextPath + "/app/chat/" + roomId + "/sendMessage", {}, chatMessage.toJSONString());
	}
}