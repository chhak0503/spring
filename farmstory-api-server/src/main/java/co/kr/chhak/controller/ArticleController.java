package co.kr.chhak.controller;

import co.kr.chhak.dto.ArticleDTO;
import co.kr.chhak.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public ResponseEntity write(@RequestBody ArticleDTO articleDTO, HttpServletRequest req) {

        log.info(articleDTO);

        articleDTO.setRegip(req.getRemoteAddr());
        int no = articleService.save(articleDTO);

        return ResponseEntity
                .ok()
                .body(no);
    }


}
