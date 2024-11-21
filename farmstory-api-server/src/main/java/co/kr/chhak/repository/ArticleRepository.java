package co.kr.chhak.repository;

import co.kr.chhak.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    public Page<Article> findAllByCate(String cate, Pageable pageable);
}
