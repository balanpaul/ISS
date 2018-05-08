package repository.DateSangeRepo;

import entities.DateSange;
import repository.AbstractCRUDRepository;
import repository.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DateSangeSQLRepository extends AbstractCRUDRepository<DateSange> {
    private JdbcUtils dbUtils;

    public DateSangeSQLRepository(Properties props) {
        this.dbUtils = new JdbcUtils(props);
    }

    @Override
    public int size() {
        Connection con=dbUtils.getConnection();
        try
        {
            PreparedStatement preStmt = con.prepareStatement("SELECT COUNT (*) as SIZE from DateSnage");
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

    public DateSange add(DateSange entity) {
        Connection con =dbUtils.getConnection();
        int id = entity.getIdSange();
        String grupa = entity.getGrupaSanguina();
        Boolean sanatos  = entity.getSanatos();
        String rh = entity.getRh();
        int trombocite = entity.getTrombocite();
        String leucocite = entity.getLeucocite();
        String hematii = entity.getHematii();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Centre VALUES (?,?,?,?,?,?,?)")) {
            preStmt.setInt(1, id);
            preStmt.setString(2, grupa);
            preStmt.setBoolean(3, sanatos);
            preStmt.setString(4, rh);
            preStmt.setInt(5, trombocite);
            preStmt.setString(6,leucocite);
            preStmt.setString(7,hematii);
            preStmt.executeUpdate();
            return new DateSange(id, grupa, sanatos, rh, trombocite, leucocite, hematii);
            //preStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Eror save DB" + e);
        }
        return null;
    }


    public DateSange delete(DateSange boala) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("delete from DateSange where idSange=?")) {
            preStmt.setInt(1, boala.getIdSange());
            int result = preStmt.executeUpdate();
            return boala;
        } catch (SQLException e) {
            System.out.print("Eror delete DB" + e);
        }
        return  null;
    }

    public DateSange update(int id, DateSange boala) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("UPDATE Echipe SET GrupaSanguina=?, Sanatos=?, RH=?, Trombocite=?, Leucocite=?, Hematii=? WHERE idSange=?")){
            preStmt.setString(1, boala.getGrupaSanguina());
            preStmt.setBoolean(2,boala.getSanatos());
            preStmt.setString(3,boala.getRh());
            preStmt.setInt(4,boala.getTrombocite());
            preStmt.setString(5,boala.getLeucocite());
            preStmt.setString(6,boala.getHematii());
            preStmt.setInt(4,id);
            int result = preStmt.executeUpdate();
            return findOne(id);
        }catch (SQLException e){
            System.out.println("Error update DB"+e);
        }
        return null;
    }


    public DateSange findOne(Integer idE) {
        Connection con=dbUtils.getConnection();
        try (PreparedStatement preStmt =con.prepareStatement("select * from DateSange where idSange=?")){
            preStmt.setInt(1,idE);
            try(ResultSet result=preStmt.executeQuery()){
                if(result.next()){
                    int id=result.getInt("idSange");
                    String grupa = result.getString("GrupaSanguina");
                    Boolean sanatos = result.getBoolean("Sanatos");
                    String rh = result.getString("RH");
                    int trombocite = result.getInt("Trombocite");
                    String leucocite = result.getString("Leucocite");
                    String hematii = result.getString("Hematii");
                    DateSange boala = new DateSange(id, grupa, sanatos, rh, trombocite, leucocite, hematii);
                    return boala;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error findOne DB "+e);
        }

        return null;
    }


    @Override
    public ArrayList<DateSange> getList() {
        Connection con=dbUtils.getConnection();
        ArrayList<DateSange> list=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("SELECT * FROM DateSange")){
            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()) {
                    int id=result.getInt("idSange");
                    String grupa = result.getString("GrupaSanguina");
                    Boolean sanatos = result.getBoolean("Sanatos");
                    String rh = result.getString("RH");
                    int trombocite = result.getInt("Trombocite");
                    String leucocite = result.getString("Leucocite");
                    String hematii = result.getString("Hematii");
                    DateSange boala = new DateSange(id, grupa, sanatos, rh, trombocite, leucocite, hematii);
                    list.add(boala);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
