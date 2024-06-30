package com.scaler.productservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/say")
public class SampleController {

    @RequestMapping("/hello/{name}/{times}")
    public String sayHello(@PathVariable("name") String name, @PathVariable ("times") int times)
    {
        String output="";
        for (int i = 0; i < times; i++)
            output += "Hello " + name+ " " ;
        return output;
    }

    @GetMapping("/bye")
    public String saybye()
    {
        return "bye";
    }

}
