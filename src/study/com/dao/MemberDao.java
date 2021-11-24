package study.com.dao;

import java.util.ArrayList;
import java.util.List;

import study.com.dto.Article;
import study.com.dto.Member;

public class MemberDao {
	public List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}
	public void makeTestData() {
		System.out.println("Article Test Data를 생성합니다.");

		members.add(new Member("admin", "admin", "admin"));
		members.add(new Member("user 1", "user 1", "user 1"));
		members.add(new Member("user 2", "user 2", "user 2"));
	}
	public void add(Member member) {
		members.add(member);
		
	}
	public Member getMemberByLoginId(String loginId) {
			Member foundMember = null;

			for (Member member : members) {
				if (member.loginId.equals(loginId)) {
					foundMember = member;
				}
			}
			return foundMember;
		}
	public boolean isJoinable(String loginId) {
		boolean isJoinable = false;

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				isJoinable = true;
				break;
			}
		}
		return isJoinable;
	}
}
