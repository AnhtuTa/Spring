package hello.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import hello.model.Sticker;

@Repository
public class StickerDAO {
	public List<Sticker> getQooBeeStickers() {
		List<Sticker> stickerList = new ArrayList<>();
		int numOfFiles = 45;
		
		for(int i=1; i<=numOfFiles; i++) {
			stickerList.add(new Sticker("/img/qoobee/qoobee_" + i + ".gif", "Qoo Bee"));
		}
		
		return stickerList;
	}
	
	public List<Sticker> getPepeStickers() {
		List<Sticker> stickerList = new ArrayList<>();
		
		// 13 = số lượng sticker pepe trong thư mục /img/pepe_frog
		for(int i=1; i<=13; i++) {
			stickerList.add(new Sticker("/img/pepe_frog/pepe_the_frog" + i + ".png", "Pepe the frog"));
		}
		for(int i=1; i<=14; i++) {
			stickerList.add(new Sticker("/img/pepe_frog/pepe_the_frog_" + i + ".gif", "Pepe the frog"));
		}
		return stickerList;
	}
	
	public List<Sticker> getYaoMingStickers() {
		List<Sticker> stickerList = new ArrayList<>();
		
		for(int i=1; i<=3; i++) {
			stickerList.add(new Sticker("/img/yao_ming/yao_ming_" + i + ".jpg", "Yao Ming"));
		}

		return stickerList;
	}
}
