package com.github.Hanselmito.Model.Dao;

import com.github.Hanselmito.Model.Conection.ConnectionMariaDB;
import com.github.Hanselmito.Model.Entity.Biome;
import com.github.Hanselmito.Model.Entity.Enums.*;
import com.github.Hanselmito.Model.Entity.World;
import com.github.Hanselmito.Model.Entity.object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorldDAO implements DAO<World>{
    private final static String INSERT="INSERT INTO World (IDWorld,Dificulty,SizeWorld) VALUES (?, ?, ?)";
    private final static String UPDATE="UPDATE World SET Dificulty=?,SizeWorld=? WHERE IDWorld=?";
    private final static String FINDBYID="SELECT w.IDWorld,w.Dificulty,w.SizeWorld FROM World AS w WHERE w.IDWorld=?";
    private final static String FINDALL="SELECT IDWorld,Dificulty,SizeWorld FROM World";
    private final static String FINDBYDIFICULTY="SELECT w.IDWorld,w.Dificulty,w.SizeWorld FROM World AS w WHERE w.Dificulty=?";
    private final static String DELETE="DELETE FROM World WHERE IDWorld=?";

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
        if (entity == null || entity.getIDWorld()==0) return entity;
        try (PreparedStatement pst = conn.prepareStatement(DELETE)){
            pst.setInt(1,entity.getIDWorld());
            pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * Método para borrar un mundo y asignarle a todos sus objetos y biomas el ID de un mundo en nulo
     * asigna un ID del mundo en nulo que sería en 20 a todos los objetos y biomas de este mundo
     */
    public World deleteWorld(World entity, World newId){
        if (entity == null || entity.getIDWorld() == 0) return entity;
        try {
            if (entity.getObject() != null) {
                ObjectDAO objDAO = new ObjectDAO();
                for (object obj : entity.getObject()) {
                    obj.setWorld(newId);
                    objDAO.update(obj);
                }
            }

            if (entity.getBiome() != null) {
                BiomeDAO bioDAO = new BiomeDAO();
                for (Biome bio : entity.getBiome()) {
                    bio.setWorld(newId);
                    bioDAO.update(bio);
                }
            }
            delete(entity);

        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<World> findAll() {
        List<World> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL)){
            ResultSet res = pst.executeQuery();
            while(res.next()){
                World world = new World();
                world.setIDWorld(res.getInt("IDWorld"));
                world.setDificulty(Dificulty.valueOf(res.getString("Dificulty")));
                world.setSizeWorld(SizeWorld.valueOf(res.getString("SizeWorld")));
                result.add(world);
            }
            res.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<World> findByDificulty(String key) {
        List<World> result =new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYDIFICULTY)){
            pst.setString(1, String.valueOf(key));
            try(ResultSet res = pst.executeQuery()){
                while(res.next()){
                    World world = new World();
                    world.setIDWorld(res.getInt("IDWorld"));
                    world.setDificulty(Dificulty.valueOf(res.getString("Dificulty")));
                    world.setSizeWorld(SizeWorld.valueOf(res.getString("SizeWorld")));
                    result.add(world);
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
    public void close(){

    }

    /**
     * Clase para encontrar todos los mundos con al menos un objeto o un bioma asociado
     */
    public class WorldLazyAll extends World {
        private final static String FIND_ALL="SELECT DISTINCT w.* FROM World w LEFT JOIN Object o ON w.IDWorld = o.IDWorld LEFT JOIN Biome b ON w.IDWorld = b.IDWorld WHERE o.IDWorld IS NOT NULL OR b.IDWorld IS NOT NULL";


        public WorldLazyAll(int IDWorld, Dificulty dificulty, SizeWorld sizeWorld, List<object> List_Objects, List<Biome> List_Biomes) {
            super(IDWorld, dificulty, sizeWorld, List_Objects, List_Biomes);

        }

        /**
         * Método para encontrar todos los mundos con al menos un objeto o un bioma asociado
         * Obtén y establece todos los objetos asociados a este mundo
         * Obtén y establece todos los biomas asociados a este mundo
         */
        public List<World> findAllWorldsWithObjectAndBiome() {
            List<World> result = new ArrayList<>();
            try (PreparedStatement pst = conn.prepareStatement(FIND_ALL)) {
                try (ResultSet res = pst.executeQuery()){
                    while (res.next()) {
                        World world = new World();
                        world.setIDWorld(res.getInt("IDWorld"));
                        world.setDificulty(Dificulty.valueOf(res.getString("Dificulty")));
                        world.setSizeWorld(SizeWorld.valueOf(res.getString("SizeWorld")));

                        List<object> obj = new ObjectDAO().FindObjectForWorld(world.getIDWorld());
                        world.setObject(obj);
                        List<Biome> biome = new BiomeDAO().FindBiomeForWorld(world.getIDWorld());
                        world.setBiome(biome);

                        result.add(world);
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            return result;
        }
    }
}
