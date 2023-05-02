package controller;

import org.springframework.web.bind.annotation.*;

@RestController //
@RequestMapping("/televisions") //root path
public class TelevisionController {

    //lijst met alle tv's
    @GetMapping

    //lijst met een enkele tv, op id
    @GetMapping("/{id}")

    //tv toevoegen
    @PostMapping("/{id}")

    //tv aanpassen
    @PutMapping("/{id}")

    //tv verwijderen
    @DeleteMapping("/{id}")


}
