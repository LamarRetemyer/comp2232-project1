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

    public void addSegment(String sname, String start, String sEnd){
        stations.stream().filter(station -> station.getName().equals(start));
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

    public void addRoute(String rName, boolean isRoundTrip, List<Segment> rStations) {
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
        trains.add(new Train());//need to add an id
    }

    public void removeTrain(int id) {
        trains.removeIf(train -> train.getId() == id);
    }

    public void registerTrain(int trainId, String routeName) {
        trains.stream().filter(train -> train.getId() == trainId).findFirst().ifPresent(train -> train.register(getRouteByName(routeName)));
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



