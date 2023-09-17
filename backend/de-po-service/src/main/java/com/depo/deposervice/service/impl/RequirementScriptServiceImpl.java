package com.depo.deposervice.service.impl;

import com.depo.deposervice.collection.RequirementScript;
import com.depo.deposervice.dto.RequirementScriptExecuteDto;
import com.depo.deposervice.exception.RequirementScriptNotFoundException;
import com.depo.deposervice.repository.RequirementScriptRepository;
import com.depo.deposervice.service.RequirementScriptService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class RequirementScriptServiceImpl implements RequirementScriptService {

    private final RequirementScriptRepository repository;

    public RequirementScriptServiceImpl(RequirementScriptRepository repository) {
        this.repository = repository;
    }


    @Override
    public RequirementScript getById(String id) {
        return repository.findById(id).orElseThrow(RequirementScriptNotFoundException::new);
    }

    @Override
    public RequirementScript save(RequirementScript requirementScript) {
        return repository.save(requirementScript);
    }

    @Override
    public String executeScript(String id, RequirementScriptExecuteDto dto) {
        RequirementScript script = this.getById(id);
        String scriptText = script.getScript();
        for (Map.Entry<String, String> entry : dto.getPlaceholders().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            scriptText = scriptText.replaceAll(Pattern.quote("{{" + key + "}}"), value);
        }
        int exitCode = 1;
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("bash");
                Process process = processBuilder.start();
                OutputStream scriptInput = process.getOutputStream();
                scriptInput.write((scriptText).getBytes());
                scriptInput.close();
                exitCode = process.waitFor();
                if (exitCode == 0) {
                    return "Script executed successfully.";
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return "An error occurred" + " " + exitCode;
    }

    @Override
    public void deleteScript(String id) {
        repository.deleteById(id);
    }
}
