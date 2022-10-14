package com.br.scheduler.model;

import javax.persistence.*;

@Entity
public class Process implements Runnable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="process_generator")
    @SequenceGenerator(name="process_generator", sequenceName="process_seq", allocationSize=1)
    private long id;
    private String name;
    private String message;
    private long millisLeftToProcess;

    public Process() {}

    public Process(String name, String message, long millisLeftToProcess) {
        this.name = name;
        this.message = message;
        this.millisLeftToProcess = millisLeftToProcess;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public String getMessage() {
        return message;
    }

    public void run() {
        try {
            Thread.sleep(millisLeftToProcess);
        } catch (Exception ignored) {}
    }
}
