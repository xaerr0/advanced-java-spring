package platform.codingnomads.co.springdata.example.mybatis.extraexample.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.models.Chapter;

import java.util.LinkedList;
import java.util.List;

@Mapper
public interface ChapterMapper {

    @Insert("INSERT INTO mybatis.chapters (name, section_id) VALUES (#{param1}, #{param2});")
    void insertNewChapter(String name, Long sectionId);

    @Select("SELECT id, name FROM mybatis.chapters WHERE id = #{param1}")
    @Results(
            @Result(
                    column = "id",
                    property = "lessons",
                    javaType = List.class,
                    many = @Many(
                            select = "com.bensiegler.mappers.LessonMapper.getLessonByChapterId",
                            fetchType = FetchType.LAZY
                    )
            )
    )
    Chapter getByChapterId(Long id);

    @Select("SELECT id, name FROM mybatis.chapters WHERE section_id = #{param1};")
    @Results(
            @Result(
                    column = "id",
                    property = "lessons",
                    javaType = List.class,
                    many = @Many(
                            select = "com.bensiegler.mappers.LessonMapper.getLessonByChapterId",
                            fetchType = FetchType.LAZY
                    )
            )
    )
    LinkedList<Chapter> getChaptersBySectionId(Long sectionId);

    @Delete("DELETE FROM mybatis.chapters WHERE id = #{param1};")
    void deleteChapterById(Long chapterId);
}
