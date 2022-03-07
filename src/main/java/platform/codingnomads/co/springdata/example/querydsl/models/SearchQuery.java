package platform.codingnomads.co.springdata.example.querydsl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SearchQuery {
    private Long id;
    private String origin;
    private String destination;
    private String code;
    private String name;
}
