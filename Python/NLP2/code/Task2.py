import os
from nltk.corpus import wordnet as wn
from nltk.tokenize import RegexpTokenizer
from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer 
import string
# PATH SIMILARITY FUNCTION IS CALLED FROM TASK 1
from Task1 import get_path_similarity
import re
# opens a file and return a tuple of word data
def task2_open(file_name):
    try:
        file = open(file_name.strip(), "r",encoding="utf8")
        cleaned_list = []
        words_data = []
        for sentences in file:
            for word in sentences.split(' '):
                word  =word.replace('--', " ")
                word = word.replace('-', "")
                word = word.replace('_', " ")
                words_data.append(word.strip())
                
        for word in words_data:
            for wr in word.split(' '):
                wr = wr.lower()
                cleaned_list.append(wr.strip())
        cleaned_list = ' '.join(cleaned_list).split() 
        return cleaned_list
    
    except(FileNotFoundError, IOError):
        print("Wrong file or file path")



#converting all letters to lower or upper case
file_data = task2_open('text1.txt')

def remove_punctuation(word_data):
    filtered_punc = []
    regex = re.compile('[^a-zA-Z]')
    for word in word_data:
        word = regex.sub('',word)
        filtered_punc.append(word.strip())
    return filtered_punc


def remove_word_dup(word_data):
    result=[]
    marker = set()
    for word in word_data:
        ll = word.lower()
        if ll not in marker:   # test presence
            marker.add(ll)
            result.append(word)   # preserve order
    return result



def remove_stopWords(word_data):
    stop_words = stopwords.words('english')
    filtered_word = []
    for word in word_data:
        if(word.lower() not in stop_words):
            filtered_word.append(word)
    return filtered_word
    
def lemmatize_word(word_data):
    lematized_words = []
    lemmatizer = WordNetLemmatizer() 
    for word in word_data:
         lematized_words.append(lemmatizer.lemmatize(word))
    return lematized_words

         
def word_pairs(word_data):
    single_word = word_data
    word_pair = [[word_1, word_2] for word_1 in single_word for word_2 in word_data if word_1 != word_2]
    tuple_data = []
    for word_set in word_pair:
        tuple_data.append(tuple(word_set))     
    return tuple_data   


def write_file(word_data):
    print("original-pairs.txt created, Starting to write similarities")
    print("it may take few mins")
    with open('original-pairs.txt', 'w') as f:
        for word1,word2 in word_data:
            path_sim = get_path_similarity(word1,word2) # Path similarity is called from Task 1
            line = ('{:10} {:10} {:15}'.format(word1,word2,str(path_sim)))
            print(line)
            f.write("%s\n" % line)
         
             
                    


def write_pairs_inFile(word_data):
    if os.path.exists("original-pairs.txt"):
        os.remove("original-pairs.txt")
        write_file(word_data)         
    else:
        write_file(word_data)






def main():
    print("Reading file ......")
    data_punctuation = remove_punctuation(file_data)
    print("Removing Pucnctions")
    processed_data  = remove_stopWords(data_punctuation )
    print("Removing Stop words")
    remove_word_dup(processed_data)
    print("Removing Duplicates")
    processed_data = lemmatize_word(remove_word_dup(processed_data))
    processed_data = [i for i in processed_data if i] 
    print("Lematizating Data")
    word_pair = word_pairs(processed_data)
    print("Creating pairs.......")
    write_pairs_inFile(word_pair)
    print("File wrtiing complelted")

if __name__ == "__main__":
    main()








