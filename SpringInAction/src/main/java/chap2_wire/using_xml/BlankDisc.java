package chap2_wire.using_xml;

// trong VD này thì đĩa CD BlankDisc chỉ có 1 bài hát/bản ghi/track
// Và title và artist chính là tên của bài hát đó và ca sĩ hát nó
public class BlankDisc implements CompactDisc {
	String title;
	String artist;
	
	public BlankDisc() {}
	
	public BlankDisc(String title, String artist) {
		super();
		this.title = title;
		this.artist = artist;
	}

	@Override
	public void play() {
		System.out.println("Playing \"" + this.title + "\" - " + this.artist);
	}

}
