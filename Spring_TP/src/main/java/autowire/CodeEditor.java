package autowire;

public class CodeEditor {
	String name;
	private SpellChecker spellChecker;

	// Cần có hàm khởi tạo mới xài đc autowire by constructor
	public CodeEditor(String name, SpellChecker spellChecker) {
		this.name = name;
		this.spellChecker = spellChecker;
	}

	public String getName() {
		return name;
	}
	public SpellChecker getSpellChecker() {
		return spellChecker;
	}


	public void spellCheck() {
		spellChecker.spellCheck();
	}
}
