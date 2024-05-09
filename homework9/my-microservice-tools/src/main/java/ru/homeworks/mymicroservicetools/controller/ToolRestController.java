package ru.homeworks.mymicroservicetools.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.homeworks.mymicroservicetools.domain.Tool;
import ru.homeworks.mymicroservicetools.service.ToolService;

import java.util.List;

@RestController
@RequestMapping("/tools")
@RequiredArgsConstructor
public class ToolRestController {
    private final ToolService toolService;
    @GetMapping()
    public List<Tool> getAllTools(){
        return toolService.findAll();
    }
    @GetMapping("/{code}")
    public Tool getToolByCode(@PathVariable String code){
        return toolService.findByCode(code);
    }
    @DeleteMapping("/delete/{code}")
    public void deleteTool(@PathVariable String code){
        var tool = toolService.findByCode(code);
        toolService.delete(tool);
    }
    @PutMapping("/update")
    public Tool updateTool(@RequestBody Tool tool, @RequestParam String code){
        return toolService.update(code, tool);
    }
    @PostMapping("/create")
    public Tool createTool(@RequestBody Tool tool){
        return toolService.save(tool);
    }
}
