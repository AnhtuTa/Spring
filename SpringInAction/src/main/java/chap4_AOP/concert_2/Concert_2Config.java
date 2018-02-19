package chap4_AOP.concert_2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy		//tự động phát hiện bean nào là Aspect
public class Concert_2Config {
	@Bean
	public TrackCounter TetHolidayIsComming() {
		return new TrackCounter();
	}
	
	@Bean(name="sonTungCD")
	public CompactDisc getCD() {
		BlankDisc bd = new BlankDisc();
		List<String> trackList = new ArrayList<>();
		trackList.add("Em cua ngay hom qua");
		trackList.add("Con mua ngang qua");
		trackList.add("Nang am xa dan");
		trackList.add("Khong phai dang vua dau");
		trackList.add("Lac troi");
		trackList.add("Chung ta khong thuoc ve nhau");
		trackList.add("Noi nay co anh");
		trackList.add("Chac ai do se ve");
		
		bd.setTitle("Tuyen tap cac ca khuc ghe ghom nhat cua Son Tung MTP");
		bd.setArtist("Son tung");
		bd.setTracks(trackList);
		
		return bd;
	}
}
