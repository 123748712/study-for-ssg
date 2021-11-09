package study.com.controller;

import java.util.List;
import java.util.Scanner;

import study.com.dto.Article;
import study.com.dto.Member;

public class MemberController extends Controller {
	private Scanner scanner;
	private List<Member> members;

	public MemberController(Scanner scanner, List<Member> members) {
		this.scanner = scanner;
		this.members = members;
	}

	public void doAction(String command, String actionMethodName) {
		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout" :
			doLogout();
			break;
		default:
			System.out.println("명령어를 잘못 입력하셨습니다.");
			break;
		}
	}
	public void doJoin() {
		System.out.println("회원가입 기능을 구현합니다.");

		String loginId = null;

		while (true) {
			System.out.println("회원가입 ID : ");
			loginId = scanner.nextLine();

			if (isJoinable(loginId)) {
				System.out.println("이미 가입된 아이디 입니다.");
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.println("회원가입 PW : ");
			loginPw = scanner.nextLine();

			System.out.println("회원가입 PW 확인 : ");
			loginPwConfirm = scanner.nextLine();

			if (!loginPw.equals(loginPwConfirm)) {
				System.out.println("비밀번호를 잘못 입력하셨습니다.");
				continue;
			}
			break;
		}
		System.out.println("회원가입 이름 : ");
		String name = scanner.nextLine();

		Member member = new Member(loginId, loginPw, name);

		members.add(member);

		System.out.println(name + "님 회원가입이 완료되었습니다.");
	}

	public void doLogin() {
		System.out.println("로그인 기능을 구현합니다.");

		if (loginedMember != null) {
			System.out.println("이미 로그인 되어있습니다.");
			return;
		}

		System.out.println("login ID : ");
		String loginId = scanner.nextLine();

		System.out.println("login PW : ");
		String loginPw = scanner.nextLine();

		Member foundMember = getMemberByloginId(loginId);

		if (foundMember == null) {
			System.out.println("존재하지 않는 ID 입니다.");
			return;
		}

		if (!foundMember.loginPw.equals(loginPw)) {
			System.out.println("비밀번호를 잘못 입력하셨습니다.");
			return;
		}
		loginedMember = foundMember;

		System.out.println(foundMember.name + "님 로그인 되었습니다.");
	}

	public void doLogout() {
		System.out.println("로그아웃 기능을 구현합니다.");
		
		if(loginedMember == null) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
			loginedMember = null;
			System.out.println("로그아웃 되었습니다.");
		}

	private Member getMemberByloginId(String loginId) {
		Member foundMember = null;

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				foundMember = member;
			}
		}
		return foundMember;
	}

	boolean isJoinable(String loginId) {
		boolean isJoinable = false;

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				isJoinable = true;
				break;
			}
		}
		return isJoinable;
	}
	public void makeTestData() {
		members.add(new Member("admin", "admin", "admin"));
		members.add(new Member("user 1", "user 1", "user 1"));
		members.add(new Member("user 2", "user 2", "user 2"));

		System.out.println("Test Member 가 생성되었습니다.");
	}
}
