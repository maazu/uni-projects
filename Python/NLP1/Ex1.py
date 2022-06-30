#!/usr/bin/env python
# coding: utf-8

# In[49]:

import nltk
import urllib
from nltk.stem import WordNetLemmatizer
from bs4 import BeautifulSoup


# Opening and reading the data from the website using beautiful soup

# In[45]:


url  = "https://www.theguardian.com/music/2018/oct/19/while-my-guitar-gently-weeps-beatles-george-harrison"
req  = urllib.request.Request(url)
resp = urllib.request.urlopen(req)
data = resp.read().decode('utf-8')
soup = BeautifulSoup(data, 'html.parser')
paragraphs = soup.findAll('p') #finds all the p tags
paragraphs = [sentence.text.strip() for sentence in paragraphs]
#prints everything within the p tag into a list 
#print(paragraphs)


# Function to return a tokenize list 
# of words using nltk tokenizer

# In[ ]:


def tokenize_words(paragraph):
    words =[]
    for sentence in paragraph:
        for word in nltk.word_tokenize(sentence):
            words.append(word)
    return words


# Fucntion to return a lemmatize wordlist 
# uses wordnet lemmatizer for lemmatization

# In[18]:


def lemmatize(words):
    lemmatizedList = []
    lemmatizer = nltk.stem.WordNetLemmatizer()
    for word in words:
        lemmatizedList.append(lemmatizer.lemmatize(word.lower(), pos="v"))
    return lemmatizedList


# In[27]:


#print(tokenize_words(paragraphs))
#print(set(tokenize_words(paragraphs)))


# In[38]:


print("The text contains types before lemmatization")
print(len(set(tokenize_words(paragraphs))))
print("The text contains tokens before lemmatization")
print(len(tokenize_words(paragraphs)))
#After lemmatization
print("The text contains types after lemmatization")
print(len(set(lemmatize(tokenize_words(paragraphs)))))
print("The text contains tokens aftet lemmatization")
print(len(lemmatize(tokenize_words(paragraphs))))


# In[60]:


def posTag(words):
    return nltk.pos_tag(words)


# Identification of Errors:
# 1. Pos taging is not identify punctuation marks 
# 2. Some words are not identified correctly     

# In[61]:


print(posTag((tokenize_words(paragraphs))))