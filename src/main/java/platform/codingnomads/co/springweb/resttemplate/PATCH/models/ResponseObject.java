package platform.codingnomads.co.springweb.resttemplate.PATCH.models;

import lombok.Data;

@Data
public class ResponseObject {
    Task data;
    Error error;
    String status;
}
