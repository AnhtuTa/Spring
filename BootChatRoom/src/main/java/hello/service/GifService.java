package hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.entity.Gif;
import hello.repository.GifRepository;

@Service
public class GifService {

	@Autowired
	private GifRepository gifRepository;
	
	public List<Gif> getAllDespicableMe() {
		return gifRepository.findByType(Gif.GIF_DESPICABLE_ME);
	}

	public List<Gif> getAllDragonball() {
		return gifRepository.findByType(Gif.GIF_DRAGONBALL);
	}
}
