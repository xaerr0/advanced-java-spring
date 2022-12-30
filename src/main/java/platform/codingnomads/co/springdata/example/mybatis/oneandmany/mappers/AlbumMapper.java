package platform.codingnomads.co.springdata.example.mybatis.oneandmany.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import platform.codingnomads.co.springdata.example.mybatis.oneandmany.models.Album;
import platform.codingnomads.co.springdata.example.mybatis.oneandmany.models.Artist;
//TODO HALP! https://platform.codingnomads.co/learn/mod/page/view.php?id=6543

@Mapper
public interface AlbumMapper {

    @Insert("INSERT INTO mybatis.albums " +
            "(name, year " +
            "VALUES (#{name}, #{year})")
    void insertNewAlbum(Album album);

    @Select("SELECT * " +
            "FROM mybatis.albums " +
            "WHERE id = #{param1};")
    @Results(
            id = "albumResultMap",
            value = {
                    @Result(property = "name", column = "album_name"),
                    @Result(property = "year", column = "album_year"),
                    @Result(property = "artist", column = "artist_id", javaType = Artist.class,
                            one = @One(
                                    select = "platform.codingnomads.co.springdata.example.mybatis.oneandmany.mappers.ArtistMapper.getArtistByIdWithoutSongs",
                                    fetchType = FetchType.LAZY
                            )
                    )
            }
    )
    Album getAlbumById(Long id);



}