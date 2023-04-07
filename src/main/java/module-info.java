module sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;

    opens sample to javafx.fxml;
    exports sample;
    exports controller;
    opens controller to javafx.fxml;
    exports Domain;
    opens Domain to javafx.fxml;
}