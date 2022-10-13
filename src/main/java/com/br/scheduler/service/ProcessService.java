package com.br.scheduler.service;

import com.br.scheduler.model.Process;
import com.br.scheduler.repository.ProcessRepository;
import com.br.scheduler.util.RandomNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ProcessService {

    @Autowired
    private ProcessRepository processRepository;

    public Process save(Process process) {
        return processRepository.save(process);
    }

    public List<Process> findAll() {
        return processRepository.findAll();
    }

    public void delete(Process process) {
        processRepository.delete(process);
    }

    public void createMultipleProcesses(int amount) {
        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            int randomMilli = random.nextInt(3000, 20000);
            String randomName = RandomNameGenerator.generate();

            save(new Process(randomName, randomMilli));
        }
    }
}
