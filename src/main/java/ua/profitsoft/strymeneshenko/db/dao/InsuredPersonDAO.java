package ua.profitsoft.strymeneshenko.db.dao;

import org.apache.log4j.Logger;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.db.ConnectionFactory;

import java.sql.*;

public class InsuredPersonDAO implements IDao<InsuredPerson>{

    private static final Logger LOGGER = Logger.getLogger(InsuredPersonDAO.class);

    ConnectionFactory cf = ConnectionFactory.getInstance();

    private static final String SQL_INSERT_INSURED_PERSON = "INSERT INTO insuredperson VALUES(DEFAULT, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_INSURED_PERSON = "SELECT id, firstName, lastName, dateOfBirth, cost, identificationNumber FROM insuredperson WHERE id = ?";
    private static final String SQL_UPDATE_INSURED_PERSON = "UPDATE insuredperson SET firstName = ?, lastName = ?, dateOfBirth = ?, cost = ?, identificationNumber = ? WHERE id = ?";
    private static final String SQL_DELETE_INSURED_PERSON = "DELETE FROM insuredperson WHERE id = ?";

    private static final Long ONE_DAY_MILL_SEC = 86400000L;


    @Override
    public void create(InsuredPerson insuredPerson){
        ResultSet rs = null;
        try(Connection con = cf.getMySQLConnection();
            PreparedStatement ps = createPrepareStatement(con, insuredPerson)){
            if(ps.executeUpdate() > 0){
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    insuredPerson.setId(rs.getLong(1));
                }
                con.commit();
            }
        }catch (SQLException e){
            LOGGER.error("Can't insert insured person into DB!!!", e);
        }finally {
            close(rs);
        }
    }

    @Override
    public InsuredPerson read(long id){
        InsuredPerson insuredPerson = null;
        try(Connection con = cf.getMySQLConnection();
            PreparedStatement ps = readPrepareStatement(con, id);
            ResultSet rs = ps.executeQuery()){
            if(rs.next()){
                insuredPerson = extractPerson(rs);
            }
            con.commit();
            return insuredPerson;
        }catch (SQLException e){
            LOGGER.error("Can't read insured person from DB!!!", e);
        }
        return null;
    }

    @Override
    public void update(InsuredPerson insuredPerson){
        try(Connection con = cf.getMySQLConnection();
            PreparedStatement ps = updatePrepareStatement(con, insuredPerson)){
            if (ps.executeUpdate() > 0){
                con.commit();
            }
        }catch (SQLException e){
            LOGGER.error("Can't update insured person into DB!!!", e);
        }
    }

    @Override
    public void delete(long id){
        try(Connection con = cf.getMySQLConnection();
            PreparedStatement ps = deletePrepareStatement(con, id)){
            if (ps.executeUpdate() > 0){
                con.commit();
            }
        }catch (SQLException e){
            LOGGER.error("Can't delete insured person from DB!!!", e);
        }
    }

    private PreparedStatement createPrepareStatement(Connection con, InsuredPerson insuredPerson) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_INSERT_INSURED_PERSON, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, insuredPerson.getFirstName());
        ps.setString(2, insuredPerson.getLastName());
        ps.setDate(3, new Date(insuredPerson.getDateOfBirth().getTime() + ONE_DAY_MILL_SEC));
        ps.setInt(4, insuredPerson.getCost());
        ps.setLong(5, insuredPerson.getIdentificationNumber());
        return ps;
    }

    private PreparedStatement readPrepareStatement(Connection con, long id) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_SELECT_INSURED_PERSON);
        ps.setLong(1, id);
        return ps;
    }

    private InsuredPerson extractPerson(ResultSet rs) throws SQLException {
        InsuredPerson ip = new InsuredPerson();
        ip.setId(rs.getLong("id"));
        ip.setFirstName(rs.getString("firstName"));
        ip.setLastName(rs.getString("lastName"));
        ip.setDateOfBirth(rs.getDate("dateOfBirth"));
        ip.setCost(rs.getInt("cost"));
        ip.setIdentificationNumber(rs.getLong("identificationNumber"));
        return ip;
    }

    private PreparedStatement updatePrepareStatement(Connection con, InsuredPerson insuredPerson) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_UPDATE_INSURED_PERSON);
        ps.setString(1, insuredPerson.getFirstName());
        ps.setString(2, insuredPerson.getLastName());
        ps.setDate(3, new Date(insuredPerson.getDateOfBirth().getTime() + ONE_DAY_MILL_SEC));
        ps.setInt(4, insuredPerson.getCost());
        ps.setLong(5, insuredPerson.getIdentificationNumber());
        ps.setLong(6, insuredPerson.getId());
        return ps;
    }

    private PreparedStatement deletePrepareStatement(Connection con, long id) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_DELETE_INSURED_PERSON);
        ps.setLong(1, id);
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
