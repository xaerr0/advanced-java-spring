package platform.codingnomads.co.springdata.example.mybatis.extraexample.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image {

    private String name;
    private byte[] imageData;
}
