module com.github.Hanselmito {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;
    requires javafx.media;
    requires java.desktop;

    opens com.github.Hanselmito to javafx.fxml;
    opens com.github.Hanselmito.Model.Conection to java.xml.bind;
    exports com.github.Hanselmito;
    exports com.github.Hanselmito.view;
    opens com.github.Hanselmito.view to javafx.fxml;
    exports com.github.Hanselmito.Utils;
    opens com.github.Hanselmito.Utils to javafx.fxml;
}
