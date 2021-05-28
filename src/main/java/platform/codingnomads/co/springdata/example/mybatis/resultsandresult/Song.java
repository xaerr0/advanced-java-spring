package platform.codingnomads.co.springdata.example.mybatis.resultsandresult;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Song {

    private Long id;

    private String name;

    private String albumName;

    private String artistName;

    //song length in seconds
    private int songLength;

    public Song(String name, String albumName, String artistName, int songLength) {
        this.name = name;
        this.albumName = albumName;
        this.artistName = artistName;
        this.songLength = songLength;
    }
}
