package p2;

import java.util.*;

import p2.enums.SystemStatus;

public class TrainSystem {
    private String name;
    private List<Route> trainRoutes;
    private List<Train> trains;
    private List<Station> stations;
    private List<Segment> segments; // Added segments list
    private Map<Integer, Train> trainMap; // To store trains by ID
    private SystemStatus systemStatus; // Added field for system status

    public TrainSystem(String name) {
        this.name = name;
        this.trainRoutes = new ArrayList<>();
        this.trains = new ArrayList<>();
        this.stations = new ArrayList<>();
        this.segments = new ArrayList<>(); // Initialize segments list
        this.trainMap = new HashMap<>();
        this.systemStatus = SystemStatus.Initialised; // Initialize system status
    }
    
    // Station-related methods
    public void addStation(String sname) {
        Station newStation= new Station(sname);
        stations.add(newStation);
        System.out.println("Station " + sname + " added to " + name);      
    }

    public void removeStation(String sname) {
        for (Station station : stations) {
            if (station.getName().equals(sname)) {
                stations.remove(station);
                System.out.println("Station " + sname + " removed from " + name);
                return;
            }
        }
        System.out.println("Station " + sname + " not found.");
    }
        
    public void openStation(String sname) {
        for (Station station : stations) {
            if (station.getName().equals(sname)) {
                station.open();
                System.out.println("Station " + sname + " is now open.");
                return;
            }
        }
        System.out.println("Station " + sname + " not found.");
    }
    

    public void closeStation(String sname) {
        for (Station station : stations) {
            if (station.getName().equals(sname)) {
                station.close(); // Assuming Station class has a close method
                System.out.println("Station " + sname + " closed successfully.");
                return;
            }
        }
        System.out.println("Station " + sname + " not found.");
    }
    

    // Segment-related methods
    public void addSegment(String sname, String start, String sEnd) {
        // Find the route that corresponds to the given route name (sname)
        // Route targetRoute = null;
        // for (Route route : trainRoutes) {
        //     if (route.getName().equals(sname)) {
        //         targetRoute = route;
        //         break;
        //     }
        // }
    
        // if (targetRoute == null) {
        //     System.out.println("Route " + sname + " not found.");
        //     return;
        // }
    
        // Create new segment based on start and end station names
        Segment newSegment = new Segment(start + "->" + sEnd); // Assuming Segment constructor takes segment name
    
        // Add the segment to the route
        segments.add(newSegment); // Assuming addSegment method in Route class
    
        System.out.println("Segment added to route " + sname + ": " + newSegment.getName());
    }
    

    public void removeSegment(String sname) {
        // Find the segment in the list of segments
        Segment targetSegment = null;
        for (Segment segment : segments) {
            if (segment.getName().equals(sname)) {
                targetSegment = segment;
                break;
            }
        }
    
        if (targetSegment == null) {
            System.out.println("Segment " + sname + " not found.");
            return;
        }
    
        // Remove the segment from the segments list
        segments.remove(targetSegment);
    
        System.out.println("Segment " + sname + " removed.");
    }
    
    

    public void openSegment(String sname) {
        // Find the segment in the list of segments
        Segment targetSegment = null;
        for (Segment segment : segments) {
            if (segment.getName().equals(sname)) {
                targetSegment = segment;
                break;
            }
        }
    
        if (targetSegment == null) {
            System.out.println("Segment " + sname + " not found.");
            return;
        }
    
        // Open the segment
        targetSegment.open();
    
        System.out.println("Segment " + sname+ " opened.");
    }
    
    

    public void closeSegment(String sname) {
        // Find the segment in the list of segments
        Segment targetSegment = null;
        for (Segment segment : segments) {
            if (segment.getName().equals(sname)) {
                targetSegment = segment;
                break;
            }
        }
    
        if (targetSegment == null) {
            System.out.println("Segment " + sname + " not found.");
            return;
        }
    
        // Close the segment
        targetSegment.close();
    
        System.out.println("Segment " + sname + " closed.");
    }
    
    

