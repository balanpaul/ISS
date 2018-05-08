package repository.BoalaRepo;

import entities.Boala;
import repository.AbstractCRUDRepository;
import repository.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class BoalaSQLRepository extends AbstractCRUDRepository<Boala> {
    private JdbcUtils dbUtils;

    public BoalaSQLRepository(Properties props) {
        this.dbUtils = new JdbcUtils(props);
    }

    @Override
    public int size() {
        Connection con=dbUtils.getConnection();
        try
        {
            PreparedStatement preStmt = con.prepareStatement("SELECT COUNT (*) as SIZE from Boli");
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



    public Boala add(Boala entity) {
        Connection con =dbUtils.getConnection();
        int id = entity.getIdBoala();
        String denumire = entity.getDenumire();
        String descriere = entity.getDescriere();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Boli VALUES (?,?,?)")) {
            preStmt.setInt(1,id);
            preStmt.setString(2,denumire);
            preStmt.setString(3,descriere);
            preStmt.executeUpdate();
            return new Boala(id,denumire,descriere);
            //preStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Eror save DB" + e);
        }
        return null;
    }


    public Boala delete(Boala boala) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("delete from Boala where idBoala=?")) {
            preStmt.setInt(1, boala.getIdBoala());
            int result = preStmt.executeUpdate();
            return boala;
        } catch (SQLException e) {
            System.out.print("Eror delete DB" + e);
        }
        return  null;
    }

    public Boala update(int id, Boala boala) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("UPDATE Echipe SET Denumire=?, Descriere=? WHERE idBoala=?")){
            preStmt.setString(1, boala.getDenumire());
            preStmt.setString(2,boala.getDescriere());
            preStmt.setInt(3,id);
            int result = preStmt.executeUpdate();
            return findOne(id);
        }catch (SQLException e){
            System.out.println("Error update DB"+e);
        }
        return null;
    }


    public Boala findOne(Integer idE) {
        Connection con=dbUtils.getConnection();
        try (PreparedStatement preStmt =con.prepareStatement("select * from Boala where idBoala=?")){
            preStmt.setInt(1,idE);
            try(ResultSet result=preStmt.executeQuery()){
                if(result.next()){
                    int id=result.getInt("idBoala");
                    String denumire=result.getString("Denumire");
                    String descriere=result.getString("Descriere");
                    Boala boala=new Boala(id,denumire,descriere);
                    return boala;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error findOne DB "+e);
        }

        return null;
    }


    @Override
    public ArrayList<Boala> getList() {
        Connection con=dbUtils.getConnection();
        ArrayList<Boala> list=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("SELECT * FROM Boli")){
            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()) {
                    int id = result.getInt("CodE");
                    String denumire = result.getString("Denumire");
                    String descriere = result.getString("Descriere");
                    Boala boala = new Boala(id, denumire,descriere);
                    list.add(boala);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
