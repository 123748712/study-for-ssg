package study.com.dto;

import study.com.util.Util;

public class Member extends Dto {
	public static int index;
	public int memberId;
	public String loginId;
	public String loginPw;
	public String name;
	public String regDate;

	public Member(String loginId, String loginPw, String name) {
		this.index++;
		this.memberId = index;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.regDate = Util.getNowDateStr();
	}
}
