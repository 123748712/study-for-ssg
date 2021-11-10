package study.com.dao;

import java.util.ArrayList;
import java.util.List;

import study.com.dto.Article;

public class ArticleDao {
	public List<Article> articles;

	public ArticleDao() {
		articles = new ArrayList<>();
	}
}