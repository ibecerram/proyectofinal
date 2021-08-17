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

from sklearn import preprocessing
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import confusion_matrix, accuracy_score, roc_auc_score, roc_curve
from sklearn import preprocessing
from sklearn.model_selection import train_test_split
from sklearn.feature_selection import RFE

import ntpath

### PARA GUARDAR UN MODELO ENTRENADO
from sklearn import svm
from sklearn import datasets
import pickle
import joblib

############## READ DATA
general_path = 'datos.csv'
data = pd.read_csv(general_path)
data = data.iloc[0:, 1:] 
data.head()
#print(data)



############## FEATURES AND TARGET VARIABLE
y = data['label'] # genre variable.
X = data.loc[:, data.columns != 'label'] #select all columns but not the labels

#### NORMALIZE X ####

# Normalize so everything is on the same scale. 

cols = X.columns
min_max_scaler = preprocessing.MinMaxScaler()
np_scaled = min_max_scaler.fit_transform(X)

# new data frame with the new scaled data. 
X = pd.DataFrame(np_scaled, columns = cols)




############## SPLITTING THE DATA IN TRAINING AND TESTING
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)




############## CREATE PREDEFINED FUNCTION TO ASSES THE ACCURACY
def model_assess(model, title = "Default"):
    model.fit(X_train, y_train)
    preds = model.predict(X_test)
    #print(confusion_matrix(y_test, preds))
    print('Accuracy', title, ':', round(accuracy_score(y_test, preds), 5), '\n')



############## TRY NEURAL NETS TO ASSES THE PERFORMANCE 
##nn = MLPClassifier(solver='lbfgs', alpha=1e-5, hidden_layer_sizes=(50, 10), random_state=1)
##model_assess(nn, "Neural Nets")

##nn.fit(X_train, y_train)
##preds = nn.predict(X_test);

#print('Accuracy', ':', round(accuracy_score(y_test, preds), 5), '\n')

#joblib.dump(nn, 'prueba.joblib')
eje = joblib.load('neuralNetwork.joblib')
preds = eje.predict(X_test)
#print('Accuracy FROM JOBLIB', ':', round(accuracy_score(y_test, preds), 5), '\n')
#joblib.dump(nn, 'filename.joblib')
#s = pickle.dumps(nn)
#clf2 = pickle.loads(s)
#b = clf2.predict(X_test)




# Confusion Matrix
#confusion_matr = confusion_matrix(y_test, preds) #normalize = 'true'
#plt.figure(figsize = (16, 9))
#sns.heatmap(confusion_matr, cmap="Blues", annot=True,
 #           xticklabels = ["blues", "classical", "country", "disco", "hiphop", "jazz", "metal", "pop", "reggae", "rock"],
  #         yticklabels=["blues", "classical", "country", "disco", "hiphop", "jazz", "metal", "pop", "reggae", "rock"]);
#plt.savefig("conf matrix")
#plt.show()




############## RECOMMENDER SYSTEMS
# Libraries
import IPython.display as ipd
from sklearn.metrics.pairwise import cosine_similarity
from sklearn import preprocessing

# Read data
data = pd.read_csv(general_path, index_col='filename')

# Extract labels
labels = data[['label']]

# Drop labels from original dataframe
data = data.drop(columns=['length','label'])
data.head()

# Scale the data
data_scaled=preprocessing.scale(data)
print('Scaled data type:', type(data_scaled))


############## COSINE SIMILARITY

#Cosine similarity
similarity = cosine_similarity(data_scaled)
print("Similarity shape:", similarity.shape)

# Convert into a dataframe and then set the row index and column names as labels
sim_df_labels = pd.DataFrame(similarity)
sim_df_names = sim_df_labels.set_index(labels.index)
sim_df_names.columns = labels.index

sim_df_names.head()
print(sim_df_names.head())
print("################")
print(sim_df_names.columns)

print("################")



############## SONG SIMILARITY SCORING
def find_similar_songs(name):
    print("xXXXXXXXXXXXXXXXXXXX")
    # Find songs most similar to another song
    series = sim_df_names[name].sort_values(ascending = False)
    listaX = series[series.index[0]].values.tolist()
    print("Lista: ", listaX)
    # Remove cosine similarity == 1 (songs will always have the best match with themselves)
    series = series.drop(name)
    print(series.index[0])
    
    listaX = series[series.index[0]].values.tolist()
    print("Lista: ", listaX)
    #print(series.columns())
    # Display the 5 top matches 
    print("\n*******\nSimilar songs to ", name)
    print(series.head(5))


x = '';
with open('pathMusic.txt') as f:
    first_line = f.readline().strip()
    x = first_line

filename = ntpath.basename(x)


name = filename
# Find songs most similar to another son
series = sim_df_names[name].sort_values(ascending = False)
listaX = series[series.index[1]].tolist()
#print("Lista: ", listaX)
# Remove cosine similarity == 1 (songs will always have the best match with themselves)
series = series.drop(name)
#print("\n*******\nSimilar songs to ", name)
#print(series.head(5))
nombreCoincidencia = series.index[0]
porcentaje = listaX
#print("Lista: ", listaX)
#print(series.columns())
# Display the 5 top matches
#print(series.head(5))
seriesX = sim_df_names[name].sort_values(ascending = False)
seriesX = seriesX.drop(name)
# pop.00019 - Britney Spears "Hit me baby one more time"
#find_similar_songs('pop.00019.wav') 
#ipd.Audio(f'{general_path}/genres_original/pop/pop.00019.wav')
# PIANO WAV PRUEBA
#find_similar_songs('stay.wav') 
#ipd.Audio(f'{general_path}/genres_original/pop/pop.00019.wav')
retorno = name + " | " + nombreCoincidencia + " | " + str(porcentaje).strip()
print("Estoy en retorno", retorno)
