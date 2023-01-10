package platform.codingnomads.co.springweb.resttemplate.DELETE.models;

import lombok.Data;

@Data
public class ResponseObject2 {
    User data;
    Error error;
    String status;
}