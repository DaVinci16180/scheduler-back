package com.br.scheduler.model;

import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    Heap priorityQueue = new Heap();
    Process executing;

    public Process getNextProcess() {
        return priorityQueue.remove();
    }

    public void updateHeap(Heap heap) {
        this.priorityQueue = heap;
    }

    public void print() {
        priorityQueue.print();
    }

    public Process getExecuting() {
        return executing;
    }

    public void setExecuting(Process executing) {
        this.executing = executing;
    }
}
