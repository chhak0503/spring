package co.kr.chhak.controller;

import co.kr.chhak.dto.PageRequestDTO;
import co.kr.chhak.dto.PageResponseDTO;
import co.kr.chhak.dto.ProductDTO;
import co.kr.chhak.service.ProductService;
import co.kr.chhak.util.CustomFileUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;
    private final CustomFileUtil customFileUtil;

    @GetMapping("/product/{pg}")
    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO){

        PageResponseDTO<ProductDTO> pageResponseDTO = productService.list(pageRequestDTO);
        log.info(pageResponseDTO);

        return pageResponseDTO;
    }

    @GetMapping("/product/thumb/{fileName}")
    public ResponseEntity<Resource> thumbnail(@PathVariable String fileName){
        return customFileUtil.getFile(fileName);
    }

    @PostMapping("/product")
    public ResponseEntity<Map<String, Integer>> register(ProductDTO productDTO) {
        log.info(productDTO);

        List<MultipartFile> files = productDTO.getThumbFiles();

        // 파일 저장
        Map<String, String> uploadFileNames = customFileUtil.saveFiles(files);

        productDTO.setThumbNames(uploadFileNames);

        // 상품 저장
        int pno = productService.register(productDTO);

        return ResponseEntity.ok(Map.of("pno", pno));

    }

}
