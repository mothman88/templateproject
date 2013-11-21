package net.mothman.project.service;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * @author mothman88
 *
 */
@Service
public class SQLiteDatabaseService {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier(value="sqliteDS")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public List<Map<String, Object>> query(String query, Object ... args) {
		return jdbcTemplate.queryForList(query, args);
	}
	
	public Map<String, Object> querySingle(String query, Object ... args) {
		return jdbcTemplate.queryForMap(query, args);
	}
	
	public long count(String query, Object ... args) {
		return jdbcTemplate.queryForObject(query, args, Long.class);
	}
	
}
