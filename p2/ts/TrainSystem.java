package p2.ts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import p2.enums.SystemStatus;
import p2.enums.ObjectType;
import p2.logging.*;
import p2.events.*;

public class TrainSystem {

    private SystemStatus status = SystemStatus.Initialised;
    private int currentTime = -1;

    private List<Station> stations = new ArrayList<>();
    private List<Segment> segments = new ArrayList<>();
    private List<Route> routes = new ArrayList<>();
    private List<Train> trains = new ArrayList<>();
    private Map<Integer, Train> trainMap = new HashMap<>(); // Store trains by ID for quick access

    public TrainSystem() {}

    // Station methods
    public void addStation(String sname) {
        stations.add(new Station(sname));
        System.out.println("Station " + sname + " added.");
    }

    public void removeStation(String sname) {
        stations.removeIf(s -> s.getName().equals(sname));
        System.out.println("Station " + sname + " removed.");
    }

    public Event openStation(String sname) {
        // Implementation for opening a station
        Station station = stations.stream()
                                  .filter(s -> s.getName().equals(sname))
                                  .findFirst()
                                  .orElse(null);
        if (station != null) {
            return station.open();
        }
        return null;
    }

    public Event closeStation(String sname) {
        // Implementation for closing a station
        Station station = stations.stream()
                                  .filter(s -> s.getName().equals(sname))
                                  .findFirst()
                                  .orElse(null);
        if (station != null) {
            return station.close();
        }
        return null;
    }

    // Segment methods
    public void addSegment(String start, String end) {
        segments.add(new Segment(start, end));
        System.out.println("Segment from " + start + " to " + end + " added.");
    }

    public void removeSegment(String start, String end) {
        segments.removeIf(s -> s.getStart().equals(start) && s.getEnd().equals(end));
        System.out.println("Segment from " + start + " to " + end + " removed.");
    }

    public Event openSegment(String name) {
        // Implementation for opening a segment
        Segment segment = segments.stream()
                                  .filter(s -> s.getName().equals(name))
                                  .findFirst()
                                  .orElse(null);
        if (segment != null) {
            return segment.open();
        }
        return null;
    }

    public Event closeSegment(String name) {
        // Implementation for closing a segment
        Segment segment = segments.stream()
                                  .filter(s -> s.getName().equals(name))
                                  .findFirst()
                                  .orElse(null);
        if (segment != null) {
            return segment.close();
        }
        return null;
    }

    // Route methods
    public void addRoute(String rname, List<String> stations) {
        Route route = new Route(rname, stations);
        routes.add(route);
        System.out.println("Route " + rname + " added with stations " + stations.toString());
    }

    public void removeRoute(String rname) {
        routes.removeIf(r -> r.getName().equals(rname));
        System.out.println("Route " + rname + " removed.");
    }

    public Event openRoute(String rname) {
        // Implementation for opening a route
        Route route = routes.stream()
                            .filter(r -> r.getName().equals(rname))
                            .findFirst()
                            .orElse(null);
        if (route != null) {
            return route.open();
        }
        return null;
    }

    public Event closeRoute(String rname) {
        // Implementation for closing a route
        Route route = routes.stream()
                            .filter(r -> r.getName().equals(rname))
                            .findFirst()
                            .orElse(null);
        if (route != null) {
            return route.close();
        }
        return null;
    }

    // Train methods
    public void addTrain(int id, String name) {
        Train train = new Train(id, name);
        trains.add(train);
        trainMap.put(id, train);
        System.out.println("Train " + name + " added with ID " + id);
    }

    public void removeTrain(int id) {
        trains.removeIf(t -> t.getId() == id);
        trainMap.remove(id);
        System.out.println("Train with ID " + id + " removed.");
    }

    public Event registerTrain(int trainId, String routeName) {
        Train train = trainMap.get(trainId);
        Route route = routes.stream()
                            .filter(r -> r.getName().equals(routeName))
                            .findFirst()
                            .orElse(null);
        if (train != null && route != null) {
            return train.registerToRoute(route);
        }
        return null;
    }

    public Event deregisterTrain(int trainId) {
        Train train = trainMap.get(trainId);
        if (train != null) {
            return train.deregisterFromRoute();
        }
        return null;
    }

    // Validation and status methods
    public boolean verify() {
        // Validate all components
        return stations.stream().allMatch(Station::verify) &&
               segments.stream().allMatch(Segment::verify) &&
               routes.stream().allMatch(Route::verify) &&
               trains.stream().allMatch(Train::verify);
    }

    public SystemStatus currentStatus() {
        return status;
    }

    public void advanceTime() {
        currentTime++;
        System.out.println("Time advanced to " + currentTime);
    }

    public void resetSystem() {
        status = SystemStatus.Initialised;
        currentTime = 0;
        System.out.println("System reset to initial state.");
    }
}
