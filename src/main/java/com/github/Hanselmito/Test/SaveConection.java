package com.github.Hanselmito.Test;

import com.github.Hanselmito.Model.Conection.ConnectionProperties;
import com.github.Hanselmito.Utils.XMLmanager;

public class SaveConection {
    public static void main(String[] args) {
        ConnectionProperties c = new ConnectionProperties("localhost","3306","WikiCalamity","root","root");
        XMLmanager.writeXML(c,"Connection.xml");
    }
}
