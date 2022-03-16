package platform.codingnomads.co.springdata.example.mybatis.oneandmany.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import platform.codingnomads.co.springdata.example.mybatis.oneandmany.models.Artist;
import platform.codingnomads.co.springdata.example.mybatis.oneandmany.models.Song;

import java.util.ArrayList;

@Mapper
public interface SongMapper {

    @Insert("INSERT INTO mybatis.songs " +
            "(name, artist_id, album_name, song_length) " +
            "VALUES (#{name}, #{artist.id}, #{albumName}, #{songLength});")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertNewSong(Song song);

    @Select("SELECT * " +
            "FROM mybatis.songs " +
            "WHERE id = #{param1};")
    @Results(
            id = "songResultMap",
            value = {
                    @Result(property = "albumName", column = "album_name"),
                    @Result(property = "songLength", column = "song_length"),
                    @Result(
                            //property to map to
                            property = "artist",
                            column = "artist_id",
                            javaType = Artist.class,
                            one = @One(
                                    select = "platform.codingnomads.co.springdata.example.mybatis.oneandmany.mappers.ArtistMapper.getArtistByIdWithoutSongs",
                                    fetchType = FetchType.LAZY
                            )
                    )
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
            "WHERE artist_id = #{param1} AND album_name = #{param2};")
    @ResultMap("songResultMap")
    ArrayList<Song> getSongsByAlbumAndArtist(Long artistId, String albumName);

    @Select("SELECT *" +
            "FROM mybatis.songs " +
            "WHERE artist_id = #{param1};")
    @ResultMap("songResultMap")
    ArrayList<Song> getSongsByArtistId(Long artistId);

    @Update("UPDATE mybatis.songs " +
            "SET name = #{name}, artist_id = #{artist.id}, album_name = #{albumName}, song_length = #{songLength} " +
            "WHERE id = #{id};")
    void updateSong(Song song);

    @Delete("DELETE FROM mybatis.songs WHERE id = #{param1};")
    void deleteSongById(Long songId);

    @Delete("DELETE FROM mybatis.songs " +
            "WHERE artist_id = #{artistId} AND album_name = #{albumName};")
    void deleteSongsByAlbumAndArtist(Long artistId, String albumName);

}
