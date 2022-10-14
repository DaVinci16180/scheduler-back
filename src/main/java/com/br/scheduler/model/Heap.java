package com.br.scheduler.model;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    List<Process> list = new ArrayList<>();

    public Heap() {}

    public Heap(List<Process> list) {
        this.list = list;
        buildHeap();
    }

    private void switchPlaces(int index1, int index2) {
        Process temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    public void add(Process data) {
        if (list.contains(data)) return;

        list.add(data);

        if (list.size() == 1) return;

        ascend(list.size() - 1);
    }

    public Process remove() {
        if (list.size() == 0) return null;

        Process pop = list.get(0);
        switchPlaces(0, list.size() - 1);
        list.remove(list.size() - 1);
        descend(0, list.size());

        return pop;
    }

    private void ascend(int index) {
        int father = (index - 1) / 2;

        if (index > 0 && list.get(index).getMillisLeftToProcess() < list.get(father).getMillisLeftToProcess()) {
            switchPlaces(index, father);
            ascend(father);
        }
    }

    private void descend(int index, int size) {
        int son = index * 2 + 1;

        if (son < size) {
            if (son < size - 1 && list.get(son).getMillisLeftToProcess() > list.get(son + 1).getMillisLeftToProcess()) {
                son++;
            }

            if (list.get(index).getMillisLeftToProcess() > list.get(son).getMillisLeftToProcess()) {
                switchPlaces(index, son);
                descend(son, size);
            }
        }
    }

    private void buildHeap() {
        for (int i = (list.size() - 1) / 2; i >= 0; i--) {
            descend(i, list.size());
        }
    }

    public void print() {
        int tabs = (int) Math.ceil(Math.log(list.size()));
        int i = 1;
        int doubleI = 1;

        printTabs(tabs);

        for (var row : list) {
            System.out.print("[" + row.getMillisLeftToProcess() + "]");
            if (i == doubleI) {
                tabs--;
                doubleI *= 2;
                System.out.print('\n');
                printTabs(tabs);
                i = 0;
            }

            i++;
        }

        System.out.println('\n');
    }

    private void printTabs(int tabs) {
        for (int i = 0; i < tabs; i++) {
            System.out.print("     ");
        }
    }

    public int size() {
        return list.size();
    }

    public List<Process> getList() {
        return list;
    }
}
