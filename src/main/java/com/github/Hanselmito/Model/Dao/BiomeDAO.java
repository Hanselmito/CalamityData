package com.github.Hanselmito.Model.Dao;

import com.github.Hanselmito.Model.Conection.ConnectionMariaDB;
import com.github.Hanselmito.Model.Entity.Biome;
import com.github.Hanselmito.Model.Entity.Enemys;
import com.github.Hanselmito.Model.Entity.Enums.Dificulty;
import com.github.Hanselmito.Model.Entity.Enums.SizeWorld;
import com.github.Hanselmito.Model.Entity.Enums.TipeEnemies;
import com.github.Hanselmito.Model.Entity.Enums.ZoneGenerate;
import com.github.Hanselmito.Model.Entity.World;
import com.github.Hanselmito.Model.Entity.object;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.github.Hanselmito.Model.Dao.WorldDAO.build;

public class BiomeDAO implements DAO<Biome>{
    private final static String INSERT="INSERT INTO Biome (IDBiome,IDWorld,NameBiome,ZoneGenerate,GenerationDificulty) VALUES (?,?,?,?,?)";
    private final static String UPDATE="UPDATE Biome SET IDWorld=?,NameBiome=?,ZoneGenerate=?,GenerationDificulty=? WHERE IDBiome=?";
    private final static String FINDBYID="SELECT b.IDBiome,b.IDWorld,b.NameBiome,b.ZoneGenerate,b.GenerationDificulty FROM Biome AS b WHERE b.IDBiome=?";
    private final static String FINDBYDIFICULTY="SELECT b.IDBiome,b.IDWorld,b.NameBiome,b.ZoneGenerate,b.GenerationDificulty FROM Biome AS b WHERE b.GenerationDificulty=?";
    private final static String FINDALL="SELECT b.IDBiome,b.IDWorld,b.NameBiome,b.ZoneGenerate,b.GenerationDificulty FROM Biome AS b";
    private final static String FIND_BIOMES_FOR_WORLD = "SELECT * FROM Biome WHERE IDWorld=?";
    private final static String DELETE="DELETE FROM Biome AS b WHERE b.IDBiome=?";

    private Connection conn;
    public BiomeDAO() {
        conn = ConnectionMariaDB.getConnection();
    }

