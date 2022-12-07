ID, Passport and Visa Scanner (MRZ)
=====




This library is an easy to use Android machine-readable zone scanner. This library uses machine learning in order to recognize data from the document. It can find the name, surname, country code, date of birth, document type, and document ID.
It is blazing fast, stable, effortless, efficient and will deal with complex scanning algorithms for you!
## Table of contents

- [Features](#features)
- [Screenshots](#screenshots)
- [Setup](#setup)
- [Implementation](#implementation)
- [Reporting Issues](#reporting-issues)
- [License](#license-1)
- [Contact Us](#contact-us)
- [About Us](#about-us)



# Features
This library is capable to scan three types of documents corresponding to the ISO/IEC 7810 sizes:
- "Type 3" is typical of passport booklets. The MRZ consists of 2 lines × 44 characters.
- "Type 2" is relatively rare with 2 lines × 36 characters.
- "Type 1" is of a credit card-size with 3 lines × 30 characters.


## Screenshots
<p float="left">
  <img src="https://user-images.githubusercontent.com/61969245/204537805-d2d649dd-9332-42ae-acd5-572815ae6e36.png" alt="drawing" width="200"/>
<img src="https://user-images.githubusercontent.com/61969245/204539129-56789398-612c-4280-b9c2-5d62886d54b0.png" alt="drawing" width="200"/>
<img src="https://user-images.githubusercontent.com/61969245/204539160-8e239d7a-9638-4e40-9fae-6780c8245ae4.png" alt="drawing" width="200"/>
</p>




# Setup
#### Current Version

```kotlin
def mrz_version = '0.0.24'

```

#### Gradle - Maven Repository
Check that you have the MRZ Scanner artifactory repository in the list of your repositories.


```gradle
allprojects {
    repositories {
        google()
        jcenter()
        // Add MRZ Scanner Repository
        maven{
            url "https://troido.jfrog.io/artifactory/troido-gradle-release"
        }
}
```

#### Dependencies
In your app module, declare corresponding dependencies
```gradle
   implementation "com.troido.scanner:scanner:$mrz_version"
```

#### License
For release version a license is needed. To apply the license add the following tag to your app manifest (contact us at info@troido.de in order to get the licence).
```xml
      <application
                …>

        <meta-data
            android:name="troido_license"
            android:value="YOUR_LICENSE_HERE" />
        
    </application>
  ```


#### Initialization
The final step is MRZ Scanner initialization in your Application class

```kotlin
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        …
        
        TroidoMrzScanner.initialize(applicationContext)
    }

}
  ```

# Implementation

```kotlin
 val scanContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                    val mrzData = it.data?.getSerializableExtra(MrzScanActivity.INTENT_MRZ_DATA_KEY) as MrzData?
                }
            }
```
``` kotlin
override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      ...
      scanContent.launch(MrzScanActivity.getIntent(this))
}
```
You see! That's it. Six lines of code just saved you hours of research and implementation. Please find more examples like this in our sample project application.

#### Custom implementation
If you want more freedom, you can also use our special scanning view in your application.\
First you add our view to layout file of your liking.

```xml
    <com.troido.mrzscanner.camera.ItemScanner
        android:id="@+id/scannerContainer"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```
And then you simply start the scanner. As simple as that.
```kotlin
     MrzScanProcessor(object : MrzScanListener{
            override fun mrzReady(data: MrzData) {
               //do stuff with retrieved data
            }
        }).startCamera(binding.scannerContainer, lifecycleScope, lifecycleOwner)
```
## Reporting Issues
Found a bug on a specific feature? Please share by opening an issue on  [Github](https://github.com/troido/ID-Passport-Visa-Reader/issues).
## License
MRZ Scanner is a licensed software. You can use it for free without any restrictions in debug mode. For production apps you need to contact us and get a License Key. We also provide special offers for Open-Source software.
## Contact Us
For licensing and other questions contact us:\
***Germany***\
***troido GmbH***\
***Volmerswerther Strasse 80-86***\
***40221 Dusseldorf***\
***Tel: +49 (0) 211 9421 666 0***\
***Fax: +49 (0) 211 9421 666 2***\
***Email:info@troido.de***

## About Us
Troido is a company that focuses on Android and IoT for more than 10 years. MRZ Scanner is developed by AI and Android experts in Troido.
