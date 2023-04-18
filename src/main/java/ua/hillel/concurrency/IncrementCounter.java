package ua.hillel.concurrency;

public class IncrementCounter {

    /*
    * CPU - 1 lvl cache, 2 lvl cache, 3 lvl cache > RAM
    * volatile write to the RAM, but not to the cache
    * CPU 4-core
    * 1 core - 10 -> RAM
    * 2 core - 20 -> RAM
    *
    * */
    // volatile - write happens before the next read (its guaranteed by the JVM)
    private int count;

    // mutex - монітор, t1 acquired mutex, so t2 cannot enter to method increment
    public synchronized void increment() {
        count++; // write
        // atomicity -> 1. count 2. count + 1 3. count = count + 1
        // atomicity -> -- all operations runs in a single context --
    }

    public int getCount() {
        return count; // read
    }
}
