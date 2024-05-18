package com.github.Hanselmito.Model.Dao;

import com.github.Hanselmito.Model.Conection.ConnectionMariaDB;
import com.github.Hanselmito.Model.Entity.Enemys;
import com.github.Hanselmito.Model.Entity.Enums.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnemysDAO implements DAO<Enemys>{
    private final static String INSERT="INSERT INTO Enemys (IDEnemies,IDBiome,TipeEnemies,NameEnemies,DificultySpawn,Imagen) VALUES (?,?,?,?,?,?)";
    private final static String UPDATE="UPDATE enemys SET IDBiome=?,TipeEnemies=?,NameEnemies=?,DificultySpawn=?,Imagen=? WHERE IDEnemies=?";
    private final static String FINDBYID="SELECT e.IDEnemies,e.IDBiome,e.TipeEnemies,e.NameEnemies,e.DificultySpawn,e.Imagen FROM enemys AS e WHERE e.IDEnemies=?";
    private final static String FINDALL="SELECT IDEnemies,IDBiome,TipeEnemies,NameEnemies,DificultySpawn,Imagen FROM enemys";
    private final static String FINDBYTIPEENEMIES="SELECT e.IDEnemies,e.IDBiome,e.TipeEnemies,e.NameEnemies,e.DificultySpawn,e.Imagen FROM enemys AS e WHERE e.TipeEnemies=?";
    private final static String DELETE="DELETE FROM enemys AS e WHERE e.IDEnemies=?";

    private Connection conn;
    public EnemysDAO() {
        conn = ConnectionMariaDB.getConnection();
    }

    @Override
    public Enemys save(Enemys entity) {
        Enemys result = entity;
        if (entity==null || entity.getIDEnemies()==0) return result;
        Enemys en = findById(entity.getIDEnemies());
        if (en!=null){
            //insert
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT)){
                pst.setInt(1,entity.getIDEnemies());
                pst.setInt(2,entity.getBiome().getIDBiome());
                pst.setString(3,entity.getTipeEnemies().getTipe());
                pst.setString(4,entity.getNameEnemies());
                pst.setString(5,entity.getDificultySpawn().getPartOfDificulty());
                pst.setBytes(6, entity.getImagen());
                pst.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Enemys update(Enemys entity) {
        Enemys result = entity;
        if (entity==null || entity.getIDEnemies()==0) return result;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)){
            pst.setInt(1,entity.getBiome().getIDBiome());
            pst.setString(2,entity.getTipeEnemies().getTipe());
            pst.setString(3,entity.getNameEnemies());
            pst.setString(4,entity.getDificultySpawn().getPartOfDificulty());
            pst.setBytes(5, entity.getImagen());
            pst.setInt(6,entity.getIDEnemies());
            pst.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Enemys delete(Enemys entity) throws SQLException {
        if (entity == null || entity.getIDEnemies()==0)return entity;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)){
            pst.setInt(1,entity.getIDEnemies());
            pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Enemys findById(int key) {
        Enemys result =new Enemys();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYID)){
            pst.setString(1, String.valueOf(key));
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    result.setIDEnemies(res.getInt("IDEnemies"));
                    result.setBiome(BiomeDAO.build().findById(res.getInt("IDBiome")));
                    result.setTipeEnemies(TipeEnemies.valueOf(res.getString("TipeEnemies")));
                    result.setNameEnemies(res.getString("NameEnemies"));
                    result.setDificultySpawn(Dificulty.valueOf(res.getString("DificultySpawn")));
                    result.setImagen(res.getBytes("Imagen"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public List<Enemys> findAll() {
        List<Enemys> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL)){
            ResultSet res = pst.executeQuery();
            while(res.next()){
                Enemys enemy = new Enemys();
                enemy.setIDEnemies(res.getInt("IDEnemies"));
                enemy.setBiome(BiomeDAO.build().findById(res.getInt("IDBiome")));
                enemy.setTipeEnemies(TipeEnemies.valueOf(res.getString("TipeEnemies")));
                enemy.setNameEnemies(res.getString("NameEnemies"));
                enemy.setDificultySpawn(Dificulty.valueOf(res.getString("DificultySpawn")));
                enemy.setImagen(res.getBytes("Imagen"));
                result.add(enemy);
            }
            res.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Enemys> findByTipeEnemies(String key) {
        List<Enemys> result =new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYTIPEENEMIES)){
            pst.setString(1, String.valueOf(key));
            try(ResultSet res = pst.executeQuery()){
                while(res.next()){
                    Enemys enemy = new Enemys();
                    enemy.setIDEnemies(res.getInt("IDEnemies"));
                    enemy.setBiome(BiomeDAO.build().findById(res.getInt("IDBiome")));
                    enemy.setTipeEnemies(TipeEnemies.valueOf(res.getString("TipeEnemies")));
                    enemy.setNameEnemies(res.getString("NameEnemies"));
                    enemy.setDificultySpawn(Dificulty.valueOf(res.getString("DificultySpawn")));
                    enemy.setImagen(res.getBytes("Imagen"));
                    result.add(enemy);
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

    public static EnemysDAO build(){
        return new EnemysDAO();
    }
}