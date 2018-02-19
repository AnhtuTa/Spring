package chap2_wire.using_java;

public class CDPlayer {
	private CompactDisc compactDisc;

	public CompactDisc getCd() {
		return compactDisc;
	}

	public void setCompactDisc(CompactDisc cd) {
		this.compactDisc = cd;
	}
	
	public void play() {
		compactDisc.play();
	}
}
