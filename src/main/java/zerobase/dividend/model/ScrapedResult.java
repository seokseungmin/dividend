package zerobase.dividend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
//@ToString(includeFieldNames = true)
//@EqualsAndHashCode
//@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class ScrapedResult {
    private Company company;
    private List<Dividend> dividendEntities;

    public ScrapedResult() {
        this.dividendEntities = new ArrayList<>();
    }
}
