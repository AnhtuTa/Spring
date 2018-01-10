package dependency_injection;

public class TextEditor {
	String name;
	private SpellChecker spellChecker;
	
	public TextEditor(String name, SpellChecker spellChecker) {
		this.name = name;
		this.spellChecker = spellChecker;
		System.out.println("This is constructor-based DI [TextEditor.java]");
	}
	
	public void spellCheck() {
		spellChecker.spellCheck();
	}
}
