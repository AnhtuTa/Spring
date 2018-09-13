package chap4_AOP.concert;

public class EmCuaNgayHomQua_SonTungMTP implements Performance {

	@Override
	public void perform() {
		System.out.println("Son Tung starts singing Em cua ngay hom qua...");
		sleep(1000);
		
		System.out.println("[🎵🎵🎵] Dung voi vang em hay la em cua ngay hom qua, ú u u ù... [🎵🎵🎵]");
		sleep(1000);
		
		System.out.println("[🎵🎵🎵] Xin hay la em cua ngay hom qua, ú u ú ú... [🎵🎵🎵]");
		System.out.println(".......");
		sleep(1000);
		
		System.out.println("[🎵🎵🎵] Dung lai va xoa di nhung ky uc, ú u ú ù... [🎵🎵🎵]");
		sleep(1000);
		
		System.out.println("......");
		System.out.println("Son Tung finishes the song!");
	}

	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
