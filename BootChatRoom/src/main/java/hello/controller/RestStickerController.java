package hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hello.dao.StickerDAO;
import hello.model.Sticker;

@RestController
public class RestStickerController {
	@Autowired
	StickerDAO stickerDAO;

	@RequestMapping(value = "/rest/stickers/pepe-frog", method = RequestMethod.GET, //
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public List<Sticker> getPepeStickers() {
		return stickerDAO.getPepeStickers();
	}
	
	@RequestMapping(value = "/rest/stickers/qoobee", method = RequestMethod.GET, //
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public List<Sticker> getQooBeeStickers() {
		return stickerDAO.getQooBeeStickers();
	}

	@RequestMapping(value = "/rest/stickers/yao-ming", method = RequestMethod.GET, //
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public List<Sticker> getYaoMingStickers() {
		return stickerDAO.getYaoMingStickers();
	}
}
