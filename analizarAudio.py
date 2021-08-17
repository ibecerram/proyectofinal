# Usual Libraries
import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
#%matplotlib inline se reemplaza poniendo plt.show()
import sklearn
import statistics
# Librosa (the mother of audio files)
import librosa
import librosa.display
import IPython.display as ipd
import warnings
warnings.filterwarnings('ignore')
import os
import ntpath
import csv

x = '';
with open('pathMusic.txt') as f:
    first_line = f.readline().strip()
    x = first_line

print(x)

general_path = x
lists = []

# Trim leading and trailing silence from an audio signal (silence before and after the actual audio)
#y, sr = librosa.load(f'{general_path}/genres_original/blues/blues.00002.wav')
filename = ntpath.basename(x)

lists.append(filename)
lists.append(66149)
print('FILENAME: ', filename)

y, sr = librosa.load(f'{general_path}')
audio_file, _ = librosa.effects.trim(y)
# LENGTH
print('Audio File:', audio_file, '\n')
print('Audio File shape:', np.shape(audio_file))
print(" ");


# CHROMOGRAM
chromagram = librosa.feature.chroma_stft(audio_file)
print("Chromagram: MEAN", np.mean(chromagram, dtype=np.float64));
print("Chromagram: VAR", np.var(chromagram, dtype=np.float64));
print("Chromagram MEAN", chromagram.mean());
print("Chromagram: VAR", chromagram.var());
lists.append(chromagram.mean())
lists.append(chromagram.var())
#print('Chromogram shape:', chromagram.shape)
print(" ");


# RMS
RMS = librosa.feature.rms(audio_file)
print("RMS MEAN : ", RMS.mean());
print("RSM VAR : ", RMS.var());
lists.append(RMS.mean())
lists.append(RMS.var())
print(" ");


# Calculate the Spectral Centroids
spectral_centroids = librosa.feature.spectral_centroid(audio_file, sr=sr)[0]
#print('Centroids:', spectral_centroids, '\n')
print("Spectral CENTROID MEAN: ", np.mean(spectral_centroids));
print("Spectral CENTROID VAR: ", np.var(spectral_centroids));
lists.append(np.mean(spectral_centroids))
lists.append(np.var(spectral_centroids))
print(" ");


# Spectral Centroids BANDWITH
spectral_bandwidth = librosa.feature.spectral_bandwidth(audio_file, sr=sr)[0]
print("Spectral BANDWITH: MEAN", np.mean(spectral_bandwidth));
print("Spectral BANDWITH: VAR", np.var(spectral_bandwidth));
lists.append(np.mean(spectral_bandwidth))
lists.append(np.var(spectral_bandwidth))
print(" ");


# Spectral RollOff Vector
spectral_rolloff = librosa.feature.spectral_rolloff(audio_file, sr=sr)[0]
print("RollOff MEAN: ", np.mean(spectral_rolloff));
print("RollOff VAR: ", np.var(spectral_rolloff));
lists.append(np.mean(spectral_rolloff))
lists.append(np.var(spectral_rolloff))
print(" ");


# Total zero_crossings in our 1 song
zero_crossings = librosa.feature.zero_crossing_rate(audio_file)
#print("Zero Crossing Rate  MEAN : ", np.mean(zero_crossings, dtype=np.float64));
#print("Zero Crossing Rate  VAR : ", np.var(zero_crossings, dtype=np.float64));
print("Zero Crossing Rate  MEAN : ", zero_crossings.mean());
print("Zero Crossing Rate  VAR : ", zero_crossings.var());
lists.append(zero_crossings.mean())
lists.append(zero_crossings.var())
print(" ");


# HARMONY
y_harm, y_perc = librosa.effects.hpss(audio_file)
print("Harmony MEAN = ", np.mean(y_harm));
print("Harmony MEAN = ", np.var(y_harm));
lists.append(np.mean(y_harm))
lists.append(np.var(y_harm))
print(" ");

#PERCEPTRUAL 
print("Perceptrual MEAN = ", np.mean(y_perc));
print("Perceptrual MEAN = ", np.var(y_perc));
lists.append(np.mean(y_perc))
lists.append(np.var(y_perc))
print(" ");


#TEMPO
tempo, _ = librosa.beat.beat_track(y, sr = sr)
print("TEMPO = ", tempo)
lists.append(tempo)
print(" ");


# MFCCS (20)
mfccs = librosa.feature.mfcc(audio_file, sr=sr)
#print(mfccs);
x = 0;

listaFrecuencias = []

for melfrequency in mfccs:
    print("MFCC ", x);
    print("MEAN = ", np.mean(melfrequency));
    lists.append(np.mean(melfrequency))
    print("VAR = ", np.var(melfrequency));
    lists.append(np.var(melfrequency))
    print("--------------------------------");
    x = x +1;
print("null");

#print(listaFrecuencias)
lists.append("null")
print(lists)
print('Longitud lista: ', len(lists))

with open("datos.csv", "a", newline='', encoding='utf-8') as fp:
    wr = csv.writer(fp, dialect='excel')
    wr.writerow(lists)

xPrueba = 'Se creo'





