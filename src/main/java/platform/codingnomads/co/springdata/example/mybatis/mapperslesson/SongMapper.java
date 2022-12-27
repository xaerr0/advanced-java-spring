package platform.codingnomads.co.springdata.example.mybatis.mapperslesson;

import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface SongMapper {

    @Insert("INSERT INTO mybatis.songs " +
            "(name, artist_name, album_name, song_length) " +
            "VALUES (#{name}, #{artistName}, #{albumName}, #{songLength});")
    void insertNewSong(Song song);

    @Select("SELECT * " +
            "FROM mybatis.songs " +
            "WHERE id = #{param1};")
    @Results(
            id = "songResultMap",
            value = {
                    @Result(property = "artistName", column = "artist_name"),
                    @Result(property = "albumName", column = "album_name"),
                    @Result(property = "songLength", column = "song_length")
            }
    )
    Song getSongById(Long id);

    @Select("SELECT * " +
            "FROM mybatis.songs " +
            "WHERE name = #{param1};")
    @ResultMap("songResultMap")
    ArrayList<Song> getSongsByName(String name);

    @Select("SELECT * " +
            "FROM mybatis.songs " +
            "WHERE artist_name = #{param1} AND album_name = #{param2};")
    @ResultMap("songResultMap")
    ArrayList<Song> getSongsByAlbumAndArtist(String artistName, String albumName);

    @Select("SELECT *" +
            "FROM mybatis.songs " +
            "WHERE artist_name = #{param1};")
    @ResultMap("songResultMap")
    ArrayList<Song> getSongsByArtist(String artistName);

    @Select("SELECT * " +
            "FROM mybatis.songs " +
            "WHERE name LIKE '%' #{param1} '%';")
    @ResultMap("songResultMap")
    ArrayList<Song> getSongsLike(String name);

    @Select("SELECT * " +
            "FROM mybatis.songs " +
            "WHERE song_length < #{param1} AND artist_name LIKE '%' #{param2} '%';")
    @ResultMap("songResultMap")
    ArrayList<Song> getSongsShorterThanAndLike(int song_length, String artistName);

    @Select("SELECT * " +
            "FROM mybatis.songs " +
            "WHERE song_length > #{param1}")
    @ResultMap("songResultMap")
    ArrayList<Song> getSongsWithLengthGreaterThan(int song_length);


    @Update("UPDATE mybatis.songs " +
            "SET name = #{name}, artist_name = #{artistName}, " +
            "album_name = #{albumName}, song_length = #{songLength} " +
            "WHERE id = #{id};")
    void updateSong(Song song);

    @Delete("DELETE FROM mybatis.songs WHERE id = #{param1};")
    void deleteSongById(Long songId);

    @Delete("DELETE FROM mybatis.songs " +
            "WHERE artist_name = #{artistName} AND album_name = #{albumName};")
    void deleteSongsByAlbumAndArtist(String artistName, String albumName);

}