package com.github.Hanselmito.Test;

import com.github.Hanselmito.Model.Dao.BiomeDAO;
import com.github.Hanselmito.Model.Dao.EnemysDAO;
import com.github.Hanselmito.Model.Dao.ObjectDAO;
import com.github.Hanselmito.Model.Dao.WorldDAO;
import com.github.Hanselmito.Model.Entity.Biome;
import com.github.Hanselmito.Model.Entity.Enemys;
import com.github.Hanselmito.Model.Entity.Enums.*;
import com.github.Hanselmito.Model.Entity.World;
import com.github.Hanselmito.Model.Entity.object;
import com.github.Hanselmito.Utils.ImagenAByts;

import java.sql.SQLException;
import java.util.ArrayList;

public class Test1Insert {
    public static void main(String[] args) throws SQLException {
        byte[] imageBytes = ImagenAByts.imageToBytes("src/main/resources/Icons/2slime.png");
        /**World w = new World(9, Dificulty.Post_Moonlord, SizeWorld.Big,new ArrayList<>(),new ArrayList<>());
         WorldDAO wDAO = new WorldDAO();
         wDAO.save(w);**/
        /**WorldDAO wDAO = new WorldDAO();
        World world = wDAO.findById(1);*/
        BiomeDAO bDAO = new BiomeDAO();
        Biome biome = bDAO.findById(1);

        /**object o = new object(1,world,"Fathom Swarmer armor", TipeObject.Armor,
                "Incremento del 10% al daño de invocador y +2 súbditos máximos. Otorga la habilidad de escalar paredes. Incremento del 30% al daño de invocador mientras se esté sumergido en líquidos. Provee una cantidad moderada de luz y reduce la perdida aire en el abismo.",
                TipeClass.Summoner);
        ObjectDAO oDAO = new ObjectDAO();
        oDAO.update(o);**/
        /**Biome b = new Biome(2,world,"Hell", ZoneGenerate.Under,Dificulty.hardmode,new ArrayList<>());
        BiomeDAO bDAO = new BiomeDAO();
        bDAO.save(b);**/
        Enemys enemys = new Enemys(4,biome,TipeEnemies.Bosses,"he Slime God",Dificulty.Pre_Hardmode,imageBytes);
        EnemysDAO enDAO = new EnemysDAO();
        enDAO.update(enemys);
    }
}
