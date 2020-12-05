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
  implementation 'com.github.Jintin:PreferencesExtension:1.0.0'
}
```

## Usage

1. Provide reified type usage:
```kotlin
// Existing way
val value1 = preference.getString(MY_KEY, "")

// New feature
val value2 : String = preference.get(MY_KEY)
val value3  = preference.get<String>(MY_KEY)
```

2. `LiveData` style of on change listener usage:
```kotlin
// Existing way
override fun onResume() {
    super.onResume()
    preference.registerOnSharedPreferenceChangeListener(this)
}

override fun onPause() {
    super.onPause()
    preference.unregisterOnSharedPreferenceChangeListener(this)
}

override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
    if (key == MY_KEY) {
        // get update here
    }
}

// New feature
val preferenceLiveData = preference.liveData<String>(MY_KEY)

preferenceLiveData.observe(this) {
    // get update here
}
```

You can go to `./app` module to see the full example and get more information.

## Contributing
Bug reports and pull requests are welcome on GitHub at [https://github.com/Jintin/PreferencesExtension](https://github.com/Jintin/PreferencesExtension).

## License
The package is available as open source under the terms of the [MIT License](http://opensource.org/licenses/MIT).

[![Buy Me A Coffee](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/jintin)
