# cloudinary

Cloud-based image and video management solution. It enables users to upload, store, manage, manipulate and deliver images and video for websites and apps.


Requirement: 

Create account https://cloudinary.com 

Add Gradle dependency required to build the system.

implementation "com.cloudinary:cloudinary-android:1.25.0"

Config with init

To set the configuration parameters programmatically while initializing the MediaManager, pass a HashMap with parameters as the second argument of the init method. For example:

HashMap config = new HashMap();
config.put("cloud_name", "xxxxxx");
config.put("api_key", "xxx");
config.put("api_secret", "xxx");
MediaManager.init(this,config);

Android image upload:

Cloudinary provides support for uploading media directly from your mobile application to your Cloudinary account, without going through your servers first. This method allows for faster uploading and a better user experience. It also reduces load on your servers and reduces the complexity of your applications.


Signed upload: 

To implement signed uploads from an Android device to your Cloudinary account you must provide a class that implements the SignatureProvider interface. You class must implement a synchronous HTTPS call to your backend signature generation endpoint, which must return an object with a timestamp, your API key, and the signatureitself.


Unsigned upload:
Unsigned upload is an option for performing upload without the need to generate a signature on your backend. Unsigned upload options are controlled by an upload preset: to use this feature, you first need to enable unsigned uploading for your Cloudinary account from the Upload Settings page.


Upload options:
Use the MediaManager's option method to add an upload parameter to the upload request. The method accepts 2 parameters: the first specifies the name of the upload parameter and the second its value. For example, to upload an image called samplepic.jpg and set thepublic_id option to sample1:

String requestId = MediaManager.get().upload("samplepic.jpg")
   .unsigned("sample_preset")
   .option("public_id", "sample1")
   .dispatch();
Current example works on Unsigned upload options. For More info please visit the 

https://cloudinary.com/documentation/android_integration


1.Added java doc Scope 
2.Added class UML diagram(Requirement install plugin in SimpleUML in adroid studio.)
3. Project include below dependency: 
1. RxJava : 

* implementation 'io.reactivex.rxjava2:rxjava:2.2.2'
* implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'

2. Retrofit
* implementation 'com.squareup.retrofit2:retrofit:2.4.0'
* implementation 'com.squareup.retrofit2:adapter-rxjava:2.4.0'
* implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
* implementation 'com.squareup.retrofit2:converter-scalars:2.4.0'
* implementation 'com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0'
* implementation 'com.squareup.okhttp3:logging-interceptor:3.3.0'
3. Dagger
* def daggerVersion = '2.16'
* implementation "com.google.dagger:dagger:${daggerVersion}"
* implementation "com.google.dagger:dagger-android:${daggerVersion}"
* implementation "com.google.dagger:dagger-android-support:${daggerVersion}"
* annotationProcessor "com.google.dagger:dagger-compiler:${daggerVersion}"
4. cloudinary
* implementation "com.cloudinary:cloudinary-android:1.25.0"

5. butterknife
* implementation 'com.jakewharton:butterknife:8.8.1'
* annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'






