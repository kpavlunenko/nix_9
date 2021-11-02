package ua.com.alevel.controller;

import ua.com.alevel.service.BaseService;

public interface BaseController {

    void run();
    BaseService getService();
}
