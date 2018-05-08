package repository.ClinicaRepo;

import repository.AbstractCRUDRepository;
import repository.JdbcUtils;
import entities.Clinica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ClinicaSQLRepository extends AbstractCRUDRepository<Clinica> {
    private JdbcUtils dbUtils;

    public ClinicaSQLRepository(Properties props) {
        this.dbUtils = new JdbcUtils(props);
    }

    @Override
    public int size() {
        Connection con=dbUtils.getConnection();
        try
        {
            PreparedStatement preStmt = con.prepareStatement("SELECT COUNT (*) as SIZE from Clinici");
            ResultSet resultSet = preStmt.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("SIZE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error DB"+e);
        }

        return 0;
    }

    public Clinica add(Clinica entity) {
        Connection con =dbUtils.getConnection();
        int id = entity.getIdClinica();
        int idPersonal = entity.getIdPersonal();
        String contact = entity.getContact();
        String adresa = entity.getAdresa();
        int idSange = entity.getIdSange();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Centre VALUES (?,?,?,?,?)")) {
            preStmt.setInt(1, id);
            preStmt.setInt(2, idPersonal);
            preStmt.setString(3, contact);
            preStmt.setString(4, adresa);
            preStmt.setInt(5, idSange);
            preStmt.executeUpdate();
            return new Clinica(id, idPersonal, contact, adresa,idSange);
            //preStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Eror save DB" + e);
        }
        return null;
    }


    public Clinica delete(Clinica boala) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("delete from Clinici where idClinica=?")) {
            preStmt.setInt(1, boala.getIdClinica());
            int result = preStmt.executeUpdate();
            return boala;
        } catch (SQLException e) {
            System.out.print("Eror delete DB" + e);
        }
        return  null;
    }

    public Clinica update(int id, Clinica boala) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("UPDATE Echipe SET idPersoanl=?, Contact=?, Adresa=?, idSange=? WHERE idClinica=?")){
            preStmt.setInt(1, boala.getIdPersonal());
            preStmt.setString(2,boala.getContact());
            preStmt.setString(3,boala.getAdresa());
            preStmt.setInt(4,id);
            int result = preStmt.executeUpdate();
            return findOne(id);
        }catch (SQLException e){
            System.out.println("Error update DB"+e);
        }
        return null;
    }


    public Clinica findOne(Integer idE) {
        Connection con=dbUtils.getConnection();
        try (PreparedStatement preStmt =con.prepareStatement("select * from Clinici where idClinica=?")){
            preStmt.setInt(1,idE);
            try(ResultSet result=preStmt.executeQuery()){
                if(result.next()){
                    int id=result.getInt("idClinica");
                    int idPersonal=result.getInt("idPersonal");
                    String contact=result.getString("Contact");
                    String adresa = result.getString("Adresa");
                    int idSange = result.getInt("idSange");
                    Clinica boala=new Clinica(id,idPersonal,contact,adresa,idSange);
                    return boala;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error findOne DB "+e);
        }

        return null;
    }


    @Override
    public ArrayList<Clinica> getList() {
        Connection con=dbUtils.getConnection();
        ArrayList<Clinica> list=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("SELECT * FROM Clinici")){
            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()) {
                    int id=result.getInt("idClinica");
                    int idPersonal=result.getInt("idPersonal");
                    String contact=result.getString("Contact");
                    String adresa = result.getString("Adresa");
                    int idSange = result.getInt("idSange");
                    Clinica boala=new Clinica(id,idPersonal,contact,adresa,idSange);
                    list.add(boala);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
