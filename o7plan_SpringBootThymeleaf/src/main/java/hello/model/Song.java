package hello.model;

public class Song {
	int id;
	String title;
	String artist;
	int year;	// năm phát hành
	
	private String info;
	
	public Song() {}
	
	public Song(int id, String title, String artist, int year) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.year = year;
		this.info = title + " - " + artist + " - " + year;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}
