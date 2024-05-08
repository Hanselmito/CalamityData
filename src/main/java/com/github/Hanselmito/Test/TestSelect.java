package com.github.Hanselmito.Test;

import com.github.Hanselmito.Model.Dao.BiomeDAO;
import com.github.Hanselmito.Model.Dao.WorldDAO;
import com.github.Hanselmito.Model.Entity.Biome;
import com.github.Hanselmito.Model.Entity.World;

import java.util.List;

public class TestSelect {
    public static void main(String[] args) {
        /**WorldDAO wDAO = new WorldDAO();
        World w = wDAO.findById(2);
        System.out.println(w);**/
        /**ObjectDAO oDAO = new ObjectDAO();
        object o = oDAO.findById(2);
        System.out.println(o);**/
        BiomeDAO bDAO = new BiomeDAO();
        List<Biome> b = bDAO.findAll();
        System.out.println(b);
    }
}
