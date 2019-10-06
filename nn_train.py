from keras import layers 
from keras import models
import os
model = models.Sequential() 
model.add(layers.Conv2D(32, (3, 3), activation='relu', input_shape=(180,180, 3)))
model.add(layers.MaxPooling2D((2, 2))) 
model.add(layers.Conv2D(32, (3, 3), activation='relu')) 
model.add(layers.MaxPooling2D((2, 2))) 
model.add(layers.Conv2D(32, (3, 3), activation='relu'))
model.add(layers.MaxPooling2D((2, 2)))
model.add(layers.Conv2D(64, (3, 3), activation='relu'))
model.add(layers.MaxPooling2D((2, 2))) 
model.add(layers.Conv2D(64, (3, 3), activation='relu'))
model.add(layers.MaxPooling2D((2, 2)))
model.add(layers.Conv2D(128, (3, 3), activation='relu')) 
model.add(layers.Flatten()) 
model.add(layers.Dense(512, activation='relu')) 
model.add(layers.Dense(1, activation='sigmoid'))
from keras import optimizers
model.compile(loss='binary_crossentropy', optimizer=optimizers.RMSprop(lr=1e-4),metrics=['acc'])
from keras.preprocessing.image import ImageDataGenerator
base_dir = '/content/drive/My Drive/FaceClassification'
train_dir = os.path.join(base_dir, 'train') 
validation_dir = os.path.join(base_dir, 'validation') 
test_dir = os.path.join(base_dir, 'test') 
train_datagen = ImageDataGenerator(rescale=1./255) 
test_datagen = ImageDataGenerator(rescale=1./255)  
train_generator = train_datagen.flow_from_directory(train_dir, target_size=(180,180),batch_size=100, class_mode='binary') 
validation_generator = test_datagen.flow_from_directory(test_dir, target_size=(180,180), batch_size=100,class_mode='binary')
history = model.fit_generator(train_generator, steps_per_epoch=100, epochs=7, validation_data=validation_generator,validation_steps=20)
model.save(os.path.join(base_dir, 'face_with_glsses1.h5'))