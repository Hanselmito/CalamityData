package com.github.Hanselmito.Test;

import com.github.Hanselmito.Model.Conection.ConectionPropeties;
import com.github.Hanselmito.Utils.XMLmanager;

public class SaveConection {
    public static void main(String[] args) {
        ConectionPropeties c = new ConectionPropeties("localhost","3306","WikiCalamity","root","root");
        XMLmanager.writeXML(c,"Connection.xml");
    }
}
