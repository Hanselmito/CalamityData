package com.github.Hanselmito.Model.Dao;

import com.github.Hanselmito.Model.Conection.ConnectionMariaDB;
import com.github.Hanselmito.Model.Entity.Obgect;
import com.github.Hanselmito.Model.Entity.World;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObgectDAO implements DAO<Obgect>{
    private final static String INSERT="INSERT INTO obgect (IDObject,NameObject,TipeObject,Effect,TipeClass) VALUES (?,?,?,?,?)";
    private final static String UPDATE="UPDATE obgect SET NameObject=?,tipeObject=?,Effect=?,tipeClass=? WHERE IDObject=?";
    private final static String FINDBYID="SELECT o.IDObject,o.NameObject,o.tipeObject,o.Effect,o.tipeClass FROM obgect AS o WHERE o.IDObject=?";
    private final static String DELETE="DELETE FROM obgect AS o WHERE o.IDObject=?";

    private Connection conn;
    public ObgectDAO() {
        conn = ConnectionMariaDB.getConnection();
    }

    @Override
    public Obgect save(Obgect entity) {
        Obgect result = entity;
        if (entity==null || entity.getIDObject()==0) return result;
        Obgect o = findById(entity.getIDObject());
        if (entity.getIDObject()==0){
            //insert
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT)){
                pst.setInt(1,entity.getIDObject());
                pst.setString(2,entity.getNameObject());
                pst.setString(3,entity.getTipeObject().name());
                pst.setString(4, String.valueOf(entity.getTipeObject().getDefence()));
                pst.setString(5,entity.getTipeObject().getRarity());
                pst.setString(6, entity.getEffect());
                pst.setString(7,entity.getTipeClass().name());
                pst.executeUpdate();
                /*save cascada -> opcional
                if (entity.getBiomes()!=null) {
                    for (Biomes b : entity.getBiomes()) {
                        BiomesDAO.build().save(b);
                    }
                }*/
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)){
                pst.setInt(1,entity.getIDObject());
                pst.setString(2, entity.getNameObject());
                pst.setString(3,entity.getTipeObject().name());
                pst.setString(4, String.valueOf(entity.getTipeObject().getDefence()));
                pst.setString(5,entity.getTipeObject().getRarity());
                pst.setString(6, entity.getEffect());
                pst.setString(7,entity.getTipeClass().name());
                pst.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Obgect delete(Obgect entity) throws SQLException {
        if (entity == null || entity.getIDObject()==0)return entity;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)){
            pst.setInt(1,entity.getIDObject());
            pst.executeUpdate();
        }
        return entity;
    }

    @Override
    public Obgect findById(int key) {
        Obgect result =null;
        try(PreparedStatement pst = conn.prepareStatement(FINDBYID)){
            pst.setString(1, String.valueOf(key));
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    World w = new World();
                    w.setIDWorld(res.getInt("IDWorld"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void close() throws IOException {

    }
}
