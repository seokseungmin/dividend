package zerobase.dividend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@ToString(includeFieldNames = true)
//@EqualsAndHashCode
//@RequiredArgsConstructor
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private String ticker;
    private String name;
}
