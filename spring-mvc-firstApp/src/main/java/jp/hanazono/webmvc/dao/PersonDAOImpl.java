package jp.hanazono.webmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import jp.hanazono.webmvc.domain.Person;

@Repository
public class PersonDAOImpl implements PersonDAO{
	
	@Autowired
	@Qualifier("datasource")
	private DriverManagerDataSource dataSource;
	private JdbcTemplate template;
	
	@PostConstruct
	public void init()
	{
		this.template = new JdbcTemplate(this.dataSource);
	}

	public void addPerson(Person person) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO PERSON VALUES(");
		sql.append(person.getId());
		sql.append(", '");
		sql.append(person.getFirstname());
		sql.append("', '");
		sql.append(person.getLastname());
		sql.append("', '");
		sql.append(person.getAge());
		sql.append("')");
		template.execute(sql.toString());
		
	}

	public void updatePerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	public void deletePerson(Person person) {
		template.execute("DELETE FROM PERSON WHERE ID = " + person.getId());
		
	}
	
	public Person findByID(Integer id) {

		return template.query("SELECT * FROM PERSON WHERE ID = " + id, rowMapper).get(0);
	}

	public List<Person> getAllPersons() {
		
		return template.query("SELECT * FROM PERSON", rowMapper);
	}
	
	RowMapper<Person> rowMapper = new RowMapper<Person>() {

		public Person mapRow(ResultSet rs, int numRow) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("ID"));
			person.setFirstname(rs.getString("Firstname"));
			person.setLastname(rs.getString("LASTNAME"));
			person.setAge(rs.getInt("AGE"));
			return person;
		}
	};

}
