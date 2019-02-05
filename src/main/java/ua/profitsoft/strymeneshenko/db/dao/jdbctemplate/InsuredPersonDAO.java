package ua.profitsoft.strymeneshenko.db.dao.jdbctemplate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.db.dao.IDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class InsuredPersonDAO implements IDao<InsuredPerson> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final Logger LOGGER = Logger.getLogger(InsuredPersonDAO.class);

    private static final String SQL_INSERT_INSURED_PERSON = "INSERT INTO insuredperson VALUES(DEFAULT, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_INSURED_PERSON = "SELECT id, firstName, lastName, dateOfBirth, cost, identificationNumber FROM insuredperson WHERE id = ?";
    private static final String SQL_UPDATE_INSURED_PERSON = "UPDATE insuredperson SET firstName = ?, lastName = ?, dateOfBirth = ?, cost = ?, identificationNumber = ? WHERE id = ?";
    private static final String SQL_DELETE_INSURED_PERSON = "DELETE FROM insuredperson WHERE id = ?";

    private static final Long ONE_DAY_MILL_SEC = 86400000L;

    private static RowMapper EXTRACT_INSURED_PERSON_MAPPER = new RowMapper() {
        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            InsuredPerson insuredPerson = new InsuredPerson();
            insuredPerson.setId(rs.getLong("id"));
            insuredPerson.setFirstName(rs.getString("firstName"));
            insuredPerson.setLastName(rs.getString("lastName"));
            insuredPerson.setDateOfBirth(rs.getDate("dateOfBirth"));
            insuredPerson.setCost(rs.getInt("cost"));
            insuredPerson.setIdentificationNumber(rs.getLong("identificationNumber"));
            return insuredPerson;
        }
    };

    @Override
    public void create(InsuredPerson insuredPerson){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INSURED_PERSON, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, insuredPerson.getFirstName());
            ps.setString(2, insuredPerson.getLastName());
            ps.setDate(3, new Date(insuredPerson.getDateOfBirth().getTime() + ONE_DAY_MILL_SEC));
            ps.setInt(4, insuredPerson.getCost());
            ps.setLong(5, insuredPerson.getIdentificationNumber());
            return ps;
        }, keyHolder);
        insuredPerson.setId(keyHolder.getKey().longValue());
    }

    @Override
    public InsuredPerson read(long id){
        try{
            return (InsuredPerson) jdbcTemplate.queryForObject(SQL_SELECT_INSURED_PERSON, new Object[]{id}, EXTRACT_INSURED_PERSON_MAPPER);
        }catch (EmptyResultDataAccessException ex){
            LOGGER.info("Can not read InsuredPerson!!!");
            return null;
        }
    }

    @Override
    public void update(InsuredPerson insuredPerson){
        jdbcTemplate.update(SQL_UPDATE_INSURED_PERSON, insuredPerson.getFirstName(), insuredPerson.getLastName(),
                insuredPerson.getDateOfBirth(), insuredPerson.getCost(), insuredPerson.getIdentificationNumber(), insuredPerson.getId());
    }

    @Override
    public void delete(long id){
        jdbcTemplate.update(SQL_DELETE_INSURED_PERSON, id);
    }

    @Override
    public List<InsuredPerson> getAllList() throws Exception {
        LOGGER.info("Not implemented method getAllList");
        throw new NoSuchMethodException("Not implemented method getAllList");
    }
}
