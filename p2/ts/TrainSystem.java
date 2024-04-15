package p2.ts;

import java.util.ArrayList;
import java.util.List;
import p2.enums.SystemStatus;
import p2.events.*;
import p2.logging.*;

public class TrainSystem {

    private SystemStatus status;
    private Logger logger;
    private List<Station> stations;
    private List<Segment> segments;
    private List<Route> routes;
    private List<Train> trains;

    public TrainSystem() {
        this.status = SystemStatus.Initialised;
        this.logger = new Logger();
        this.stations = new ArrayList<>();
        this.segments = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.trains = new ArrayList<>();
        logger.log("TrainSystem initialized");
    }

    public void addStation(Station station) {
        if (!stations.contains(station)) {
            stations.add(station);
            logger.log("Station added: " + station.getName());
        }
    }

    public void removeStation(String sname) {
        stations.removeIf(station -> station.getName().equals(sname));
        logger.log("Station removed: " + sname);
    }

    public void openStation(String sname) {
        Station station = findStationByName(sname);
        if (station != null) {
            station.open();
            logger.log("Station opened: " + sname);
        }
    }

    public void closeStation(String sname) {
        Station station = findStationByName(sname);
        if (station != null) {
            station.close();
            logger.log("Station closed: " + sname);
        }
    }

    public void addSegment(Segment segment) {
        if (!segments.contains(segment)) {
            segments.add(segment);
            logger.log("Segment added: " + segment.getName());
        }
    }

    public void removeSegment(String sname) {
        segments.removeIf(segment -> segment.getName().equals(sname));
        logger.log("Segment removed: " + sname);
    }

    public void openSegment(String sname) {
        Segment segment = findSegmentByName(sname);
        if (segment != null) {
            segment.open();
            logger.log("Segment opened: " + sname);
        }
    }

    public void closeSegment(String sname) {
        Segment segment = findSegmentByName(sname);
        if (segment != null) {
            segment.close();
            logger.log("Segment closed: " + sname);
        }
    }

    public void addRoute(Route route) {
        if (!routes.contains(route)) {
            routes.add(route);
            logger.log("Route added: " + route.getName());
        }
    }

    public void removeRoute(String rName) {
        routes.removeIf(route -> route.getName().equals(rName));
        logger.log("Route removed: " + rName);
    }

    public void openRoute(String rName) {
        Route route = findRouteByName(rName);
        if (route != null) {
            route.open();
            logger.log("Route opened: " + rName);
        }
    }

    public void closeRoute(String rName) {
        Route route = findRouteByName(rName);
        if (route != null) {
            route.close();
            logger.log("Route closed: " + rName);
        }
    }

    public void addTrain(String name) {
        Train newTrain = new Train(name, trains.size() + 1);
        trains.add(newTrain);
        logger.log("Train added: ID " + newTrain.getId() + ", Name: " + name);
    }

    public void removeTrain(String name) {
        trains.removeIf(train -> train.getName().equals(name));
        logger.log("Train removed: " + name);
    }

    private Station findStationByName(String name) {
        return stations.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    private Segment findSegmentByName(String name) {
        return segments.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    private Route findRouteByName(String name) {
        return routes.stream().filter(r -> r.getName().equals(name)).findFirst().orElse(null);
    }

    public SystemStatus getStatus() {
        return status;
    }

    public void setStatus(SystemStatus status) {
        this.status = status;
        logger.log("System status updated to: " + status);
    }

    public boolean containsStation(String stationName) {
        return stations.stream().anyMatch(station -> station.getName().equals(stationName));
    }

    public boolean containsSegment(String segmentName) {
        return segments.stream().anyMatch(segment -> segment.getName().equals(segmentName));
    }

    public boolean containsRoute(String routeName) {
        return routes.stream().anyMatch(route -> route.getName().equals(routeName));
    }

    public boolean containsTrain(String trainName) {
        return trains.stream().anyMatch(train -> train.getName().equals(trainName));
    }

    public String getStationInfo(String stationName) {
        Station station = findStationByName(stationName);
        if (station != null) {
            return station.toString();
        }
        return "Station not found";
    }

    public String getSegmentInfo(String segmentName) {
        Segment segment = findSegmentByName(segmentName);
        if (segment != null) {
            return segment.toString();
        }
        return "Segment not found";
    }

    public String getRouteInfo(String routeName) {
        Route route = findRouteByName(routeName);
        if (route != null) {
            return route.toString();
        }
        return "Route not found";
    }

    public String getTrainInfo(String trainName) {
        Train train = trains.stream().filter(t -> t.getName().equals(trainName)).findFirst().orElse(null);
        if (train != null) {
            return train.toString();
        }
        return "Train not found";
    }

    public void setToWorking() {
        this.status = SystemStatus.Operational;
        logger.log("System status set to Operational");
    }

    public void setStopped() {
        this.status = SystemStatus.Finished;
        logger.log("System status set to Finished");
    }

    public SystemStatus currentStatus() {
        return this.status;
    }

    public boolean verify() {
        // Example implementation - Actual validation might depend on additional conditions
        boolean valid = true;
        for (Train train : trains) {
            if (!train.verify()) {
                valid = false;
                logger.log("Verification failed for train ID: " + train.getId());
            }
        }
        return valid;
    }

    public boolean closuresHinderingMovement() {
        for (Segment segment : segments) {
            if (!segment.isOpen()) {
                logger.log("Closed segment hindering movement: " + segment.getName());
                return true;
            }
        }
        for (Station station : stations) {
            if (!station.isOpen()) {
                logger.log("Closed station hindering movement: " + station.getName());
                return true;
            }
        }
        return false;
    }

    public List<Event> advance() {
        List<Event> events = new ArrayList<>();
        for (Train train : trains) {
            Event event = train.advance(1); // Assuming 'advance' simulates one time unit movement
            if (event != null) {
                events.add(event);
                logger.log("Advancing train ID: " + train.getId());
            }
        }
        return events;
    }

}
