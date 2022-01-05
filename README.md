# rn-help-scout (Android only)

A package that will help people to use HelpScout in React Native projects

## Installation

```sh
npm install rn-help-scout
```
or
```sh
yarn add rn-help-scout
```

## Changes in android
### Step 1
  In your project folder, go to ```android/gradle.properties```, and add:
  ```gradle
    ...
    
    # gradle.properties
    org.gradle.daemon=true
    org.gradle.jvmargs=-Xmx2560m
  ```

### Step 2
  After, go to ```android/app/src/main/AndroidManifest.xml```, and add in ```<application>``` tag:
  ```xml
    ...
    android:theme="@style/AppTheme"
    tools:replace="android:supportsRtl"
    android:supportsRtl="false">
  ```

## Usage

```js
import React from "react";
import { View, TouchableOpacity, Text } from "react-native";

import Beacon from "rn-help-scout";

export function Faq(){
  function handleOpenBeacon(){
    Beacon.init("BeaconID");
    Beacon.open();
  }

  return(
    <View>
      <TouchableOpacity onPress={handleOpenBeacon}>
        <Text>Open Beacon</Text>
      </TouchableOpacity>
    </View>
  );
}
```

## Atributtes

```init(beaconID)```: Initialize Beacon with your beacon id;  
```identify({ name: "John Doe", email: "johndoe@gmail.com" })```:Identify user to Help Scout chat (optional);  
```open()```: Open beacon in your app;  
```contactForm()```: Open beacon in Help Scout chat form;  

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
