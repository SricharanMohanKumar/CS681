package edu.umb.cs681.hw09;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args)
    {
        double lat = 37.7749;
        double lon = -122.4194;
        double alt = 10000;
        Position initialPosition = new Position(lat, lon, alt);
        Aircraft aircraft = new Aircraft(initialPosition);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++)
        {
            executorService.submit(new AircraftPositionSetter(aircraft));
        }
        executorService.shutdown();
        List<Runnable> runnables = new ArrayList<>();
        runnables.add(() -> {
            aircraft.setPosition(37.6152, -122.3899, 15000);
            Position currentPosition = aircraft.getPosition();
            System.out.println("New aircraft position: " + currentPosition);
            System.out.println("New aircraft coordinates: " + currentPosition.coordinate());
        });
        Position otherPosition = new Position(37.7380, -122.3732, 12000);
        runnables.add(() -> {
            boolean higherAlt = initialPosition.higherAltThan(otherPosition);
            boolean lowerAlt = initialPosition.lowerAltThan(otherPosition);
            System.out.println("Is initial position higher than other position? " + higherAlt);
            System.out.println("Is initial position lower than other position? " + lowerAlt);
        });
        runnables.add(() -> {
            boolean northOf = initialPosition.northOf(otherPosition);
            boolean southOf = initialPosition.southOf(otherPosition);
            System.out.println("Is initial position north of other position? " + northOf);
            System.out.println("Is initial position south of other position? " + southOf);
        });
        runnables.add(() -> {
            Position newPosition = initialPosition.change(initialPosition.latitude(), initialPosition.longitude(), 20000);
            System.out.println("New position with updated altitude: " + newPosition);
            System.out.println("New position coordinates: " + newPosition.coordinate());
        });
        List<Thread> threads = new ArrayList<>();
        for (Runnable runnable : runnables) {
            threads.add(new Thread(runnable));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads)
        {
            try
            {
                thread.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static class AircraftPositionSetter implements Runnable
    {

        private final Aircraft aircraft;

        public AircraftPositionSetter(Aircraft aircraft)
        {
            this.aircraft = aircraft;
        }

        @Override
        public void run()
        {
            Position currentPosition = aircraft.getPosition();
            Thread writerThread = new Thread(() -> {
                for (int i = 0; i < 10; i++)
                {
                    aircraft.setPosition(37.6152, -122.3899, 15000);
                }
            });
            currentPosition = aircraft.getPosition();
            System.out.println("New aircraft position: " + currentPosition);
            System.out.println("New aircraft coordinates: " + currentPosition.coordinate());
        }
    }
}
