package chap2_wire.using_java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CDPlayerConfig {
	//declare a simple bean
	@Bean(name = "HoQuangHieuCD")
	public CDPlayer what_the_hell(CompactDisc cd) {
		CDPlayer cdPlayer = new CDPlayer();
		cdPlayer.setCompactDisc(cd);
		return cdPlayer;
	}

	@Bean(name = "koPhaiDangVua")
	public CompactDisc KPDV() {
		return new KoPhaiDangVua();
	}

	/* 
	 * Bean koPhaiDangVua sẽ TỰ ĐỘNG được inject vào bean HoQuangHieuCD
	 * 
	 * Nếu có thêm 1 bean nữa cũng có kiểu CompactDisc thì sẽ lỗi,
	 * do có 2 thằng nên Spring ko biết chọn thằng nào để tiêm
	 */
	//@Bean(name = "lonelyHeartsClubBand")
	public CompactDisc sgtPeppers() {
		return new SgtPeppers();
	}
}
