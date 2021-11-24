package study.com.container;

import java.util.List;

import study.com.dao.ArticleDao;
import study.com.dao.MemberDao;
import study.com.dto.Article;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
	}
}
