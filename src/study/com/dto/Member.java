package study.com.dto;

import study.com.util.Util;

public class Member extends Dto {
	public int index;
	public int memberId;
	public String loginId;
	public String loginPw;
	public String name;
	public String regData;

	public Member(String loginId, String loginPw, String name) {
		this.index++;
		this.memberId = index;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.regData = Util.getNowDateStr();
	}
}
