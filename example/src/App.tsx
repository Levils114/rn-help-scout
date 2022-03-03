import * as React from 'react';

import { StyleSheet, View, } from 'react-native';
import Beacon from 'rn-help-scout';

export default function App() {
  React.useEffect(() => {
    Beacon.init('beaconID');
    Beacon.identify({ name: 'Levi Siebra', email: 'john.cena@gmail.com' });
    Beacon.open();
  }, []);

  return (
    <View style={styles.container}/>
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
