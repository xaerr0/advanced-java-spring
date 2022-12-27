package platform.codingnomads.co.springdata.example.mybatis.resultsandresult;

import org.apache.ibatis.annotations.*;

@Mapper
public interface LabelMapper {

    @Insert("INSERT INTO mybatis.labels " +
            "(label_name, label_genre) " +
            "VALUES (#{name}, #{genre});")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertNewLabel(Label label);

    @Select("SELECT * " +
            "FROM mybatis.labels " +
            "WHERE id = #{param1};")
    @Results(
            id = "labelResultMap",
            value = {
                    @Result(property = "name", column = "label_name"),
                    @Result(property = "genre", column = "label_genre")

            }
    )
    Label getLabelById(Long id);

}