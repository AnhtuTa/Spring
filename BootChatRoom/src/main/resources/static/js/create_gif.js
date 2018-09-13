function getDespicableMeGif(wrapper) {
	getStickers(wrapper, "/rest/gifs/despicable-me/", "sendGif");
}

function getDragonballGif(wrapper) {
	getStickers(wrapper, "/rest/gifs/dragonball/", "sendGif");
}

function getGifsForTab3(wrapper) {
	wrapper.innerHTML = "<h2>This is tab3, currently doesn't have any gif on it!</h2>";
}

function showGif() {
	var gif_picker = document.getElementsByClassName("gif-picker")[0];
	var btn_gif = document.getElementsByClassName("btn_gif")[0];
	
	hideStickerOnClickOutside(gif_picker, btn_gif);
	
	if(gif_picker.classList.contains("hidden")) {
		showStickerPicker(gif_picker);
	} else {
		hideStickerPicker(gif_picker);
	}
}

function sendGif(img) {
	sendStickerOrGif(img, 4);
}