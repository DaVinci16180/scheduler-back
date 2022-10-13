package com.br.scheduler.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduler {
    Heap priorityQueue = new Heap();

    public void addProcess(Process process) {
        priorityQueue.add(process);
        priorityQueue.print();
    }

    public Process getNextProcess() {
        return priorityQueue.remove();
    }

    public Process peek() {
        return priorityQueue.peek();
    }

    public int queueSize() {
        return priorityQueue.size();
    }

    public List<Process> getPriorityList() {
        return priorityQueue.getList();
    }

    public void updateHeap(Heap heap) {
        this.priorityQueue = heap;
    }

    public void print() {
        priorityQueue.print();
    }
}
