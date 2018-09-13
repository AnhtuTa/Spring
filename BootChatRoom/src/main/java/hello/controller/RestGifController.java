package hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hello.entity.Gif;
import hello.service.GifService;

@RestController
public class RestGifController {
	@Autowired
	GifService gifDespicableMeService;

	@RequestMapping(value = "/rest/gifs/despicable-me", method = RequestMethod.GET, //
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public List<Gif> getDespicableMeGif() {
		return gifDespicableMeService.getAllDespicableMe();
	}
	
	@RequestMapping(value = "/rest/gifs/dragonball", method = RequestMethod.GET, //
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public List<Gif> getDragonballGif() {
		return gifDespicableMeService.getAllDragonball();
	}
}
