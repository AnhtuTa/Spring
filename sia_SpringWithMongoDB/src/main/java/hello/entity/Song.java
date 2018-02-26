package hello.entity;

public class Song {
	String title;
	String artist;
	int year;
	
	public Song() {}
	
	public Song(String title, String artist, int year) {
		super();
		this.title = title;
		this.artist = artist;
		this.year = year;
	}

	public Song(String title, String artist) {
		super();
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String getInfo() {
		return this.title + " - " + this.artist + " - " + this.year;
	}
}
