package study.com.dao;

import java.util.ArrayList;
import java.util.List;

import study.com.dto.Article;

public class ArticleDao {
	public List<Article> articles;

	public ArticleDao() {
		articles = new ArrayList<>();
	}

	public void makeTestData() {
		System.out.println("Article Test Data를 생성합니다.");

		articles.add(new Article("제목 1", "내용 1", 1, "admin"));
		articles.add(new Article("제목 2", "내용 2", 2, "user 1"));
		articles.add(new Article("제목 3", "내용 3", 3, "user 2"));
	}

	public void add(Article article) {
		articles.add(article);
	}

	public List<Article> getSearchedArticlesByKeyword(String searchKeyword) {
		List<Article> searchedArticles = null;
		if (searchKeyword.length() > 0) {
			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					searchedArticles.add(article);
				}
			}
		} else {
			searchedArticles = articles;
		}
		return searchedArticles;
	}

	public int getFoundIdByCheckStr(String checkStr) {
		boolean checkInt = checkStr.matches("-?\\d+");
		int foundId = 0;

		if (checkInt) {
			foundId = Integer.parseInt(checkStr);
		}
		return foundId;
	}

	public Article getFoundArticleById(int foundId) {
		Article foundArticle = null;

		for (Article article : articles) {
			if (article.articleId == foundId) {
				foundArticle = article;
			}
		}
		return foundArticle;
	}

	public void remove(Article foundArticle) {
		articles.remove(foundArticle);
	}
}