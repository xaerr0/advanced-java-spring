package platform.codingnomads.co.springdata.example.mybatis.extraexample.models;

import lombok.Data;

import java.util.List;

@Data
public class Chapter {

    private Long id;
    private String name;
    private List<Lesson> lessons;
}
