#!/usr/bin/env python
# coding: utf-8

# In[41]:


from collections import Counter
from collections import defaultdict as ddict
import re


# In[242]:


def main():
    N = open_file("sampledata.txt",with_s_tags=False)
    Nv = open_file("sampledata.vocab.txt",with_vocabulary = True)
    Ns =  open_file("sampledata.txt",with_s_tags=False)
    v = vocab_tags(Nv,begin_tag=False)
    vb = vocab_tags(Nv,begin_tag=True)

    cal_unigram(N) #prints unigram
    print("\n")
    smoothed_model(N,Nv) #prints smo unigram
    
    N = open_file("sampledata.txt",with_s_tags=True)

    print("=== Bigram Modal ===")
    print("\n")
    print(" ",end="\t\t")
    for s in v:
        print(s,end="\t\t")
    print_bigram_uns(N,Nv) #prints bigram_un
    print("\n")     
    print_bigram_smo(N,Nv) #prints bigram_sm
    print("\n")
 
    ds = read_sentence("sampledata.txt",with_stag=False)
    ds.append("UNK")
    print("=== Sentence Probabilities ===")
   
    sentence_probablities("sampletest.txt",N,Nv,vb)
    S = read_sentence("sampledata.txt",with_stag=False)
    
    


# In[233]:


# return the text from the file
def open_file(file_name,with_vocabulary=True,with_s_tags=True,):  
    if (with_vocabulary == False):
        file = open(file_name,"r")
        file_data = file.read()
        words = []
        for word in file_data.split():
            words.append(word)
        return words
    elif(with_s_tags==False):
        file = open(file_name,"r")
        file_data = file.read()
        file_data = re.findall(r'<s>(.*?)</s>', str(file_data))
        words = []
        for sentence in file_data:
            for word in sentence.split():
                words.append(word)
        return words
    else:
        f = open(file_name, "r")
        types = (f.read())
        V = []
        for w in types.split():
            V.append(w)
        V.append("UNK")
        return V

#unigram modal 
def cal_unigram(N):
    length = len(N)
    word_dict ={}
    # count the occurences of word
    for word in N:
        if word in word_dict.keys():
            word_dict[word] += 1
        else:
            word_dict[word] = 1
    print("- Unsmoothed -")
    for word, count in word_dict.items():
        bigram_probablities =count/length
        print(word, ":", bigram_probablities, end=" ")
    print("")

# ungram smoothed model
def smoothed_model(N,Nv):
    totalNumOfN =len(N)
    totalNumOfNv =len(Nv)
    word_dict ={}
    # count the occurences of word
    for word in N:
        if word in word_dict.keys():
            word_dict[word] += 1
        else:
            word_dict[word] = 1
    word_dict["UNK"] = 0    
    print("- Smoothed -")
    for word, count in word_dict.items():  
        smoothed_probablities =(count+1)/(totalNumOfN+totalNumOfNv)
        print(word, ":",  smoothed_probablities, end=" ")
    print("")


#to add or remvoe stags into any provided array 
def vocab_tags(vocabulary,begin_tag = False,with_both=False):
    vocab = vocabulary[:]
    if(begin_tag == True):
        vocab.append("<s>")
        return vocab
    elif(begin_tag == False):
        vocab.append("</s>")
        return vocab
    elif(with_both == True):
        vocab.insert(0, "<s>")
        vocab.append("</s>")
        return vocab
    
# counts the pairs of words in N
def countPair(N,h, w):
    count = 0
    for c in range(1, len(N)):
        if h == N[c - 1] and w == N[c]:
            count += 1
    return count


#perform bigram calculations and return a sub dictionary 
def bigram_model(N,Nv):
    V_begin = vocab_tags(Nv,begin_tag = True)
    V_end = vocab_tags(Nv,begin_tag = False) 
    d = ddict(dict) 
    for h in V_begin:
        for m in V_end:
            if (N.count(h) == 0):
                biP = 0
            else:
                biP = countPair(N,h, m) / N.count(h)
                d[h][m] = biP
    return d

#prints unsmothed bigram with a layout      
def print_bigram_uns(N,Nv):
    print("\n")
    print("- Unsmoothed -")
    d= (bigram_model(N,Nv))
    print("",end="\t")
    
    for field, possible_values in d.items():
        print("")
        print(field, end = "\t\t")
        for a,v in possible_values.items():
            print("{0:.5f}".format(v),end="\t\t")

# calculate the bigram 
def bigram_smoothed(N,Nv):
    V_begin = vocab_tags(Nv,begin_tag = True)
    V_end = vocab_tags(Nv,begin_tag = False) 
    d = ddict(dict)
    for h in V_begin:
        for m in V_end:
            pair_count = countPair(N,h,m)
            biP = (pair_count+1)/(N.count(h)+len(V_begin))
            d[h][m] = biP
    return d

# print the bigram layoout 
def print_bigram_smo(N,Nv):
    print("\n")
    print("- Smoothed -")
    d= (bigram_smoothed(N,Nv))
    print("",end="\t")
    for field, possible_values in d.items():
        print("")
        print(field, end = "\t\t")
        for a,v in possible_values.items():
            print("{0:.5f}".format(round(v,4)),end="\t\t")
           
 #fucntion to read sentences from a file        
def read_sentence(file ,with_stag=False):
    f = open(file, "r")
    txtData = (f.read())
    Fetched_paragraph = re.findall(r'<s>(.*?)</s>', str(txtData))
    return Fetched_paragraph
    if (with_stag==True):
        txtData = (f.read())
        return Fetched_paragraph
    elif(with_vocabulary == True):
        file = open(file_name,"r")
        file_data = file.read()
        words = []
        for word in file_data.split():
            words.append(word)
            
# function to calculate the probablities of the sentences
def sentence_probablities(file,N,Nv,vb):
    n = bigram_smoothed(N,Nv)
    sentences = read_sentence(file)
    vb.append('</s>') 
    count =0
    for sentence in sentences:
        count +=1
        print(count,sentence,end="\t\t")
        sentence = sentence.split()
        P = 1
        for i in range(1, len(sentence)):
            h = sentence[i-1] if sentence[i-1] in vb else 'UNK'
            w = sentence[i] if sentence[i] in vb else 'UNK'
            P *= n[h][w]
        print("{0:.5f}".format(P))
    print("\n") 

if __name__ == '__main__':
    main()
