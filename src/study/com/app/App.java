package study.com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import study.com.controller.ArticleController;
import study.com.dto.Article;
import study.com.dto.Member;

public class App {

	List<Article> articles = new ArrayList<>();
	List<Member> members = new ArrayList<>();

	public void start() {

		System.out.println("=== 프로그램 시작 ===");
		Scanner scanner = new Scanner(System.in);
		int articleId = 0;

		makeTestData();
		ArticleController articleController = new ArticleController(scanner, articles);
		while (true)

		{
			System.out.println("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();

			if (command.equals("member join")) {
				System.out.println("회원가입 기능을 구현합니다.");

				String loginId = null;

				while (true) {
					boolean isJoinable = false;

					System.out.println("회원가입 ID : ");
					loginId = scanner.nextLine();

					if (loginIdConfirm(loginId)) {
						System.out.println("이미 존재하는 아이디입니다.");
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
				System.out.println(member.name + "님 회원가입이 완료되었습닌다.");
			} else if (command.equals("article write")) {
				articleController.doWrite();
			} else if (command.startsWith("article list")) {
				articleController.showList(command);
			} else if (command.startsWith("article detail ")) {
				articleController.showDetail(command);
			} else if (command.startsWith("article modify ")) {
				articleController.doModify(command);
			} else if (command.startsWith("article delete ")) {
				articleController.doDelete(command);
			} else if (command.equals("system exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("잘못된 명령어를 입력하셨습니다.");
			}
		}
		System.out.println("=== 프로그램 종료 ===");
	}

	void makeTestData() {
		articles.add(new Article("제목 1", "내용 1"));
		articles.add(new Article("제목 2", "내용 2"));
		articles.add(new Article("제목 3", "내용 3"));

		System.out.println("Test Article 이 생성되었습니다.");
	}

	boolean loginIdConfirm(String loginId) {
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