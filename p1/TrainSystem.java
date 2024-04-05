package p1;

import java.util.*;


public class TrainSystem {
    private SystemStatus status;
    private Set<Station> stations;
    private List<Segment> segments;
    private Set<Route> routes;
    private Set<Train> trains;
    

    public TrainSystem(){
        this.status = SystemStatus.Initialised;
        this.stations = new HashSet<>();
        this.segments = new ArrayList<>();
        this.routes = new HashSet<>();
        this.trains = new HashSet<>();
    }
    
    public void addStation(String sname){        
        stations.add(new Station(sname));
    }

    public void removeStation(String sname){
        stations.removeIf(station -> station.getName().equals(sname));
    }

    public void openStation(String sname){
        stations.stream().filter(station -> station.getName().equals(sname)).forEach(Station::open);
    }

    public void closeStation(String sname){
        stations.stream().filter(station -> station.getName().equals(sname)).forEach(Station::close);
    }

    public void addSegment(String sname, Station start, Station sEnd){        
        Segment segment = new Segment(sname, start, sEnd);
        segments.add(segment);
    }

    public void removeSegment(String sname){
        segments.removeIf(segment -> segment.getName().equals(sname));
    }

    public void openSegment(String sname){
        segments.stream().filter(segment -> segment.getName().equals(sname)).forEach(Segment::open);
    }

    public void closeSegment(String sname){
        segments.stream().filter(segment -> segment.getName().equals(sname)).forEach(Segment::close);
    }

    public void addRoute(String rName, boolean isRoundTrip, List<Station> rStations) {
        routes.add(new Route(rName, isRoundTrip, rStations));
    }

    public void removeRoute(String rName) {
        routes.removeIf(route -> route.getName().equals(rName));
    }

    public void openRoute(String rName) {
        routes.stream().filter(route -> route.getName().equals(rName)).forEach(Route::open);
    }

    public void closeRoute(String rName) {
        routes.stream().filter(route -> route.getName().equals(rName)).forEach(Route::close);
    }

    // Train methods
    public void addTrain() {
        trains.add(new Train(trains.size()));//need to add an id
    }

    public void removeTrain(int id) {
        trains.removeIf(train -> train.getId() == id);
    }

    public void registerTrain(int trainId, String routeName) {
        Train train = trains.stream().filter(t -> t.getId() == trainId).findFirst().orElse(null);
        Route route = routes.stream().filter(r -> r.getName().equals(routeName)).findFirst().orElse(null);
    
        if (train != null && route != null) {
            boolean isValidJourney = true;
    
            // Check if the journey traverses any closed segments or stops at closed stations
            for (Segment segment : route.getSegments()) {
                if (!segment.isOpen() || !segment.getSEnd().isOpen()) {
                    isValidJourney = false;
                    break;
                }
            }
    
            // Check if the journey stops at stations not on the stated route
            List<Station> journeyStops = route.getStations();
            journeyStops.remove(0); // Remove the start station
            journeyStops.remove(journeyStops.size() - 1); // Remove the end station
            for (Station stop : train.getStops()) {
                if (!journeyStops.contains(stop)) {
                    isValidJourney = false;
                    break;
                }
            }
    
            if (isValidJourney) {
                // Assuming approval received 2 time units ago
                train.register(2); 
            } else {
                System.out.println("Invalid journey. Cannot register train.");
            }
        } else {
            System.out.println("Train or route not found.");
        }
    }
    
    

    public void deRegisterTrain(int trainId) {
        trains.stream().filter(train -> train.getId() == trainId).findFirst().ifPresent(Train::deregister);
    }

   public boolean containsStation(String stationName){    
    return stations.stream().anyMatch(station -> station.getName().equals(stationName));
   }

   public boolean containsSegment(String segmentName) {
    return segments.stream().anyMatch(segment -> segment.getName().equals(segmentName));
    }

    public boolean containsRoute(String routeName) {
        return routes.stream().anyMatch(route -> route.getName().equals(routeName));
    }

    public boolean containsTrain(int trainId) {
        return trains.stream().anyMatch(train -> train.getId() == trainId);
    }

    public String getStationInfo(String stationName){
        Station station = stations.stream().filter(s -> s.getName().equals(stationName)).findFirst().orElse(null);
        if (station != null) {
            return "Station Name: " + station.getName() + ", Status: " + station.getStatus();
        } else {
            return "Station not found.";
        }
    }

    public String getSegmentInfo(String segmentName) {
    Segment segment = segments.stream()
            .filter(s -> s.getName().equals(segmentName))
            .findFirst()
            .orElse(null);
    if (segment != null) {
        return "Segment Name: " + segment.getName() + ", Start: " + segment.getStart() + ", End: " + segment.getEnd() + ", Status: " + segment.getStatus();
    } else {
        return "Segment not found.";
    }
}

    public String getRouteInfo(String routeName) {
        Route route = routes.stream()
                .filter(r -> r.getName().equals(routeName))
                .findFirst()
                .orElse(null);
        if (route != null) {
            return "Route Name: " + route.getName() + ", Round Trip: " + route.isRoundTrip() + ", Stations: " + route.getStations();
        } else {
            return "Route not found.";
        }
    }

    public String getTrainInfo(int trainId) {
        Train train = trains.stream()
                .filter(t -> t.getId() == trainId)
                .findFirst()
                .orElse(null);
        if (train != null) {
            return "Train ID: " + train.getId() + ", Current Segment: " + train.getCurrentSegment() + ", Route: " + train.getRoute();
        } else {
            return "Train not found.";
        }
    }

    public void setToWorking() {
        status = SystemStatus.Operational;
    }
    
    public void setPaused() {
        status = SystemStatus.Deadlocked;
    }
    
    public void setStopped() {
        status = SystemStatus.Finished;
    }
    
    public SystemStatus currentStatus() {
        return status;
    }

    
    
}



