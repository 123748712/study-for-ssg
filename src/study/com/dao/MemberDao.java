package study.com.dao;

import java.util.ArrayList;
import java.util.List;

import study.com.dto.Member;

public class MemberDao {
	public List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}

	public void makeTestData() {
		members.add(new Member("admin", "admin", "admin"));
		members.add(new Member("user 1", "user 1", "user 1"));
		members.add(new Member("user 2", "user 2", "user 2"));

		System.out.println("Test Member 가 생성되었습니다.");
	}
	public Member getMemberByloginId(String loginId) {
		Member foundMember = null;

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				foundMember = member;
			}
		}
		return foundMember;
	}

	public void add(Member member) {
		members.add(member);
		
	}
}
