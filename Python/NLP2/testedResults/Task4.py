#!/usr/bin/env python
# coding: utf-8



import os
# opens a file and return a tuple of word data
def task4_open(file_name):
    try:
        file = open(file_name.strip(), "r",encoding="utf8")
        words_data = []
        for line in file:
            line=line.split()
            words_data.append((line))
            
            
        del words_data[0]
        return words_data
    except(FileNotFoundError, IOError):
        print("Wrong file or file path")


def write_data(top_pairs):
    with open('top.txt', 'w') as f:
        f.write("%s\n" % '{:20}| {:20}| {:10}'.format("Word1","Word2","Similarity"))
        f.write("--------------------------------------------------- \n")
        for pairs in top_pairs:
            print(pairs)
            f.write("%s\n" % '{:20}| {:20}| {:10}'.format(pairs[0],pairs[1],str(pairs[2])))
    print("Top words written in top.txt")
    f.close()

def write_top_words(sorted_pairs):
    top_pairs = sorted_pairs[:10] #first ten elements of the list 
    if os.path.exists("top.txt"):
        os.remove("top.txt")
        write_data(top_pairs)
    else:
        write_data(top_pairs)

    



def main():
    #original-pairs.txt
    #read a file list pairs in a list       
    similarity_data = task4_open('original-pairs.txt')

    #sorts list pair with higest to lowest probabilit
    sorted_list =sorted(similarity_data ,reverse=True,key=lambda  x: float(x[2]))

    #writing the top.txt file with top ten words
    write_top_words(sorted_list) 



if __name__ == "__main__":
    main()








