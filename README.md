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

## _Credentials_
Once the plugin is added, go to
```
plugins > cordova.plugin.sentinel.sdk > src > android > build.gradle
```
And change the username and password for a valid credentials

## _Support_
Just in case, that the project doesn works, into the cordova project go to
```
platforms > android > app > build.gradle
```
and delete the line 
```
apply plugin: 'kotlin-android-extensions'
```