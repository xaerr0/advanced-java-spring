package platform.codingnomads.co.springdata.example.mybatis.extraexample.models;

import lombok.Data;

import java.util.List;

@Data
public class Lesson {

    private Long id;
    private String name;
    private String text;
    private List<Image> imageArrayList;
}
