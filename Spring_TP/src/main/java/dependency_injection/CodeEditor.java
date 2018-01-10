package dependency_injection;

public class CodeEditor {
	String name;
	String version;
	HTMLChecker htmlChecker;
	PythonChecker pythonChecker;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public HTMLChecker getHtmlChecker() {
		return htmlChecker;
	}
	public void setHtmlChecker(HTMLChecker htmlChecker) {
		this.htmlChecker = htmlChecker;
	}
	public PythonChecker getPythonChecker() {
		return pythonChecker;
	}
	public void setPythonChecker(PythonChecker pythonChecker) {
		this.pythonChecker = pythonChecker;
	}
	
	public void codeCheck() {
		System.out.println("This is codeCheck() [CodeEditor.java]");
		if(htmlChecker != null) htmlChecker.htmlCheck();
		if(pythonChecker != null) pythonChecker.pythonCheck();
	}
}
