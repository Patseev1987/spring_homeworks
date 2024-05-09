package ru.homeworks.mymicroservicetools.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homeworks.mymicroservicetools.domain.Tool;
import ru.homeworks.mymicroservicetools.repository.ToolRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;

    public Tool findByCode(String code) {
        return toolRepository.findByCode(code).orElse(null);
    }

    public List<Tool> findAll() {
        return toolRepository.findAll();
    }
    public Tool save(Tool tool) {
        return toolRepository.save(tool);
    }
    public void delete(Tool tool) {
        toolRepository.delete(tool);
    }
    public Tool update(String code,Tool tool) {
        var oldTool = findByCode(code);
        oldTool.setCode(tool.getCode());
        oldTool.setName(tool.getName());
        return toolRepository.save(oldTool);
    }

}
