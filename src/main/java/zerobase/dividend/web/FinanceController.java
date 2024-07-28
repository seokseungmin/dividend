package zerobase.dividend.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.dividend.service.FinanceService;

@Tag(name = "Finance", description = "배당금 관련 API 입니다.")
@RestController
@RequestMapping("finance")
@AllArgsConstructor
public class FinanceController {

    private final FinanceService financeService;

    @Operation(summary = "배당금 조회 API", description = "회사 이름을 기반으로 배당금을 조회합니다.")
    @GetMapping("/dividend/{companyName}")
    @PreAuthorize("hasRole('READ')")
    public ResponseEntity<?> searchFinance(
            @Parameter(description = "배당금을 조회할 회사 이름", required = true)
            @PathVariable("companyName") String companyName) {
        var result = this.financeService.getDividendByCompanyName(companyName);
        return ResponseEntity.ok(result);
    }
}