    // Route-related methods
    public void addRoute(String rName, boolean isRoundTrip, List<String> rStations) {
        // Create a new Route object
        Route newRoute = new Route(rName, isRoundTrip);
    
        // Add stations to the route
        for (String stationName : rStations) {
            Station station = getStationByName(stationName);
            if (station != null) {
                newRoute.addStation(station);
            } else {
                System.out.println("Station " + stationName + " not found.");
            }
        }
    
        // Add the new route to the trainRoutes list
        trainRoutes.add(newRoute);
    
        System.out.println("Route " + rName + " added successfully.");
    }
    
    

    public boolean removeRoute(String sname) {
        // Implement logic to remove a route
        // ...
        return false;
    }
    public void openRoute(String sname) {
        for (Route route : trainRoutes) {
            if (route.getName().equals(sname)) {
                route.open();
                System.out.println("Route " + sname + " is now open.");
                return;
            }
        }
        System.out.println("Route " + sname + " not found.");
    }

    public void closeRoute(String sname) {
        for (Route route : trainRoutes) {
            if (route.getName().equals(sname)) {
                route.close();
                System.out.println("Route " + sname + " is now closed.");
                return;
            }
        }
        System.out.println("Route " + sname + " not found.");
    }

    // Train-related methods
    public void addTrain() {
        // Generate a unique ID for the new train
        int newTrainId = generateUniqueId();
    
        // Create a new Train object with the generated ID
        Train newTrain = new Train(newTrainId);
    
        // Add the new train to the trains list
        trains.add(newTrain);
    
        // Optionally, add the train to the trainMap for tracking by ID
        trainMap.put(newTrainId, newTrain);
    
        System.out.println("Train with ID " + newTrainId + " added successfully.");
    }
    
    private int generateUniqueId() {
        // Logic to generate a unique ID, such as incrementing a counter or using a UUID
        // For simplicity, we'll use a counter in this example
        return trains.size() + 1; // Incrementing the size of the trains list
    }
    

    public void removeTrain(int id) {
        for (Train train : trains) {
            if (train.getId() == id) {
                trains.remove(train);
                System.out.println("Train with ID " + id + " removed.");
                return;
            }
        }
        System.out.println("Train with ID " + id + " not found.");
    }
    

    public void registerTrain(int trainId, String routeName) {
        // Find the train with the given ID
        Train trainToRegister = null;
        for (Train train : trains) {
            if (train.getId() == trainId) {
                trainToRegister = train;
                break;
            }
        }
    
        if (trainToRegister == null) {
            System.out.println("Train with ID " + trainId + " not found.");
            return;
        }
    
        // Find the route with the given name
        Route targetRoute = null;
        for (Route route : trainRoutes) {
            if (route.getName().equals(routeName)) {
                targetRoute = route;
                break;
            }
        }
    
        if (targetRoute == null) {
            System.out.println("Route " + routeName + " not found.");
            return;
        }
    
        // Associate the train with the route
        trainToRegister.changeRoute(targetRoute);
        System.out.println("Train with ID " + trainId + " registered to route " + routeName);
    }
    

    public void deRegisterTrain(int trainId) {
        // Find the train with the given ID
        Train trainToDeRegister = null;
        for (Train train : trains) {
            if (train.getId() == trainId) {
                trainToDeRegister = train;
                break;
            }
        }
    
        if (trainToDeRegister == null) {
            System.out.println("Train with ID " + trainId + " not found.");
            return;
        }
    
        // Deregister the train by setting its current route to null
        trainToDeRegister.changeRoute(null);
        System.out.println("Train with ID " + trainId + " deregistered successfully.");
    }
    

    public boolean containsStation(String station) {
        for (Station s : stations) {
            if (s.getName().equals(station)) {
                return true;
            }
        }
        return false;
    }

