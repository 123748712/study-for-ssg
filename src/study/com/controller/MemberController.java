package study.com.controller;

import java.util.List;
import java.util.Scanner;

import study.com.dto.Member;

public class MemberController extends Controller {
	private Scanner scanner;
	private List<Member> members;

	public MemberController(Scanner scanner, List<Member> members) {
		this.scanner = scanner;
		this.members = members;
	}

	public void doAction(String command, String actionMethod) {
		switch (actionMethod) {
		case "join":
			doJoin();
			break;
		default:
			System.out.println("잘못된 명령어를 입력하셨습니다.");
			break;
		}
	}

	private void doJoin() {
		System.out.println("회원가입 기능을 구현합니다.");

		String loginId = null;

		while (true) {

			System.out.println("회원가입 ID : ");
			loginId = scanner.nextLine();

			if (isJoinable(loginId)) {
				System.out.println("이미 존재하는 ID 입니다.");
				continue;
			}
			break;
		}

		String loginPw = null;
		String checkLoginPw = null;

		while (true) {
			System.out.println("회원가입 PW : ");
			loginPw = scanner.nextLine();

			System.out.println("회원가입 PW 확인 : ");
			checkLoginPw = scanner.nextLine();

			if (!loginPw.equals(checkLoginPw)) {
				System.out.println("비밀번호를 확인해주세요.");
				continue;
			}
			break;
		}

		System.out.println("회원가입 이름 : ");
		String name = scanner.nextLine();

		Member member = new Member(loginId, loginPw, name);

		members.add(member);

		System.out.println(member.name + "님 회원가입이 완료되었습니다.");
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
}