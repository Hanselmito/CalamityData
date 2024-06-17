package com.github.Hanselmito.Model.Dao;

import com.github.Hanselmito.Model.Conection.ConnectionMariaDB;
import com.github.Hanselmito.Model.Entity.Enums.TipeClass;
import com.github.Hanselmito.Model.Entity.Enums.TipeObject;
import com.github.Hanselmito.Model.Entity.object;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObjectDAO implements DAO<object>{
    private final static String INSERT="INSERT INTO Object (IDObject,IDWorld,NameObject,TipeObject,Effect,TipeClass) VALUES (?,?,?,?,?,?)";
    private final static String UPDATE="UPDATE Object SET IDWorld=?,NameObject=?,TipeObject=?,Effect=?,TipeClass=? WHERE IDObject=?";
    private final static String FINDBYID="SELECT o.IDObject,o.IDWorld,o.NameObject,o.TipeObject,o.Effect,o.TipeClass FROM Object AS o WHERE o.IDObject=?";
    private final static String FINDALL="SELECT IDObject,IDWorld,NameObject,TipeObject,Effect,TipeClass FROM Object";
    private final static String FINDBYTIPEOBJECT="SELECT o.IDObject,o.IDWorld,o.NameObject,o.TipeObject,o.Effect,o.TipeClass FROM Object AS o WHERE o.TipeObject=?";
    private final static String FINDBYTIPECLASS="SELECT o.IDObject,o.IDWorld,o.NameObject,o.TipeObject,o.Effect,o.TipeClass FROM Object AS o WHERE o.TipeClass=?";
    private final static String FIND_OBJECTS_FOR_WORLD = "SELECT * FROM Object WHERE IDWorld=?";
    private final static String DELETE="DELETE FROM Object WHERE IDObject=?";

    private Connection conn;
    public ObjectDAO() {
        conn = ConnectionMariaDB.getConnection();
    }

    @Override
    public object save(object entity) {
        object result = entity;
        if (entity==null || entity.getIDObject()==0) return result;
        object o = findById(entity.getIDObject());
        if (o!=null){
            //insert
            try (PreparedStatement pst = conn.prepareStatement(INSERT)){
                pst.setInt(1,entity.getIDObject());
                pst.setInt(2,entity.getWorld().getIDWorld());
                pst.setString(3,entity.getNameObject());
                pst.setString(4,entity.getTipeObject().getDate());
                pst.setString(5, entity.getEffect());
                pst.setString(6,entity.getTipeClass().getName());
                pst.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public object update(object entity) {
        object result = entity;
        if (entity==null || entity.getIDObject()==0) return result;
        try (PreparedStatement pst = conn.prepareStatement(UPDATE)){
            pst.setInt(1,entity.getWorld().getIDWorld());
            pst.setString(2,entity.getNameObject());
            pst.setString(3,entity.getTipeObject().getDate());
            pst.setString(4, entity.getEffect());
            pst.setString(5,entity.getTipeClass().getName());
            pst.setInt(6,entity.getIDObject());
            pst.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public object delete(object entity) throws SQLException {
        if (entity == null || entity.getIDObject()==0)return entity;
        try (PreparedStatement pst = conn.prepareStatement(DELETE)){
            pst.setInt(1,entity.getIDObject());
            pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }


    @Override
    public object findById(int key) {
        object result =new object();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYID)){
            pst.setString(1, String.valueOf(key));
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    result.setIDObject(res.getInt("IDObject"));
                    result.setWorld(WorldDAO.build().findById(res.getInt("IDWorld")));
                    result.setNameObject(res.getString("NameObject"));
                    result.setTipeObject(TipeObject.valueOf(res.getString("TipeObject")));
                    result.setEffect(res.getString("Effect"));
                    result.setTipeClass(TipeClass.valueOf(res.getString("TipeClass")));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public List<object> findAll() {
        List<object> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL)){
            ResultSet res = pst.executeQuery();
            while(res.next()){
                object objects = new object();
                objects.setIDObject(res.getInt("IDObject"));
                objects.setWorld(WorldDAO.build().findById(res.getInt("IDWorld")));
                objects.setNameObject(res.getString("NameObject"));
                objects.setTipeObject(TipeObject.valueOf(res.getString("TipeObject")));
                objects.setEffect(res.getString("Effect"));
                objects.setTipeClass(TipeClass.valueOf(res.getString("TipeClass")));
                result.add(objects);
            }
            res.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<object> findByTipeObject(String key) {
        List<object> result = new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYTIPEOBJECT)){
            pst.setString(1, String.valueOf(key));
            try(ResultSet res = pst.executeQuery()){
                while(res.next()){
                    object objects = new object();
                    objects.setIDObject(res.getInt("IDObject"));
                    objects.setWorld(WorldDAO.build().findById(res.getInt("IDWorld")));
                    objects.setNameObject(res.getString("NameObject"));
                    objects.setTipeObject(TipeObject.valueOf(res.getString("TipeObject")));
                    objects.setEffect(res.getString("Effect"));
                    objects.setTipeClass(TipeClass.valueOf(res.getString("TipeClass")));
                    result.add(objects);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<object> findByTipeClass(String key) {
        List<object> result = new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYTIPECLASS)){
            pst.setString(1, String.valueOf(key));
            try(ResultSet res = pst.executeQuery()){
                while(res.next()){
                    object objects = new object();
                    objects.setIDObject(res.getInt("IDObject"));
                    objects.setWorld(WorldDAO.build().findById(res.getInt("IDWorld")));
                    objects.setNameObject(res.getString("NameObject"));
                    objects.setTipeObject(TipeObject.valueOf(res.getString("TipeObject")));
                    objects.setEffect(res.getString("Effect"));
                    objects.setTipeClass(TipeClass.valueOf(res.getString("TipeClass")));
                    result.add(objects);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<object> FindObjectForWorld(int IDWorld) {
        List<object> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FIND_OBJECTS_FOR_WORLD)) {
            pst.setInt(1, IDWorld);
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    object obj = new object();
                    obj.setIDObject(res.getInt("IDObject"));
                    obj.setWorld(WorldDAO.build().findById(res.getInt("IDWorld")));
                    obj.setNameObject(res.getString("NameObject"));
                    obj.setTipeObject(TipeObject.valueOf(res.getString("TipeObject")));
                    obj.setEffect(res.getString("Effect"));
                    obj.setTipeClass(TipeClass.valueOf(res.getString("TipeClass")));
                    result.add(obj);
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

    public static ObjectDAO build(){return new ObjectDAO();}

}
