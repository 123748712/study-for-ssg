package study.com.dao;

import java.util.ArrayList;
import java.util.List;

import study.com.dto.Member;

public class MemberDao {
	public List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}
}
