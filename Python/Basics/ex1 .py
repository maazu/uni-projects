#!/usr/bin/env python
# coding: utf-8
"""
CE151 ASSIGNMENT 2 
----------------------------
Exercise  1 (45%)
-----------------------------
AUTHOR
Registration Number  (name)
"""


# #(open_file('ex1_sample_data.txt')) 
# #Return a tuple containing all the players data


def open_file(filename):
    players_data_list = []
    file = open(filename, "r")
    listOfLines = file.readlines()
    for line in listOfLines:
        line = line.strip()
        players_data_list.append(tuple(line.split()))
    player_data_tuple = tuple(players_data_list)
    return player_data_tuple


# In[ ]:


def print_table_head():
    print("\n")
    print(" ---------------------------------------------------------------------------------------------")
    print('{:>15} | {:>15} |  {:>9}  | {:>15} | {:>15}'.format("LAST-NAME","FIRST-NAME","SALARY","POSITION","TEAM"))
    print(" ---------------------------------------------------------------------------------------------")
    


# #Zappacosta, Davide 20,000,000 Right-back Chelsea
# print in the following format

def print_all_details(player_data_tuple):
    player_data_list = list(player_data_tuple)
    print_table_head() # printing the head data
    for team,position, firstname,lastname, salary in player_data_list:
        print('{:>15} | {:>15} |  {:>9} | {:>15} | {:>15}'.format(lastname,firstname,salary,position,team))
    return player_data_list 


# searches by last name

def find_player_by_name(player_name,player_data_tuple):
    player_data_list = list(player_data_tuple)
    count = 0
    print_table_head() # printing the head data
    for team,position, firstname,lastname, salary in player_data_list:
        if(lastname == player_name):
            print('{:>15} | {:>15} |  {:>9} | {:>15} | {:>15}'.format(lastname,firstname,salary,position,team))
            count=+1
    #if player is not found       
    if(count==0):
        print(player_name," Not found in the search.")
    return player_data_list 

# searches by last name

def find_player_by_team(team_name,player_data_tuple):
    player_data_list = list(player_data_tuple)
    count = 0
    print_table_head() # printing the head data
    for team,position, firstname,lastname, salary in player_data_list:
        if(team == team_name):
            print('{:>15} | {:>15} |  {:>9} | {:>15} | {:>15}'.format(lastname,firstname,salary,position,team))
            count=+1
    #if player is not found       
    if(count==0):
        print(" Not found in the search.")
    return player_data_list


# searches by postion and team 
def find_player_by_pos_team(team_name,position_g,player_data_tuple):
    player_data_list = list(player_data_tuple)
    count = 0
    print_table_head() # printing the head data
    for team,position, firstname,lastname, salary in player_data_list:
        if(team == team_name and position_g == position):
            print('{:>15} | {:>15} |  {:>9} | {:>15} | {:>15}'.format(lastname,firstname,salary,position,team))
            count=+1
    #if player is not found       
    if(count==0):
        print(" Not found in the search.")
    return player_data_list


# Prints the data of the all players in a table


def print_name_of_player(player_data_tuple):
    player_data_list = list(player_data_tuple)
    for team,position, firstname,lastname, salary in player_data_list:
        print('{:>15}  | {:>15} '.format(lastname,firstname,salary,position,team))    


# Searches for the players in a particular salary range


def print_player_salary_range(salary1,salary2,player_data_tuple):
    player_data_list = list(player_data_tuple)
    salary1 = salary1.replace(',', '')
    salary1 = int(salary1)
    salary2 = salary2.replace(',', '')
    salary2 = int(salary2)
    found_pairs = [] #fOUND PLAYER WITH A SALARY IN RANGE
    for team,position, firstname,lastname, salary in player_data_list:
        salary = salary.replace(',', '')
        salary = int(salary)
        if(salary in range(salary1,salary2+1)): # HChecks the range
            found_pairs.append(lastname) #adding the last name
    
    if(len(found_pairs)> 0): 
        print_table_head() # printing the head data
        for player_last_name in found_pairs:
            find_player_by_name(player_last_name,player_data_tuple) #Calling the find player method by using the last name
    elif(not found_pairs):
        print("No player found with this salary range.")        



while True:
    # test file name : ex1_sample_data.txt
    file_name = input("Please enter the name of the file and press 'Enter': ")
    file_name = file_name.strip() # removes the spaces
    try:
        print_all_details(open_file(file_name)) ## prints the table 
        print("\n")
        options = ['PLEASE NOTE THAT ALL SEARCHES ARE CASE SENSISTIVE','Enter "1" to view details of the player with a given last name','Enter "2" to view all players with a salary in a particular range (e.g. 20000 to 30000)','Enter "3" to view the first-and last- names of all players of a team last-name','Enter "4" to view the detials of team members of a paritcular team.','Enter "5" to serarch by postion and team ','Enter "9" to quit Programe']
        for option in options:
             print("-->",option)
        print("\n")
        while True:
            choice_input = input("Please enter your chosen option : ")
            if(choice_input == "1"):
                player_name = input("Enter the player name: ")
                find_player_by_name(player_name.strip(),open_file(file_name))

            elif(choice_input == "2"):
                salary_range1 = input("Enter the salary lower bound: ")
                salary_range2 = input("Enter the salary upper bound: ")
                print_player_salary_range(salary_range1.strip(),salary_range2.strip(),open_file(file_name))
                
            elif(choice_input == "3"):
                print_name_of_player(open_file(file_name))
            
            elif(choice_input == "4"):
                team_name = input("Enter the name of the team: ")
                find_player_by_team(team_name.strip(),open_file(file_name))
            
            elif(choice_input == "5"):
                team_name = input("Enter the team name: ")
                position_g = input("Enter the position: ")
                find_player_by_pos_team(team_name.strip(),position_g.strip(),open_file(file_name))
                
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
