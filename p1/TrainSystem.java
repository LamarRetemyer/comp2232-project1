package p1;

import java.util.*;

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
    
    /** addStation is a method that adds a new station to the route.
     * @return NONE
     * @param sname String object of the station.
     */
    public void addStation(String sname) {
        Station newStation= new Station(sname);
        stations.add(newStation);
        System.out.println("Station " + sname + " added to " + name);      
    }

    /** removeStation is a method that removes a station from the route.
     * @return NONE
     * @param sname String object of the station.
     */
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
    
    /** openStation is a method that opens a station on the route based on name. 
     * @return NONE
     * @param sname String object of the station being opened.
     */
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
    

    /** closeStation is a method that closes a station on a route.
     * @return NONE
     * @param sname The string object of the station being closed. 
     */
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
    


    public void addSegment(String sname, String start, String sEnd) {
          // Create new segment based on start and end station names
        Segment newSegment = new Segment(start + "->" + sEnd); // Assuming Segment constructor takes segment name
    
        // Add the segment to the route
        segments.add(newSegment); // Assuming addSegment method in Route class
    
        System.out.println("Segment added to route " + sname + ": " + newSegment.getName());
    }
    

    /** removeSegment is a method that removes a segment from the route
     * @return NONE
     * @param sname The string object of the station.
     */
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
    
    

    /** openSegment is a method that opens a segment on the route.
     * @return NONE
     * @param sname The string object of the segment being opened.
     */
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
    
    

    /** closeSegment is a method that closes a segment on the route.
     * @return NONE
     * @param sname The string object of the station being closed.
     */
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
    
    

    /** addRoute is a method that adds a new route and its stations to the list of routes.
     * @return NONE
     * @param rName String object of the new route.
     * @param isRoundTrip Boolean determining whether the trip is round or not.
     * @param rStations List of string objects of the new stations to be added to the route.
     */
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
    
    

    /** removeRoute is a method that removes a route from the list of routes.
     * REVIEW
     * @param sname The string object of the route being removed.
     * @return Boolean value determining whether it was successfully removed or not.
     */
    public boolean removeRoute(String sname) {
        // Implement logic to remove a route
        // ...
        return false;
    }

    /** openRoute is a method that opens a route in the list of routes.
     * @return NONE
     * @param sname The string object of the route being opened.
     */
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

    /** closeRoute method is a method that closes a route in the list of routes.
     * @return NONE
     * @param sname
     */
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

    /** addTrain is a method that adds a train to the list of trains.
     * @return NONE
     * @param NONE
    */
    public void addTrain() {
        // Generates a unique ID for the new train
        int newTrainId = generateUniqueId();
    
        // Creates a new Train object with the generated ID
        Train newTrain = new Train(newTrainId);
    
        // Adds the new train to the trains list
        trains.add(newTrain);
    
        // Optionally, add the train to the trainMap for tracking by ID
        trainMap.put(newTrainId, newTrain);
    
        System.out.println("Train with ID " + newTrainId + " added successfully.");
    }
    /** generateUniqueId is a method that will create a unique ID for a train.
     * @param NONE
     * @return An integer that represents the unique ID for the train.
     * REVIEW
     */
    private int generateUniqueId() {
        // Logic to generate a unique ID, such as incrementing a counter or using a UUID
        // For simplicity, we'll use a counter in this example
        return trains.size() + 1; // Incrementing the size of the trains list
    }
    

    /** removeTrain is a method that removes a train from the list based on ID.
     * @return NONE
     * @param id The id integer of the train being removed.
     */
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
    

    /** registerTrain is a method that registers a train to a route.
     * @return NONE
     * @param trainId ID of the train being registered
     * @param routeName Name of the route train is being registered to.
     */
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
    

    /** deRegisterTrain is a method that deregisters a train from the current route.
     * @return NONE
     * @param trainId The integer ID of the train to be deregistered.
     */
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
    

    /**
     * containsStation is a method that checks whether a station is present in the list of stations
     * @param station
     * @return Boolean value of true if the station is found and false if it is not found.
     */
    public boolean containsStation(String station) {
        for (Station s : stations) {
            if (s.getName().equals(station)) {
                return true;
            }
        }
        return false;
    }

    /** containsSegment is a method that checks whether a segment is present in a route. 
     * @param segment String object referring to the name of the segment being checked.
     * @return Boolean value of true if found and false if not.
     */
    public boolean containsSegment(String segment) {
        for (Route route : trainRoutes) {
            if (route.containsSegment(segment)) {
                return true;
            }
        }
        return false;
    }

    /** containsRoute is a method that checks to see whether a route exists within the list.
     * @param route String object referring to the name of the route
     * @return Boolean value of true if found and false if not.
     */
    public boolean containsRoute(String route) {
        for (Route r : trainRoutes) {
            if (r.getName().equals(route)) {
                return true;
            }
        }
        return false;
    }

    
    /** containsTrain is a method that checks if a train with a specific ID exists.
     * @param trainId Integer value of the ID of the train.
     * @return Boolean value of true if found and false if not.
     */
    public boolean containsTrain(int trainId) {
        return trainMap.containsKey(trainId);
    }

    /** getStationByName is a method that retrives a station by its name.
     * @param stationName The string object of the station's name.
     * @return The desired station object if found, otherwise will return null.
     */
    public Station getStationByName(String stationName) {
        for (Station station : stations) {
            if (station.getName().equals(stationName)) {
                return station;
            }
        }
        return null;
    }
    
    /** getStationInfo is a method that retrieves information about a station.
     * @param stationName String object of the station's name.
     * @return String information of the station if found, otherwise returns "Station not found".
     */
    public String getStationInfo(String stationName) {
        // Gets the station by name
        Station station = getStationByName(stationName);
        
        if (station != null) {
            // Gathers information about the station
            String info = "Station Name: " + station.getName() + "\n";
            info += "Status: " + station.getStatus() + "\n";
            info += "Train: " + (station.getTrain() != null ? station.getTrain().getId() : "None") + "\n";
            // more maybe?
            
            return info;
        } else {
            return "Station not found: " + stationName;
        }
    }
    
    /** getRouteByName is a method that gets a route from the list.
     * @param routeName String object of the route being retrieved.
     * @return Route object if found, null if not.
     */
    public Route getRouteByName(String routeName) {
        for (Route route : trainRoutes) {
            if (route.getName().equals(routeName)) {
                return route;
            }
        }
        return null; // Route not found
    }
    
    /** getRouteInfo is a method that retrieves information about the route.
     * @param routeName String object of the route to be retrieved.
     * @return String information about the route if found, null if not.
     */
    public String getRouteInfo(String routeName) {
        // Find the route by name
        Route route = getRouteByName(routeName);
        
        if (route != null) {
            // Gathers information about the route
            String info = "Route Name: " + route.getName() + "\n";
            info += "Round Trip: " + route.isRoundTrip() + "\n";
            info += "Status: " + route.getStatus() + "\n";
            info += "Segments: " + route.getSegments().size() + "\n";
            
            
            return info;
        } else {
            return "Route not found: " + routeName;
        }
    }
    

    /** getTrainInfo is a method that retrieves information about the Train.
     * @param trainId Integer ID of the train to receive info.
     * @return Train info on the desired train if found, "Train with ID {trainID} not found", if not.
     */
    public String getTrainInfo(int trainId) {
        Train train = trainMap.get(trainId);
        if (train != null) {
            return "Train info for ID " + trainId + ": Registered at " + train.getTimeRegistered()
                    + ", Start Time: " + train.getStartTime() + ", Current Location: " + train.getCurrentLocation().getName();
        } else {
            return "Train with ID " + trainId + " not found.";
        }
    }
    
    /** verify is a verification method that seeks to ensure a number of things are consistent in the TrainSystem.
     * @return Boolean value of true if verification checks are accurate, false if not.
     */
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
    
    

    /** setToWorking is a method that updates the system status to Operational by assigning SystemStatus.Operational value to the systemStatus attribute.
     * @return NONE
     * @param NONE
     */
    public void setToWorking() {
        this.systemStatus = SystemStatus.Operational;
        System.out.println("System status set to: " + this.systemStatus.getDescription());
    }
        

    /** setPaused is a method that sets the system to "Deadlocked", regarding it as paused.
     * @return NONE
     * @param NONE
     */
    public void setPaused() {        
        this.systemStatus = SystemStatus.Deadlocked;
        System.out.println("System status set to: " + this.systemStatus.getDescription());
    }    
    

    /** setStopped is a method that sets the system to "Finished" regarding it as stopped.
     * @return NONE
     * @param NONE
     */
    public void setStopped() {
        this.systemStatus = SystemStatus.Finished;
        System.out.println("System status set to: " + this.systemStatus.getDescription());
    }

    /** currentStatus is a method that displays the current status of the system.
     * @param NONE
     * @return The current status of the system.
     */
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
