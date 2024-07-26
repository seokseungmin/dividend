package zerobase.dividend.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

//@Getter
//@Setter
//@ToString(includeFieldNames = true)
//@EqualsAndHashCode
//@RequiredArgsConstructor
@Data
@Builder
public class Dividend {
    private LocalDateTime date;
    private String dividend;
}
