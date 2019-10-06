#!/bin/usr/python3
from keras.models import load_model
from keras.preprocessing.image import ImageDataGenerator
from keras.preprocessing.image import img_to_array, load_img
from keras import models
from PIL import Image
import numpy as np
import cv2
import pickle
import os, shutil
base_dir='C:\\Users\\Vlastellin\\NeuralNetwork'
cascadePath = "C:\\Anaconda3\\Lib\\site-packages\\cv2\\data\\haarcascade_frontalface_default.xml"


def get_images_name(path1):
    temp = path1.split('\\')
    return temp[-1]

def face_recognition(base_dir):
    faceCascade = cv2.CascadeClassifier(cascadePath)
    recognizer = "cv2.createLBPHFaceRecognizer(1,8,8,8,123)"
    path=os.path.join(base_dir, 'data')
    image_paths = [os.path.join(path, f) for f in os.listdir(path) ]
    path_for_cut_img = os.path.join(base_dir, 'cut_data\data')
    shutil.rmtree(path_for_cut_img)
    os.mkdir(path_for_cut_img)
    for image_path in image_paths:
        gray = Image.open(image_path).convert('L')
        image = np.array(gray, 'uint8')
        faces = faceCascade.detectMultiScale(image, scaleFactor=1.1, minNeighbors=5, minSize=(60, 60))
        for (x, y, w, h) in faces:
            cv2.imshow("", image[y: y + h, x: x + w])
            cv2.waitKey(50)            
            cv2.imwrite(os.path.join(path_for_cut_img,get_images_name(image_path)) , image[y: y + h, x: x + w])
                
def prediction(base_dir):
    IMAGE_FOLDER = os.path.join(base_dir,"cut_data")
    MODEL_FILENAME =os.path.join(base_dir,'face_with_glasses.h5')
    image_paths = [os.path.join(IMAGE_FOLDER, f) for f in os.listdir(IMAGE_FOLDER) if not f.endswith('.happy')]
    model = models.load_model(MODEL_FILENAME)
    test_datagen = ImageDataGenerator(rescale=1./255)
    data_generator = test_datagen.flow_from_directory(IMAGE_FOLDER,target_size=(180,180),batch_size=1, shuffle=False)
    predictions=model.predict_generator(data_generator,len(data_generator.filenames));
    for i,filename in enumerate(data_generator.filenames):
        if predictions[i]>0.5:
            print(str(get_images_name(filename))+" "+str(predictions[i][0]))
    
face_recognition(base_dir)
cv2.destroyAllWindows()
prediction(base_dir)