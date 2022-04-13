import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Inuit {

    /*
        -> Skipping the queue (too much wait time).
        -> How will things change when number of employees or number of petrol pumps will change.
        P    D    D    P
    */


    static void manageItems(String[] cars, int petrol_station, int diesel_station, int employees){

        try {

            int categories_of_stations = 2;

            if(employees == 0) {
                System.out.println("Sorry, nobody to assist you today");
                return;
            }

            Queue<String> petrolCars = new LinkedList<>();
            Queue<String> dieselCars = new LinkedList<>();
            Queue<String> priorityQueue = new LinkedList<>();

            for(String car: cars) {
    
                if(car.equals("D") || car.equals("d")) {
                    dieselCars.add(car);
                }
                else if(car.equals("P") || car.equals("p")){
                    petrolCars.add(car);
                }
                else {
                    priorityQueue.add(car);
                }
            }

            if(petrol_station == 0 && petrolCars.size() > 0) {
                System.out.println("Sorry, Petrol Station is closed today");
                petrolCars.clear();
            }

            if(diesel_station == 0 && dieselCars.size() > 0) {
                System.out.println("Sorry, Diesel Station is closed today");
                dieselCars.clear();
            }


            while(!priorityQueue.isEmpty()) {

                System.out.println("Filling Priority Cars");
                System.out.println("Remaining cars: " + priorityQueue.size());

                // Assuming petrol_stations = diesel_stations.
                int numberOfCarsToBeEvicted = Math.min(employees, petrol_station);

                for(int i = 0; i < numberOfCarsToBeEvicted; i++) {
                    if(!priorityQueue.isEmpty()) {
                        priorityQueue.remove();
                    }
                }
    
                TimeUnit.SECONDS.sleep(2);
            }
    
            // 2 Queues will be processed simultaneously
            if(!petrolCars.isEmpty() && !dieselCars.isEmpty()) {
                System.out.println("Both the stations busy!!");
            }

            /*
            while(!petrolCars.isEmpty() && !dieselCars.isEmpty()) {
    
                // Pick one car from Petrol and one from Diesel and fill it.                

                System.out.println("Remaining Petrol Cars: " + petrolCars.size());
                System.out.println("Remaining Diesel Cars: " + dieselCars.size());


                petrolCars.remove();
                dieselCars.remove();

                System.out.println("Filling one car at Petrol Station and one car at Diesel Station");

                TimeUnit.SECONDS.sleep(2);
            }
            */

            while(!petrolCars.isEmpty() && !dieselCars.isEmpty()) {
    
                // Pick one car from Petrol and one from Diesel and fill it.                
        
                System.out.println("Remaining Petrol Cars: " + petrolCars.size());
                System.out.println("Remaining Diesel Cars: " + dieselCars.size());
        
                // As long as cars are there in petrol and diesel stations, at least one guy has to be there on each of these stations.
                // Now, if we have some extra employee or new employees, we can devise a logic of where to put them.
                // At least 2 workers (according to question) workers are occupied
                // Assuming petrol_stations = diesel_stations
        
                int flexibleEmployees = employees % categories_of_stations;
                int fixedEmployeesPerStation = (employees - flexibleEmployees) / 2;
        
                // First remove fixed cars
                for(int i = 0; i<fixedEmployeesPerStation * categories_of_stations; i++) {
        
                        if(!petrolCars.isEmpty()) {
                            
                            petrolCars.remove();
                        }
                        
                        if(!dieselCars.isEmpty()) {
                            dieselCars.remove();
                        }
                        
                }
        
                // Where is the flexible employee supposed to go?
                if(petrolCars.size() > dieselCars.size()) {
        
                    System.out.println("Extra employee filling Petrol now.");
                    for(int i = 0; i < flexibleEmployees; i++) {
        
                        if(!petrolCars.isEmpty()) {
                            
                            petrolCars.remove();
                        }
                    }
                }
                else {
                    System.out.println("Extra employee filling Diesel now.");
                    for(int i = 0; i < flexibleEmployees; i++) {
        
                        if(!dieselCars.isEmpty()) {
                            dieselCars.remove();
                        }
                    }
                }
        
                TimeUnit.SECONDS.sleep(2);
            }

            // All the diesel cars were done, only petrol cars left, so both employees can come here.
            if(!petrolCars.isEmpty()) {
                System.out.println("All employees dedicated to Petrol Station");
            }
            while(!petrolCars.isEmpty()) {
    
                System.out.println("Remaining Petrol Cars: " + petrolCars.size());
                System.out.println("Filling ONLY Petrol");
                
                int numberOfCarsToBeEvicted = Math.min(employees, petrol_station);
                for(int i = 0; i < numberOfCarsToBeEvicted; i+=1) {
                    if(!petrolCars.isEmpty()) {
                        petrolCars.remove();
                    }
                }
    
                TimeUnit.SECONDS.sleep(2);
            }
    
            // All the petrol cars were done, only diesel cars left, so both employees can come here.
            if(!dieselCars.isEmpty()) {
                System.out.println("All employees dedicated to Diesel Station");
            }
            while(!dieselCars.isEmpty()) {
    
                
                System.out.println("Remaining Diesel Cars: " + dieselCars.size());
                System.out.println("Filling ONLY Diesel");

                int numberOfCarsToBeEvicted = Math.min(employees, diesel_station);
                for(int i = 0; i<numberOfCarsToBeEvicted; i+=1) {
                    if(!dieselCars.isEmpty()) {
                        dieselCars.remove();
                    }
                }
                
                TimeUnit.SECONDS.sleep(2);
            }

            System.out.println("\n\n*** Break Time ***\n\n");
        }
        catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }

       

    }

    
    public static void main(String[] args) {

        final int petrol_station = 2;

        final int dielsel_station = 2;

        final int employees = 3;

        // Take car input here
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of cars");

        int carsCount = sc.nextInt();
        String[] cars = new String[carsCount];

        sc.nextLine();

        System.out.println("Please enter the type of car(s) - Type 'D' for Diesel Car and 'P' for Petrol type car");
        for(int i = 0; i<carsCount; i++) {

            cars[i] = sc.nextLine();
        }
        sc.close();
        for(String car: cars) {
            System.out.print(car + " ");
        }
        System.out.println();

        manageItems(cars, petrol_station, dielsel_station, employees);
    }
}
