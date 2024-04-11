package ru.bogdan.homework4.repositories;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.bogdan.homework4.domain.Cat;
import ru.bogdan.homework4.property.SQLCustomProperty;

import java.util.List;
@Repository
@AllArgsConstructor
public class CatRepository {


    private final JdbcTemplate jdbc;
    //use custom property for SQL queries
    //property base on SQLCustomProperty
    private final SQLCustomProperty sQLCustomProperty;

    public List<Cat> findAll() {
        String sql = sQLCustomProperty.getFindAll();

        RowMapper<Cat> catsRowMapper = (r, i) -> {
            Cat rowObject = new Cat();
            rowObject.setId(r.getLong("id"));
            rowObject.setName(r.getString("name"));
            rowObject.setAge(r.getInt("age"));
            return rowObject;
        };

        return jdbc.query(sql, catsRowMapper);
    }

    public Cat saveCat(Cat cat) {
        String sql = sQLCustomProperty.getSaveCat();
        jdbc.update(sql, cat.getName(), cat.getAge());
        return cat;
    }

    public void deleteCatById(Long id) {
        String sql = sQLCustomProperty.getDeleteCatById();
        jdbc.update(sql, id);
    }


    public Cat findCatById(Long id) {
        String sql = sQLCustomProperty.getFindCatByName();
        RowMapper<Cat> catsRowMapper = (r, i) -> {
            Cat rowObject = new Cat();
            rowObject.setId(r.getLong("id"));
            rowObject.setName(r.getString("name"));
            rowObject.setAge(r.getInt("age"));
            return rowObject;
        };

        // if query couldn't find anything, it would return null
        return jdbc.query(sql, new Object[]{id}, catsRowMapper)
                .stream()
                .findAny()
                .orElse(null);
    }

    public Cat updateCat(Cat cat) {
        String sql = sQLCustomProperty.getUpdateCat();
        jdbc.update(sql, cat.getName(), cat.getAge(), cat.getId());
        return cat;
    }
}
