package com.java.FxGui;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FxGuiApplication extends Application {

    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        launch();
        SpringApplication.run(FxGuiApplication.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        context = SpringApplication.run(FxGuiApplication.class);
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/java/FxGui/main.fxml"));
        fxml.setControllerFactory(context::getBean);
        Scene scene = new Scene(fxml.load());
        stage.setTitle("* C R U D - JAVA FX Y SRPINNG BOOT *");
        stage.setScene(scene);
        stage.show();
    }

}