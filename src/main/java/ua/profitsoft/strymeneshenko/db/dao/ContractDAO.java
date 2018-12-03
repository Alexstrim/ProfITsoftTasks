package ua.profitsoft.strymeneshenko.db.dao;

import org.apache.log4j.Logger;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.db.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ContractDAO implements IDao<Contract>{

    private static final Logger LOGGER = Logger.getLogger(ContractDAO.class);

    ConnectionFactory cf = ConnectionFactory.getInstance();

    private static final String SQL_INSERT_CONTRACT = "INSERT INTO contract VALUES(DEFAULT, ?, ?, ?, ?)";
    private static final String SQL_INSERT_INSUREDPERSONS_LIST = "INSERT INTO contract_insuredperson VALUES (?, ?)";
    private static final String SQL_SELECT_CONTRACT_BY_ID = "SELECT number, dateConclusion, startDate, endDate, client_id FROM contract WHERE number = ?";
    private static final String SQL_SELECT_LIST_INSUREDPERSONS_BY_CONTRACT_ID = "SELECT InsuredPerson_id FROM contract_insuredperson WHERE Contract_number = ?";
    private static final String SQL_UPDATE_CONTRACT = "UPDATE contract SET dateConclusion = ?, startDate = ?, endDate = ?, Client_id = ? WHERE number = ?";
    private static final String SQL_DELETE_CONTRACT_LIST_INSURED_PERSONS = "DELETE FROM contract_insuredperson WHERE Contract_number = ?";
    private static final String SQL_DELETE_CONTRACT = "DELETE FROM contract WHERE number = ?";

    //first write the data to the `contract` table
    //then in `contract_insuredperson`(keep a list of insured persons)
    @Override
    public void create(Contract contract) throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps1 = null;
        try{
            con = cf.getMySQLConnection();
            ps1 = createPrepareStatement(con, contract);
            if(ps1.executeUpdate() > 0){
                rs = ps1.getGeneratedKeys();
                if(rs.next()){
                    contract.setNumber(rs.getLong(1));
                }
            }
            ps2 = createPrepareStatementList(con, contract);
            ps2.executeBatch();
            con.commit();
        }catch (SQLException e){
            con.rollback();
            LOGGER.error("Can't insert Contract into DB!!!", e);
        }finally {
            close(con);
            close(rs);
            close(ps1);
            close(ps2);
        }
    }
    //First, we read the data in the `contract` table, then read list insured persons
    //from the`contract_insuredperson`(keep a list of insured persons)
    @Override
    public Contract read(long number){
        Contract contract = null;
        try(Connection con = cf.getMySQLConnection();
            PreparedStatement ps = readPrepareStatement(con, number);
            PreparedStatement ps2 = readPrepareStatementList(con, number);
            ResultSet rs = ps.executeQuery();
            ResultSet rsForList = ps2.executeQuery()){
            if(rs.next()){
                contract = extractContract(rs, rsForList);
            }
            con.commit();
            return contract;
        }catch (SQLException e){
            LOGGER.error("Can't read Contract from DB!!!", e);
        }
        return null;
    }
    //First, we update the data in the contract table, then delete the old list insured persons
    //from the database and insert the new list
    @Override
    public void update(Contract contract){
        try(Connection con = cf.getMySQLConnection();
            PreparedStatement ps = updatePrepareStatement(con, contract);
            PreparedStatement ps2 = deletePrepareStatementList(con, contract.getNumber());
            PreparedStatement ps3 = createPrepareStatementList(con, contract)){
            if (ps.executeUpdate() > 0 && ps2.executeUpdate() > 0 && ps3.executeUpdate() > 0){
                con.commit();
            }
        }catch (SQLException e){
            LOGGER.error("Can't update Contract into DB!!!", e);
        }
    }
    //First we delete the data from `contract_insuredperson`(keep a list of insured persons),
    //then from `contract` table
    @Override
    public void delete(long id){
        try(Connection con = cf.getMySQLConnection();
            PreparedStatement ps1 = deletePrepareStatement(con, id);
            PreparedStatement ps2 = deletePrepareStatementList(con, id)){
            if (ps2.executeUpdate() > 0 && ps1.executeUpdate() > 0){
                con.commit();
            }
        }catch (SQLException e){
            LOGGER.error("Can't delete Contract from DB!!!", e);
        }
    }

    private PreparedStatement deletePrepareStatement(Connection con, long id) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_DELETE_CONTRACT);
        ps.setLong(1, id);
        return ps;
    }

    private PreparedStatement createPrepareStatementList(Connection con, Contract contract) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_INSERT_INSUREDPERSONS_LIST);
        Set<InsuredPerson> persons = contract.getPersons();
        for (InsuredPerson ip: persons) {
            ps.setLong(1, contract.getNumber());
            ps.setLong(2, ip.getId());
            ps.addBatch();
        }
        return ps;
    }

    private PreparedStatement createPrepareStatement(Connection con, Contract contract) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_INSERT_CONTRACT, Statement.RETURN_GENERATED_KEYS);
        ps.setDate(1, new Date(contract.getDateConclusion().getTime()));
        ps.setDate(2, new Date(contract.getStartDate().getTime()));
        ps.setDate(3, new Date(contract.getEndDate().getTime()));
        ps.setLong(4, contract.getClient().getId());
        return ps;
    }

    private PreparedStatement readPrepareStatement(Connection con, long number) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_SELECT_CONTRACT_BY_ID);
        ps.setLong(1,number);
        return ps;
    }

    private PreparedStatement readPrepareStatementList(Connection con, long number) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_SELECT_LIST_INSUREDPERSONS_BY_CONTRACT_ID);
        ps.setLong(1, number);
        return ps;
    }

    private Contract extractContract(ResultSet rs, ResultSet rsForList) throws SQLException {
        Contract contract = new Contract();
        ClientDAO clientDAO = new ClientDAO();
        InsuredPersonDAO insuredPersonDAO = new InsuredPersonDAO();

        Set<InsuredPerson> personSet = new HashSet<>();
        while(rsForList.next()){
            personSet.add(insuredPersonDAO.read(rsForList.getLong("InsuredPerson_id")));
        }
        contract.setNumber(rs.getLong("number"));
        contract.setDateConclusion(rs.getDate("dateConclusion"));
        contract.setStartDate(rs.getDate("startDate"));
        contract.setEndDate(rs.getDate("endDate"));
        contract.setClient(clientDAO.read(rs.getLong("Client_id")));
        contract.setPersons(personSet);
        return contract;
    }

    private PreparedStatement updatePrepareStatement(Connection con, Contract contract) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_UPDATE_CONTRACT);
        ps.setDate(1, new Date(contract.getDateConclusion().getTime()));
        ps.setDate(2, new Date(contract.getStartDate().getTime()));
        ps.setDate(3, new Date(contract.getEndDate().getTime()));
        ps.setLong(4, contract.getClient().getId());
        ps.setLong(5, contract.getNumber());
        return ps;
    }

    private PreparedStatement deletePrepareStatementList(Connection con, long number) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_DELETE_CONTRACT_LIST_INSURED_PERSONS);
        ps.setLong(1, number);
        return ps;
    }

    private void close(AutoCloseable closeables){
        if (closeables != null) {
            try {
                closeables.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
