package repository.PersonalCliniciCliniciRepo;

import entities.PersonalClinici;
import repository.AbstractCRUDRepository;
import repository.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class PersonalCliniciSQLRepository extends AbstractCRUDRepository<PersonalClinici> {
    private JdbcUtils dbUtils;

    public PersonalCliniciSQLRepository(Properties props) {
        this.dbUtils = new JdbcUtils(props);
    }

    @Override
    public int size() {
        Connection con=dbUtils.getConnection();
        try
        {
            PreparedStatement preStmt = con.prepareStatement("SELECT COUNT (*) as SIZE from PersonalClinici");
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

    public PersonalClinici add(PersonalClinici entity) {
        Connection con =dbUtils.getConnection();
        int id = entity.getIdPersonal();
        String functie = entity.getFunctie();
        String nume = entity.getNume();
        String prenume = entity.getPrenume();
        String numarTelefon = entity.getNrTelefon();
        try(PreparedStatement preStmt=con.prepareStatement("insert into PersonalClinici VALUES (?,?,?,?,?)")) {
            preStmt.setInt(1, id);
            preStmt.setString(2, functie);
            preStmt.setString(3, nume);
            preStmt.setString(4, prenume);
            preStmt.setString(5, numarTelefon);
            preStmt.executeUpdate();
            return new PersonalClinici(id, functie, nume, prenume, numarTelefon);
            //preStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Eror save DB" + e);
        }
        return null;
    }


    public PersonalClinici delete(PersonalClinici entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("delete from PersonalClinici where idPersonalClinici=?")) {
            preStmt.setInt(1, entity.getIdPersonal());
            int result = preStmt.executeUpdate();
            return entity;
        } catch (SQLException e) {
            System.out.print("Eror delete DB" + e);
        }
        return  null;
    }

    public PersonalClinici update(int id, PersonalClinici entity) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("UPDATE PersonalClinici SET Functie=?, Nume=?, Prenume=?, NrTelefon=? WHERE idPersonalClinici=?")){
            preStmt.setInt(1, entity.getIdPersonal());
            preStmt.setString(2,entity.getFunctie());
            preStmt.setString(3,entity.getNume());
            preStmt.setString(4,entity.getPrenume());
            preStmt.setInt(5,id);
            int result = preStmt.executeUpdate();
            return findOne(id);
        }catch (SQLException e){
            System.out.println("Error update DB"+e);
        }
        return null;
    }


    public PersonalClinici findOne(Integer idE) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt =con.prepareStatement("select * from PersonalClinici where idPersonalClinici=?")){
            preStmt.setInt(1,idE);
            try(ResultSet result = preStmt.executeQuery()){
                if(result.next()){
                    int id = result.getInt("idPersonalClinici");
                    String functie = result.getString("Functie");
                    String nume = result.getString("Nume");
                    String prenume = result.getString("Prenume");
                    String numarTelefon = result.getString("NrTelefon");
                    PersonalClinici entity = new PersonalClinici(id, functie, nume, prenume, numarTelefon);
                    return entity;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error findOne DB "+e);
        }

        return null;
    }


    @Override
    public ArrayList<PersonalClinici> getList() {
        Connection con=dbUtils.getConnection();
        ArrayList<PersonalClinici> list=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("SELECT * FROM PersonalClinici")){
            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()) {
                    int id = result.getInt("idPersonalClinici");
                    String functie = result.getString("Functie");
                    String nume = result.getString("Nume");
                    String prenume = result.getString("Prenume");
                    String numarTelefon = result.getString("NrTelefon");
                    PersonalClinici entity = new PersonalClinici(id, functie, nume, prenume, numarTelefon);
                    list.add(entity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
