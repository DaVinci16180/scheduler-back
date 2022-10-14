package com.br.scheduler.controller;

import com.br.scheduler.model.Heap;
import com.br.scheduler.model.Process;
import com.br.scheduler.model.Scheduler;
import com.br.scheduler.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    Scheduler scheduler;

    @Autowired
    ProcessService processService;

    @GetMapping("/priorityList")
    public List<Process> getPriorityList() {
        Heap heap = new Heap(processService.findAll());
        return heap.getList();
    }

    @GetMapping("/executingNow")
    public Process getProcessExecutingNow() {
        Heap heap = new Heap(processService.findAll());
        return heap.peek();
    }

    @GetMapping("/createProcesses")
    public void createProcesses(@RequestParam("amount") int amount) {
        processService.createMultipleProcesses(amount);
    }
}
