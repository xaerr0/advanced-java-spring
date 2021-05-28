package platform.codingnomads.co.springdata.example.mybatis.extraexample.models;

import lombok.Data;

import java.util.List;

@Data
public class Section {

    private Long id;
    private String name;
    private List<Chapter> chapters;

    public Section() {
    }

    //    public Section(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
}
