package com.br.scheduler.scheduled_service;

import com.br.scheduler.model.Heap;
import com.br.scheduler.model.Process;
import com.br.scheduler.model.Scheduler;
import com.br.scheduler.service.ProcessService;
import com.br.scheduler.util.RandomNameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.*;

@Service
public class SchedulerScheduledService {

    @Autowired
    Scheduler scheduler;

    @Autowired
    ProcessService processService;

    @Scheduled(fixedRate = 3 * 1000)
    public void process() {
        Process process = scheduler.getNextProcess();

        if (process == null) {
            scheduler.updateHeap(new Heap(processService.findAll()));
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(process);

        long startTime = System.currentTimeMillis();
        long endTime;
        try {
            System.out.print("Começou ... ");
            startTime = System.currentTimeMillis();

            future.get(3000, TimeUnit.MILLISECONDS);

            endTime = System.currentTimeMillis();
            System.out.println("Terminou após " + (endTime - startTime) + " milissegundos");

            long duration = (endTime - startTime);
            updateProcess(process, duration);

            process();
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            future.cancel(true);
            endTime = System.currentTimeMillis();
            System.out.println("Cancelou após " + (endTime - startTime) + " milissegundos");

            long duration = (endTime - startTime);
            updateProcess(process, duration);
        } finally {
            executor.shutdownNow();
        }
    }

    @Scheduled(fixedRate = 10 * 1000)
    public void addProcess() {
//        Random random = new Random();
//
//        if (processService.findAll().size() >= 20) return;
//
//        for (int i = 0; i < 2; i++) {
//            int randomMilli = random.nextInt(3000, 20000);
//            String randomName = RandomNameGenerator.generate();
//
//            processService.save(new Process(randomName, randomMilli));
//        }
    }

    private void updateProcess(Process process, long duration) {
        process.setMillisLeftToProcess(process.getMillisLeftToProcess() - duration);

        if (process.getMillisLeftToProcess() <= 0) {
            processService.delete(process);
        } else {
            processService.save(process);
        }

        scheduler.updateHeap(new Heap(processService.findAll()));

        scheduler.print();
    }
}
