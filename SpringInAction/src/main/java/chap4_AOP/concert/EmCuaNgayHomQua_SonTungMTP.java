package chap4_AOP.concert;

public class EmCuaNgayHomQua_SonTungMTP implements Performance {

	@Override
	public void perform() {
		System.out.println("Son Tung starts singing Em cua ngay hom qua...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[🎵🎵🎵] Dung voi vang em hay la em cua ngay hom qua, ú u u ù... [🎵🎵🎵]");
		System.out.println("[🎵🎵🎵] Xin hay la em cua ngay hom qua, ú u ú ú... [🎵🎵🎵]");
		System.out.println(".......");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[🎵🎵🎵] Dung lai va xoa di nhung ky uc, ú u ú ù... [🎵🎵🎵]");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("......");
		System.out.println("Son Tung finishes the song!");
	}

}
