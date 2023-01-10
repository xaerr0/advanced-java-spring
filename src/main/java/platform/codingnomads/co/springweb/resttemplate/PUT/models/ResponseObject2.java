package platform.codingnomads.co.springweb.resttemplate.PUT.models;

import lombok.Data;

@Data
public class ResponseObject2 {
    User data;
    Error error;
    String status;
}