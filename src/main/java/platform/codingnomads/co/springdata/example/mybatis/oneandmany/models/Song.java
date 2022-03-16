package platform.codingnomads.co.springdata.example.mybatis.oneandmany.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Song {

    private Long id;

    private String name;

    private String albumName;

    private Artist artist;

    //song length in seconds
    private int songLength;

    public Song(String name, String albumName, Artist artist, int songLength) {
        this.name = name;
        this.albumName = albumName;
        this.artist = artist;
        this.songLength = songLength;
    }
}
