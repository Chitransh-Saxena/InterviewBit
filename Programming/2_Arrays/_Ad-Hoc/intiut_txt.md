    petrol_stations = 4
    diesel_stations = 4

    employees = 3;

    final int categories_of_stations = 2;   // Only diesel and petrol right now.

    How to divide employees?
        -> flexibleEmployees = employees % categories_of_stations;
        -> fixedEmployeesPerStation = (employees - flexibleEmployees) / 2;

    P P D P

    PPP - 1 1
    D - 1

    while(!petrolCars.isEmpty() && !dieselCars.isEmpty()) {
    
        // Pick one car from Petrol and one from Diesel and fill it.                

        System.out.println("Remaining Petrol Cars: " + petrolCars.size());
        System.out.println("Remaining Diesel Cars: " + dieselCars.size());

        // As long as cars are there in petrol and diesel stations, at least one guy has to be there on each of these stations.
        // Now, if we have some extra employee or new employees, we can devise a logic of where to put them.

        // At least 2 workers (according to question) workers are occupied
        // Assuming petrol_stations = diesel_stations
        int numberOfCarsToBeEvicted = Math.min(employees, petrol_station);

        int flexibleEmployees = employees % categories_of_stations;
        fixedEmployeesPerStation = (employees - flexibleEmployees) / 2;

        // First remove fixed cars
        for(int i = 0; i<fixedEmployeesPerStation * categories_of_stations; i++) {

                petrolCars.remove();
                dieselCars.remove();
        }

        // Where is the flexible employee supposed to go?
        if(petrolCars.size() > dieselCars.size()) {

            for(int i = 0; i < flexibleEmployees; i++) {

                petrolCars.remove();
            }
        }
        else {
            for(int i = 0; i < flexibleEmployees; i++) {

                dieselCars.remove();
            }
        }

        TimeUnit.SECONDS.sleep(2);
    }