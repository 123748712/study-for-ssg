package study.com.service;

import java.util.List;

import study.com.container.Container;
import study.com.dto.Article;

public class ArticleService {

	public void add(Article article) {
		Container.articleDao.add(article);

	}

	public List<Article> getSearchedArticlesByKeyword(String searchKeyword) {
		return Container.articleDao.getSearchedArticlesByKeyword(searchKeyword);
	}

	public Article getFoundArticleById(int foundId) {
		return Container.articleDao.getFoundArticleById(foundId);
	}

	public void remove(Article foundArticle) {
		Container.articleDao.remove(foundArticle);
	}

	public int getFoundIdByCheckStr(String checkStr) {
	
		return Container.articleDao.getFoundIdByCheckStr(checkStr);
	}
}
