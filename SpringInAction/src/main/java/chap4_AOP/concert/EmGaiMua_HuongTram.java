package chap4_AOP.concert;

public class EmGaiMua_HuongTram implements Performance {

	@Override
	public void perform() throws Exception {
		System.out.println("Huong Tram starts singing Em cua ngay hom qua...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[🎵🎵🎵] Đừng lo lắng về em khi mà em... vẫn còn yêu anh [🎵🎵🎵]");
		System.out.println("[🎵🎵🎵] Càng xa lánh, càng trống vắng... tim cứ đau và nhớ lắm... [🎵🎵🎵]");
		System.out.println(".......");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[🎵🎵🎵] Mưa trôi để lại ngây thơ, trong giấc mơ buốt lạnh... [🎵🎵🎵]");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		throw new Exception("Huong Tram starts coughing and sneezing... Hat_xi...");
//		System.out.println("......");
//		System.out.println("Huong Tram finishes the song!");
	}

}