    // Check if a segment exists in the system
    public boolean containsSegment(String segment) {
        for (Route route : trainRoutes) {
            if (route.containsSegment(segment)) {
                return true;
            }
        }
        return false;
    }

    // Check if a route exists in the system
    public boolean containsRoute(String route) {
        for (Route r : trainRoutes) {
            if (r.getName().equals(route)) {
                return true;
            }
        }
        return false;
    }

    // Check if a train exists in the system
    public boolean containsTrain(int trainId) {
        return trainMap.containsKey(trainId);
    }

    public Station getStationByName(String stationName) {
        for (Station station : stations) {
            if (station.getName().equals(stationName)) {
                return station;
            }
        }
        return null;
    }
    // Get information about a segment
    public String getStationInfo(String stationName) {
        // Get the station by name
        Station station = getStationByName(stationName);
        
        if (station != null) {
            // Gather information about the station
            String info = "Station Name: " + station.getName() + "\n";
            info += "Status: " + station.getStatus() + "\n";
            info += "Train: " + (station.getTrain() != null ? station.getTrain().getId() : "None") + "\n";
            // Add more information if needed
            
            return info;
        } else {
            return "Station not found: " + stationName;
        }
    }
    
    public Route getRouteByName(String routeName) {
        for (Route route : trainRoutes) {
            if (route.getName().equals(routeName)) {
                return route;
            }
        }
        return null; // Route not found
    }
    
    // Get information about a route
    public String getRouteInfo(String routeName) {
        // Find the route by name
        Route route = getRouteByName(routeName);
        
        if (route != null) {
            // Gather information about the route
            String info = "Route Name: " + route.getName() + "\n";
            info += "Round Trip: " + route.isRoundTrip() + "\n";
            info += "Status: " + route.getStatus() + "\n";
            info += "Segments: " + route.getSegments().size() + "\n";
            // Add more information if needed
            
            return info;
        } else {
            return "Route not found: " + routeName;
        }
    }
    

    public String getTrainInfo(int trainId) {
        Train train = trainMap.get(trainId);
        if (train != null) {
            return "Train info for ID " + trainId + ": Registered at " + train.getTimeRegistered()
                    + ", Start Time: " + train.getStartTime() + ", Current Location: " + train.getCurrentLocation().getName();
        } else {
            return "Train with ID " + trainId + " not found.";
        }
    }
    
    public boolean verify() {
        for (Station station : stations) {
            if (!station.verify()) {
                System.out.println("Verification failed for station: " + station.getName());
                return false;
            }
        }
        for (Segment segment : segments) {
            if (!segment.verify()) {
                System.out.println("Verification failed for segment: " + segment.getName());
                return false;
            }
        }
        for (Route route : trainRoutes) {
            if (!route.verify()) {
                System.out.println("Verification failed for route: " + route.getName());
                return false;
            }
        }
        for (Train train : trains) {
            if (!train.verify()) {
                System.out.println("Verification failed for train with ID: " + train.getId());
                return false;
            }
        }
        return true;
    }
    
    

    public void setToWorking() {
        this.systemStatus = SystemStatus.Operational;
        System.out.println("System status set to: " + this.systemStatus.getDescription());
    }
        

    // Set system status to paused
    public void setPaused() {        
        this.systemStatus = SystemStatus.Deadlocked;
        System.out.println("System status set to: " + this.systemStatus.getDescription());
    }    
    

    // Set system status to stopped
    public void setStopped() {
        this.systemStatus = SystemStatus.Finished;
        System.out.println("System status set to: " + this.systemStatus.getDescription());
    }

    // Get current system status
    public String currentStatus() {
        return "Current system status: " + this.systemStatus.getDescription();
    }
    

    public String getName() {
        return name;
    }

    public List<Route> getTrainRoutes() {
        return trainRoutes;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public List<Station> getStations() {
        return stations;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setTrainRoutes(List<Route> trainRoutes) {
        this.trainRoutes = trainRoutes;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