    @Override
    public Biome save(Biome entity) {
        Biome result = entity;
        if (entity==null || entity.getIDBiome()==0) return result;
        Biome b = findById(entity.getIDBiome());
        if (b!=null){
            //insert
            try (PreparedStatement pst = conn.prepareStatement(INSERT)){
                pst.setInt(1,entity.getIDBiome());
                pst.setInt(2,entity.getWorld().getIDWorld());
                pst.setString(3,entity.getNameBiome());
                pst.setString(4,entity.getZoneGenerate().getLocate());
                pst.setString(5,entity.getGenerationDificulty().getPartOfDificulty());
                pst.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Biome update(Biome entity) {
        Biome result = entity;
        if (entity==null || entity.getIDBiome()==0) return result;
        try (PreparedStatement pst = conn.prepareStatement(UPDATE)){
            pst.setInt(1,entity.getWorld().getIDWorld());
            pst.setString(2,entity.getNameBiome());
            pst.setString(3,entity.getZoneGenerate().getLocate());
            pst.setString(4,entity.getGenerationDificulty().getPartOfDificulty());
            pst.setInt(5,entity.getIDBiome());
            pst.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Biome delete(Biome entity) throws SQLException {
        if (entity == null || entity.getIDBiome()==0)return entity;
        try (PreparedStatement pst = conn.prepareStatement(DELETE)){
            pst.setInt(1,entity.getIDBiome());
            pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Biome findById(int key) {
        Biome result = new Biome();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYID)){
            pst.setString(1, String.valueOf(key));
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    result.setIDBiome(res.getInt("IDBiome"));
                    result.setWorld(WorldDAO.build().findById(res.getInt("IDWorld")));
                    result.setNameBiome(res.getString("NameBiome"));
                    result.setZoneGenerate(ZoneGenerate.valueOf(res.getString("ZoneGenerate")));
                    result.setGenerationDificulty(Dificulty.valueOf(res.getString("GenerationDificulty")));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Biome> findAll() {
        List<Biome> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL)){
            ResultSet res = pst.executeQuery();
            while(res.next()){
                Biome biome = new Biome();
                biome.setIDBiome(res.getInt("IDBiome"));
                biome.setWorld(WorldDAO.build().findById(res.getInt("IDWorld")));
                biome.setNameBiome(res.getString("NameBiome"));
                biome.setZoneGenerate(ZoneGenerate.valueOf(res.getString("ZoneGenerate")));
                biome.setGenerationDificulty(Dificulty.valueOf(res.getString("GenerationDificulty")));
                result.add(biome);
            }
            res.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Biome> findByDificulty(String key) {
        List<Biome> result = new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYDIFICULTY)){
            pst.setString(1,String.valueOf(key));
            try(ResultSet res = pst.executeQuery()){
                while(res.next()){
                    Biome biome = new Biome();
                    biome.setIDBiome(res.getInt("IDBiome"));
                    biome.setWorld(WorldDAO.build().findById(res.getInt("IDWorld")));
                    biome.setNameBiome(res.getString("NameBiome"));
                    biome.setZoneGenerate(ZoneGenerate.valueOf(res.getString("ZoneGenerate")));
                    biome.setGenerationDificulty(Dificulty.valueOf(res.getString("GenerationDificulty")));
                    result.add(biome);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Biome> FindBiomeForWorld(int IDWorld) {
        List<Biome> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FIND_BIOMES_FOR_WORLD)) {
            pst.setInt(1, IDWorld);
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Biome biome = new Biome();
                    biome.setIDBiome(res.getInt("IDBiome"));
                    biome.setWorld(WorldDAO.build().findById(res.getInt("IDWorld")));
                    biome.setNameBiome(res.getString("NameBiome"));
                    biome.setZoneGenerate(ZoneGenerate.valueOf(res.getString("ZoneGenerate")));
                    biome.setGenerationDificulty(Dificulty.valueOf(res.getString("GenerationDificulty")));
                    result.add(biome);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {

    }

    public static BiomeDAO build(){
        return new BiomeDAO();
    }

    public class BiomeLazyAll extends Biome {
        private final static String FIND_ALL="SELECT DISTINCT b.* FROM Biome b LEFT JOIN Enemys e ON b.IDBiome = e.IDBiome WHERE e.IDBiome IS NOT NULL";


        public BiomeLazyAll(int IDBiome, World world, String nameBiome, ZoneGenerate zoneGenerate, Dificulty generationDificulty, List<Enemys> enemys) {
            super(IDBiome, world, nameBiome, zoneGenerate, generationDificulty, enemys);
        }

        public List<Biome> findAllBiomesWithEnemys() {
            List<Biome> result = new ArrayList<>();
            try (PreparedStatement pst = conn.prepareStatement(FIND_ALL)) {
                try (ResultSet res = pst.executeQuery()){
                    while (res.next()) {
                        Biome biome = new Biome();
                        biome.setIDBiome(res.getInt("IDBiome"));
                        biome.setWorld(WorldDAO.build().findById(res.getInt("IDWorld")));
                        biome.setNameBiome(res.getString("NameBiome"));
                        biome.setZoneGenerate(ZoneGenerate.valueOf(res.getString("ZoneGenerate")));
                        biome.setGenerationDificulty(Dificulty.valueOf(res.getString("GenerationDificulty")));

                        // Obtén y establece todos los enemigos asociados a este bioma
                        List<Enemys> enemys = new EnemysDAO().FindEnemysForBiome(biome.getIDBiome());
                        biome.setEnemys(enemys);

                        result.add(biome);
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            return result;
        }
    }
}
