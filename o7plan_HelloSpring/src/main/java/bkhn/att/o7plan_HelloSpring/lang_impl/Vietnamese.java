package bkhn.att.o7plan_HelloSpring.lang_impl;

import bkhn.att.o7plan_HelloSpring.lang.Language;

public class Vietnamese implements Language {

	@Override
	public String getGreeting() {
		return "Xin chào các bạn";
	}

	@Override
	public String getBye() {
		return "Tạm biệt các bạn";
	}

}
