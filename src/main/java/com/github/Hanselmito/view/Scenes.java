package com.github.Hanselmito.view;

public enum Scenes {
    ROOT("view/layout.fxml"),
    MENU("view/Menu.fxml"),
    ADMIN("view/Admin.fxml"),
    WIKICONTROLLER("view/WikiController.fxml"),
    WCONTROLLER("view/WController.fxml"),
    OCONTROLLER("view/OController.fxml"),
    BCONTROLLER("view/BController.fxml"),
    ECONTROLLER("view/EController.fxml"),
    WLIST("view/WorldList.fxml"),
    OLIST("view/ObjectList.fxml"),
    BLIST("view/BiomeList.fxml"),
    ELIST("view/EnemysList.fxml");

    private String url;
    Scenes(String url){
        this.url=url;
    }
    public String getURL(){
        return url;
    }

}
