package repository.CentruRepo;

import entities.Centru;
import repository.AbstractCRUDRepository;
import repository.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class CentruSQLRepository extends AbstractCRUDRepository<Centru> {
    private JdbcUtils dbUtils;

    public CentruSQLRepository(Properties props) {
        this.dbUtils = new JdbcUtils(props);
    }

    @Override
    public int size() {
        Connection con=dbUtils.getConnection();
        try
        {
            PreparedStatement preStmt = con.prepareStatement("SELECT COUNT (*) as SIZE from Centre");
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

    public Centru add(Centru entity) {
        Connection con =dbUtils.getConnection();
        int id = entity.getIdCentru();
        String nume = entity.getNume();
        int idPersonal = entity.getIdPersonal();
        int idDonator = entity.getIdDonator();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Centre VALUES (?,?,?,?)")) {
            preStmt.setInt(1, id);
            preStmt.setString(2, nume);
            preStmt.setInt(1, idPersonal);
            preStmt.setInt(3, idDonator);
            preStmt.executeUpdate();
            return new Centru(id, nume, idPersonal, idDonator);
            //preStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Eror save DB" + e);
        }
        return null;
    }


    public Centru delete(Centru boala) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("delete from Centre where idCentru=?")) {
            preStmt.setInt(1, boala.getIdCentru());
            int result = preStmt.executeUpdate();
            return boala;
        } catch (SQLException e) {
            System.out.print("Eror delete DB" + e);
        }
        return  null;
    }

    public Centru update(int id, Centru boala) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("UPDATE Echipe SET Nume=?, idPersonal=?, idDonator=? WHERE idBoala=?")){
            preStmt.setString(1, boala.getNume());
            preStmt.setInt(2,boala.getIdCentru());
            preStmt.setInt(3,boala.getIdDonator());
            preStmt.setInt(4,id);
            int result = preStmt.executeUpdate();
            return findOne(id);
        }catch (SQLException e){
            System.out.println("Error update DB"+e);
        }
        return null;
    }


    public Centru findOne(Integer idE) {
        Connection con=dbUtils.getConnection();
        try (PreparedStatement preStmt =con.prepareStatement("select * from Centru where idCentru=?")){
            preStmt.setInt(1,idE);
            try(ResultSet result=preStmt.executeQuery()){
                if(result.next()){
                    int id=result.getInt("idCentru");
                    String nume=result.getString("Nume");
                    int idPersonal=result.getInt("idPersonal");
                    int idDoantor = result.getInt("idDonator");
                    Centru boala=new Centru(id,nume,idPersonal,idDoantor);
                    return boala;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error findOne DB "+e);
        }

        return null;
    }


    @Override
    public ArrayList<Centru> getList() {
        Connection con=dbUtils.getConnection();
        ArrayList<Centru> list=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("SELECT * FROM Centre")){
            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()) {
                    int id = result.getInt("CodE");
                    String nume = result.getString("Nume");
                    int idPersonal = result.getInt("idPersonal");
                    int idDonator = result.getInt("idDonator");
                    Centru boala = new Centru(id, nume, idPersonal, idDonator);
                    list.add(boala);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}