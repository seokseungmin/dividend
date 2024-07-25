package zerobase.dividend.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    // 배당금 검색 - 자동완성 API
    @GetMapping("/autocomplete")
    public ResponseEntity autoComplete(@RequestParam String keyword) {
        return null;
    }

    // 회사 리스트 조회 API
    @GetMapping
    public ResponseEntity<?> searchCompany() {
        return null;
    }

    // 배당금 데이터 저장 API
    @PostMapping
    public ResponseEntity<?> addCompany(){
        return null;
    }

    // 배당금 데이터 삭제 API
    @DeleteMapping
    public ResponseEntity<?> deleteCompany(){
        return null;
    }
}
