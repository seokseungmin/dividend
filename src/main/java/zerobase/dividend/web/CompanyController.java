package zerobase.dividend.web;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import zerobase.dividend.model.Company;
import zerobase.dividend.persist.entity.CompanyEntity;
import zerobase.dividend.service.CompanyService;

import java.util.List;


@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    // 배당금 검색 - 자동완성 API
    @GetMapping("/autocomplete")
    public ResponseEntity<?> autoComplete(@RequestParam String keyword) {
        //저장된 Trie에서 데이터를 가져오는 작업.
        var result = this.companyService.getCompanyNamesByKeyword(keyword);
        return ResponseEntity.ok(result);
    }

    // 회사 리스트 조회 API
    @GetMapping
    public ResponseEntity<?> searchCompany(final Pageable pageable) {
        Page<CompanyEntity> companies = this.companyService.getAllCompany(pageable);
        return ResponseEntity.ok(companies);
    }

    // 배당금 데이터 저장 API
    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody Company request) {
        String ticker = request.getTicker().trim();
        if (ObjectUtils.isEmpty(ticker)) {
            throw new RuntimeException("ticker is empty");
        }

        Company company = this.companyService.save(ticker);

        // 회사를 저장할때마다 Trie에 회사명이 들어감.
        this.companyService.addAutoCompleteKeyword(company.getName());

        return ResponseEntity.ok(company);
    }

    // 배당금 데이터 삭제 API
    @DeleteMapping
    public ResponseEntity<?> deleteCompany() {
        return null;
    }
}
