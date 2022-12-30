package platform.codingnomads.co.springdata.example.mybatis.extraexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.mappers.ChapterMapper;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.mappers.ImageMapper;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.mappers.LessonMapper;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.mappers.SectionMapper;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.models.Lesson;

import java.util.ArrayList;

@SpringBootApplication
public class MyBatisExampleApplication implements CommandLineRunner {

    /* Before running this app, be sure to:

        * create a new empty schema in the mysql database named "mybatis"

        * execute the SQL found "database_structure.sql" on the mybatis schema

        * update the "spring.datasource.url" property in your application.properties file to
          jdbc:mysql://localhost:3306/mybatis?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC

     */

    @Autowired
    ImageMapper imageMapper;

    @Autowired
    LessonMapper lessonMapper;

    @Autowired
    ChapterMapper chapterMapper;

    @Autowired
    SectionMapper sectionMapper;

    public static void main(String[] args) {
        SpringApplication.run(MyBatisExampleApplication.class);
    }

    //TODO Why no work? https://platform.codingnomads.co/learn/mod/page/view.php?id=6544
    @Override
    public void run(String... args) throws Exception {

        // Chapter Mapper

        chapterMapper.insertNewChapter("Chapter 1", 1L);
        chapterMapper.insertNewChapter("Chapter 2", 2L);
        chapterMapper.insertNewChapter("Chapter 3", 3L);
        chapterMapper.insertNewChapter("Chapter 4", 4L);
        chapterMapper.insertNewChapter("Chapter 5", 5L);

        // image mapper
//        imageMapper.insertNewImage("Image 1", 4, 3);


        // Lesson Mapper

        // insert lessons
//        lessonMapper.insertNewLesson("Lesson 1", "Text 1", 1L);
//        lessonMapper.insertNewLesson("Lesson 2", "Text 2", 2L);
//        lessonMapper.insertNewLesson("Lesson 3", "Text 3", 3L);
//        lessonMapper.insertNewLesson("Lesson 3.a", "Text 3.a", 3L);
//        lessonMapper.insertNewLesson("Lesson 3.b", "Text 3.b", 3L);

//        // get lesson by id
//        lessonMapper.getLessonById(2L);
//
//        // get lesson by chapter id
//        ArrayList<Lesson> lessonById = lessonMapper.getLessonByChapterId(3L);
//        lessonById.forEach(System.out::println);
//
//        // get lesson by name
//        Lesson lessonByName = lessonMapper.getLessonByName("Lesson 2");
//        System.out.println(lessonByName);
//
//        // delete lesson
//        lessonMapper.deleteLessonById(2L);
//
//



    };
}