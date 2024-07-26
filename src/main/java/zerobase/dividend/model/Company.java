package zerobase.dividend.model;

import lombok.Builder;
import lombok.Data;

//@Getter
//@Setter
//@ToString(includeFieldNames = true)
//@EqualsAndHashCode
//@RequiredArgsConstructor
@Data
@Builder
public class Company {
    private String ticker;
    private String name;
}
