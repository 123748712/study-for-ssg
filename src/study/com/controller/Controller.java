package study.com.controller;

import study.com.dto.Member;

public abstract class Controller {
	static Member loginedMember = null;
	
	public abstract void doAction(String command, String actionMethod);

}
