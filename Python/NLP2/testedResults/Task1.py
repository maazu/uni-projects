#!/usr/bin/env python
# coding: utf-8

# In[4]:


import os
from nltk.corpus import wordnet as wn

# opens a file and return a tuple of word data
def open_file(file_name):
    try:
        file = open(file_name.strip(), "r")
        words_data = []
        for line in file:
            line=line.split()
            words_data.append(tuple(line))
        del words_data[0] #deletes 1 first head row
        return words_data
    except(FileNotFoundError, IOError):
        print("Wrong file or file path")

        
def get_path_similarity(word_1, word_2):
    max_sim = -1.0
    word1_synsets_1 = wn.synsets(word_1)
    word2_synsets_2 = wn.synsets(word_2)
    if len(word1_synsets_1) == 0 or len(word2_synsets_2) == 0:
        return 0
    else:
        higest_similarity = -1.0
        best_pair = None, None
        path_similarity  = 0
        for wsynset_1 in word1_synsets_1:
            for wsynset_2 in word2_synsets_2:
               sim = wn.path_similarity(wsynset_1, wsynset_2)
               if sim is not None and sim > higest_similarity:
                   higest_similarity  = sim
                   path_similarity =  wn.path_similarity(wsynset_1, wsynset_2)
        return path_similarity
    
    
def path_similarity(word_data):
    data_wd_path_sim = []
    data_wd_path_sim.append('------------------------------------------------------------------------------')
    data_wd_path_sim.append('{:20}| {:20}| {:10}| {:20}'.format("word1","word2","gold","path similarity |"))
    data_wd_path_sim.append('------------------------------------------------------------------------------')
    for word1,word2,gold_sim in word_data:
        path_sim = get_path_similarity(word1,word2)
        data_wd_path_sim.append('{:20}| {:20}| {:10}| {:20}'.format(word1,word2,gold_sim,str(path_sim)))
    return data_wd_path_sim


#Writes path similarity data into a new file
#for testing it will remove the file if same file already exist
def write_path_similarity(data):
    if os.path.exists("BioSim-100-predicted.txt"):
        os.remove("BioSim-100-predicted.txt")
        with open('BioSim-100-predicted.txt', 'w') as f:
            for line in data:
                f.write("%s\n" % line)
            print("BioSim-100-predicted.txt has been created")
            f.close()
    else:
        with open('BioSim-100-predicted.txt', 'w') as f:
            for line in data:
                f.write("%s\n" % line)
            print("BioSim-100-predicted.txt has been created")
            f.close()

def main():
    print("Calculating similarities ......")
    write_path_similarity(path_similarity(open_file('SimLex999-100.txt')))

if __name__ == "__main__":
    main()

