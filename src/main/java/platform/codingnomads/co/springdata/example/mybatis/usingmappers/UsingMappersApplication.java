package platform.codingnomads.co.springdata.example.mybatis.usingmappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.codingnomads.co.springdata.example.mybatis.usingmappers.services.SongService;

@SpringBootApplication
public class UsingMappersApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(UsingMappersApplication.class);
    }

    @Autowired
    SongService songService;

    @Override
    public void run(String... args) throws Exception {
        songService.insertData();
        songService.queryData();
        songService.deleteSomeData();
//        service.doSomething();
    }
}
