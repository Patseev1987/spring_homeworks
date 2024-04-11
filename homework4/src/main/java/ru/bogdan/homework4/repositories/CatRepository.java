package ru.bogdan.homework4.repositories;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.bogdan.homework4.domain.Cat;

import java.util.List;
@Repository
@AllArgsConstructor
public class CatRepository {


    private final JdbcTemplate jdbc;



    public List<Cat> findAll() {
        String sql = "SELECT * FROM catsTable";

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
        String sql = "INSERT INTO catsTable (name,age) VALUES ( ?, ?)";
        jdbc.update(sql, cat.getName(), cat.getAge());
        return cat;
    }

    public void deleteCatById(Long id) {
        String sql = "DELETE FROM  catsTable WHERE id=?";
        jdbc.update(sql, id);
    }


    public Cat findCatById(Long id) {
        String sql = "SELECT * FROM catsTable WHERE id=?";
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
        String sql = "UPDATE catsTable SET name = ?, age = ? WHERE id=?";
        jdbc.update(sql, cat.getName(), cat.getAge(), cat.getId());
        return cat;
    }
}
