package chap4_AOP.concert_2;

import java.util.List;
import java.util.Random;

public class BlankDisc implements CompactDisc {
	private String title;	// tên của đĩa CD
	private String artist;	// ca sĩ của đĩa này
	private List<String> tracks;	//tập các bài hát trong đĩa CD này (của ca sĩ trên)
	
	public BlankDisc() {}
	
	public BlankDisc(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public List<String> getTracks() {
		return tracks;
	}

	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}

	// playing a random track
	@Override
	public void play() {
		System.out.println("Selecting a random track to play...");
		Random rd = new Random();
		int num = tracks.size() > 0 ? rd.nextInt(tracks.size()) : -1;
		if(num == -1) System.out.println("There's nothing to play!");
		else {
			System.out.println("Playing \"" + tracks.get(num) + "\" - " + this.artist);
		}
	}

	@Override
	public void playTrack(int trackNumber) {
		System.out.println("Playing \"" + tracks.get(trackNumber) + "\" - " + this.artist);
	}

}
