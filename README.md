[brew]: <https://docs.brew.sh/Installation>

# sentinel-cordova-plugin
## _Set up_
To add this plugins into your cordova project, for now you have to clone this repository
```sh
git clone https://github.com/Quality-XP-Development-SESSA/sentinel-cordova-plugin.git
```

then, go to your cordova project and add it, Change 'directory' to the path where you cloned this repository
```
cordova plugin add 'directory'
```
Example:
```
cordova plugin add ~/Desktop/sentinel-cordova-plugin
```

## _Ktlint_
To use the ktlint script into the plugin you have to install [Brew][brew], then execute this command
```sh
brew install ktlint
```

## _Credentials_
Once the plugin is added, go to
```
plugins > cordova.plugin.sentinel.sdk > src > android > build.gradle
```
And change the username and password for a valid credentials

## _Koin_
To use this plugin you have to initialize Koin in your Cordova project, so, first of all let's implement koin and SentinelSDK.
```sh
// Koin
implementation 'io.insert-koin:koin-android:<KOIN_VERSION>'

// Sentinel SDK
implementation 'com.qxdev.sentinel-sdk:android-sdk:<VERSION>'
```
you can find the build.gradle on 
```
platforms > android > app > src > main 
```
Then, you have to create a Kotlin file where is MainActivity.java
```sh
// <YOUR_PACKAGE>

import android.content.Context
import com.qxdev.sentinel_sdk.di.Koin
import com.qxdev.sentinel_sdk.onboarding.ConnectionToDevice
import com.qxdev.sentinel_sdk.onboarding.interfaces.WifiManager
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class ConfigKoin{
    companion object {
        fun initialize(context: Context){
            startKoin {
                modules(
                    module {
                        single<WifiManager> { ConnectionToDevice(context) }
                    },
                    Koin.sentinelSDKModule("https://api-stage.sensys-iot.com"),
                )
            }
        }
    }
}
```
Finally into the MainActivity import the kotlin file and call the initialize function
```
import <YOUR_PACKAGE>.ConfigKoin;

public class MainActivity extends CordovaActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConfigKoin.Companion.initialize(this.getApplicationContext());

        // ...
    }
}
```

## _Support_
Cordova build the project with some deprecated values or properties, so we need to fix it manually, so please go to
```
platforms > android > app > build.gradle
```
and delete the line 
```
apply plugin: 'kotlin-android-extensions'
```
Finally, go to the AndroidManifest.xml wheres is located in
```
platforms > android > app > src > main
```
and add the next android property into application tag
```sh
android:usesCleartextTraffic="true"
```
