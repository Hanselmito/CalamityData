package com.github.Hanselmito.Model.Dao;

import com.github.Hanselmito.Model.Conection.ConnectionMariaDB;
import com.github.Hanselmito.Model.Entity.Biome;
import com.github.Hanselmito.Model.Entity.Enums.Dificulty;
import com.github.Hanselmito.Model.Entity.Enums.SizeWorld;
import com.github.Hanselmito.Model.Entity.World;
import com.github.Hanselmito.Model.Entity.object;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorldDAO implements DAO<World>{
    private final static String INSERT="INSERT INTO World (IDWorld,Dificulty,SizeWorld) VALUES (?, ?, ?)";
    private final static String UPDATE="UPDATE World SET Dificulty=?,SizeWorld=? WHERE IDWorld=?";
    private final static String FINDBYID="SELECT a.IDWorld,a.Dificulty,a.SizeWorld FROM World AS a WHERE a.IDWorld=?";
    private final static String DELETE="DELETE FROM World AS a WHERE a.IDWorld=?";

    private Connection conn;
    public WorldDAO() {
        conn = ConnectionMariaDB.getConnection();
    }

    @Override
    public World save(World entity) {
        World result = entity;
        if (entity==null || entity.getIDWorld()==0) return result;
        World w = findById(entity.getIDWorld());
        if (w!=null){
            try (PreparedStatement pst = conn.prepareStatement(INSERT)){
                pst.setInt(1,entity.getIDWorld());
                pst.setString(2,entity.getDificulty().getPartOfDificulty());
                pst.setString(3,entity.getSizeWorld().getSize());
                pst.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public World update(World entity) {
        World result = entity;
        if (entity==null || entity.getIDWorld()==0) return result;
            try (PreparedStatement pst = conn.prepareStatement(UPDATE)){
                pst.setString(1,entity.getDificulty().getPartOfDificulty());
                pst.setString(2,entity.getSizeWorld().getSize());
                pst.setInt(3,entity.getIDWorld());
                pst.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }
        return result;
    }

    @Override
    public World delete(World entity) throws SQLException {
        if (entity == null || entity.getIDWorld()==0)return entity;
        try (PreparedStatement pst = conn.prepareStatement(DELETE)){
            pst.setInt(1,entity.getIDWorld());
            pst.executeUpdate();
        }
        return entity;
    }

    @Override
    public World findById(int key) {
        World result =new World();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYID)){
            pst.setString(1, String.valueOf(key));
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    result.setIDWorld(res.getInt("IDWorld"));
                    result.setDificulty(Dificulty.valueOf(res.getString("Dificulty")));
                    result.setSizeWorld(SizeWorld.valueOf(res.getString("SizeWorld")));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
    public static WorldDAO build(){
        return new WorldDAO();
    }
    @Override
    public void close() throws IOException {

    }
}
