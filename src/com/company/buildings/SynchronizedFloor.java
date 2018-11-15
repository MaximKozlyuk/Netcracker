package com.company.buildings;

import java.util.Iterator;

public class SynchronizedFloor implements Floor  {

    private Floor floor;

    public SynchronizedFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public synchronized int amount() {
        return floor.amount();
    }

    @Override
    public synchronized double totalArea() {
        return floor.totalArea();
    }

    @Override
    public synchronized int totalRoomAmount() {
        return floor.totalRoomAmount();
    }

    @Override
    public synchronized Space[] toArray() throws CloneNotSupportedException {
        return floor.toArray();
    }

    @Override
    public synchronized Space getSpace(int n) throws CloneNotSupportedException {
        return floor.getSpace(n);
    }

    @Override
    public synchronized Space setSpace(int n, Space s) {
        return floor.setSpace(n, s);
    }

    @Override
    public synchronized void addSpace(Space s, int n) {
        floor.addSpace(s, n);
    }

    @Override
    public synchronized void removeSpace(int n) {
        floor.removeSpace(n);
    }

    @Override
    public synchronized Space getBestSpace() {
        return floor.getBestSpace();
    }

    @Override
    public synchronized Iterator<Space> iterator() {
        return floor.iterator();
    }

    @Override
    public synchronized Object clone() {
        return floor.clone();
    }

    @Override
    public synchronized int hashCode() {
        return super.hashCode();
    }

    @Override
    public synchronized boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public synchronized String toString() {
        return super.toString();
    }
}
