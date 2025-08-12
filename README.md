<p align="center">
  <a href="https://play.google.com/store/apps/dev?id=7086930298279250852" target="_blank">
    <img alt="" src="https://github-production-user-asset-6210df.s3.amazonaws.com/125717930/246971879-8ce757c3-90dc-438d-807f-3f3d29ddc064.png" width=500/>
  </a>  
</p>

# Face-Group-Identification-Android
<p align="left">Identify a person from group of people with real-time face recognition | Face liveness detection | Android-optimized</p>

## ✨ Key Features  
- `Real-time processing` under 200ms latency

- `Liveness detection` prevents photo/video spoofing

- `Lightweight` APK under 35MB  

- Compliant with ISO/IEC 30107-1 biometric standards  

- All data processed locally (no cloud transmission)


### Our facial recognition algorithm is globally top-ranked by NIST in the FRVT 1:1 leaderboards. <span><img src="https://github.com/kby-ai/.github/assets/125717930/bcf351c5-8b7a-496e-a8f9-c236eb8ad59e" alt="badge" width="36" height="20"></span>  
[Latest NIST FRVT evaluation report 2024-12-20](https://pages.nist.gov/frvt/html/frvt11.html)  

![FRVT Sheet](https://github.com/user-attachments/assets/16b4cee2-3a91-453f-94e0-9e81262393d7)

#### 🆔 ID Document Liveness Detection - Linux - [Here](https://web.kby-ai.com)  <span><img src="https://github.com/kby-ai/.github/assets/125717930/bcf351c5-8b7a-496e-a8f9-c236eb8ad59e" alt="badge" width="36" height="20"></span>
#### 🤗 Hugging Face - [Here](https://huggingface.co/kby-ai)
#### 📚 Product & Resources - [Here](https://github.com/kby-ai/Product)
#### 🛟 Help Center - [Here](https://docs.kby-ai.com)
#### 💼 KYC Verification Demo - [Here](https://github.com/kby-ai/KYC-Verification-Demo-Android)
#### 🙋‍♀️ Docker Hub - [Here](https://hub.docker.com/u/kbyai)

## Overview

This repository demonstrates `face group identification` that identifies a person from group of people with real-time face recognition.</br>
> In this repository, we integrated `KBY-AI`'s both face liveness detection and face recognition technology into `Android` native platform.</br>
### ◾FaceSDK(Mobile) Details

  | Basic      | 🔽 Standard | Premium |
  |------------------|------------------|------------------|
  | Face Detection        | <b>Face Detection</b>    | Face Detection |
  | Face Liveness Detection        | <b>Face Liveness Detection</b>    | Face Liveness Detection |
  | Pose Estimation        | <b>Pose Estimation</b>    | Pose Estimation |
  |         | <b>Face Recognition</b>    | Face Recognition |
  |         |         | 68 points Face Landmark Detection |
  |         |         | Face Quality Calculation |
  |         |         | Face Occlusion Detection |
  |         |         | Eye Closure Detection |
  |         |         | Age, Gender Estimation |

### ◾FaceSDK(Mobile) Product List
  | No.      | Repository | SDK Details |
  |------------------|------------------|------------------|
  | 1        | [Face Liveness Detection - Android](https://github.com/kby-ai/FaceLivenessDetection-Android)    | Basic SDK |
  | 2        | [Face Liveness Detection - iOS](https://github.com/kby-ai/FaceLivenessDetection-iOS)    | Basic SDK |
  | 3        | [Face Recognition + Face Liveness Detection - Android](https://github.com/kby-ai/FaceRecognition-Android)    | Standard SDK |
  | 4        | [Face Recognition + Face Liveness Detection - iOS](https://github.com/kby-ai/FaceRecognition-iOS)    | Standard SDK |
  | 5        | [Face Recognition + Face Liveness Detection - Flutter](https://github.com/kby-ai/FaceRecognition-Flutter)        | Standard SDK |
  | 6        | [Face Recognition + Face Liveness Detection - Ionic-Cordova](https://github.com/kby-ai/FaceRecognition-Ionic-Cordova)        | Standard SDK |
  | 7        | [Face Recognition + Face Liveness Detection - React-Native](https://github.com/kby-ai/FaceRecognition-React-Native)        | Standard SDK |
  | 8        | [Face Attribute - Android](https://github.com/kby-ai/FaceAttribute-Android)        | Premium SDK |
  | 9        | [Face Attribute - iOS](https://github.com/kby-ai/FaceAttribute-iOS)        | Premium SDK |
  | 10        | [Face Attribute - Flutter](https://github.com/kby-ai/FaceAttribute-Flutter)        | Premium SDK |
  | ➡️        | <b>[Face Group Identification - Android](https://github.com/kby-ai/Face-Group-Identification-Android)</b>    |  |


 > To get `Face SDK(server)`, please visit products [here](https://github.com/kby-ai/Product).<br/>

## SDK License

- The code below shows how to use the license: https://github.com/kby-ai/Face-Group-Identification-Android/blob/5f87fedfe3a095c15bad87880bff559f44e6b4f6/app/src/main/java/com/kbyai/facerecognition/MainActivity.kt#L33-L43

- To request a license, please contact us:</br>
🧙`Email:` contact@kby-ai.com</br>
🧙`Telegram:` [@kbyai](https://t.me/kbyai)</br>
🧙`WhatsApp:` [+19092802609](https://wa.me/+19092802609)</br>
🧙`Discord:` [KBY-AI](https://discord.gg/CgHtWQ3k9T)</br>
🧙`Teams:` [KBY-AI](https://teams.live.com/l/invite/FBAYGB1-IlXkuQM3AY)</br>

## About SDK

### 1. Set up
1. Copy the SDK (`libfacesdk` folder) to the `root` folder in your project.

2. Add SDK to the project in `settings.gradle`.
```bash
include ':libfacesdk'
```

3. Add dependency to your `build.gradle`.
```bash
implementation project(path: ':libfacesdk')
```

### 2. Initializing an SDK

- Step One

To begin, you need to activate the SDK using the license that you have received.
```kotlin
FaceSDK.setActivation("...")
```

If activation is successful, the return value will be `SDK_SUCCESS`. Otherwise, an error value will be returned.

- Step Two

After activation, call the `SDK`'s initialization function.
```kotlin
FaceSDK.init(getAssets());
```
If initialization is successful, the return value will be `SDK_SUCCESS`. Otherwise, an error value will be returned.

### 3. SDK Classes
  - FaceDetectionParam
  
    This class serves as the input parameter for face detection, allowing the inclusion of face liveness detection and specifying the desired liveness detection level.

    | Feature| Type | Name |
    |------------------|------------------|------------------|
    | Check liveness        | boolean    | check_liveness |
    | Check liveness level        | int    | check_liveness_level |

    When check_liveness_level is set to `0`, the liveness detection achieves high accuracy.
    When check_liveness_level is set to `1`, the liveness detection operates with light weight.

### 4. APIs
#### - Face Detection and Liveness Detection

The `FaceSDK` offers a single function for detecting faces, allowing the inclusion of face liveness detection and specifying the desired liveness detection level.
```kotlin
FaceSDK.faceDetection(bitmap, param)
```

This function requires two parameters: a `Bitmap` object and a `FaceDetectionParam` object that enables various processing functionalities.

The return value of the function is a list of `FaceBox` objects. Each `FaceBox` object contains the detected face rectangle, liveness score, and facial angles such as `yaw`, `roll`, and `pitch`.


#### - Create Templates

The `FaceSDK` provides a function that can generate a `template` from a `Bitmap` image. This `template` can then be used to verify the identity of the individual image captured.

```kotlin
byte[] templates = FaceSDK.templateExtraction(bitmap, faceBox);
```

The `SDK`'s `template` extraction function takes two parameters: a `Bitmap` object and an object of `FaceBox`. 

The function returns a byte array, which contains the `template` that can be used for person verification.

#### - Calculation similarity

The `similarityCalculation` function takes a byte array of two `template`s as a parameter. 

```kotlin
float similarity = FaceSDK.similarityCalculation(templates1, templates1);
```

It returns the similarity value between the two `template`s, which can be used to determine the level of likeness between the two individuals.

#### - Yuv to Bitmap
The `SDK` provides a function called `yuv2Bitmap`, which converts a `yuv` frame to a `Bitmap`. Since camera frames are typically in `yuv` format, this function is necessary to convert them to `Bitmap`. The usage of this function is as follows:
```kotlin
Bitmap bitmap = FaceSDK.yuv2Bitmap(nv21, image.getWidth(), image.getHeight(), 7);
```
The first parameter is an `nv21` byte array containing the `yuv` data. 

The second parameter is the width of the `yuv` frame, and the third parameter is its height. 

The fourth parameter is the `conversion mode`, which is determined by the camera orientation.

To determine the appropriate `conversion mode`, the following method can be used:
```kotlin
 1        2       3      4         5            6           7          8

 888888  888888      88  88      8888888888  88                  88  8888888888
 88          88      88  88      88  88      88  88          88  88      88  88
 8888      8888    8888  8888    88          8888888888  8888888888          88
 88          88      88  88
 88          88  888888  888888
```

