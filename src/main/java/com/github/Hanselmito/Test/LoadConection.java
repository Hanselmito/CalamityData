package com.github.Hanselmito.Test;

import com.github.Hanselmito.Model.Conection.ConectionPropeties;
import com.github.Hanselmito.Utils.XMLmanager;

public class LoadConection {
    public static void main(String[] args) {
        ConectionPropeties c = XMLmanager.readXML(new ConectionPropeties(),"Connection.xml");
        System.out.println(c);
    }
}
