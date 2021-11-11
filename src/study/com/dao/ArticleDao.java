package study.com.dao;

import java.util.ArrayList;
import java.util.List;

import study.com.dto.Article;
import study.com.dto.Member;

public class ArticleDao {
	public List<Article> articles;

	public ArticleDao() {
		articles = new ArrayList<>();
	}
}