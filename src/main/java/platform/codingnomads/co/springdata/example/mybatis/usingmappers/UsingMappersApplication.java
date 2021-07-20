package platform.codingnomads.co.springdata.example.mybatis.usingmappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.codingnomads.co.springdata.example.mybatis.usingmappers.mappers.ArtistMapper;
import platform.codingnomads.co.springdata.example.mybatis.usingmappers.mappers.SongMapper;
import platform.codingnomads.co.springdata.example.mybatis.usingmappers.models.Artist;
import platform.codingnomads.co.springdata.example.mybatis.usingmappers.models.Song;

@SpringBootApplication
public class UsingMappersApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(UsingMappersApplication.class);
    }

    @Autowired
    ArtistMapper artistMapper;

    @Autowired
    SongMapper songMapper;

    @Autowired
    Service service;

    @Override
    public void run(String... args) throws Exception {
        insertData();
        queryData();
        deleteSomeData();
//        service.doSomething();
    }
    
    public void insertData() {
        //create artist
        Artist tyler = new Artist();
        tyler.setName("Tyler, The Creator");
        tyler.setBio("Insert Bio Here");

        //enter artist first since song requires artist ID
        artistMapper.insertNewArtist(tyler);

        //create song using artist
        Song earfquake = new Song();
        earfquake.setName("EARFQUAKE");
        earfquake.setAlbumName("IGOR");
        earfquake.setArtist(tyler);
        earfquake.setSongLength(190);

        //insert new song
        songMapper.insertNewSong(earfquake);

        Song seeYouAgain = new Song();
        seeYouAgain.setName("See You Again (feat. Kali Uchis)");
        seeYouAgain.setAlbumName("Flower Boy");
        seeYouAgain.setArtist(tyler);
        seeYouAgain.setSongLength(180);

        //insert new song. note there are no detachment issues
        songMapper.insertNewSong(seeYouAgain);
    }

    public void queryData() {

        //get a song by name
        System.out.println(songMapper.getSongsByName("EARFQUAKE"));

        //get a song by ID
        System.out.println(songMapper.getSongById(1L));

        //get an artist (remember songs excluded from toString())
        Artist artist = artistMapper.getArtistById(1L);
        System.out.println(artist);

        //print artists songs
        for(Song s: artist.getSongs()) {
            System.out.println(s);
        }
    }

    public void deleteSomeData() {
        songMapper.deleteSongById(1L);
    }
}
