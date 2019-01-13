package ua.profitsoft.strymeneshenko.db.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContractDAO implements IDao<Contract>{

    private JdbcTemplate jdbcTemplate;
    private static ClientDAO clientDAO;
    private static InsuredPersonDAO insuredPersonDAO;

    private static final Logger LOGGER = Logger.getLogger(ContractDAO.class);

    private static final String SQL_INSERT_CONTRACT = "INSERT INTO contract VALUES(DEFAULT, ?, ?, ?, ?)";

    private static final String SQL_INSERT_INSUREDPERSONS_LIST = "INSERT INTO contract_insuredperson VALUES (?, ?)";
    private static final String SQL_SELECT_CONTRACT_BY_ID = "SELECT number, dateConclusion, startDate, endDate, client_id FROM contract WHERE number = ?";
    private static final String SQL_SELECT_LIST_INSUREDPERSONS_BY_CONTRACT_ID = "SELECT InsuredPerson_id FROM contract_insuredperson WHERE Contract_number = ?";
    private static final String SQL_UPDATE_CONTRACT = "UPDATE contract SET dateConclusion = ?, startDate = ?, endDate = ?, Client_id = ? WHERE number = ?";
    private static final String SQL_DELETE_CONTRACT_LIST_INSURED_PERSONS = "DELETE FROM contract_insuredperson WHERE Contract_number = ?";
    private static final String SQL_DELETE_CONTRACT = "DELETE FROM contract WHERE number = ?";
    private static final Long ONE_DAY_MILL_SEC = 86400000L;

    private static RowMapper EXTRACT_CONTRACT_MAPPER= new RowMapper(){

        @Override
        public Contract mapRow(ResultSet rs, int i) throws SQLException {
            Contract contract = new Contract();
            contract.setNumber(rs.getLong("number"));
            contract.setDateConclusion(rs.getDate("dateConclusion"));
            contract.setStartDate(rs.getDate("startDate"));
            contract.setEndDate(rs.getDate("endDate"));
            contract.setClient(clientDAO.read(rs.getLong("Client_id")));
            return contract;
        }
    };

    private static RowMapper EXTRACT_PERSONS = new RowMapper() {
        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            InsuredPerson insuredPerson = insuredPersonDAO.read(rs.getLong("InsuredPerson_id"));
            return insuredPerson;
        }
    };

    //first write the data to the `contract` table
    //then in `contract_insuredperson`(keep a list of insured persons)

    @Override
    public void create(Contract contract) throws Exception {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_CONTRACT, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new Date(contract.getDateConclusion().getTime() + ONE_DAY_MILL_SEC));
            ps.setDate(2, new Date(contract.getStartDate().getTime() + ONE_DAY_MILL_SEC));
            ps.setDate(3, new Date(contract.getEndDate().getTime() + ONE_DAY_MILL_SEC));
            ps.setLong(4, contract.getClient().getId());
            return ps;
        }, keyHolder);

        contract.setNumber(keyHolder.getKey().longValue());
        insertSetPersons(contract);
    }
    //First, we read the data in the `contract` table, then read list insured persons
    //from the`contract_insuredperson`(keep a list of insured persons)

    @Override
    public Contract read(long number){
        try{
            Contract contract;
            contract = (Contract) jdbcTemplate.queryForObject(SQL_SELECT_CONTRACT_BY_ID, new Object[]{number}, EXTRACT_CONTRACT_MAPPER);
            Set<InsuredPerson> personSet = new HashSet<>(jdbcTemplate.query(SQL_SELECT_LIST_INSUREDPERSONS_BY_CONTRACT_ID, new Object[]{number}, EXTRACT_PERSONS));
            contract.setPersons(personSet);
            return contract;
        }catch (EmptyResultDataAccessException ex){
            LOGGER.info("Can not read Contract!!!");
            return null;
        }

    }
    //First, we update the data in the contract table, then delete the old list insured persons
    //from the database and insert the new list
    @Override
    public void update(Contract contract){
        jdbcTemplate.update(SQL_UPDATE_CONTRACT, contract.getDateConclusion(), contract.getStartDate(),
        contract.getEndDate(),contract.getClient().getId(), contract.getNumber());
        jdbcTemplate.update(SQL_DELETE_CONTRACT_LIST_INSURED_PERSONS, contract.getNumber());
        insertSetPersons(contract);
    }
    //First we delete the data from `contract_insuredperson`(keep a list of insured persons),
    //then from `contract` table
    @Override
    public void delete(long id){
        jdbcTemplate.update(SQL_DELETE_CONTRACT_LIST_INSURED_PERSONS, id);
        jdbcTemplate.update(SQL_DELETE_CONTRACT, id);
    }

    private void insertSetPersons(Contract contract){
        List<InsuredPerson> persons = new ArrayList<>(contract.getPersons());
        jdbcTemplate.batchUpdate(SQL_INSERT_INSUREDPERSONS_LIST, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, contract.getNumber());
                ps.setLong(2, persons.get(i).getId());
            }

            @Override
            public int getBatchSize() {
                return persons.size();
            }
        });
    }

    @Autowired
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Autowired
    public void setInsuredPersonDAO(InsuredPersonDAO insuredPersonDAO) {
        this.insuredPersonDAO = insuredPersonDAO;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
