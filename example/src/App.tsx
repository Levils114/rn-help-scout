import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import Beacon from 'rn-help-scout';

export default function App() {
  React.useEffect(() => {
    Beacon.init('1e6f7c78-9104-4d11-9a67-53adee9cd1c3');
    Beacon.identify({ name: 'Levi Siebra', email: 'levils114@gmail.com' });
    Beacon.open();
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: 12</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
