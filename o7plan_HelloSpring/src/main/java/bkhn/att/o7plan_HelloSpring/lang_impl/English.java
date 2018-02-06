package bkhn.att.o7plan_HelloSpring.lang_impl;

import bkhn.att.o7plan_HelloSpring.lang.Language;

public class English implements Language {

	@Override
	public String getGreeting() {
		return "Hello guys!";
	}

	@Override
	public String getBye() {
		return "Goodbye guys";
	}

}
