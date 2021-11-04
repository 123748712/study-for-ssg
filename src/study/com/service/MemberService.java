package study.com.service;

import study.com.container.Container;
import study.com.dto.Member;

public class MemberService {

	public void add(Member member) {
		Container.memberDao.add(member);

	}

	public Member getMemberByLoginId(String loginId) {

		return Container.memberDao.getMemberByLoginId(loginId);
	}
}
