package repository.DonatorRepo;

import entities.Donator;
import repository.AbstractCRUDRepository;
import repository.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DonatorSQLRepository extends AbstractCRUDRepository<Donator> {
    private JdbcUtils dbUtils;

    public DonatorSQLRepository(Properties props) {
        this.dbUtils = new JdbcUtils(props);
    }

    @Override
    public int size() {
        Connection con=dbUtils.getConnection();
        try
        {
            PreparedStatement preStmt = con.prepareStatement("SELECT COUNT (*) as SIZE from Donator");
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

    public Donator add(Donator entity) {
        Connection con =dbUtils.getConnection();
        int id = entity.getCnp();
        String nume = entity.getNume();
        String prenume = entity.getPrenume();
        String dataNasterii = entity.getDataNasterii();
        String numarTelefon = entity.getNrTelefon();
        String email = entity.getEmail();
        int idSange = entity.getIdSange();
        String judet = entity.getJudet();
        String oras = entity.getOras();
        String strada = entity.getStrada();
        String apartament = entity.getApartament();
        String bloc = entity.getBloc();
        String scara = entity.getScara();
        String numar = entity.getNumar();
        String codPostal = entity.getCodPostal();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Donator VALUES (?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)")) {
            preStmt.setInt(1, id);
            preStmt.setString(2, nume);
            preStmt.setString(3, prenume);
            preStmt.setString(4, dataNasterii);
            preStmt.setString(5, numarTelefon);
            preStmt.setString(6, email);
            preStmt.setInt(7, idSange);
            preStmt.setString(8, judet);
            preStmt.setString(9, oras);
            preStmt.setString(10, strada);
            preStmt.setString(11, apartament);
            preStmt.setString(12, bloc);
            preStmt.setString(13, scara);
            preStmt.setString(14, numar);
            preStmt.setString(15, codPostal);
            preStmt.executeUpdate();
            return new Donator(id, nume, prenume, dataNasterii, numarTelefon, email, idSange, judet, oras, strada, apartament, bloc, scara, numar, codPostal);
            //preStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Eror save DB" + e);
        }
        return null;
    }


    public Donator delete(Donator boala) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("delete from Donator where CNP=?")) {
            preStmt.setInt(1, boala.getCnp());
            int result = preStmt.executeUpdate();
            return boala;
        } catch (SQLException e) {
            System.out.print("Eror delete DB" + e);
        }
        return  null;
    }

    public Donator update(int id, Donator boala) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("UPDATE Donator SET Nume=?, Prenume=?, DataNasterii=?, NrTelefon=?, Email=?, idSange=?, Judet=?, Oras=?, Strada=?, Apartament=?, Bloc=?, Scara=?, Numar=?, CodPostal=? WHERE CNP=?")){
            preStmt.setString(1, boala.getNume());
            preStmt.setString(2, boala.getPrenume());
            preStmt.setString(3, boala.getDataNasterii());
            preStmt.setString(4, boala.getNrTelefon());
            preStmt.setString(5, boala.getEmail());
            preStmt.setInt(6, boala.getIdSange());
            preStmt.setString(7, boala.getJudet());
            preStmt.setString(8, boala.getOras());
            preStmt.setString(9, boala.getStrada());
            preStmt.setString(10, boala.getApartament());
            preStmt.setString(11, boala.getBloc());
            preStmt.setString(12, boala.getScara());
            preStmt.setString(13, boala.getNumar());
            preStmt.setString(14, boala.getCodPostal());
            preStmt.setInt(15, id);
            int result = preStmt.executeUpdate();
            return findOne(id);
        }catch (SQLException e){
            System.out.println("Error update DB"+e);
        }
        return null;
    }


    public Donator findOne(Integer idE) {
        Connection con=dbUtils.getConnection();
        try (PreparedStatement preStmt =con.prepareStatement("select * from Donator where CNP=?")){
            preStmt.setInt(1,idE);
            try(ResultSet result=preStmt.executeQuery()){
                if(result.next()){
                    int id = result.getInt("CNP");
                    String nume = result.getString("Nume");
                    String prenume = result.getString("Prenume");
                    String dataNasterii = result.getString("DataNasterii");
                    String numarTelefon = result.getString("NrTelefon");
                    String email = result.getString("Email");
                    int idSange = result.getInt("idSange");
                    String judat = result.getString("Judet");
                    String oras = result.getString("Oras");
                    String strada = result.getString("Strada");
                    String apartament = result.getString("Apartament");
                    String bloc = result.getString("Bloc");
                    String scara = result.getString("Scara");
                    String numar = result.getString("Numar");
                    String codPostal = result.getString("CodPostal");
                    Donator boala=new Donator(id, nume, prenume, dataNasterii, numarTelefon, email, idSange, judat, oras, strada, apartament, bloc, scara, numar, codPostal);
                    return boala;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error findOne DB "+e);
        }

        return null;
    }


    @Override
    public ArrayList<Donator> getList() {
        Connection con=dbUtils.getConnection();
        ArrayList<Donator> list=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("SELECT * FROM Clinici")){
            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()) {
                    int id = result.getInt("CNP");
                    String nume = result.getString("Nume");
                    String prenume = result.getString("Prenume");
                    String dataNasterii = result.getString("DataNasterii");
                    String numarTelefon = result.getString("NrTelefon");
                    String email = result.getString("Email");
                    int idSange = result.getInt("idSange");
                    String judat = result.getString("Judet");
                    String oras = result.getString("Oras");
                    String strada = result.getString("Strada");
                    String apartament = result.getString("Apartament");
                    String bloc = result.getString("Bloc");
                    String scara = result.getString("Scara");
                    String numar = result.getString("Numar");
                    String codPostal = result.getString("CodPostal");
                    Donator boala=new Donator(id, nume, prenume, dataNasterii, numarTelefon, email, idSange, judat, oras, strada, apartament, bloc, scara, numar, codPostal);
                    list.add(boala);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
