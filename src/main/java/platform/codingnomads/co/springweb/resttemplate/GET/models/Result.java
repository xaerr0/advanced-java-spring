package platform.codingnomads.co.springweb.resttemplate.GET.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Result {
    private String display;

    @JsonProperty("search_value")
    private String searchValue;

    private String type;
}