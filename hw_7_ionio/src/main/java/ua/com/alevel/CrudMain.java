package ua.com.alevel;

import ua.com.alevel.controller.impl.DBControllerImpl;

public class CrudMain {

    public static void main(String[] args) {
        new DBControllerImpl().run();
    }
}
