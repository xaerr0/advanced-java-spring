package platform.codingnomads.co.springdata.example.mybatis.mapperslesson;

import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface SongMapper {

    @Insert("INSERT INTO mybatis.songs " +
            "(name, artist_name, album_name, song_length) " +
            "VALUES (#{name}, #{artist_name}, #{album_name}, #{song_length});")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertNewSong(Song song);

    @Select("SELECT * " +
            "FROM mybatis.songs " +
            "WHERE id = #{param1};")
    Song getSongById(Long id);

    @Select("SELECT * " +
            "FROM mybatis.songs " +
            "WHERE name = #{param1};")
    ArrayList<Song> getSongsByName(String name);

    @Select("SELECT * " +
            "FROM mybatis.songs " +
            "WHERE song_length > #{param1}")
    ArrayList<Song> getSongsWithLengthGreaterThan(int song_length);

    @Select("SELECT * " +
            "FROM mybatis.songs " +
            "WHERE artist_name = #{param1} AND album_name = #{param2};")
    ArrayList<Song> getSongsByAlbumAndArtist(String artistName, String albumName);

    @Select("SELECT *" +
            "FROM mybatis.songs " +
            "WHERE artist_name = #{param1};")
    ArrayList<Song> getSongsByArtist(String artistName);

    @Update("UPDATE mybatis.songs " +
            "SET name = #{name}, artist_name = #{artist_name}, album_name = #{album_name}, song_length = #{song_length} " +
            "WHERE id = #{id};")
    void updateSong(Song song);

    @Delete("DELETE FROM mybatis.songs WHERE id = #{param1};")
    void deleteSongById(Long songId);

    @Delete("DELETE FROM mybatis.songs " +
            "WHERE artist_name = #{artistName} AND album_name = #{albumName};")
    void deleteSongsByAlbumAndArtist(String artistName, String albumName);

}
