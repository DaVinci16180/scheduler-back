package com.br.scheduler.model;

import javax.persistence.*;

@Entity
public class Process implements Runnable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="process_generator")
    @SequenceGenerator(name="process_generator", sequenceName="process_seq", allocationSize=1)
    private long id;
    private String name;
    private long millisLeftToProcess;

    public Process() {}

    public Process(String name, long millisLeftToProcess) {
        this.name = name;
        this.millisLeftToProcess = millisLeftToProcess;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMillisLeftToProcess() {
        return millisLeftToProcess;
    }

    public void setMillisLeftToProcess(long millisLeftToProcess) {
        if (millisLeftToProcess < 0)
            this.millisLeftToProcess = 0;
        else
            this.millisLeftToProcess = millisLeftToProcess;
    }

    public void run() {
        try {
            Thread.sleep(millisLeftToProcess);
        } catch (Exception ignored) {}
    }
}
