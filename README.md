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

## _Support_
Just in case, that the project doesn't works, into the cordova project go to
```
platforms > android > app > build.gradle
```
and delete the line 
```
apply plugin: 'kotlin-android-extensions'
```