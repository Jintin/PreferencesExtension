# PreferencesExtension

[![CircleCI](https://circleci.com/gh/Jintin/PreferencesExtension.svg?style=shield)](https://circleci.com/gh/Jintin/PreferencesExtension)
[![](https://jitpack.io/v/Jintin/PreferencesExtension.svg)](https://jitpack.io/#Jintin/PreferencesExtension)

Extension for Android `SharePreferences`, provide easier usage and LiveData style of change listener.

## Install

Add [Jitpack](https://jitpack.io/) repository to your root `build.grable`:
```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Then add dependency in your module `build.gradle`:
```groovy
dependencies {
  // Base module and LiveData usage
  implementation 'com.github.Jintin.PreferencesExtension:preferences:1.3.0'
  // RxJava3 usage
  implementation 'com.github.Jintin.PreferencesExtension:rxjava:1.3.0'
  // Flow usage
  implementation 'com.github.Jintin.PreferencesExtension:flow:1.3.0'

}
```

## Usage

1. Provide reified type usage:
```kotlin
val value = preference.get<String>(MY_KEY)
// or
val value: String = preference.get(MY_KEY)
```

2. `LiveData` style to get preference modify event:
```kotlin
val liveData = preference.liveData<String>(MY_KEY)

liveData.observe(this) {
    // get update here
}
```

3. `RxJava3` style to get preference modify event:
```kotlin
val observable = preference.observable<String>(MY_KEY)

observable.subscribe {
    // get update here
}
```

4. `Flow` style to get preference modify event:
```kotlin
val flow = preference.flow<String>(MY_KEY)

flow.collect {
    // get update here
}
```

You can go to `./app` module to see the full example and get more information.

## Contributing
Bug reports and pull requests are welcome on GitHub at [https://github.com/Jintin/PreferencesExtension](https://github.com/Jintin/PreferencesExtension).

## License
The package is available as open source under the terms of the [MIT License](http://opensource.org/licenses/MIT).

[![Buy Me A Coffee](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/jintin)
