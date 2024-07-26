package zerobase.dividend.persist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import zerobase.dividend.model.Company;

@Entity(name = "COMPANY")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ticker가 중복이 되면 안되기 때문에 unique = true 설정
    @Column(unique = true)
    private String ticker;

    private String name;

    public CompanyEntity(Company company) {
        this.ticker = company.getTicker();
        this.name = company.getName();
    }
}
