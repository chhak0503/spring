package co.kr.chhak.service;

import co.kr.chhak.dto.ArticleDTO;
import co.kr.chhak.entity.Article;
import co.kr.chhak.entity.User;
import co.kr.chhak.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;

    public int save(ArticleDTO articleDTO) {

        Article article = modelMapper.map(articleDTO, Article.class);

        User user = User.builder()
                        .uid(articleDTO.getWriter())
                        .build();

        article.setUser(user);

        Article savedArticle = articleRepository.save(article);

        return savedArticle.getNo();
    }



}
