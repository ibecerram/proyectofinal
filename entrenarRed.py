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

### PARA GUARDAR UN MODELO ENTRENADO
from sklearn import svm
from sklearn import datasets
import pickle
import joblib


############## READ DATA
general_path = 'datos.csv'
data = pd.read_csv(f'{general_path}')
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
nn = MLPClassifier(solver='lbfgs', alpha=1e-5, hidden_layer_sizes=(50, 10), random_state=1)
model_assess(nn, "Neural Nets")

nn.fit(X_train, y_train)

joblib.dump(nn, 'neuralNetwork.joblib')
xPrueba = 'Si se creó'
