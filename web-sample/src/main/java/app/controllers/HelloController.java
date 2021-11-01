package app.controllers;


import nanonout.endpointrouting.ControllerBase;
import nanonout.endpointrouting.annotations.Delete;
import nanonout.endpointrouting.annotations.Get;
import nanonout.endpointrouting.annotations.Post;
import nanonout.endpointrouting.annotations.Put;

public class HelloController  extends ControllerBase {
    @Get(path = "/hello")
    public String get() {
        return "welcome";
    }
    @Post(path = "/hello")
    public String post(String name){
        return "this is post "+name;
    }
    @Put(path = "/helloupdate")
    public String put(String name){
        return "this is post "+name;
    }
    @Delete(path = "/helloDelete")
    public String delete(String name){
        return "this is post "+name;
    }

}
