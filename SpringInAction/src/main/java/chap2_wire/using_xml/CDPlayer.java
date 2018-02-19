package chap2_wire.using_xml;

import org.springframework.beans.factory.annotation.Autowired;

public class CDPlayer {
	private CompactDisc compactDisc;

	public CompactDisc getCd() {
		return compactDisc;
	}

	// Nếu ko dùng anno @Autowired thì bên file beans.xml phải có thêm thẻ
	// <property> trong bean HoQuangHieuCD
	@Autowired
	public void setCompactDisc(CompactDisc compactDisc) {
		this.compactDisc = compactDisc;
	}
	
	public void play() {
		compactDisc.play();
	}
}
