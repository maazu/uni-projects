from nltk.corpus import wordnet as wn

# Importing similarity function from task 1
from Task1 import get_path_similarity

# opens a file and return a tuple of word data
def open_file(file_name):
    try:
        file = open(file_name.strip(), "r")
        words_data = []
        for line in file:
            line=line.split()
            words_data.append(list(line))
        
        return words_data
    except(FileNotFoundError, IOError):
        print("Wrong file or file path")

        
word_data = open_file('original-pairs.txt')


# calculates the hyper

hyper_word_data = []
hyper_word_data2 = []
for words in word_data:
    hypernym_word1 = wn.synsets(words[0])
    hypernym_word2 = wn.synsets(words[1])
    if hypernym_word1: # if hyper has senset 
        hypernym_word1 = hypernym_word1[0].hypernyms() 
        if hypernym_word1: 
            hypernym_word1 = hypernym_word1[0].lemma_names()
            hyper_word_data .append(hypernym_word1[0])

    if hypernym_word2:
        hypernym_word2 = hypernym_word2[0].hypernyms()
        if hypernym_word2:
            hypernym_word2 = hypernym_word2[0].lemma_names()
            hyper_word_data2.append(hypernym_word2[0])
                   
    if not hypernym_word1: # if sens
        hyper_word_data.append("None")
    if not hypernym_word1:
        hyper_word_data2.append("None")


f = open("original-pairs-hypernyms.txt", "w")

print('{} {} {} {} {} {}\n'.format("word1","word2","similarity","hyp1"," hyp2","Similarity"))
f.write('{} {} {} {} {} {}\n'.format("word1","word2","similarity","hyp1"," hyp2","Similarity"))
for x,y,z in zip(word_data,hyper_word_data,hyper_word_data2):
    print('{} {} {} {} {} {}\n'.format(x[0],x[1],x[2],y,z,get_path_similarity(y,z)))
    f.write('{} {} {} {} {} {}\n'.format(x[0],x[1],x[2],y,z,get_path_similarity(y,z)))# uses the similarity function from task 1

f.close()
