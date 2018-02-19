package chap4_AOP.concert_2;

// Chú ý rằng mỗi đĩa CD (CompactDisc) gồm nhiều bài hát/bản ghi/bản nhạc/track
public interface CompactDisc {
	public void play();
	
	public void playTrack(int trackNumber);
}
