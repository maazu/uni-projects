# -*- coding: utf-8 -*-
"""
Created on Wed Jul 22 15:30:27 2020

@author: Maaz
"""
from itertools import chain
import pandas as pd
import json
import csv
import time

start = time.time()
pd.set_option('display.max_rows', 500)
pd.set_option('display.max_columns', 500)
pd.set_option('display.width', 1000)
print('Opening and read file..')
df = pd.read_excel('2005-2020-05_producao_mar_(offshore).xlsx')
data = {}



oil_index = 1
cond_index = 2
petrol_index = 3
gasAssociado_index = 4
gasNaoAssociado_index = 5
gastotal_index = 6
gasvol_index = 7
gasAgua_index = 8

def find_in_2d_array(arr, value):
    return value in chain.from_iterable(arr)

def find_index_2d_array(arr, value):
    return [[index, row.index(value)] for index, row in enumerate(arr) if value in row]



print('reading filtering file data......')
for index, row in df.iterrows():
    if (index == 1):
        print("reading first record" ,time.time()  - start) 
        
    if(type(row['Campo']) is not str):
        print(index)
        break
    
    if(type(row['Óleo (bbl/dia)']) is not float):
       print(index)
       break
    
    #well = str(row['ANP']).strip()
    field = row['Campo'].strip()
    year  = row['Período'].strip()
    oil   = float(row['Óleo (bbl/dia)'])
    condensation  = float(row['Condensado (bbl/dia)'])
    petrol  = float(row['Petróleo (bbl/dia)'])
    gasAssociado = float(row['Associado'])
    gasNaoAssociado = float(row['Não Associado'])
    gastotal =  float(row['Gás Total'])
    gasvol =  float(row['Volume Gás Royalties (Mm³/dia)'])
    gasAgua = float(row['Água (bbl/dia)'])
   
   
    if(field in data):
        if(find_in_2d_array(data[field],year)==True):
            get_date_index = find_index_2d_array(data[field],year)
            date_index   = int(get_date_index[0][0]) 
            data[field][date_index][oil_index]      = float(data[field][date_index][oil_index]) + float(oil)
            data[field][date_index][cond_index]     = float(data[field][date_index][cond_index]) + float(condensation)
            data[field][date_index][petrol_index]   = float(data[field][date_index][petrol_index]) + float(petrol)
            
            data[field][date_index][gasAssociado_index] = float(data[field][date_index][gasAssociado_index]) + float(gasAssociado)
            data[field][date_index][gasNaoAssociado_index]     = float(data[field][date_index][gasNaoAssociado_index]) + float(gasNaoAssociado)
            data[field][date_index][gastotal_index]     = float(data[field][date_index][gastotal_index]) + float(gastotal)
            data[field][date_index][gasvol_index]   = float(data[field][date_index][gasvol_index]) + float(gasvol)
            data[field][date_index][gasAgua_index]  = float(data[field][date_index][gasAgua_index]) + float(gasAgua)
        else:
           data[field].append([year,oil,condensation,petrol,gasAssociado,gasNaoAssociado,gastotal,gasvol,gasAgua])
    else:
        data[field] = [[year,oil,condensation,petrol,gasAssociado,gasNaoAssociado,gastotal,gasvol,gasAgua]]
    
    
print("reading finshed complete" ,time.time()  - start)   
print('writing data into csv......')
with open('refiltered.csv', 'w', newline='') as brazil_file:
    csv_writer = csv.writer(brazil_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
    header = [
              'Campo','Período','Óleo (bbl/dia)','Condensado (bbl/dia)',
              'Petróleo (bbl/dia)', 'Associado', 'Não Associado', 'Gás Total',
              'Volume Gás Royalties (Mm³/dia)','Água (bbl/dia)'  
              ]
    csv_writer.writerow(header)      
              
    for key, value in data.items():
        length_key = len(data[key])
        #print("Total field data:" , length_key)
        for i in range(0,length_key):
            csv_writer.writerow([key,
                  data[key][i][0],
                  data[key][i][1],
                  data[key][i][2],
                  data[key][i][3],
                  data[key][i][4],
                  data[key][i][5],
                  data[key][i][6],
                  data[key][i][7],
                  data[key][i][8]]
                                ) 

print('Finshed the script please see the file.')
end = time.time()
print(end - start)
