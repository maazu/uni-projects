#!/usr/bin/env python
# coding: utf-8
from math import sin, cos, sqrt, atan2, radians

def open_file(filename):
    city_data_list = []
    file = open(filename, "r")
    listOfLines = file.readlines()
    for line in listOfLines:
        line = line.strip()
        city_data_list.append(tuple(line.split()))
    cities_data_tuple = tuple(city_data_list)
    return cities_data_tuple


def print_table_head():
    print("\n")
    print("----------------------------------------------------------------------------------")
    print('{:>30} | {:>8} | {:>8} | {:>15}'.format("CITY","LONGITUDE","LATITUDE","CITIZENS"))
    print("----------------------------------------------------------------------------------")
    
# print akk the details into the table
def print_all_details(cities_data_tuple):
    cities_data_list = list(cities_data_tuple)
    print_table_head()
    for city,long,lat,citizens in cities_data_list:
        print('{:>30}  | {:>8} | {:>8} | {:>15}'.format(city,long,lat,citizens))
    return cities_data_list 


# find the rcity nmae
def find_city_by_name(city_name,cities_data_tuple):
    city_data_list = list(cities_data_tuple)
    count = 0
    coordinates = []
   
    for city,long,lat,citizens in city_data_list:
        if(city == city_name):
            print('{:>30} | {:>8} | {:>8} | {:>15}'.format(city,long,lat,citizens))
            coordinates.append(long)
            coordinates.append(lat)
            count=+1        
    if(count==0):
        print(city_name," record not found in the provided data")
    return coordinates

# srarches for the city in the population in range
def print_population_range(pop_range1,pop_range2,cities_data_tuple):
    cities_data_list = list(cities_data_tuple)
    pop_range1= pop_range1.replace(',', '')
    pop_range1 = int(pop_range1)
    pop_range2 = pop_range2.replace(',', '')
    pop_range2 = int(pop_range2)
    found_cities = []
    for city,long,lat,citizensp in cities_data_list:
        citizensp = citizensp.replace(',', '')
        citizensp = int(citizensp)
        if(citizensp in range(pop_range1,pop_range2+1)):
            found_cities.append(city)
  
    if(len(found_cities)> 0):
        for city_name in found_cities:
            find_city_by_name(city_name,cities_data_tuple)
    elif(not found_cities):
        print("Population range is not available in the data.")  

# calculates the distance
def getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2):
    R = 6371  # Radius of the earth
    dlon = lon2 - lon1
    dlat = lat2 - lat1
    a = sin(dlat / 2)**2 + cos(lat1) * cos(lat2) * sin(dlon / 2)**2
    c = 2 * atan2(sqrt(a), sqrt(1 - a))
    distance = R * c
    return distance

## returns the total
def cal_distance(city1,city2,cities_data_tuple):
    city_data_list = list(cities_data_tuple)
    cordin1 = find_city_by_name(city1,cities_data_tuple)
    cordin2 = find_city_by_name(city2,cities_data_tuple)
    distance = getDistanceFromLatLonInKm(float(cordin1[0]),float(cordin1[1]),int(cordin2[0])  ,float(cordin2[1]))       
    print("Total Distance b/w",city1,"And",city2," ",distance,"KM" )


while True:
    #test file name : 5
    file_name = input("Please enter the name of the file and press 'Enter': ") #file na,e
    file_name = file_name.strip()
    try:
        print("\n")
        print_all_details(open_file(file_name.strip()))
        print("\n")
        options = ['Enter "1" to view full details ofthe city with a given name ','Enter "2" to view all the cities with a population in a particular range','Enter "3" to view the cities, if any, within 10Km from the coordinates specified by the user','Enter "8" to calculate the distance between two cities','Enter "9" to quit Programe']
        for option in options:
             print("-->",option)
        print("\n")
        while True:
            choice_input = input("Please enter your chosen option : ")
            if(choice_input == "1"):
                city_name = input("Enter the city name: ")
                find_city_by_name(city_name.strip(),open_file(file_name))

            elif(choice_input == "2"):
                pop_range1 = input("Enter the population range from: ")
                pop_range2 = input("Enter the population range to: ")
                print_population_range(pop_range1.strip(),pop_range2.strip(),open_file(file_name))
                
            elif(choice_input == "3"):
                longitude = input("Enter Londgitutde: ")
                latitude = input("Enter latitude: ")
                print("This part is incomplete")
                
            elif(choice_input == "8"):
                city_1 = input("Enter the first city name: ")
                city_2 = input("Enter the second city name: ")
                cal_distance(city_1.strip(),city_2.strip(),open_file(file_name))  
            
            elif(choice_input == "9"):
                print("Terminating the program...")
                print(quit)
                break
            else:
                print("Invalid Choice ! Try Again")
                

    except FileNotFoundError:
        print("File name Invaild filename  or file path")
        break
    else:
        break
        
