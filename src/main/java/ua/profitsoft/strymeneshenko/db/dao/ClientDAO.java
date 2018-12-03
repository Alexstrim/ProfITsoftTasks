package ua.profitsoft.strymeneshenko.db.dao;

import org.apache.log4j.Logger;
import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.data.Individual;
import ua.profitsoft.strymeneshenko.data.LegalEntity;
import ua.profitsoft.strymeneshenko.db.ConnectionFactory;

import java.sql.*;

public class ClientDAO implements IDao<Client>{

    private static final Logger LOGGER = Logger.getLogger(ClientDAO.class);

    ConnectionFactory cf = ConnectionFactory.getInstance();

    private static final String SQL_INSERT_INDIVIDUAL = "INSERT INTO client VALUES(DEFAULT,?,?,?,null)";
    private static final String SQL_INSERT_LEGAL_ENTITY = "INSERT INTO client VALUES(DEFAULT,?,null,null,?)";
    private static final String SQL_SELECT_CLIENT_BY_ID = "SELECT id, adress, firstName, lastName, nameOrganization FROM client WHERE id = ?";
    private static final String SQL_UPDATE_INDIVIDUAL = "UPDATE client SET adress = ?, firstName = ?, lastName = ?, nameOrganization = null WHERE id = ?";
    private static final String SQL_UPDATE_LEGAL_ENTITY = "UPDATE client SET adress = ?, nameOrganization = ?, firstName = null, lastName = null WHERE id = ?";
    private static final String SQL_DELETE_CLIENT = "DELETE FROM client WHERE id = ?";

    @Override
    public void create(Client client){
        ResultSet rs = null;
        try(Connection con = cf.getMySQLConnection();
            PreparedStatement ps = createPrepareStatement(con, client)){
            if(ps.executeUpdate() > 0){
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    client.setId(rs.getLong(1));
                }
                con.commit();
            }
        }catch (SQLException e){
            LOGGER.error("Can't insert Client into DB!!!", e);
        }finally {
            close(rs);
        }
    }

    @Override
    public Client read(long id){
        Client client = null;
        try(Connection con = cf.getMySQLConnection();
            PreparedStatement ps = readPrepareStatement(con, id);
            ResultSet rs = ps.executeQuery()){
            if(rs.next()){
                client = extractClient(rs);
            }
            con.commit();
            return client;
        }catch (SQLException e){
            LOGGER.error("Can't read Client from DB!!!", e);
        }
        return null;
    }

    @Override
    public void update(Client client){
        try(Connection con = cf.getMySQLConnection();
            PreparedStatement ps = updatePrepareStatement(con, client)){
            if (ps.executeUpdate() > 0){
                con.commit();
            }
        }catch (SQLException e){
            LOGGER.error("Can't update Client into DB!!!", e);
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
            LOGGER.error("Can't delete Client from DB!!!", e);
        }
    }

    private PreparedStatement createPrepareStatement(Connection con, Client client) throws SQLException {
        if(client.getClass() == Individual.class){
            Individual individual = (Individual) client;
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_INDIVIDUAL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, individual.getAdress());
            ps.setString(2, individual.getFirstName());
            ps.setString(3, individual.getLastName());
            return ps;
        }
        if(client.getClass() == LegalEntity.class){
            LegalEntity legalEntity = (LegalEntity) client;
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_LEGAL_ENTITY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, legalEntity.getAdress());
            ps.setString(2, legalEntity.getNameOrganization());
            return ps;
        }
        return null;
    }

    private PreparedStatement readPrepareStatement(Connection con, long id) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_SELECT_CLIENT_BY_ID);
        ps.setLong(1, id);
        return ps;
    }

    private Client extractClient(ResultSet rs) throws SQLException {
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

    private PreparedStatement updatePrepareStatement(Connection con, Client client) throws SQLException {
        if(client.getClass() == Individual.class){
            Individual individual = (Individual) client;
            PreparedStatement ps = con.prepareStatement(SQL_UPDATE_INDIVIDUAL);
            ps.setString(1, individual.getAdress());
            ps.setString(2, individual.getFirstName());
            ps.setString(3, individual.getLastName());
            ps.setLong(4, individual.getId());
            return ps;
        }
        if(client.getClass() == LegalEntity.class){
            LegalEntity legalEntity = (LegalEntity) client;
            PreparedStatement ps = con.prepareStatement(SQL_UPDATE_LEGAL_ENTITY);
            ps.setString(1, legalEntity.getAdress());
            ps.setString(2, legalEntity.getNameOrganization());
            ps.setLong(3, legalEntity.getId());
            return ps;
        }
        return null;
    }

    private PreparedStatement deletePrepareStatement(Connection con, long id) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_DELETE_CLIENT);
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
