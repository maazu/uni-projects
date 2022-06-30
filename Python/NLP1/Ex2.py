#!/usr/bin/env python
# coding: utf-8

# In[40]:


import re
import nltk
import urllib
from bs4 import BeautifulSoup 


#https://www.theguardian.com/help/contact-us


# In[41]:


url = input("Enter url: ")


# In[127]:


req  = urllib.request.Request(url)
resp = urllib.request.urlopen(req)
data = resp.read().decode('utf-8')
soup = BeautifulSoup(data, 'html.parser')
paragraphs = soup.findAll('p')


Fetched_paragraph = re.findall(r'\+?\d{2,4}\s?\d{2,4}\s?\d{6,10}', str(paragraphs))


for line in Fetched_paragraph:
    print("Found a match !")
    print("Telephone: ",end ="")
    print((str(line)))

