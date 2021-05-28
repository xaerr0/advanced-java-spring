package platform.codingnomads.co.springdata.example.mybatis.mapperslesson;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Song {
    private Long id;

    private String name;

    private String album_name;

    private String artist_name;

    //song length in seconds
    private int song_length;

}
