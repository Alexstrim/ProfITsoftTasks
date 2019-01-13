package ua.profitsoft.strymeneshenko.db.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.data.Individual;
import ua.profitsoft.strymeneshenko.data.LegalEntity;

import javax.sql.DataSource;
import java.sql.*;

public class ClientDAO implements IDao<Client>{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private static final Logger LOGGER = Logger.getLogger(ClientDAO.class);

    private static final String SQL_INSERT_INDIVIDUAL = "INSERT INTO client VALUES(DEFAULT,?,?,?,null,?)";
    private static final String SQL_INSERT_LEGAL_ENTITY = "INSERT INTO client VALUES(DEFAULT,?,null,null,?,?)";
    private static final String SQL_SELECT_CLIENT_BY_ID = "SELECT id, adress, firstName, lastName, nameOrganization FROM client WHERE id = ?";
    private static final String SQL_UPDATE_INDIVIDUAL = "UPDATE client SET adress = ?, firstName = ?, lastName = ?, nameOrganization = null WHERE id = ?";
    private static final String SQL_UPDATE_LEGAL_ENTITY = "UPDATE client SET adress = ?, nameOrganization = ?, firstName = null, lastName = null WHERE id = ?";
    private static final String SQL_DELETE_CLIENT = "DELETE FROM client WHERE id = ?";

    private static RowMapper EXTRACT_CLIENT_MAPPER = new RowMapper() {
        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            String nameOrganization = rs.getString("nameOrganization");
            if(!rs.wasNull()){
                LegalEntity legalEntity = new LegalEntity();
                legalEntity.setId(rs.getLong("id"));
                legalEntity.setAdress(rs.getString("adress"));
                legalEntity.setNameOrganization(nameOrganization);
                return legalEntity;
            }else{
                Individual individual = new Individual();
                individual.setId(rs.getLong("id"));
                individual.setAdress(rs.getString("adress"));
                individual.setFirstName(rs.getString("firstName"));
                individual.setLastName(rs.getString("lastName"));
                return individual;
            }
        }
    };

    @Override
    public void create(Client client){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if(client.getClass() == Individual.class){
            Individual individual = (Individual) client;
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INDIVIDUAL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, individual.getAdress());
                ps.setString(2, individual.getFirstName());
                ps.setString(3, individual.getLastName());
                ps.setString(4, "Individual");
                return ps;
            }, keyHolder);
            client.setId(keyHolder.getKey().longValue());
        }
        if(client.getClass() == LegalEntity.class){
            LegalEntity legalEntity = (LegalEntity)client;
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_LEGAL_ENTITY, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, legalEntity.getAdress());
                ps.setString(2, legalEntity.getNameOrganization());
                ps.setString(3, "LegalEntity");
                return ps;
            }, keyHolder);
            client.setId(keyHolder.getKey().longValue());
        }

    }

    @Override
    public Client read(long id){
        try{
            return (Client) jdbcTemplate.queryForObject(SQL_SELECT_CLIENT_BY_ID, new Object[]{id}, EXTRACT_CLIENT_MAPPER);
        }catch (EmptyResultDataAccessException ex){
            LOGGER.info("Can not read Client!!!");
            return null;
        }
    }

    @Override
    public void update(Client client){
        if(client.getClass() == Individual.class){
            Individual individual = (Individual) client;
            jdbcTemplate.update(SQL_UPDATE_INDIVIDUAL, individual.getAdress(),individual.getFirstName(),individual.getLastName(),individual.getId());
        }
        if(client.getClass() == LegalEntity.class){
            LegalEntity legalEntity = (LegalEntity) client;
            jdbcTemplate.update(SQL_UPDATE_LEGAL_ENTITY, legalEntity.getAdress(),legalEntity.getNameOrganization(),legalEntity.getId());
        }
    }

    @Override
    public void delete(long id){
        jdbcTemplate.update(SQL_DELETE_CLIENT, id);
    }
}
