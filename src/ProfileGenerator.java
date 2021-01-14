public class ProfileGenerator {
    
    private double distance, desiredCruiseVelocity, maxAcceleration;

    public ProfileGenerator(double distance, double cruiseVelocity, double accleration){
        this.distance = distance;
        desiredCruiseVelocity = cruiseVelocity;
        maxAcceleration = accleration;
    }
    public static void main(String[] args) throws Exception {
        
        ProfileGenerator pg = new ProfileGenerator(12, 3, 3);
        pg.run();
    }

    public void run(){

        double maxVelocity = maxAcceleration * Math.sqrt(distance / maxAcceleration);

        double v_cruise = Math.min(maxVelocity, desiredCruiseVelocity);

        double timeToAccel = v_cruise/maxAcceleration;
        double x1 = v_cruise * timeToAccel;   // distance covered while acceleratinng and decelerating
        double x2 = distance - x1;                      // distance covered at cruise velocity 
        double t_cruise = x2/v_cruise;                      // time at cruise velocity
        double loopRunTime_ms = 20;
        double totalRunTime = timeToAccel *2 + t_cruise;
        
        
        int runningTime_ms = 0;
        double currVelocity, currAcceleration, currDistance;
        currVelocity = currAcceleration = currDistance = 0;



        while(runningTime_ms <= totalRunTime *1000){

            if(runningTime_ms < timeToAccel * 1000){
                currAcceleration = maxAcceleration;
            }else if(runningTime_ms < (timeToAccel + t_cruise) * 1000){
                currAcceleration = 0;
            }else{
                currAcceleration = -1 * maxAcceleration;
            }

            currVelocity += currAcceleration * loopRunTime_ms /1000.0;

            currVelocity = Math.min(currVelocity, v_cruise);
            currVelocity = Math.max(currVelocity, 0);

            currDistance += currVelocity * loopRunTime_ms/ 1000.0 + .5 * currAcceleration * Math.pow(loopRunTime_ms/1000.0, 2);

            System.out.printf("( %4d %.4f %.4f %.4f) \n", runningTime_ms, currDistance, currVelocity, currAcceleration);

            runningTime_ms += loopRunTime_ms;

        
        }

        System.out.println(totalRunTime);


  /*      for(runningTime_ms = 0; runningTime_ms <= timeToAccel*1000; runningTime_ms += 20){
        
            currAcceleration = maxAcceleration;
            currVelocity = maxAcceleration * runningTime_ms /1000.0;
            currDistance = .5 * maxAcceleration * Math.pow(runningTime_ms/1000.0, 2);

            System.out.printf("( %4d %.4f %.4f %.4f) \n", runningTime_ms, currDistance, currVelocity, currAcceleration);

        }

        while(runningTime_ms <= (timeToAccel + t_cruise)*1000){
            currAcceleration = 0;
            currVelocity = v_cruise;
            currDistance += v_cruise * loopRunTime_ms/1000.0; 

            System.out.printf("( %4d %.4f %.4f %.4f) \n", runningTime_ms, currDistance, currVelocity, currAcceleration);

            runningTime_ms += 20;

        }

        while(runningTime_ms <= totalRunTime*1000){

            currAcceleration = -1 * maxAcceleration;
            currVelocity += currAcceleration * loopRunTime_ms /1000.0;
            currDistance += currVelocity * loopRunTime_ms/ 1000.0 + .5 * maxAcceleration * Math.pow(loopRunTime_ms/1000.0, 2);

            System.out.printf("( %4d %.4f %.4f %.4f) \n", runningTime_ms, currDistance, currVelocity, currAcceleration);

            runningTime_ms +=20;
        }
*/


    }
}

