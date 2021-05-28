package platform.codingnomads.co.springdata.example.mybatis.extraexample.mappers;

import org.apache.ibatis.annotations.*;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.models.Lesson;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface LessonMapper {

    @Insert("INSERT INTO mybatis.lessons (name, text, chapter_id) VALUES (#{param1}, #{param2}, #{param3});")
    void insertNewLesson(String lessonName, String text, Long chapterId);

    @Insert("INSERT INTO mybatis.lesson_image (lesson_id, image_name) VALUES (#{param1}, #{param2});")
    void addImageToLesson(Long lessonId, String imageName);

    @Select("SELECT id, name, text FROM mybatis.lessons WHERE id = #{param1};")
    @Results(id = "lessonResultMap",
                value = @Result(
                    column = "id",
                    javaType = List.class,
                    property = "imageArrayList",
                    many = @Many(
                            select = "com.bensiegler.mappers.ImageMapper.getImagesByLessonId"
                    )
            )
    )
    Lesson getLessonById(Long lessonId);

    @Select("SELECT id, name, text FROM mybatis.lessons WHERE chapter_id = #{param1};")
    @ResultMap("lessonResultMap")
    ArrayList<Lesson> getLessonByChapterId(Long chapterId);

    @Select("SELECT id, name, text FROM mybatis.lessons WHERE name = #{param1}")
    @ResultMap("lessonResultMap")
    Lesson getLessonByName(String name);

    @Delete("DELETE FROM mybatis.lesson_image WHERE lesson_id = #{param1} AND image_name = #{param2};")
    void deleteImageFromLesson(Long lessonId, String lessonName);

    @Delete("DELETE FROM mybatis.lessons WHERE id = #{param1};")
    void deleteLessonById(Long id);

}
