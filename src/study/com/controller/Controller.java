package study.com.controller;

import study.com.dto.Member;

public abstract class Controller {

	public abstract void doAction(String command, String acthinMethodName);

	static Member loginedMember = null;
}
