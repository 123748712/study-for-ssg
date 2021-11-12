package study.com.service;

import study.com.container.Container;
import study.com.dto.Member;

public class MemberService {

	public Member getMemberByloginId(String loginId) {

		return Container.memberDao.getMemberByloginId(loginId);
	}

	public void add(Member member) {
		Container.memberDao.add(member);
		
	}

}
