package platform.codingnomads.co.springweb.usingRestTemplate.DELETE.models;

import lombok.Data;

@Data
public class ResponseObject {
    Task data;
    Error error;
    int statusCode;
}
