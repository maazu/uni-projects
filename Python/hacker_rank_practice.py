'''
checks for leap year 
The year must be evenly divisible by 4;
If the year can also be evenly divided by 100, it is not a leap year;
unless...
The year is also evenly divisible by 400. Then it is a leap year.
According to these rules, the years 2000 and 2400 are leap years,
while 1800, 1900, 2100, 2200, 2300, and 2500 are not leap years.

'''
def is_leap(year):
    leap = False
    if(year % 4 == 0 and year % 100 != 0):
       leap = False
    if(year % 4 == 0):
        leap = True
    if(year % 100 == 0):
        leap = False
  
    if(year % 100 == 0 and year % 400 == 0):
        leap = True
   
    return leap

'''
Without using any string methods, try to print the following:
123..N
'''
def without_using_string_method(n):
    n = int(input())
    for i in range(1,n+1):
        print(i,end="")
'''
conditional actions:
If  is odd, print Weird
If  is even and in the inclusive range of  to , print Not Weird
If  is even and in the inclusive range of  to , print Weird
If  is even and greater than , print Not Weird
'''
def check_n_value(n):
    n = int(input().strip())
    if(n % 2 == 0):
        if n in range(2,6):
            print('Not Weird')
        if n in range(6,21):
            print('Weird')   
        if (n > 20):
            print('Not Weird')
    else:
        print('Weird')

 
