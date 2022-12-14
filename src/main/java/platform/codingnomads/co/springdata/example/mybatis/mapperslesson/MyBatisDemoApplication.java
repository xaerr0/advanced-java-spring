package platform.codingnomads.co.springdata.example.mybatis.mapperslesson;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class MyBatisDemoApplication {

    /* Before running this app, be sure to:

        * create a new empty schema in the mysql database named "mybatis"

        * execute the SQL found "songs_table.sql" on the mybatis schema

        * update the "spring.datasource.url" property in your application.properties file to
          jdbc:mysql://localhost:3306/mybatis?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC

     */

    public static void main(String[] args) {
        SpringApplication.run(MyBatisDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(SongMapper songMapper) {
        return (args) -> {
            Song song1 = new Song();
            song1.setName("Minnesota, WI");
            song1.setAlbumName("Bon Iver");
            song1.setArtistName("Bon Iver");
            song1.setSongLength(232);

            Song song2 = new Song();
            song2.setName("Post Humorous");
            song2.setAlbumName("Orca");
            song2.setArtistName("Gus Dapperton");
            song2.setSongLength(279);

            Song song3 = new Song();
            song3.setName("Miracles");
            song3.setAlbumName("Bang! Pow! Boom!");
            song3.setArtistName("Insane Clown Posse");
            song3.setSongLength(307);

            Song song4 = new Song();
            song4.setName("Oblivion");
            song4.setAlbumName("Oblivion");
            song4.setArtistName("Astor Piazzolla");
            song4.setSongLength(201);

            Song song5 = new Song();
            song5.setName("Don't Let No One Get You Down");
            song5.setAlbumName("Why Can't We Be Friends");
            song5.setArtistName("War");
            song5.setSongLength(242);


            songMapper.insertNewSong(song1);
            songMapper.insertNewSong(song2);
            songMapper.insertNewSong(song3);
            songMapper.insertNewSong(song4);
            songMapper.insertNewSong(song5);




            ArrayList<Song> longSongs = songMapper.getSongsWithLengthGreaterThan(250);
            longSongs.forEach(System.out::println);


            System.out.println("test");


            // get song by id
            Song songById = songMapper.getSongById(3L);
            System.out.println(songById.toString());

            // get songs by name
            System.out.println("get songs by name");
            ArrayList<Song> songByName = songMapper.getSongsByName("Miracles");
            songByName.forEach(System.out::println);


            // get songs by album and artist
            System.out.println("get songs by album and artist");
            ArrayList<Song> songByAlbumAndArtist = songMapper.getSongsByAlbumAndArtist("war", "why can't");
            songByAlbumAndArtist.forEach(System.out::println);

            // get songs by artist
            System.out.println("get songs by artist");
            ArrayList<Song> songsByArtist = songMapper.getSongsByArtist("insane");
            songsByArtist.forEach(System.out::println);

            // get song like
            System.out.println("get song like");
            ArrayList<Song> songsLike = songMapper.getSongsLike("M");
            songsLike.forEach(System.out::println);


            // get song length less than and artist name
            System.out.println("get song length less than and artist name");
            ArrayList<Song> shortSongLike = songMapper.getSongsShorterThanAndLike(250, "As");
            shortSongLike.forEach(System.out::println);

            // update song

            // TODO Not sure how?
            songMapper.updateSong(song5);


            // delete song by ID
            songMapper.deleteSongById(11L);

            songMapper.deleteSongsByAlbumAndArtist("Astor Piazzolla", "Oblivion");

        };
    }
}