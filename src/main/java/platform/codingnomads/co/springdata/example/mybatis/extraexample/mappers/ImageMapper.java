package platform.codingnomads.co.springdata.example.mybatis.extraexample.mappers;

import org.apache.ibatis.annotations.*;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.models.Image;

import java.util.ArrayList;

@Mapper
public interface ImageMapper {

    @Insert("INSERT INTO mybatis.images (name, image_data) VALUES (#{param1}, #{param2});")
    int insertNewImage(String name, byte[] data);

    @Select("SELECT * FROM mybatis.images WHERE name = #{param1};")
    Image getImageByName(String name);

    @Select("SELECT i.name, i.image_data " +
            "FROM mybatis.images i " +
            "JOIN mybatis.lesson_image li " +
            "ON i.name = li.image_name " +
            "WHERE li.lesson_id = #{param1}")
    ArrayList<Image> getImagesByLessonId(Long lessonId);

    @Update("UPDATE mybatis.images SET image_data = #{param2} WHERE name = #{param1};")
    int updateImageByName(String name, String newData);

    @Update("UPDATE mybatis.images SET name = #{param2} WHERE name = #{param1};")
    void renameImage(String oldName, String newName);

    @Delete("DELETE FROM mybatis.images WHERE name = #{param1};")
    void deleteImageByName(String name);
}
