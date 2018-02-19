package chap4_AOP.concert_2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = 
			new AnnotationConfigApplicationContext(Concert_2Config.class);
		CompactDisc cd = (CompactDisc) context.getBean("sonTungCD");
		//cd.play();
		cd.playTrack(4);
		cd.playTrack(4);
		cd.playTrack(4);
		cd.playTrack(4);
		cd.playTrack(3);
		cd.playTrack(3);
		cd.playTrack(3);
		
		((ConfigurableApplicationContext) context).close();
	}
}
