package study.com.service;

import study.com.container.Container;
import study.com.dto.Member;

public class MemberService {

	public Member getMemberByLoginId(String loginId) {

		return Container.memberDao.getMemberByLoginId(loginId);
	}

	public void add(Member member) {
		Container.memberDao.add(member);
		
	}

	public boolean isJoinable(String loginId) {

		return Container.memberDao.isJoinable(loginId);
	}
}
