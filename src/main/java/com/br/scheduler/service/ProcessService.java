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

    public void save(Process process) {
        processRepository.save(process);
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
            int randomMilli = random.nextInt(1000, 20000);
            String randomName = RandomNameGenerator.generate();
            String genericMessage = "Hi, I'm process " + randomName;
            save(new Process(randomName, genericMessage, randomMilli));
        }
    }
}
