
import matplotlib.pyplot as plt
import math


distance =  5.13 #float(input("Distance: "))
desired_velocity =  2 #float(input("Cruise Velocity: "))
acceleration = 2 # float(input("Acceleration: " ))




def profileGenerator(distance, cruise_velocity, acceleration):
   
    max_velocity = math.sqrt(distance * acceleration)
    cruise_velocity = min(max_velocity, cruise_velocity)
    
    time_to_accel = cruise_velocity/ acceleration
    distance_after_accel = .5 * acceleration * math.pow(time_to_accel,2)
    cruise_distance = distance - 2* distance_after_accel
    time_at_vcruise = cruise_distance/ cruise_velocity

    running_time_ms = 0
    loop_run_time = .02
    current_pos = 0
    current_vel = 0
    current_accel = 0

    t_list = []
    x_list = []
    v_list = []
    a_list = []

    while(running_time_ms <= (2*time_to_accel + time_at_vcruise)*1000):

        if(running_time_ms < time_to_accel * 1000):
            current_accel = acceleration
        elif(running_time_ms < (time_to_accel + time_at_vcruise)*1000):
            current_accel = 0
        else:
            current_accel = -1 * acceleration
    
        current_vel += current_accel * loop_run_time
        current_vel = min(cruise_velocity, current_vel)

        current_pos += current_vel* loop_run_time + .5 * current_accel * loop_run_time * loop_run_time

        print("t: {:.2f} x: {:.2f} v:{:.2f} a: {:.2f}".format(running_time_ms/1000,current_pos,current_vel,current_accel))

        t_list.append(running_time_ms/1000)
        x_list.append(current_pos)
        v_list.append(current_vel)
        a_list.append(current_accel)

        running_time_ms += 1000*loop_run_time
    

    plt.plot(t_list, x_list, label= 'Position')
    plt.plot(t_list, v_list, label = 'Velocity')
    plt.plot(t_list, a_list, label = 'Acceleration')
    plt.xlabel('Time, s', fontsize = 12)
    plt.legend()




profileGenerator(distance, desired_velocity, acceleration)
plt.show()