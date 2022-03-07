package platform.codingnomads.co.springdata.example.mybatis.usingmappers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import platform.codingnomads.co.springdata.example.mybatis.usingmappers.mappers.ArtistMapper;
import platform.codingnomads.co.springdata.example.mybatis.usingmappers.mappers.SongMapper;
import platform.codingnomads.co.springdata.example.mybatis.usingmappers.models.Artist;
import platform.codingnomads.co.springdata.example.mybatis.usingmappers.models.Song;

@Service
public class SongService {

    @Autowired
    SongMapper songMapper;

    @Autowired
    ArtistMapper artistMapper;

    @Transactional(rollbackFor = Exception.class)
    public void doSomething() throws Exception {
        Song s = songMapper.getSongById(14L);
        songMapper.insertNewSong(s);
        throw new Exception();
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
