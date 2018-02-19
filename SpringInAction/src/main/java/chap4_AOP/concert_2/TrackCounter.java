package chap4_AOP.concert_2;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TrackCounter {
	// Đếm số lượt play của 1 bản nhạc. VD: trackCounts.get(6) = 13
	// nghĩa là bản nhạc có id=6 đã đc play 13 lần
	private Map<Integer, Integer> trackCounts = new HashMap<>();
	
	@Pointcut("execution(* chap4_AOP.concert_2.CompactDisc.playTrack(int)) && args(trackNumber)")
	public void trackPlayed(int trackNumber) {}
	
	@Before("trackPlayed(trackNumber)")
	public void countTrack(int trackNumber) {
		int currCount = getPlayCount(trackNumber);
		trackCounts.put(trackNumber, currCount+1);
		System.out.println("So lan play bai hat " + trackNumber + ": " + (currCount+1));
	}
	
	public int getPlayCount(int trackNumber) {
		return trackCounts.containsKey(trackNumber) ? trackCounts.get(trackNumber) : 0;
	}
}
