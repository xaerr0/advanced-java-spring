package platform.codingnomads.co.springdata.example.mybatis.mapperslesson;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class Song {

    private Long id;

    private String name;

    private String albumName;

    private String artistName;

    //song length in seconds
    private int songLength;
}