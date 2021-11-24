package study.com.controller;

import java.util.Scanner;

import study.com.container.Container;
import study.com.dto.Member;
import study.com.service.MemberService;

public class MemberController extends Controller {
	private Scanner scanner;
	private MemberService memberService;

	public MemberController(Scanner scanner) {
		this.scanner = scanner;
		this.memberService = new MemberService();
	}

	public void doAction(String command, String actionMethod) {
		switch (actionMethod) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
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

			if (memberService.isJoinable(loginId)) {
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
				System.out.println("PW를 다시한번 확인해주세요.");
				continue;
			}
			break;
		}

		System.out.println("회원가입 이름 : ");
		String name = scanner.nextLine();

		Member member = new Member(loginId, loginPw, name);

		memberService.add(member);

		System.out.println(member.name + "님 회원가입이 완료되었습니다.");

	}

	private void doLogin() {
		System.out.println("로그인 기능을 구현합니다.");
		
		if(loginedMember != null) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}

		System.out.println("로그인 ID : ");
		String loginId = scanner.nextLine();

		System.out.println("로그인 PW : ");
		String loginPw = scanner.nextLine();

		Member foundMember = memberService.getMemberByLoginId(loginId);

		if (foundMember == null) {
			System.out.println("존재하지 않는 아이디입니다.");
			return;
		}

		if (!foundMember.loginPw.equals(loginPw)) {
			System.out.println("비밀번호를 잘못 입력하셨습니다.");
			return;
		}
		loginedMember = foundMember;

		System.out.println(foundMember.name + "님 환영합니다.");
	}

	private void doLogout() {
		System.out.println("로그아웃 기능을 구현합니다.");
		
		if(loginedMember == null) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		loginedMember = null;
		
		System.out.println("로그아웃 되었습니다.");
	}
}