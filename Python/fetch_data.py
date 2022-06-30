# -*- coding: utf-8 -*-
"""
Created on Wed Jul 29 21:29:05 2020
@author: Maaz
Brazil Minstry of Petroleum release oil production data every month
in a zipfile by year. The zip file of each year contain offshore and onshore
oil data.
This script combines all the offshore data released after 2004 into one dataframe
and save the dataframe into a csv file. 
"""
import pandas as pd
from io import BytesIO
from zipfile import ZipFile
from urllib.request import urlopen
import datetime
import requests
import time
import re
import unicodedata
import sys

def check_url_exist(url):
    request = requests.get(url)
    if request.status_code == 200:
        return True
    else:
        return False
        
        
def get_url_by_year(year):
    OLD_URL     = "http://www.anp.gov.br/arquivos/central-conteudos/de/producao-pocos-"+str(year)+".zip"
    LATEST_URL  = "http://www.anp.gov.br/arquivos/dados-estatisticos/producao-petroleo/producao-pocos-"+str(year)+".zip"
    if year >= 2019:
        if(check_url_exist(LATEST_URL)):
            return LATEST_URL
        else:
            print(LATEST_URL,'Has been changed.')            
    else:
        if(check_url_exist(OLD_URL)):
            return OLD_URL
        else:
            print(OLD_URL,'Has been changed.')


def remove_accents(input_str):
    input_str = str(input_str)
    nfkd_form = unicodedata.normalize('NFKD', input_str)
    only_ascii = nfkd_form.encode('ASCII', 'ignore')
    only_ascii =only_ascii.decode('utf-8')
    return only_ascii
      
      
def data_combiner(year):
    file_url = get_url_by_year(year)   
    url = urlopen(file_url)
    zipfile = ZipFile(BytesIO(url.read().strip()))
    released_offshore_data = []
    monthly_df = list() 
    yearly_file_name = str(year)+'/'+str(year)+'_producao_mar.xlsx'
    for file in zipfile.namelist():
        if(file.endswith('producao_mar.xlsx')):
            released_offshore_data.append(file)
            
    if(yearly_file_name in released_offshore_data):
        released_offshore_data.clear()
        released_offshore_data.append(yearly_file_name) 
    
    print(released_offshore_data)    
    
    for i in range(len(released_offshore_data)):
        df = pd.read_excel(zipfile.open(released_offshore_data[i]),skiprows=4, delimiter = ';', decimal = ',',
                           encoding='sys.getfilesystemencoding()')
        #df.apply(remove_accents)
        monthly_df.append(df)
    
    df = pd.concat(monthly_df)[monthly_df[0].columns]
    zipfile.close()
    return df
    
    
def main():
    now = datetime.datetime.now()
    yaerly_dataframe = list() 
    year = 2004
    current_year = now.year
    timeframe = current_year - year 
    for i in range(1,timeframe+1):
        yaerly_dataframe.append(data_combiner(year+i))
    
    csv_file_name = str(year+1)+str(current_year)+'production_data.csv'
    df = pd.concat(yaerly_dataframe)[yaerly_dataframe[0].columns]
    df.to_csv(csv_file_name,index=False,encoding='utf-8-sig')
    print('All data is combined.')
    
    
 if __name__ == "__main__":
    main()
